"""
@Name: weiliao.py
@Author: lxy
@Date: 2019/5/11 14:31
@Description: 聊天界面
@Contact: lxy_jdsy@163.com
"""
import sys

from PyQt5 import QtCore
from PyQt5.QtCore import Qt
from PyQt5.QtGui import QPalette, QColor, QIcon
from PyQt5.QtWidgets import QApplication, QMainWindow, QWidget, QGridLayout, QAction, QMenu, QSystemTrayIcon

from view.widgets import left_info_widget, right_message_widget, middle_list_widget
from view.widgets.top_bar import TopBar


# 主窗口
class WeiLiao(QMainWindow):
    close_connect = QtCore.pyqtSignal()
    send_msg = QtCore.pyqtSignal(str)

    def __init__(self):
        super().__init__()
        # 因为隐藏了边框， 所以要保存起止位置，以实现拖动窗口
        self._start_pos = None
        self._end_pos = None
        # 自定义的顶栏（最小化，最大化，关闭按钮）
        self.top_bar_widget = TopBar("normal")
        # 聊天界面左侧信息
        self.left_widget = left_info_widget.LeftInfoWidget()
        # 聊天界面中间用户列表
        self.middle_widget = middle_list_widget.MiddleListWidget()
        # 聊天界面右侧消息界面
        self.right_widget = right_message_widget.RightMessageWidget()

        # 初始化
        self.init_ui()
        # 初始化系统托盘，但是并不能显示，所以先关闭
        # self.init_tray()

    def init_ui(self):
        # 设置窗口大小
        self.setFixedSize(1200, 800)
        # 设置左边信息部件
        self.left_widget.setObjectName("left_widget")
        # 设置中间列表部件
        self.middle_widget.setObjectName("middle_widget")
        # 设置右边消息部件
        self.right_widget.setObjectName("right_widget")
        # 设置主窗口部件
        main_widget = QWidget()
        # 选择网格布局
        main_layout = QGridLayout()
        # 设置边距为0，否则外面会有白边
        main_layout.setContentsMargins(0, 0, 0, 0)
        # 设置部件间距
        main_layout.setSpacing(0)
        # 添加部件
        main_layout.addWidget(self.top_bar_widget, 0, 13)
        main_layout.addWidget(self.left_widget, 1, 0, 10, 1)
        main_layout.addWidget(self.middle_widget, 1, 2, 10, 3)
        main_layout.addWidget(self.right_widget, 1, 6, 10, 8)
        # 应用布局
        main_widget.setLayout(main_layout)
        # 主窗口添加部件
        self.setCentralWidget(main_widget)

        # 设置背景色
        palette1 = QPalette()
        palette1.setColor(self.backgroundRole(), QColor(35, 38, 42))
        self.setPalette(palette1)
        # 这一项必须要开，否则无效
        self.setAutoFillBackground(True)

        # 处理信号
        self.top_bar_widget.minus_signal.connect(self.showMinimized)
        self.top_bar_widget.power_off_signal.connect(self.close)
        self.right_widget.send_msg.connect(self.emit_signal)

        # 设置为无边框
        self.setWindowFlags(Qt.FramelessWindowHint)

        # 设置标题
        self.setWindowTitle("微聊")

    # 系统托盘，不知原因，托盘不能显示
    def init_tray(self):
        tray = QSystemTrayIcon()
        # 设置图标
        tray.setIcon(QIcon("E:\pypro\chat\client\res\icon\setting.png"))
        # 托盘菜单
        tray_menu = QMenu()
        # 增加动作
        tray_menu.addAction(QAction("还原", self, triggered=self.showNormal))
        tray_menu.addAction(QAction("退出", self, triggered=self.close))
        # 应用菜单
        tray.setContextMenu(tray_menu)
        # 显示托盘
        tray.show()

    # 发送send_msg信号
    def emit_signal(self, data):
        self.send_msg.emit(data)

    # 发送add_message_item信号
    def add_message_item(self, data):
        self.right_widget.add_message_item(data)

    # 重写鼠标单击监听事件，获得鼠标位置
    def mousePressEvent(self, *args, **kwargs):
        x = args[0].pos().x()
        y = args[0].pos().y()
        # 如果鼠标点击位置在顶栏，坐标范围如下
        if x in range(1, 1070) and y in range(1, 68):
            self._start_pos = args[0].pos()

    # 重写鼠标拖动监听事件，并拖动窗口
    def mouseMoveEvent(self, *args, **kwargs):
        x = args[0].pos().x()
        y = args[0].pos().y()
        if x in range(1, 1070) and y in range(1, 68):
            self._end_pos = args[0].pos() - self._start_pos
            self.move(self.pos() + self._end_pos)

    # 更新用户列表
    def update_users(self, d):
        self.middle_widget.update_users(d)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    weiliao = WeiLiao()
    weiliao.show()
    sys.exit(app.exec_())
