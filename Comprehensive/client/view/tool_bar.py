# Author：Xiangyu Li
# Email: lxy_jdsy@163.com
# Time：2019/7/1 18:55
# Name: tool_bar.py
# Description:
from PyQt5 import QtCore
from PyQt5.QtCore import Qt, QPoint
from PyQt5.QtWidgets import QHBoxLayout, QApplication, QLabel, QWidget
import qtawesome

from view.area import Area
from view.widgets.base_widget import BaseWidget
from view.widgets.label import ClickLabel, AnyWhereLabel


class ToolBar(BaseWidget):
    to_discern = QtCore.pyqtSignal()

    def __init__(self):
        super().__init__()
        self._start_pos = None
        self._end_pos = None
        self.area = ClickLabel(chr(0xf096))
        self.translate = ClickLabel(chr(0xf002))
        self.power_off = ClickLabel(chr(0xf00d))
        self.box = QHBoxLayout()
        self.choose_area = Area()
        self.rect = None
        self.labels = []
        self.screen = QApplication.primaryScreen()
        self.texts = QWidget(parent=None)
        self.setWindowFlags(Qt.FramelessWindowHint)
        self.init_ui()
        self.set_qss()
        self.signal()

    def init_ui(self):
        self.area.setFont(qtawesome.font('fa', 30))
        self.translate.setFont(qtawesome.font('fa', 25))
        self.power_off.setFont(qtawesome.font('fa', 30))
        self.box.addWidget(self.area)
        self.box.addWidget(self.translate)
        self.box.addWidget(self.power_off)
        self.setLayout(self.box)
        self.setWindowOpacity(0.7)

    def set_qss(self):
        self.setStyleSheet('''
            QLabel{
                padding: 10px;
                color: #333;
            }
            QLabel:hover{
                color: orange;
            }
        ''')

    def signal(self):
        self.area.clicked.connect(self.start_choose)
        self.choose_area.finish.connect(self.end_choose)
        self.translate.clicked.connect(self.shot_area)
        self.power_off.clicked.connect(self.close_window)

    def close_window(self):
        self.close()
        self.texts.close()

    def start_choose(self):
        self.hide()
        self.texts.hide()
        self.choose_area.showFullScreen()

    def end_choose(self, rect):
        self.choose_area.close()
        self.show()
        self.rect = rect

    def shot_area(self):
        if self.rect:
            self.hide()
            screen_shot = self.screen.grabWindow(QApplication.desktop().winId(), self.rect.x(), self.rect.y(),
                                                 self.rect.width(), self.rect.height())
            screen_shot.save('static/shot.png', 'png')
            self.show()
            self.to_discern.emit()

    def show_text(self, res):
        if self.labels:
            for l in self.labels:
                l.close()
            self.labels.clear()
        self.texts.setWindowFlags(Qt.FramelessWindowHint)
        self.texts.setAttribute(Qt.WA_TranslucentBackground)
        self.texts.showFullScreen()
        for item in res:
            text = item['Text']
            start_cor = item['Direction'][2]
            start_pos = QPoint(start_cor['X'], start_cor['Y'])
            label = AnyWhereLabel(self.texts, text, start_pos)
            label.move_label(QPoint(self.rect.x(), self.rect.y()))
            label.show()
            self.labels.append(label)

    def mousePressEvent(self, event):
        self._start_pos = event.pos()

    def mouseMoveEvent(self, event):
        self._end_pos = event.pos() - self._start_pos
        self.move(self.pos() + self._end_pos)
