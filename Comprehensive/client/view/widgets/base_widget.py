# Author：Xiangyu Li
# Email: lxy_jdsy@163.com
# Time：2019/7/1 16:23
# Name: base_widget.py
# Description: 重写部件的父类
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
    # ！重写paintEvent方法， 否则不能使用qss样式表！！
    # ！         无特殊需要，不要修改            ！！
    # ！！！！！！！！！！！！！！！！！！！！！！！！
    def paintEvent(self, event):
        opt = QStyleOption()
        opt.initFrom(self)
        painter = QPainter(self)
        # 反锯齿
        painter.setRenderHint(QPainter.Antialiasing)
        self.style().drawPrimitive(QStyle.PE_Widget, opt, painter, self)
