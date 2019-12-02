"""
@Name: server.py
@Author: lxy
@Date: 2019/5/10 10:19
@Description: 服务器相关
@Contact: lxy_jdsy@163.com
"""
import sqlite3
import socket
from threading import Timer

from work import Work
from database import Database


class Server(object):
    def __init__(self):
        # 创建新的socket
        self.server = socket.socket()
        print("正在启动...")
        # 绑定主机信息，信息的格式必须是元组
        self.host_port = ("127.0.0.1", 2333)
        self.server.bind(self.host_port)
        print("当前ip为：{}，监听端口为：{}".format(self.host_port[0], self.host_port[1]))
        # 设置最大排队数
        self.server.listen(5)
        print("初始化成功")
        # 所有连接客户机管理
        self.clients = []
        # 所有子线程管理
        self.threads = []
        # 连接sqlite数据库
        self.db = Database("weiliao.db")
        # 设置执行时间
        Timer(5, exit).start()

    # 更新连接客户机信息
    def update_clients(self, update_mode, username, status, client, del_thread=None):
        # 如果模式为更新，则修改相应客户机的用户名与状态信息
        if update_mode == "update":
            for c in self.clients:
                if c["client"] is client:
                    c["username"] = username
                    c["status"] = status
                    print(self.clients)
                    break
        # 如果模式为删除，则删除相应客户机连接与子线程
        elif update_mode == "delete":
            for c in self.clients:
                if c["client"] is client:
                    self.clients.remove(c)
            if del_thread:
                self.threads.remove(del_thread)
        # 对所有子线程更新所有客户机列表
        for thread in self.threads:
            thread.update_clients(self.clients)
        # 向所有状态为“login”的客户机发送在线用户列表
        users = [c["username"] for c in self.clients if c["status"] == "login"]
        d = {"type": "users", "users": users}
        for client in self.clients:
            if client["status"] == "login":
                client["client"].send(bytes(str(d), encoding="utf-8"))

    # 接收连接请求
    def accept(self):
        # 持续监听端口
        while True:
            # 获得新连接客户机的数据
            client, address = self.server.accept()
            print("检测到新的连接，ip:{},端口:{}".format(address[0], address[1]))
            # 更新用户列表
            self.clients.append({"client": client, "username": "", "status": "connect"})
            # 创建子线程处理本次连接
            t = Work(self.update_clients, client, self.db)
            # 将该线程加入线程列表
            self.threads.append(t)
            # 开始子线程
            t.start()
