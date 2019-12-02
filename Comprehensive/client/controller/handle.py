# Author：Xiangyu Li
# Email: lxy_jdsy@163.com
# Time：2019/7/1 17:24
# Name: handle.py
# Description: 数据处理

import json
from urllib import request
from configparser import ConfigParser

from lib.security import md5_check, Security


class Handle(object):
    def __init__(self, **kwargs):
        self.img_64 = ""
        self.uid = ""
        self.pwd = ""
        self.rtype = ""
        self.security = Security("f393e1cc99a211e992da0c5415b219ab")
        self.cfg = ConfigParser()
        self.cfg.read("config/config.cfg")

    def set_params(self, **kwargs):
        self.img_64 = kwargs.get('img_64', "")
        self.uid = kwargs.get('uid', "")
        if not self.img_64:
            self.pwd = kwargs['pwd']
            self.rtype = kwargs['rtype']

    def request_user(self):
        uid = self.security.encrypt(self.uid)
        pwd = self.security.encrypt(self.pwd)
        m = md5_check("%s%s%s" % (self.uid, self.pwd, self.rtype))
        url = "http://%s:%s%s?UID=%s&Pwd=%s&Type=%s&Check=%s" % (
            self.cfg.get('server', 'host'),
            self.cfg.get('server', 'port'),
            self.cfg.get('server', 'user_route'),
            uid,
            pwd,
            self.rtype,
            m
        )
        print(url)
        response = request.urlopen(url)
        data = response.read()
        print(type(data))
        print(data)
        result = json.loads(data)
        return result['Result'], result['Type']

    def request_image(self):
        uid = self.security.encrypt(self.uid)
        img_64 = self.security.encrypt(self.img_64.decode('utf-8'))
        m = md5_check("%s%s" % (self.uid, self.img_64))
        url = "http://%s:%s%s?ImageBase64=%s&UID=%s&Check=%s" % (
            self.cfg.get('server', 'host'),
            self.cfg.get('server', 'port'),
            self.cfg.get('server', 'img_route'),
            # self.img_64.decode('utf-8'),
            img_64,
            uid,
            m
        )
        response = request.urlopen(url)
        return json.loads(response.read().decode('utf-8'))
