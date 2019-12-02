"""
@Name: middle_list_widget.py
@Author: lxy
@Date: 2019/5/11 15:13
@Description:
@Contact: lxy_jdsy@163.com
"""
from PyQt5.QtCore import Qt, QSize
from PyQt5.QtWidgets import QLineEdit, QLabel, QGridLayout, QListWidget, QListWidgetItem
import qtawesome

from .base_widget import BaseWidget


# 搜索框部件，赞不需要
class SearchBarWidget(BaseWidget):
    def __init__(self):
        super().__init__()
        self.init_ui()

    def init_ui(self):
        # 设置部件
        search_icon = QLabel(chr(0xf002))
        search_icon.setFont(qtawesome.font('fa', 20))
        search_icon.setStyleSheet('''
            border: none;
            color: rgb(183, 176, 176);
            margin: 0;
            padding: 0;
        ''')
        qle = QLineEdit()
        qle.setPlaceholderText("搜索")
        qle.setClearButtonEnabled(True)
        qle.setFocusPolicy(Qt.ClickFocus)
        qle.setStyleSheet('''
            border: none;
            background: transparent;
        ''')

        # 设置布局
        gird = QGridLayout()
        gird.addWidget(search_icon, 0, 0, 1, 1)
        gird.addWidget(qle, 0, 2)

        # 应用布局
        self.setLayout(gird)

        self.setStyleSheet('''
            background: rgb(240, 240, 240);
            border-radius: 20px;
            padding: 0;
        ''')

    def set_qss(self):
        pass


# 每一个用户信息
class UserItem(BaseWidget):
    def __init__(self, username):
        super().__init__()
        # 布局
        self.grid = QGridLayout()
        # 初始化
        self.init_ui(username)
        # 样式
        self.set_qss()

    def init_ui(self, username):
        # 头像图标
        head = QLabel(chr(0xf2bd))
        head.setFont(qtawesome.font('fa', 30))
        # 用户名
        user = QLabel(username)
        self.grid.addWidget(head, 0, 0)
        self.grid.addWidget(user, 0, 2)
        self.setLayout(self.grid)

    def set_qss(self):
        self.setStyleSheet('''
            QLabel{
                font-size: 25px;
            }
        ''')


# 用户列表
class UserList(QListWidget):
    def __init__(self):
        super().__init__()
        # 管理所有item
        self.items = []
        self.set_qss()

    def set_qss(self):
        pass

    def add_user_item(self, username):
        item = QListWidgetItem()
        item.setSizeHint(QSize(100, 100))
        item.setSelected(False)
        self.items.append(item)
        self.addItem(item)
        self.setItemWidget(item, UserItem(username))


class MiddleListWidget(BaseWidget):
    def __init__(self):
        super().__init__()
        # 新建用户列表
        self.user_list = UserList()
        self.grid = QGridLayout()
        self.init_ui()
        self.set_qss()

    def init_ui(self):
        self.grid.addWidget(self.user_list)
        self.grid.setContentsMargins(0, 0, 0, 0)
        self.grid.setSpacing(0)
        self.setLayout(self.grid)

    def set_qss(self):
        pass

    def update_users(self, d):
        # 每次清除之前的数据
        self.user_list.clear()
        # 添加新数据
        for username in d["users"]:
            self.user_list.add_user_item(username)
