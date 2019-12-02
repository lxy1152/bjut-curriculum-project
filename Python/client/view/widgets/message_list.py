"""
@Name: message_list.py
@Author: lxy
@Date: 2019/5/12 15:57
@Description:
@Contact: lxy_jdsy@163.com
"""
import re

import emoji
from PyQt5.QtCore import QSize, Qt
from PyQt5.QtWidgets import QListWidget, QListWidgetItem, QGridLayout, QLabel
import qtawesome

from view.widgets.base_widget import BaseWidget


# 消息列表中的每一项
class MessageItem(BaseWidget):
    def __init__(self, **kwargs):
        super().__init__()
        # 布局
        self.grid = QGridLayout()
        # 初始化
        self.init_ui(**kwargs)
        # 样式
        self.set_qss()

    def init_ui(self, **kwargs):
        # 头像图标
        head = QLabel(chr(0xf2bd))
        head.setFont(qtawesome.font('fa', 25))

        # 用户名
        name = QLabel(kwargs['name'])

        # 消息，消息不能过长，所有每35个字符后，加一个换行
        message = QLabel(re.sub(r"(.{35})", "\\1\n", emoji.emojize(kwargs['message'])))
        # 边距
        message.setContentsMargins(0, 0, 0, 0)
        message.setObjectName("message")

        # 对齐方式
        alignment = kwargs['alignment']
        if not alignment or alignment == "left":
            self.grid.addWidget(head, 0, 0)
            self.grid.addWidget(name, 0, 1)
            self.grid.addWidget(message, 1, 1)
            self.grid.setRowStretch(0, 5)
            self.grid.setRowStretch(1, 10)
            self.grid.setRowStretch(2, 1)
            self.grid.setColumnStretch(4, 5)
        else:
            self.grid.addWidget(head, 0, 6)
            self.grid.addWidget(name, 0, 4, 1, 1, alignment=Qt.AlignRight)
            self.grid.addWidget(message, 1, 4)
            self.grid.setColumnStretch(0, 10)
        # 间距
        self.grid.setSpacing(10)
        # 应用
        self.setLayout(self.grid)
        # 边距
        self.setContentsMargins(10, 10, 10, 10)

    # 样式
    def set_qss(self):
        self.setStyleSheet('''
                QLabel{
                    margin: 0;
                    font-size: 20px;
                }
                QLabel#message{
                    background: #addf7b;
                    border:none;
                    padding: 5px;
                    color: #56565e;
                }
            ''')


# 消息列表
class MessageList(QListWidget):
    def __init__(self):
        super().__init__()
        self.init_ui()
        self.set_qss()

    def init_ui(self):
        # self.setVerticalScrollBarPolicy(Qt.ScrollBarAlwaysOff)
        pass

    def set_qss(self):
        # 滚动条样式重写
        self.setStyleSheet('''
            QScrollBar:vertical{
                background: transparent;
                margin: 0px, 0px, 0px, 0px;
                width: 10px;
            }
            QScrollBar:handle:vertical{
                background: #cbcbcb;
                border-radius: 5px;
                border: none;
            }
            QScrollBar::add-line:vertical, QScrollBar::sub-line:vertical{
                background: transparent;
            }
        ''')

    # 添加消息
    def add_message_item(self, **kwargs):
        # 添加的每一项都为QListWidgetItem对象
        item = QListWidgetItem()
        # 必须设置大小，否则不显示
        item.setSizeHint(QSize(200, 200))
        # 添加
        self.addItem(item)
        # 应用部件样式
        self.setItemWidget(item, MessageItem(**kwargs))
        # 滚动到最底部
        self.scrollToBottom()
