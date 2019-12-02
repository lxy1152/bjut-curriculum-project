# Author：Xiangyu Li
# Email: lxy_jdsy@163.com
# Time：2019/7/1 16:27
# Name: label.py
# Description: 实现可以响应点击的Label
from PyQt5 import QtCore
from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import QLabel


class ClickLabel(QLabel):
    clicked = QtCore.pyqtSignal()

    def __init__(self, text):
        super().__init__()
        self.setText(text)

    # 监听鼠标点击，并发送信号
    def mousePressEvent(self, event):
        self.clicked.emit()
        QLabel.mousePressEvent(self, event)


class AnyWhereLabel(QLabel):
    def __init__(self, parent, text, point):
        super().__init__(parent)
        self.setText(text)
        self.point = point
        self.init_ui()
        self.set_qss()

    def init_ui(self):
        self.setAttribute(Qt.WA_TranslucentBackground)

    def move_label(self, point):
        self.move(self.point + point)

    def set_qss(self):
        self.setStyleSheet('''
            QLabel{
                color: white;
                font-size: 20px;
            }
        ''')
