"""
@Name: controller.py
@Author: lxy
@Date: 2019/5/12 7:37
@Description: 主控制器
@Contact: lxy_jdsy@163.com
"""
import sys

from PyQt5.QtWidgets import QApplication

from model.client import Client
from model.receive import Receive
from view.login import Login
from view.weiliao import WeiLiao


class Controller:
    def __init__(self):
        # 本地客户
        self.client = Client()
        # 接受信息线程
        self.receive = Receive(self.client)
        # qt应用
        self.app = QApplication(sys.argv)
        # 登陆界面
        self.login = Login()
        # 聊天界面
        self.weiliao = WeiLiao()
        # 登陆微聊
        self.login_weiliao()

    # 登陆微聊
    def login_weiliao(self):
        # 显示登陆界面
        self.login.show()
        # 连接信号
        self.client.login_error.connect(self.login.tip)
        self.client.success.connect(self.start_weiliao)
        self.login.login.connect(self.client_login)
        self.login.register.connect(self.client_register)

    # 启动聊天界面
    def start_weiliao(self, message):
        # 如果登陆成功
        if message == "LOGIN SUCCESS":
            # 关闭登陆界面
            self.login.close()
            # 连接信号
            self.receive.received.connect(self.weiliao.add_message_item)
            self.receive.update_users.connect(self.weiliao.update_users)
            self.weiliao.close_connect.connect(self.close_all)
            self.weiliao.send_msg.connect(self.send_message)
            # 启动聊天界面
            self.weiliao.show()
            # 启动接收消息线程
            self.receive.start()
        # 如果注册成功
        elif message == "REGISTER SUCCESS":
            self.login.tip(message)

    # 这是一个槽函数，处理login信号
    def client_login(self, username, password):
        self.client.set_user_info(username, password)
        self.client.connect()

    # 这是一个槽函数，处理register信号
    def client_register(self, username, password):
        self.client.set_user_info(username, password)
        self.client.register()

    # 这是一个槽函数，处理send_msg信号
    def send_message(self, data):
        self.client.send(data)

    # 这是一个槽函数，处理close_connect信号
    def close_all(self):
        self.client.close()
        sys.exit(self.app.exec_())


if __name__ == '__main__':
    controller = Controller()
    sys.exit(controller.app.exec_())
