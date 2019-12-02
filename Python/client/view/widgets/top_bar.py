"""
@Name: top_bar.py
@Author: lxy
@Date: 2019/5/16 14:26
@Description:
@Contact: lxy_jdsy@163.com
"""
import qtawesome
from PyQt5 import QtCore
from PyQt5.QtCore import QPoint
from PyQt5.QtGui import QMouseEvent
from PyQt5.QtWidgets import QGridLayout

from view.widgets.base_widget import BaseWidget
from view.widgets.left_info_widget import ClickLabel


# 自定义顶栏
class TopBar(BaseWidget):
    minus_signal = QtCore.pyqtSignal()
    power_off_signal = QtCore.pyqtSignal()
    position_signal = QtCore.pyqtSignal(QPoint)
    move_signal = QtCore.pyqtSignal(QMouseEvent)

    def __init__(self, top_bar_type):
        super().__init__()
        # 最小化label
        self.minus = ClickLabel(chr(0xf068))
        # 最大化label
        self.plus = ClickLabel(chr(0xf067))
        # 关闭label
        self.power_off = ClickLabel(chr(0xf00d))
        self.grid = QGridLayout()
        self.init_ui(top_bar_type)
        self.set_qss(top_bar_type)

    def init_ui(self, top_bar_type):
        # 最小化
        self.minus.setFont(qtawesome.font('fa', 25))
        self.minus.setObjectName("minus")
        self.minus.clicked.connect(lambda: self.minus_signal.emit())

        # 最大化
        self.plus.setFont(qtawesome.font('fa', 25))
        self.plus.setObjectName("plus")

        # 关闭
        self.power_off.setFont(qtawesome.font('fa', 25))
        self.power_off.setObjectName("power_off")
        self.power_off.clicked.connect(lambda: self.power_off_signal.emit())

        self.grid.addWidget(self.minus, 0, 4)
        self.grid.addWidget(self.plus, 0, 5)
        self.grid.addWidget(self.power_off, 0, 6)
        # 处理不同风格，wide是登陆界面的风格
        if top_bar_type == "wide":
            self.grid.setColumnStretch(0, 5)
            self.grid.setColumnStretch(1, 5)
            self.grid.setColumnStretch(2, 5)
            self.grid.setColumnStretch(4, 1)
            self.grid.setColumnStretch(5, 1)
            self.grid.setColumnStretch(6, 1)
            self.grid.setRowStretch(0, 5)
            self.grid.setRowStretch(1, 5)
            self.grid.setRowStretch(2, 5)
            self.grid.setRowStretch(3, 5)
        self.grid.setSpacing(20)
        self.setLayout(self.grid)
        self.setContentsMargins(0, 0, 0, 0)

    def set_qss(self, top_bar_type):
        if top_bar_type == "wide":
            self.setStyleSheet('''
                QWidget{
                    background: rgb(29, 176, 184);
                }
                QLabel{
                    background: transparent;
                    border-image: none;
                    border: none;
                    color: white;
                }
                QLabel:hover{
                    color: orange;
                }
            ''')
        else:
            self.setStyleSheet('''
                QLabel{
                    background: transparent;
                    border-image: none;
                    border: none;
                    color: white;
                }
                QLabel:hover{
                    color: orange;
                }
            ''')

    # 实现拖动窗口，重写监听
    def mousePressEvent(self, event):
        self.position_signal.emit(QPoint(event.x(), event.y()))

    def mouseMoveEvent(self, event):
        self.move_signal.emit(event)
