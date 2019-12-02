"""
@Name: client.py
@Author: lxy
@Date: 2019/5/10 10:20
@Description: 用户模型
@Contact: lxy_jdsy@163.com
"""
import socket

from PyQt5 import QtCore
from PyQt5.QtCore import QObject


# 继承QObject， 从而可以发送信号
class Client(QObject):
    login_error = QtCore.pyqtSignal(str)
    success = QtCore.pyqtSignal(str)

    def __init__(self):
        super().__init__()
        # 新建用户
        self.client = socket.socket()
        # 连接服务器
        self.client.connect(("39.96.48.165", 2333))
        # 用户名与密码
        self.username = None
        self.password = None

    # 设置用户信息
    def set_user_info(self, username, password):
        self.username = username
        self.password = password

    # 连接
    def connect(self):
        print("正在连接服务器...")
        # 发送连接信息字典
        self.client.send(
            bytes(str({"username": self.username, "password": self.password, "type": "login"}), encoding="utf-8"))
        res = self.client.recv(1024).decode()
        # 处理结果并发送信号
        if res != "SUCCESS":
            self.login_error.emit(res)
        else:
            self.success.emit("LOGIN SUCCESS")

    # 用户注册
    def register(self):
        # 发送注册信息
        self.client.send(
            bytes(str({"username": self.username, "password": self.password, "type": "register"}), encoding="utf-8"))
        res = self.client.recv(1024).decode()
        # 处理结果并发送信号
        if res != "SUCCESS":
            self.login_error.emit(res)
        else:
            self.success.emit("REGISTER SUCCESS")

    # 关闭连接
    def close(self):
        self.client.close()

    # 发送数据
    def send(self, data):
        self.client.send(bytes(data, encoding="utf-8"))

    # 接收数据，默认缓存区大小1024
    def recv(self, buff_size=1024):
        return self.client.recv(buff_size)
