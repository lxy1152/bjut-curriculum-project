import os

import database


class Device(object):
    def __init__(self):
        self.stop = True
        self.path = os.getcwd()

    def __del__(self):
        pass

    def detect(self, db: database.Database):
        pass

    def open_device(self):
        self.stop = False

    def stop_device(self):
        self.stop = True
