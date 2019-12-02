"""
@Name: main.py
@Author: lxy
@Date: 2019/5/10 10:38
@Description: 主函数， 请从此启动客户端
@Contact: lxy_jdsy@163.com
"""
import sys

from controller.controller import Controller

if __name__ == '__main__':
    controller = Controller()
    sys.exit(controller.app.exec_())
