"""
@Name: work.py
@Author: lxy
@Date: 2019/5/10 11:19
@Description: 子线程，处理用户请求
@Contact: lxy_jdsy@163.com
"""
import time
from threading import Thread


class Work(Thread):
    def __init__(self, update_clients, client, db):
        Thread.__init__(self)
        # 设置回调函数
        self.update = update_clients
        # 本次连接的用户
        self.client = client
        # 用户列表
        self.clients = []
        # 数据库
        self.db = db
        # 本次连接用户的用户名
        self.username = ""

    def run(self) -> None:
        while True:
            # 获取登陆信息字典
            login_info = eval(self.client.recv(1024).decode())
            # 判断请求类型
            if login_info['type'] == "register":
                # 查找是否有重名用户
                res = self.db.select("user", params=[
                    "username='" + login_info["username"] + "'"
                ])
                # 如果没有
                if len(res) == 0:
                    # 新建用户
                    ins = self.db.insert("user", [login_info["username"], login_info["password"]],
                                         ["username", "password"])
                    # 处理插入结果
                    if ins == "SUCCESS":
                        self.client.send(bytes(ins, encoding="utf-8"))
                    else:
                        self.client.send(bytes("UNKNOWN ERROR", encoding="utf-8"))
                # 如果存在同名
                else:
                    self.client.send(bytes("MORE THAN TWO??", encoding="utf-8"))
            elif login_info["type"] == "login":
                # 查找是否有相关用户
                res = self.db.select("user", params=[
                    "username='" + login_info["username"] + "'",
                    "password='" + login_info["password"] + "'"
                ])
                # 如果没有
                if len(res) == 0:
                    self.client.send(bytes("NO SUCH RECORD", encoding="utf-8"))
                # 如果有
                elif len(res) == 1:
                    self.client.send(bytes("SUCCESS", encoding="utf-8"))
                    self.username = login_info["username"]
                    break
                # 如果有多个，应该不会有这种情况发生
                else:
                    self.client.send(bytes("MORE THAN TWO??", encoding="utf-8"))

        # 休眠0.1s，否则客户端处理不过来，下同
        time.sleep(0.1)
        # 回调
        self.update("update", login_info["username"], "login", self.client)
        time.sleep(0.1)

        # 获取历史消息
        history_record = self.db.select("record", ["message", "username"])
        msg = {"type": "message"}
        for hr in history_record:
            # 消息组装并发送
            msg["username"] = hr[0]
            msg["data"] = hr[1]
            if hr[0] == self.username:
                msg["alignment"] = "right"
            else:
                msg["alignment"] = "left"
            self.client.send(bytes(str(msg), encoding="utf-8"))
            time.sleep(0.1)

        # 接受客户端发送的消息
        while True:
            try:
                # 因为发送的数据的类型为bytes，所以先执行decode解码返回string类型数据
                data = self.client.recv(1024).decode()
                # 如果数据空，即连接中断
                if not data:
                    print("结束当前连接..")
                    break
                # 组装消息
                msg = {"type": "message", "username": self.username, "data": data}
                # 对每一个客户机发送消息
                for client in self.clients:
                    # 如果这条消息是别人发的，那么对齐方式为“left”，否则为“right”
                    if client["client"] is not self.client:
                        msg["alignment"] = "left"
                    else:
                        msg["alignment"] = "right"
                    client["client"].send(bytes(str(msg), encoding="utf-8"))
                # 向数据库中新增聊天记录
                self.db.insert("record", [data, self.username], ["username", "message"])
            # 处理异常
            except ConnectionResetError:
                print("客户机异常，结束连接...")
                break
        # 连接中断， 回调
        self.update("delete", self.username, "delete", self.client, self)

    # 更新用户列表
    def update_clients(self, clients):
        self.clients = clients
