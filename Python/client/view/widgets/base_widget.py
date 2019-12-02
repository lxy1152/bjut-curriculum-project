"""
@Name: base_widget.py
@Author: lxy
@Date: 2019/5/11 22:03
@Description: 自定义Widget父类
@Contact: lxy_jdsy@163.com
"""
from PyQt5.QtGui import QPainter
from PyQt5.QtWidgets import QWidget, QStyleOption, QStyle


# 因为普通的QWidget如果没有重写paintEvent方法，不能使用qss，所以继承QWidget并重写该方法
# 该类为以下Widget的父类
class BaseWidget(QWidget):
    def __init__(self):
        super().__init__()

    def init_ui(self, *args):
        pass

    def set_qss(self, *args):
        pass

    # ！！！！！！！！！！！！！！！！！！！！！！！！！
    # ！重写paintEvent方法， 否则不能使用qss样式表！
    # ！         无特殊需要，不要修改           ！
    # ！！！！！！！！！！！！！！！！！！！！！！！！
    def paintEvent(self, event):
        opt = QStyleOption()
        opt.initFrom(self)
        painter = QPainter(self)
        # 反锯齿
        painter.setRenderHint(QPainter.Antialiasing)
        self.style().drawPrimitive(QStyle.PE_Widget, opt, painter, self)
