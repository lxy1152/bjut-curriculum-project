# Author：Xiangyu Li
# Email: lxy_jdsy@163.com
# Time：2019/7/1 17:34
# Name: security.py
# Description:

from hashlib import md5
from Crypto.Cipher import AES
from binascii import b2a_hex, a2b_hex


class Security:
    def __init__(self, key):
        self.key = self.normalize(key)
        self.key = bytes(self.key, encoding="utf-8")
        self.mode = AES.MODE_CBC

    @staticmethod
    def normalize(s):
        length = len(s)
        if length % 16 == 0:
            add = 0
        else:
            add = 16 - (length % 16)
        return s + ('\0' * add)

    # 加密函数，text的长度必须16的倍数，如果不是，在这里要补齐
    def encrypt(self, text):
        text = bytes(self.normalize(text), encoding="utf-8")
        cryptor = AES.new(self.key, self.mode, b'0000000000000000')
        cipher_text = cryptor.encrypt(text)
        return b2a_hex(cipher_text).decode()

    # 解密后，要去掉空格
    def decrypt(self, text):
        text = bytes(self.normalize(text), encoding="utf-8")
        cryptor = AES.new(self.key, self.mode, b'0000000000000000')
        plain_text = cryptor.decrypt(a2b_hex(text)).decode("utf-8")
        return plain_text.rsplit('\0')[0]


def md5_check(text):
    m = md5()
    m.update(text.encode('utf-8'))
    return m.hexdigest()


if __name__ == '__main__':
    security = Security("f393e1cc99a211e992da0c5415b219ab")
    uid = security.encrypt("lxy123")
    pwd = security.encrypt("password")
    print("decrypt: %s" % uid)
    print("decrypt: %s" % pwd)
