# coding=utf8
import json
from hashlib import md5
from urllib import request, parse
import random


class Translator:
    def __init__(self, appid, key):
        self.appid = appid
        self.key = key

    def baidu_translate(self, text):
        myurl = '/api/trans/vip/translate'
        q = text
        fromLang = 'auto'
        toLang = 'zh'
        salt = random.randint(32768, 65536)

        sign = self.appid + q + str(salt) + self.key
        m1 = md5()
        m1.update(sign.encode('utf-8'))
        sign = m1.hexdigest()
        myurl = myurl + '?appid=' + self.appid + '&q=' + parse.quote(
            q) + '&from=' + fromLang + '&to=' + toLang + '&salt=' + str(
            salt) + '&sign=' + sign
        try:
            response = request.urlopen('http://api.fanyi.baidu.com' + myurl)
            data = response.read().decode('utf-8')
            normal = data.encode('utf-8').decode('unicode_escape')
            print(normal)
            return json.loads(normal)['trans_result'][0]['dst']
        except Exception as e:
            print(e)


if __name__ == '__main__':
    print(baidu_translate("apple"))
