# Author：Xiangyu Li
# Email: lxy_jdsy@163.com
# Time：2019/7/1 16:15
# Name: control.py
# Description: 主控制器

import sys
import base64

from PyQt5 import QtCore
from PyQt5.QtCore import QObject, Qt
from PyQt5.QtWidgets import QApplication, QLabel, QMessageBox

from view.login import Login
from view.tool_bar import ToolBar
from controller.handle import Handle


class Control(QObject):
    res = QtCore.pyqtSignal(str, str)
    img_res = QtCore.pyqtSignal(list)

    def __init__(self):
        super().__init__()
        self.app = QApplication(sys.argv)
        self.login = Login()
        self.handle = Handle()
        self.tool = ToolBar()
        self.uid = None
        self.signal()

    def signal(self):
        self.login.login.connect(self.user_login)
        self.login.register.connect(self.user_register)
        self.login.end.connect(self.shot)
        self.tool.to_discern.connect(self.discern)
        self.res.connect(self.login.tip)
        self.img_res.connect(self.tool.show_text)

    def user_login(self, uid, pwd):
        self.handle.set_params(uid=uid, pwd=pwd, rtype="Login")
        self.uid = uid
        res, rtype = self.handle.request_user()
        self.res.emit(res, rtype)

    def user_register(self, uid, pwd):
        self.handle.set_params(uid=uid, pwd=pwd, rtype="Register")
        res, rtype = self.handle.request_user()
        self.res.emit(res, rtype)

    def discern(self):
        with open('static/shot.png', 'rb') as img:
            data = img.read()
        img_base64 = base64.b64encode(data)
        self.handle.set_params(img_64=img_base64, uid=self.uid)
        result = self.handle.request_image()
        if isinstance(result, list):
            self.img_res.emit(result)
        else:
            QMessageBox.information(self, "提示", "识别失败，请重新尝试！")

    def start(self):
        self.login.show()
        # self.tool.show()

    def shot(self):
        self.login.close()
        self.login.end.disconnect(self.shot)
        self.tool.show()

    def exec(self):
        self.app.exec()
