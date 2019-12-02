"""
@Name: main.py
@Author: lxy
@Date: 2019/5/10 10:32
@Description: 主函数，运行本程序以启动服务器
@Contact: lxy_jdsy@163.com
"""
from server import Server

if __name__ == '__main__':
    server = Server()
    server.accept()
