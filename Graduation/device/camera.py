import base64
import datetime
import os
import subprocess
import threading
import time
import csv
import schedule

import cv2

import database
from device import base_device
from device.facepp.facepp import API, APIError
from config import device_config as config


class Camera(base_device.Device):
    """ Camera object.

    Use this object to open or stop the camera, and get the information of the expression and eye.

    Attributes:
        camera: The camera(default is the default camera).
        last_video_out: Last video output time.
        last_data_out: Last data output time.
        out: Video output stream.
        data: Data output stream.
        img: Each frames of camera.
        api: Use facepp apis.
        res: The detection result.
    """

    def __init__(self, db: database.Database) -> None:
        """ Init method. """
        super(Camera, self).__init__()
        self.lock = threading.Lock()
        self.db = db
        self.timer = None
        self.camera = None
        self.last_video_out = None
        self.last_data_out = None
        self.last_video_path = None
        self.thumbnail = False
        self.out = None
        self.data = None
        self.img = None
        self.api = API()
        self.res = {
            "expression": {},
            "eye": {},
            "timestamp": 0.0
        }

    def __del__(self) -> None:
        """ Del method.

        Release the camera.
        """

        self.stop_device()

    def run_task(self):
        while not self.stop:
            schedule.run_pending()

    def open_device(self) -> None:
        """ Open the camera.

        The number of the camera is in the config file.
        Default is 0 which means the default camera.
        Set stop to False.
        """

        if self.stop:
            self.stop = False
            schedule.every(0.5).seconds.do(self.detect, self.db)
        if self.camera is None:
            self.camera = cv2.VideoCapture(config.CAMERA['NUMBER'], cv2.CAP_DSHOW)
        threading.Thread(target=self.run_task).start()

    def stop_device(self) -> None:
        """ Close the camera.

        Release the camera and output stream.
        Set stop to True.
        """

        self.lock.acquire(True)
        schedule.cancel_job(self.detect)
        if self.camera is not None:
            self.camera.release()
            self.camera = None
        if self.out is not None:
            self.out.release()
            self.out = None
        if self.timer is not None:
            self.timer.cancel()
            self.timer = None
        self.stop = True
        if self.last_video_path is not None and os.path.exists(self.last_video_path + ".avi"):
            subprocess.Popen('{} -i {} {}'.format(
                ".\\device\\native\\ffmpeg.exe", self.last_video_path + ".avi", self.last_video_path + ".mp4"))
        self.last_video_path = None
        self.lock.release()

    def get_frame(self, db: database.Database) -> bytes or None:
        """ Get each frame.

        Because JPG image format is used in transmission, format is needed after each frame is obtained.

        Returns:
            bytes buffer: The bytes buffer of the image.
        """

        filepath = config.CAMERA['OUTPUT']['VIDEO_PATH'] + datetime.datetime.now().strftime("%Y-%m-%d") + "\\"
        if not os.path.exists(filepath):
            os.makedirs(filepath)
        curr_time = datetime.datetime.now().strftime("%H-%M")
        filename = filepath + curr_time + ".avi"
        if not curr_time == self.last_video_out or self.out is None:
            self.thumbnail = False
            if self.out is not None:
                self.out.release()
                subprocess.Popen('{} -i {} {}'.format(
                    ".\\device\\native\\ffmpeg.exe", self.last_video_path + ".avi", self.last_video_path + ".mp4"))
            self.last_video_path = filepath + curr_time
            self.last_video_out = curr_time
            self.out = cv2.VideoWriter(
                filename,
                cv2.VideoWriter_fourcc(*'XVID'),
                config.CAMERA['OUTPUT']['FPS'],
                (config.CAMERA['OUTPUT']['WIDTH'], config.CAMERA['OUTPUT']['HEIGHT'])
            )
            db.execute('''
                        INSERT INTO video(path, modify_time)
                        VALUES('{}', {})
                    '''.format(filename, time.time()))
        self.lock.acquire(True)
        if not self.stop and self.out is not None:
            success, image = self.camera.read()
            if not self.thumbnail:
                cv2.imwrite(filepath + curr_time + ".jpg", image)
                self.thumbnail = True
            ret, jpeg = cv2.imencode('.jpg', image)
            self.img = jpeg
            self.out.write(image)
            self.lock.release()
            return jpeg.tobytes()
        else:
            self.lock.release()
            return None

    def detect(self, db: database.Database) -> None:
        """ Detect the expression and eyegaze and save the data.

        Use facepp api to detect the following attributes:
            face_num: Number of faces detected.
            glass: Whether to wear glasses
                value: Optional values are: None, Dark and Normal
            face_rectangle: Position of face rectangle, each value is an integer.
                top: The ordinate of the pixel point in the upper left corner of the rectangle.
                left: The ordinate of pixel points in the upper left corner of rectangular box.
                width: Width of rectangle.
                height: Height of rectangle.
            headpose: Face pose analysis results. The return value contains the following attributes.
                The value of each attribute is a [- 180, 180] floating-point number with six significant
                digits after the decimal point. The unit is angle.
                pitch_angle: Angle of head up.
                roll_angle: Angle of rotation (plane rotation).
                yaw_angle: The angle of shaking head.
            emotion: Emotion recognition results. The value of each field is a floating-point number with
                a range of [0, 100] and three significant digits after the decimal point. The larger the
                return value of each field, the higher the confidence of the state represented by that field.
                The sum of the field values is equal to 100.

                The seven emotions are:
                    anger, disgust, fear, happiness, neutral, sadness and surprise
            eyegaze: Information of eyeball position and line of sight direction.
                left_eye_gaze and right_eye_gaze: The information of left eye and right eye.
                    position_x_coordinate: The x-axis coordinate of the central position of the eyeball.
                    position_y_coordinate: The y-axis coordinate of the central position of the eyeball.
                    vector_x_component: X-axis component of eye line direction vector.
                    vector_y_component: Y-axis component of eye line direction vector.
                    vector_z_component: Z-axis component of eye line direction vector.
        Request every 500 ms due to concurrency limit.
        """

        filepath = config.CAMERA['OUTPUT']['DATA_PATH'] + "\\" + datetime.datetime.now().strftime("%Y-%m-%d") + "\\"
        if not os.path.exists(filepath):
            os.makedirs(filepath)
        curr_time = datetime.datetime.now().strftime("%H-%M")
        filename = filepath + curr_time + ".csv"
        if not os.path.exists(filename):
            with open(filename, 'w', newline='') as f:
                csv_writer = csv.writer(f)
                csv_writer.writerow(['timestamp', 'expression', 'eyegaze', 'cal_eye'])
        # if not curr_time == self.last_data_out or self.last_data_out is None:
        #     db.execute('''
        #                 INSERT INTO data_camera_eye(path, modify_time)
        #                 VALUES('{}', {})
        #             '''.format(filename, time.time()))
        #     self.last_data_out = curr_time
        if self.img is not None:
            try:
                res = self.api.detect(image_base64=str(base64.b64encode(self.img), encoding='utf-8'),
                                      return_attributes="emotion,eyegaze")
                try:
                    if len(res["faces"][0]) > 0:
                        attr = res["faces"][0]["attributes"]
                        self.res["expression"]["emotion"] = attr["emotion"]
                        p_cor = self.cal_eye(
                            (res["faces"][0]["face_rectangle"]["width"],
                             res["faces"][0]["face_rectangle"]["height"]),
                            (attr["eyegaze"]["left_eye_gaze"]["position_x_coordinate"],
                             attr["eyegaze"]["left_eye_gaze"]["position_y_coordinate"]),
                            (attr["eyegaze"]["right_eye_gaze"]["position_x_coordinate"],
                             attr["eyegaze"]["right_eye_gaze"]["position_y_coordinate"]),
                            (attr["eyegaze"]["left_eye_gaze"]["vector_x_component"],
                             attr["eyegaze"]["left_eye_gaze"]["vector_y_component"]),
                            (attr["eyegaze"]["right_eye_gaze"]["vector_x_component"],
                             attr["eyegaze"]["right_eye_gaze"]["vector_y_component"]))
                        self.res["eye"] = {
                            "p_cor_x": p_cor[0],
                            "p_cor_y": p_cor[1]
                        }
                        self.res["timestamp"] = time.time()
                        with open(filename, 'a', newline='') as f:
                            csv_writer = csv.writer(f)
                            csv_writer.writerow(
                                [self.res["timestamp"], self.res["expression"]["emotion"],
                                 attr["eyegaze"], self.res["eye"]])
                except (KeyError, IndexError):
                    print("detect err")
                    self.res = {
                        "expression": {},
                        "eye": {},
                        "timestamp": time.time()
                    }
            except APIError as e:
                print("apierr")

    def cal_eye(self, width_height: tuple, c1: tuple, c2: tuple, d1: tuple, d2: tuple):
        o_cor = ((c1[0] + c2[0]) / 2, (c1[1] + c2[1]) / 2)
        r = max(width_height[0], width_height[1])
        d_cor = ((d1[0] + d2[0]) / 2, (d1[1] + d2[1]) / 2)
        p_cor = [o_cor[0] + r * d_cor[0], o_cor[1] + r * d_cor[1]]
        return p_cor
