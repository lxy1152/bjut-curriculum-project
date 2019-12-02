# Author：Xiangyu Li
# Email: lxy_jdsy@163.com
# Time：2019/7/1 16:25
# Name: login_widget.py
# Description: 

import qtawesome
from PyQt5 import QtCore
from PyQt5.QtWidgets import QGridLayout, QLabel, QLineEdit, QPushButton

from lib.security import Security
from view.widgets.base_widget import BaseWidget


# 登陆界面中的输入
class InputMessageWidget(BaseWidget):
    def __init__(self):
        super().__init__()
        # 输入框
        self.username = QLineEdit()
        self.password = QLineEdit()
        # 布局
        self.grid = QGridLayout()
        # 初始化
        self.init_ui()
        # 样式
        self.set_qss()

    def init_ui(self):
        # 设置边距
        self.setContentsMargins(0, 0, 0, 0)
        # 设置提示信息
        self.username.setPlaceholderText("用户名")
        self.username.setObjectName("username")
        self.password.setPlaceholderText("密码")
        # 设置为*号显示模式
        self.password.setEchoMode(QLineEdit.Password)
        self.password.setObjectName("password")
        # 添加部件
        self.grid.addWidget(self.username, 0, 0, 1, 5)
        self.grid.addWidget(self.password, 2, 0, 1, 5)
        # 应用布局
        self.setLayout(self.grid)

    def set_qss(self):
        self.setStyleSheet('''
            QWidget{
                border: 1px solid #cbcbcb;
                border-radius: 10px;
            }
            QLineEdit{
                background: transparent;
                border: none;
                border-bottom: 1px solid #cbcbcb;
                border-radius: 0;
                padding: 15px;
                font-size: 20px;
            }
            QLineEdit#password{
                padding-bottom: 20px;

            }
            QLineEdit:hover{
                border-bottom: 1px solid orange;
            }
            QLineEdit:focus{
                border-bottom: 1px solid orange;
            }
        ''')


# 加入上面的部件，再加入两个按钮
class MessageWidget(BaseWidget):
    login = QtCore.pyqtSignal(str, str)
    reg = QtCore.pyqtSignal(str, str)
    error = QtCore.pyqtSignal(str)

    def __init__(self):
        super().__init__()
        # 头像图标
        self.head = QLabel(chr(0xf2bd))
        # 输入
        self.input_message = InputMessageWidget()
        # 登陆与注册按钮
        self.confirm = QPushButton("登录")
        self.register = QPushButton("注册")
        # 布局
        self.grid = QGridLayout()
        # 初始化
        self.init_ui()
        # 样式
        self.set_qss()

    def init_ui(self):
        # 头像图标
        self.head.setFont(qtawesome.font('fa', 120))
        self.head.setObjectName("head")
        # 添加部件
        self.grid.addWidget(self.head, 0, 0, 1, 1)
        self.grid.addWidget(self.input_message, 0, 2, 1, 4)
        self.grid.addWidget(self.confirm, 3, 2, 1, 1)
        self.grid.addWidget(self.register, 3, 4, 1, 1)
        # 设置间距
        self.grid.setSpacing(30)
        # 应用布局
        self.setLayout(self.grid)
        # 设置边距
        self.setContentsMargins(20, 10, 20, 10)

        # 处理信号
        self.confirm.clicked.connect(lambda: self.emit_signal("Login"))
        self.register.clicked.connect(lambda: self.emit_signal("Register"))

    def set_qss(self):
        self.setStyleSheet('''
            QLabel#head{
                color: #333333;
            }
            QPushButton{
                border: none;
                border-radius: 10px;
                background: #cbcbcb;
                padding: 20px;
            }
            QPushButton:hover{
                background: #3399cc;
                color: white;
            }
        ''')

    # 发送信号
    def emit_signal(self, emit_type):
        # 获得文本信息
        username = self.input_message.username.text()
        password = self.input_message.password.text()
        # 输入不能为空
        if not username or not password:
            self.error.emit("FORMAT ERROR")
            return
        # 发送信号
        if emit_type == "Login":
            self.login.emit(username, password)
        if emit_type == "Register":
            self.reg.emit(username, password)
