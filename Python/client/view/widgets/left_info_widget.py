"""
@Name: left_info_widget.py
@Author: lxy
@Date: 2019/5/11 15:13
@Description: 聊天窗口左侧信息
@Contact: lxy_jdsy@163.com
"""
from PyQt5 import QtCore
from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import QLabel, QGridLayout, QMessageBox, QPushButton
import qtawesome

from .base_widget import BaseWidget


# 自定义可点击Label，普通的Label没有点击信号
class ClickLabel(QLabel):
    clicked = QtCore.pyqtSignal()

    def __init__(self, text):
        super().__init__()
        self.setText(text)

    # 监听鼠标点击，并发送信号
    def mousePressEvent(self, event):
        self.clicked.emit()
        QLabel.mousePressEvent(self, event)


# 左侧信息类
class LeftInfoWidget(BaseWidget):
    close_connect = QtCore.pyqtSignal()

    def __init__(self):
        super().__init__()
        self.init_ui()
        self.set_qss()

    def init_ui(self):
        # 用户头像，使用文字图标
        head = ClickLabel(chr(0xf2bd))
        head.setFont(qtawesome.font('fa', 50))

        # 聊天图标
        chat = ClickLabel(chr(0xf086))
        chat.setFont(qtawesome.font('fa', 30))
        chat.setObjectName("chat")

        # 设置，暂不需要，关闭
        # setting = ClickLabel(chr(0xf0ad))
        # setting.setFont(qtawesome.font("fa", 30))

        # 关于图标
        about = ClickLabel(chr(0xf129))
        about.setFont(qtawesome.font('fa', 30))
        about.clicked.connect(self.tip)

        # 关闭程序，暂不需要，关闭
        # power_off = ClickLabel(chr(0xf011))
        # power_off.setFont(qtawesome.font('fa', 30))
        # power_off.setObjectName("power_off")
        # power_off.clicked.connect(lambda: self.close_connect.emit())

        # 网格布局
        gird = QGridLayout()
        gird.addWidget(head, 0, 0, alignment=Qt.AlignCenter)
        gird.addWidget(chat, 1, 0, alignment=Qt.AlignCenter)
        # gird.addWidget(setting, 2, 0, alignment=Qt.AlignCenter)
        gird.addWidget(about, 2, 0, alignment=Qt.AlignCenter)
        # gird.addWidget(power_off, 5, 0, alignment=Qt.AlignCenter)
        gird.setSpacing(20)
        # 设置各行比例
        gird.setRowStretch(0, 10)
        gird.setRowStretch(1, 5)
        gird.setRowStretch(2, 5)
        gird.setRowStretch(3, 5)
        gird.setRowStretch(4, 70)
        gird.setRowStretch(5, 10)
        self.setLayout(gird)

    # 设置qss样式
    def set_qss(self):
        self.setStyleSheet('''
            QWidget{
                background: rgb(35, 38, 42);
            }
            QLabel{
                background: transparent;
                color: white;
            }
            QLabel:hover{
                color: #addf7b;
            }
            QLabel#chat{
                color: #addf7b
            }
            QLabel#power_off{
                color: #cbcbcb;
            }
            QLabel#power_off:hover{
                color: rgb(224, 54, 54);
            }
            QPushButton{
                background: white;
                color: black;
            }
        ''')

    # 点击关于后，提示如下
    def tip(self):
        tip = QMessageBox(self)
        tip.setWindowTitle("关于")
        tip.setText("微聊是一个基于socket实现的简易聊天室，支持一对多聊天，左侧显示为当前群聊人员\n"
                    "你不仅可以发送文本信息，还可以发送emoji表情\n"
                    "表情的添加方式有两种：\n"
                    "1：在文本框中输入如:thumbs_up:形式的字符，如果输入的表情是支持的，那么它将自动转换为emoji表情\n"
                    "2：单击文本框上方的表情按钮，不过因emoji表情库过多，此处仅展示了20个表情\n"
                    "想要查看全部支持的emoji表情，可以访问http://www.unicode.org/emoji/charts/full-emoji-list.html\n"
                    "最后祝你使用愉快！")
        tip.addButton(QPushButton("我知道了"), QMessageBox.YesRole)
        tip.exec_()
