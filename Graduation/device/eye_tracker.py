import re
import subprocess
import time

from config import device_config as config
from device import base_device


class EyeTracker(base_device.Device):
    """ Eye tracker object.

    This class is deprecated.
    Because it does not exclude the possibility of further use in the future, this class will be reserved.
    For how to generate eye tracker's data, see detect function in camera object.

    Attributes:
        res: The detection result.
    """

    def __init__(self):
        """ Init method. """

        super(EyeTracker, self).__init__()
        self.res = {
            "x": 0.0,
            "y": 0.0,
            "timestamp": 0.0
        }

    def __del__(self):
        """ Del method. """

        pass

    def detect(self):
        """ Detect eye's information using tobii.

        Using tobii to detect eye's position which will return x and y coordinate.
        """

        eye_tracker = subprocess.Popen(self.path + config.EYE_TRACKER["PATH"], bufsize=0, stdout=subprocess.PIPE,
                                       universal_newlines=True)
        while not self.stop:
            if eye_tracker.poll():
                break
            self.res["timestamp"] = time.time()
            line = eye_tracker.stdout.readline().strip()
            res = re.compile("-?[1-9][0-9]+.[0-9]+").findall(line)
            self.res["x"] = res[0]
            self.res["y"] = res[1]
        if not eye_tracker.poll():
            eye_tracker.kill()


if __name__ == '__main__':
    tracker = EyeTracker()
    tracker.detect()
