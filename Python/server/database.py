"""
@Name: database.py
@Author: lxy
@Date: 2019/5/15 15:48
@Description: 数据库相关
@Contact: lxy_jdsy@163.com
"""
# 使用sqlite数据库
import sqlite3


class Database:
    def __init__(self, database):
        # 获得连接，要设置check_same_thread为False，否则不能再多线程中使用
        self.conn = sqlite3.connect(database, check_same_thread=False)
        # 获得游标
        self.cursor = self.conn.cursor()
        # 创建用户表
        self.create_table("user", [
            "'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT",
            "'username' TEXT NOT NULL UNIQUE",
            "'password' TEXT NOT NULL"
        ])
        # 创建聊天记录表
        self.create_table("record", [
            "'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT",
            "'username' TEXT NOT NULL",
            "'message' TEXT",
            "'send_time' TIMESTAMP default (datetime('now', 'localtime'))"
        ])

    # 新建表，params 为相关参数，见上例
    def create_table(self, table_name, params):
        try:
            sql = "CREATE TABLE '" + table_name + "' (" + ", ".join(params) + ");"
            self.cursor.execute(sql)
        # 如果已经存在该表，则返回
        except sqlite3.OperationalError:
            return

    # 插入数据， params为相关参数， cparams为插入的列， 例子见subthread.py
    def insert(self, table_name, params, cparams=None):
        if cparams:
            sql = "INSERT INTO " + table_name + " (" + ", ".join(cparams) + ")"
        else:
            sql = "INSERT INTO " + table_name
        sql += " VALUES ('" + "','".join(params) + "')"
        self.cursor.execute(sql)
        # 一定要commit，否则不保存
        self.conn.commit()
        return "SUCCESS"

    # 查询数据， sparams为选择的列， params为条件, 例子见subthread.py
    def select(self, table_name, sparams=None, params=None):
        if sparams:
            sql = "SELECT " + ", ".join(sparams)
        else:
            sql = "SELECT * "
        sql += " from " + table_name
        if params:
            sql += " where " + " and ".join(params)
        # fetchall才是返回结果集，执行只是返回游标
        res = self.cursor.execute(sql).fetchall()
        return res

    # 销毁时断开数据库连接
    def __del__(self):
        try:
            if self.conn:
                self.conn.close()
            if self.cursor:
                self.cursor.close()
        except sqlite3.ProgrammingError:
            exit(0)