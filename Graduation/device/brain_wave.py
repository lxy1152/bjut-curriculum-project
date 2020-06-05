import csv
import datetime
import os
import subprocess
import threading
import time

import schedule

import database
from device import base_device
from config import device_config as config


class BrainWave(base_device.Device):
    def __init__(self):
        super(BrainWave, self).__init__()
        self.last_data_out = None
        self.count = 0
        self.res = {
            "data": {
                "Attention": 0.0,
                "Meditation": 0.0,
                "BlinkStrength": 0
            },
            "run": False,
            "timestamp": 0.0
        }

    def __del__(self):
        self.stop_device()

    def reset(self, reset_run: bool = False) -> None:
        self.res["data"]["Attention"] = 0.0
        self.res["data"]["Meditation"] = 0.0
        self.res["data"]["BlinkStrength"] = 0
        if reset_run:
            self.res["run"] = False

    def check_alive(self):
        if self.res["data"]["Attention"] == 0.0 and self.res["data"]["Meditation"] == 0.0:
            self.count = self.count + 1

    def run_task(self):
        while not self.stop:
            schedule.run_pending()

    def detect(self, db: database.Database) -> None:
        schedule.every(1).seconds.do(self.check_alive)
        threading.Thread(target=self.run_task).start()
        threading.Thread(target=self.__detect, args=(db,)).start()

    def __detect(self, db: database.Database):
        bw = subprocess.Popen(self.path + config.BRAIN_WAVE["PATH"], bufsize=0, stdout=subprocess.PIPE,
                              universal_newlines=True)
        while not self.stop:
            if bw.poll():
                break
            if self.count == 10:
                self.count = 0
                if not bw.poll():
                    bw.kill()
                bw = subprocess.Popen(self.path + config.BRAIN_WAVE["PATH"], bufsize=0, stdout=subprocess.PIPE,
                                      universal_newlines=True)
            filepath = config.BRAIN_WAVE['OUTPUT']['DATA_PATH'] + "\\" + datetime.datetime.now().strftime(
                "%Y-%m-%d") + "\\"
            if not os.path.exists(filepath):
                os.makedirs(filepath)
            curr_time = datetime.datetime.now().strftime("%H-%M")
            filename = filepath + curr_time + ".csv"
            if not os.path.exists(filename):
                with open(filename, 'w', newline='') as f:
                    csv_writer = csv.writer(f)
                    csv_writer.writerow(['timestamp', 'wave_data'])
            if not curr_time == self.last_data_out or self.last_data_out is None:
                db.execute('''
                                    INSERT INTO data_wave(path, modify_time)
                                    VALUES('{}', {})
                                '''.format(filename, time.time()))
                self.last_data_out = curr_time
            line = bw.stdout.readline().strip()
            if line == "No devices found!":
                self.reset(reset_run=True)
                break
            self.res["timestamp"] = time.time()
            if line == "SIGNAL: we have good contact with the subject":
                self.res["run"] = True
                continue
            if line[0:9] == "Attention":
                self.res["run"] = True
                self.res["data"]["Attention"] = line[12:]
                with open(filename, 'a', newline='') as f:
                    csv_writer = csv.writer(f)
                    csv_writer.writerow([time.time(), "Attention:" + line[12:]])
                continue
            if line[0:10] == "Meditation":
                self.res["run"] = True
                self.res["data"]["Meditation"] = line[12:]
                with open(filename, 'a', newline='') as f:
                    csv_writer = csv.writer(f)
                    csv_writer.writerow([time.time(), "Meditation:" + line[12:]])
                continue
            if line[0:13] == "BlinkStrength":
                self.res["run"] = True
                self.res["data"]["BlinkStrength"] = line[15:]
                with open(filename, 'a', newline='') as f:
                    csv_writer = csv.writer(f)
                    csv_writer.writerow([time.time(), "BlinkStrength:" + line[12:]])
                continue
            self.reset()
        if not bw.poll():
            bw.kill()
