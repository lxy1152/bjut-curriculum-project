"""
@Name: login.py
@Author: lxy
@Date: 2019/5/15 8:14
@Description: 登陆窗口
@Contact: lxy_jdsy@163.com
"""
from PyQt5 import QtCore
from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import QGridLayout, QMessageBox

from view.widgets.base_widget import BaseWidget
from view.widgets.login_widget import MessageWidget
from view.widgets.top_bar import TopBar


# 继承BaseWidget，可以使用qss样式表
class Login(BaseWidget):
    login = QtCore.pyqtSignal(str, str)
    register = QtCore.pyqtSignal(str, str)

    def __init__(self):
        super().__init__()
        # 因无边框，需要保存坐标，以实现拖动功能
        self._start_pos = None
        self._end_pos = None
        # 自定义顶栏
        self.top_bar = TopBar("wide")
        # 登陆部件
        self.message_widget = MessageWidget()
        # 网格布局
        self.grid = QGridLayout()
        # 初始化ui
        self.init_ui()
        # 设置qss样式
        self.set_qss()

    def init_ui(self):
        # 同weiliao.py
        self.setFixedSize(800, 550)
        self.grid.addWidget(self.top_bar)
        self.grid.addWidget(self.message_widget)
        self.grid.setContentsMargins(0, 0, 0, 0)
        self.setLayout(self.grid)

        # 处理信号
        self.message_widget.login.connect(self.login_signal)
        self.message_widget.reg.connect(self.register_signal)
        self.message_widget.error.connect(self.tip)

        self.top_bar.minus_signal.connect(self.showMinimized)
        self.top_bar.power_off_signal.connect(self.close)
        self.top_bar.position_signal.connect(self.set_position)
        self.top_bar.move_signal.connect(self.move_position)

        # 实现无边框
        self.setWindowFlags(Qt.FramelessWindowHint)

        # 设置标题
        self.setWindowTitle("微聊")

    # 发送login信号
    def login_signal(self, username, password):
        self.login.emit(username, password)

    # 发送register信号
    def register_signal(self, username, password):
        self.register.emit(username, password)

    # 显示提示框信息
    def tip(self, message):
        if message == "NO SUCH RECORD":
            text = "没有找到相关记录"
        elif message == "MISMATCH":
            text = "用户名或密码输入错误"
        elif message == "FORMAT ERROR":
            text = "请正确输入用户名和密码！"
        elif message == "UNKNOWN ERROR":
            text = "未知错误"
        elif message == "MORE THAN TWO??":
            text = "用户名已存在"
        elif message == "LOGIN SUCCESS":
            text = "登陆成功"
        elif message == "REGISTER SUCCESS":
            text = "注册成功"
        else:
            text = message
        QMessageBox.information(self, "提示", text)

    # 监听鼠标单击，功能类似MousePressEvent()
    def set_position(self, point):
        self._start_pos = point

    # 监听鼠标移动，类似MouseMoveEvent()，实现窗口拖动
    def move_position(self, event):
        self._end_pos = event.pos() - self._start_pos
        self.move(self.pos() + self._end_pos)
