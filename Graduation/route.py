import csv
import datetime
import os
import time

import cv2
import flask

import database
from config import db_config as config
from config import device_config as dconfig
from device import camera, brain_wave


class Route(object):
    """ Route manager.

    The class route aims to register and manage the url rule together.

    Attributes:
        camera: The camera object. Use this you can open or stop the camera,
                and get the information of the expression and eye.
        brain_wave: The brain wave object. Use this object to get the information of the wave.
        database: Use this object to operate the database.
    """

    def __init__(self, app: flask.Flask) -> None:
        """Init method.

        Add url rules.

        Args:
            app: The flask app.
        """

        self.database = database.Database()
        self.camera = camera.Camera(self.database)
        self.brain_wave = brain_wave.BrainWave()

        app.add_url_rule('/get_source/<filename>', view_func=self.get_source, methods=['GET'])
        app.add_url_rule('/', view_func=self.index, methods=['GET'])
        app.add_url_rule('/login', view_func=self.login, methods=['GET', 'POST'])
        app.add_url_rule('/sign-up', view_func=self.sign_up, methods=['GET', 'POST'])
        app.add_url_rule('/logout', view_func=self.logout, methods=['GET'])
        app.add_url_rule('/management/setting', view_func=self.management_setting, methods=['GET'])
        app.add_url_rule('/management/index', view_func=self.management_index, methods=['GET'])
        app.add_url_rule('/management/monitor', view_func=self.management_monitor, methods=['GET'])
        app.add_url_rule('/management/write_list', view_func=self.management_write_list, methods=['GET'])
        app.add_url_rule('/management/videotape', view_func=self.management_videotape, methods=['GET'])
        app.add_url_rule('/videotape_list', view_func=self.videotape_list, methods=['GET'])
        app.add_url_rule('/video_feed', view_func=self.video_feed, methods=['GET'])
        app.add_url_rule('/detect/camera', view_func=self.detect_camera, methods=['GET'])
        app.add_url_rule('/detect/wave', view_func=self.detect_wave, methods=['GET'])
        app.add_url_rule('/write_list', view_func=self.write_list, methods=['GET'])
        app.add_url_rule('/save_write', view_func=self.save_write, methods=['POST'])

    def get_source(self, filename):
        """Get the file.

        Unrecognized '\' in filename when requested by browser.
        So use '+' instead '\'.
        For example: video+2020-02-23+14-51.mp4.

        Args:
            filename: Filename of the file you want to get
        """

        parts = filename.split("+")
        if parts[0] == "data":
            res = {}
            with open(".\\output\\{}".format(filename.replace("+", "\\")), 'r') as f:
                reader = csv.reader(f)
                if parts[1] == "camera_eye":
                    res = {
                        "expression": [],
                        "p_cor": []
                    }
                    for row in reader:
                        if row[0] == "timestamp":
                            continue
                        res["expression"].append(eval(row[1]))
                        res["p_cor"].append(eval(row[3]))
                elif parts[1] == "wave":
                    res = {
                        "wave": []
                    }
                    for row in reader:
                        if row[0] == "timestamp":
                            continue
                        p = row[1].split(":")
                        if p[0] != "BlinkStrength":
                            res["wave"].append({p[0]: p[1]})
            print(res)
            return res
        elif parts[0] == "video":
            return flask.send_file(".\\output\\{}".format(filename.replace("+", "\\")))
        else:
            flask.abort(404)

    def index(self) -> flask.redirect:
        """Redirect to login page.

        Returns:
            redirect: Redirect to login page.
        """

        self.close_device()
        return flask.redirect(flask.url_for('login'))

    def open_device(self, device) -> None:
        """Open device and start detect.

        Args:
            device: The device you want to open.
        """

        if device.stop:
            device.open_device()
            device.detect(self.database)

    def close_device(self) -> None:
        """Close all device.

        Call device.stop_device() to stop the device.
        !!! This function needs to be called except for the management/monitor.html page. !!!
        """

        if not self.camera.stop:
            self.camera.stop_device()
        if not self.brain_wave.stop:
            self.brain_wave.stop_device()

    def login(self) -> dict or flask.render_template:
        """Login page.

        There are two ways to request:
        GET: Get necessary information from session and return the login.html page.
        POST: Get information from the form and judge whether it can be logged in through the database.

        Returns:
            res: The login result.
            template: Login.html.
        """

        self.close_device()
        if flask.request.method == 'POST':
            res = {'result': '', 'des': ''}
            try:
                name = flask.request.form["name"]
                password = flask.request.form["password"]
                length = len(self.database.execute('''
                        SELECT * FROM user 
                        WHERE password='{}' AND number='{}'
                    '''.format(password, name)).fetchall())
                if length <= 0:
                    res['result'] = 'fail'
                    res['des'] = "No such user."
                elif length == 1:
                    res['result'] = 'success'
                    res['des'] = "Success."
                    self.database.execute('''
                        UPDATE user
                        SET modify_time = {}
                        WHERE number = '{}'
                    '''.format(time.time(), name))
                    flask.session["username"] = name
                    flask.session["login-time"] = time.time()
                else:
                    res['result'] = 'fail'
                    res['des'] = "Too many."
            except Exception as e:
                print(e)
                res['result'] = 'fail'
                res['des'] = 'Unknown error.'
            finally:
                return res
        else:
            name = flask.session.get("username")
            login_time = flask.session.get("login-time")
            reentry = False
            if flask.session.get("reentry") is not None:
                reentry = True
                flask.session.pop("reentry", None)
            if login_time is not None:
                if time.time() - float(login_time) <= 1800:
                    if name is not None:
                        return flask.redirect(flask.url_for("management_index"))
                else:
                    flask.session.pop("username", None)
                    flask.session.pop("login-time", None)
                    return flask.render_template("login.html", reentry=reentry)
            return flask.render_template("login.html", reentry=reentry)

    def sign_up(self) -> dict or flask.render_template:
        """Sign up page.

        There are two ways to request:
        GET: Return the sign-up.html page.
        POST: Get information from the form and judge whether it can be signed up in through the database.
        To sign up, user must provide:
            number: ID.
            email: Email address.
            name: Real name.
            password: Password.

        Returns:
            res: The sign up result.
            template: Sign-up.html
        """

        self.close_device()
        if flask.request.method == 'POST':
            res = {'result': '', 'des': ''}
            try:
                number = flask.request.form["number"]
                if number == config.SPECIAL_USER['ADMIN']:
                    position = "admin"
                elif number[config.SPECIAL_USER['TEACHER']['START']:config.SPECIAL_USER['TEACHER']['END']] == \
                        config.SPECIAL_USER['TEACHER']['FORMAT']:
                    position = "teacher"
                else:
                    position = "student"
                email = flask.request.form["email"]
                name = flask.request.form["name"]
                password = flask.request.form["password"]
                if len(self.database.execute('''
                    SELECT * FROM user
                    WHERE number={}
                '''.format(number)).fetchall()) <= 0:
                    t = time.time()
                    self.database.execute('''
                        INSERT INTO user(number, email, name, password, position, create_time, modify_time)
                        VALUES('{}', '{}', '{}', '{}', '{}', {}, {})
                    '''.format(number, email, name, password, position, t, t))
                    res['result'] = 'success'
                    res['des'] = 'Insert successfully.'
                else:
                    res['result'] = 'fail'
                    res['des'] = 'Already have.'
            except Exception as e:
                print(e)
                res["result"] = "fail"
                res['des'] = 'Unknown error.'
            finally:
                return res
        else:
            return flask.render_template("sign-up.html")

    def logout(self):
        """Logout.

        Returns:
            redirect: Redirect to login.html.
        """

        self.close_device()
        name = flask.session.get("username")
        reentry = flask.session.get("reentry")
        if reentry is not None:
            flask.session.pop("reentry", None)
        if name is not None:
            self.database.execute('''
                UPDATE user
                SET modify_time = {}
                WHERE number = '{}'
                '''.format(name, time.time()))
            flask.session.pop("username", None)
            flask.session.pop("login-time", None)
        return flask.redirect(flask.url_for("login"))

    def management_validator(self, page):
        """The validator.

        Before display the management/index.html, management/monitor.html, management/statistics.html and
        management/videotape.html(for student, only management/index.html and management/statistics.html),
        should to verify whether the user is logged in.

        Args:
            page: The name of the template.
        Returns:
            template: Return template management/xx.html.
            redirect: Redirect to login page.
        """

        self.close_device()
        name = flask.session.get("username")
        login_time = flask.session.get("login-time")
        if login_time is not None:
            if time.time() - float(login_time) <= 1800:
                if name is not None:
                    results = self.database.execute('''
                                                   SELECT name, position, email FROM user
                                                   WHERE number = '{}'
                                               '''.format(name)).fetchall()
                    if len(results) == 1:
                        count = 0
                        for dire in os.listdir(dconfig.CAMERA["OUTPUT"]["VIDEO_PATH"]):
                            for f in dire:
                                count += len(f)
                        info = {
                            "name": results[0][0],
                            "position": results[0][1],
                            "email": results[0][2],
                            "days": (datetime.datetime.now() - datetime.datetime(2020, 3, 10)).days,
                            "store": int(count / 3),
                            "member": dconfig.OTHER["MEMBER"],
                            "device": dconfig.OTHER["DEVICE"]
                        }
                        return flask.render_template("management/{}.html".format(page), flag=page, info=info)
                    else:
                        return flask.redirect(flask.url_for("login"))
            else:
                flask.session["reentry"] = "true"
        else:
            flask.session.pop("username", None)
            flask.session.pop("login-time", None)
            flask.session.pop("reentry", None)
        return flask.redirect(flask.url_for("login"))

    def management_setting(self) -> flask.redirect or flask.render_template:
        """The index page of management.

        Returns:
            redirect: Redirect to login page.
            template: Return management/index.html.
        """

        return self.management_validator("setting")

    def management_index(self) -> flask.redirect or flask.render_template:
        """The index page of management.

        Returns:
            redirect: Redirect to login page.
            template: Return management/index.html.
        """

        return self.management_validator("index")

    def management_monitor(self) -> flask.redirect or flask.render_template:
        """The monitor page of management.

        Returns:
            redirect: Redirect to login page.
            template: Return management/monitor.html.
        """

        return self.management_validator("monitor")

    def management_write_list(self) -> flask.redirect or flask.render_template:
        """The statistics page of management.

        Returns:
            redirect: Redirect to login page.
            template: Return management/statistics.html.
        """

        return self.management_validator("write_list")

    def management_videotape(self) -> flask.redirect or flask.render_template:
        """The videotape page of management.

        Returns:
            redirect: Redirect to login page.
            template: Return management/videotape.html.
        """

        return self.management_validator("videotape")

    def gen(self, db: database.Database) -> str:
        """Generate video stream.

        Returns:
            video stream: Video stream composed of many pictures.
        """

        while not self.camera.stop:
            frame = self.camera.get_frame(db)
            if frame is not None:
                # The type of content must be image/jpeg
                yield (b'--frame\r\n'
                       b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n\r\n')

    def video_feed(self) -> flask.Response:
        """ Video feed.

        Returns:
            response: Video stream.
        """

        self.open_device(self.camera)
        self.open_device(self.brain_wave)
        return flask.Response(self.gen(self.database),
                              mimetype='multipart/x-mixed-replace; boundary=frame')

    def detect_camera(self) -> dict:
        """ The detection result.

        Use this function to get the result of the detection which includes:
            expression:
                emotion:
            eye:
                eyegaze:

        Returns:
            res: The dict of result.
        """

        while self.camera.stop:
            pass
        return self.camera.res

    def detect_wave(self) -> dict:
        """The detection result.

        Use this function to get the result of the detection.

        Returns:
            res: The dict of result.
        """

        while self.brain_wave.stop:
            pass
        return self.brain_wave.res

    def videotape_list(self) -> dict:
        video_path = dconfig.CAMERA['OUTPUT']['VIDEO_PATH']
        dire = os.listdir(video_path)
        res = {}
        for d in dire:
            video_list = os.listdir(video_path + d)
            video_result = []
            for video in video_list:
                if video.endswith(".mp4"):
                    name = os.path.splitext(video)[0]
                    video_result.append(name)
                    if not os.path.exists(video_path + d + "\\" + name + ".jpg"):
                        cap = cv2.VideoCapture(video_path + d + "\\" + video)
                        ret, frame = cap.read()
                        cv2.imwrite(video_path + d + "\\" + name + ".jpg", frame)
            res[d] = video_result
        return res

    def write_list(self) -> dict:
        results = self.database.execute("SELECT title, content, create_time FROM write").fetchall()
        res = {}
        i = 0
        for result in results:
            temp = {
                "title": result[0],
                "content": result[1],
                "time": result[2]
            }
            res[i] = temp
            i = i + 1
        return res

    def save_write(self):
        content = flask.request.form["content"]
        t = str(datetime.datetime.now().year) + "-" + str(datetime.datetime.now().month) + "-" + str(
            datetime.datetime.now().day)
        self.database.execute('''
            INSERT INTO write(title, content, create_time)
            VALUES('{}', '{}', '{}')
            '''.format(t + "的计划", content, t))
        return {}
