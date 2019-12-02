"""
@Name: receive.py
@Author: lxy
@Date: 2019/5/10 12:35
@Description: 接收消息线程
@Contact: lxy_jdsy@163.com
"""
from threading import Thread

from PyQt5 import QtCore
from PyQt5.QtCore import QObject


# 多继承QObject， 以发送信号
class Receive(Thread, QObject):
    received = QtCore.pyqtSignal(dict)
    update_users = QtCore.pyqtSignal(dict)
    close_connect = QtCore.pyqtSignal()

    def __init__(self, client):
        Thread.__init__(self)
        QObject.__init__(self)
        # 当前用户
        self.client = client
        # 设置为守护线程，即主线程关闭，本线程也关闭
        self.setDaemon(True)

    # 重写run方法
    def run(self) -> None:
        while True:
            try:
                # 接收数据
                data = self.client.recv()
                if not data:
                    self.close_connect.emit()
                    break
                # 将获得的数据转换为字典
                d = eval(data.decode())
                # 处理不同类型，message为普通消息， users为用户列表消息，并发送信号
                if d["type"] == "message":
                    self.received.emit(d)
                elif d["type"] == "users":
                    self.update_users.emit(d)
            except:
                continue
