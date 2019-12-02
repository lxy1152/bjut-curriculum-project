# Author：Xiangyu Li
# Email: lxy_jdsy@163.com
# Time：2019/7/2 11:29
# Name: main.py
# Description:
import sys

from controller.control import Control

controller = Control()
controller.start()
sys.exit(controller.exec())
