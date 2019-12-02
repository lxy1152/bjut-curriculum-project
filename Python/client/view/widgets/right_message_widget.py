"""
@Name: right_message_widget.py
@Author: lxy
@Date: 2019/5/11 15:14
@Description:
@Contact: lxy_jdsy@163.com
"""
import emoji
import qtawesome
from PyQt5 import QtCore
from PyQt5.QtCore import Qt
from PyQt5.QtGui import QFont, QTextCursor
from PyQt5.QtWidgets import QLabel, QGridLayout, QTextEdit, QPushButton, QTableWidget, QAbstractItemView, QHBoxLayout, \
    QTableWidgetItem, QHeaderView

from view.widgets.left_info_widget import ClickLabel
from view.widgets.message_list import MessageList
from .base_widget import BaseWidget


# 表情
class Expression(BaseWidget):
    expression_signal = QtCore.pyqtSignal(str)

    def __init__(self):
        super().__init__()
        # 采用表格，指定行列
        self.row = 5
        self.column = 4
        # 表格部件
        self.table_widget = QTableWidget(self.row, self.column)
        # 布局
        self.box = QHBoxLayout()
        # 表情列表
        self.emoji_list = (
            ":grinning_face:",
            ":grinning_face_with_big_eyes:",
            ":grinning_face_with_smiling_eyes:",
            ":beaming_face_with_smiling_eyes:",
            ":grinning_squinting_face:",
            ":grinning_face_with_sweat:",
            ":rolling_on_the_floor_laughing:",
            ":face_with_tears_of_joy:",
            ":upside-down_face:",
            ":slightly_smiling_face:",
            ":winking_face:",
            ":smiling_face_with_smiling_eyes:",
            ":smiling_face_with_halo:",
            ":smiling_face_with_heart-eyes:",
            ":face_with_tongue:",
            ":face_blowing_a_kiss:",
            ":kissing_face:",
            ":zany_face:",
            ":winking_face_with_tongue:",
            ":kissing_face_with_smiling_eyes:",
            ":face_savoring_food:"
        )
        self.init_ui()

    def init_ui(self):
        # 设置不可编辑
        self.table_widget.setEditTriggers(QAbstractItemView.NoEditTriggers)
        # 设置列头不显示
        self.table_widget.verticalHeader().setVisible(False)
        # 设置行头不显示
        self.table_widget.horizontalHeader().setVisible(False)
        # 列均匀填充
        self.table_widget.horizontalHeader().setSectionResizeMode(QHeaderView.Stretch)
        # 行均匀填充
        self.table_widget.verticalHeader().setSectionResizeMode(QHeaderView.Stretch)

        # 索引
        index = 0
        for i in range(0, self.row):
            for j in range(0, self.column):
                # emoji库emoji化字符串
                item = QTableWidgetItem(emoji.emojize(self.emoji_list[index]))
                # 设置处置水平居中
                item.setTextAlignment(Qt.AlignHCenter | Qt.AlignVCenter)
                # 添加表情
                self.table_widget.setItem(i, j, item)
                index += 1
        # 添加表格
        self.box.addWidget(self.table_widget)
        self.box.setContentsMargins(0, 0, 0, 0)
        # 应用
        self.setLayout(self.box)
        self.setFixedSize(400, 400)

        # 处理信号
        self.table_widget.cellClicked.connect(self.add_expression)

    # 发送表情数据
    def add_expression(self, p_int, p_int1):
        item = self.table_widget.item(p_int, p_int1)
        self.expression_signal.emit(item.text())


# 工具栏，目前只开放表情
class ToolBarWidget(BaseWidget):
    expression_signal = QtCore.pyqtSignal(str)

    def __init__(self):
        super().__init__()
        # 可点击label
        self.expression = ClickLabel(chr(0xf118))
        self.expression_widget = Expression()
        # 设置显示在顶层且无边框
        self.expression_widget.setWindowFlags(Qt.WindowStaysOnTopHint | Qt.FramelessWindowHint)

        # 上传文件，暂不开放
        # self.upload_file = QLabel()

        # 应用
        self.grid = QGridLayout()
        self.init_ui()
        self.set_qss()

    def init_ui(self):
        # 表情图标
        self.expression.setFont(qtawesome.font('fa', 22))

        # 上传文件图标
        # self.upload_file.setText(chr(0xf07b))
        # self.upload_file.setFont(qtawesome.font('fa', 22))

        self.grid.addWidget(self.expression, 0, 0, alignment=Qt.AlignLeft)
        # self.grid.addWidget(self.upload_file, 0, 1, alignment=Qt.AlignLeft)

        # 设置列比例
        self.grid.setColumnStretch(0, 10)
        # self.grid.setColumnStretch(1, 10)
        self.grid.setColumnStretch(2, 100)
        self.setLayout(self.grid)

        # 处理信号
        self.expression.clicked.connect(lambda: self.expression_widget.show())
        self.expression_widget.expression_signal.connect(self.add_expression)

    def set_qss(self):
        self.setStyleSheet('''
            QWidget{
                background: transparent;
                border-top: 1px solid #cbcbcb;
            }
            QLabel{
                border: none;
                color: #636363;
            }
        ''')

    # 处理信号
    def add_expression(self, expression):
        # 关闭表情框
        self.expression_widget.close()
        # 发送信号
        self.expression_signal.emit(expression)


# 自定义TextEdit
class TextEdit(QTextEdit):
    send_msg = QtCore.pyqtSignal()

    def __init__(self):
        super().__init__()

    # 重写键盘监听，动态转换emoji表情
    def keyReleaseEvent(self, event):
        QTextEdit.keyReleaseEvent(self, event)
        origin = self.toPlainText()
        changed = emoji.emojize(origin)
        if changed != origin:
            self.setText(changed)
            self.moveCursor(QTextCursor.End)

    # 发送信号
    def keyPressEvent(self, event):
        QTextEdit.keyPressEvent(self, event)
        if event.key() == Qt.Key_Return:
            self.send_msg.emit()


# 输入部件
class InputWidget(BaseWidget):
    send_msg = QtCore.pyqtSignal(str)

    def __init__(self):
        super().__init__()
        self.text = TextEdit()

        # 处理信号
        self.text.send_msg.connect(self.emit_signal)

        # 发送按钮
        self.confirm = QPushButton("发送")
        self.grid = QGridLayout()
        self.init_ui()
        self.set_qss()

    def init_ui(self):
        # 设置提示
        self.text.setPlaceholderText("请输入要发送的内容...")
        # 设置文字格式
        self.text.setFont(QFont("Timers", 12))
        # 不显示滚动条
        self.text.setVerticalScrollBarPolicy(Qt.ScrollBarAlwaysOff)
        # 处理信号
        self.confirm.clicked.connect(self.emit_signal)
        self.grid.addWidget(self.text, 0, 0, 3, 5)
        self.grid.addWidget(self.confirm, 4, 4)
        self.setLayout(self.grid)

    def set_qss(self):
        self.setStyleSheet('''
            QTextEdit{
                background: transparent;
                border: none;
                color: #333333;
            }
            QPushButton{
                border-radius: 10px;
                border: 1px solid #cbcbcb;
                padding: 10px;
                color: #333333;
                background: white;
            }
        ''')

    # 发送信号
    def emit_signal(self):
        t = self.text.toPlainText()
        if t[-1] == "\n":
            t = t[:-1]
        self.send_msg.emit(t)
        self.text.setText("")

    # 添加表情
    def add_expression(self, expression):
        self.text.insertPlainText(expression)


# 添加工具栏
class InputMessageWidget(BaseWidget):
    send_msg = QtCore.pyqtSignal(str)

    def __init__(self):
        super().__init__()
        # 工具栏
        self.tool_bar = ToolBarWidget()
        self.input_widget = InputWidget()

        # 处理信号
        self.input_widget.send_msg.connect(self.emit_signal)
        self.tool_bar.expression_signal.connect(self.add_expression)

        # 布局
        self.grid = QGridLayout()
        self.init_ui()
        self.set_qss()

    def init_ui(self):
        self.input_widget.setObjectName("input")
        self.grid.addWidget(self.tool_bar)
        self.grid.addWidget(self.input_widget)
        self.grid.setContentsMargins(0, 0, 0, 0)
        self.setLayout(self.grid)

    def set_qss(self):
        # self.setStyleSheet('''
        #
        # ''')
        pass

    # 发送信号
    def emit_signal(self, data):
        self.send_msg.emit(data)

    # 添加表情
    def add_expression(self, expression):
        self.input_widget.add_expression(expression)


# 总类
class RightMessageWidget(BaseWidget):
    send_msg = QtCore.pyqtSignal(str)

    def __init__(self):
        super().__init__()
        # 标题
        self.title = QLabel()
        # 消息内容
        self.contents = MessageList()
        # 输入消息
        self.input_message = InputMessageWidget()
        self.input_message.send_msg.connect(self.emit_signal)
        self.grid = QGridLayout()
        self.init_ui()
        self.set_qss()

    def init_ui(self):
        self.title.setText("聊天室")
        self.title.setObjectName("title")
        self.contents.setObjectName("contents")

        self.grid.addWidget(self.title, 0, 0, alignment=Qt.AlignCenter)
        self.grid.addWidget(self.contents)
        self.grid.addWidget(self.input_message)
        self.grid.setContentsMargins(10, 0, 10, 0)
        self.setLayout(self.grid)

    def set_qss(self):
        self.setStyleSheet('''
            QWidget{
                background: #ececec;
                padding: 0px;
            }
            QLabel#title{
                color: #333333;
                font-size: 25px;
                padding-top: 15px;
                padding-bottom: 10px;
            }
            QListView#contents{
                border-top: 1px solid #cbcbcb;
            }
        ''')

    def emit_signal(self, data):
        self.send_msg.emit(data)

    def add_message_item(self, data):
        self.contents.add_message_item(name=data["username"], message=data["data"], alignment=data["alignment"])
