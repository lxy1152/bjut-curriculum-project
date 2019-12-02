from configparser import ConfigParser
from flask import Flask, request, Response, jsonify
from security import Security
import sqlite3
import hashlib
import translate_fullversion

app = Flask(__name__)
cp = ConfigParser()
cp.read("config.cfg")
security = Security("f393e1cc99a211e992da0c5415b219ab")

# 创建数据库，数据库名称为users
def create_sqlite():
    conn = sqlite3.connect("users.db")
    cursor = conn.cursor()
    # sql = 'create table users(UID string primary key, Pwd string not null);'
    sql = '''CREATE TABLE users
      (UID string primary key, 
       Pwd string)'''
    cursor.execute(sql)  # 创建表的命令
    cursor.close()
    conn.commit()
    conn.close()


# 获取用户名,密码,类型API
@app.route('/api/user/')
def main():
    # create_sqlite()                # 创建数据库
    uid_ = request.args.get("UID")  # 获取用户名uid
    pwd_ = request.args.get("Pwd")  # 获取密码pwd
    typ = request.args.get("Type")  # 获取类型typ
    check = request.args.get("Check")  # 获取校验码check

    
    uid = security.decrypt(uid_)
    pwd = security.decrypt(pwd_)

    # 如果是注册
    if typ == "Register":
        # 检查注册用户是否重复，添加用户名密码到数据库
        conn = sqlite3.connect('users.db')
        cursor = conn.cursor()
        data = conn.execute("select * from users where UID = '%s'" % uid).fetchone()
        if not data:  # 不重复，添加数据库，返回SUCCESS
            cursor.execute("INSERT INTO users (UID, Pwd) VALUES ('%s', '%s')" % (uid, pwd))
            conn.commit()
            conn.close()
            a = {
                'Result': "SUCCESS",
                'Type': "Register"
            }
            return jsonify(a)
        else:  # 重复，返回EXIST
            conn.commit()
            conn.close()
            b = {
                'Result': "EXIST",
                'Type': "Register"
            }
            return jsonify(b)

    # 如果是登录
    elif typ == "Login":
        # 检查登录用户是否存在
        conn = sqlite3.connect('users.db')
        cursor = conn.cursor()
        data = conn.execute("select * from users where UID = '%s'" % uid).fetchone()
        if not data:  # 用户不存在,返回FAIL
            conn.commit()
            conn.close()
            c = {
                'Result': "FAIL",
                'Type': "Login"
            }
            return jsonify(c)
        else:  # 用户存在，判断密码是否正确
            upwd = cursor.execute("select * from users where UID = '%s'" % uid)
            for row in upwd:
                if pwd == str(row[1]):
                    d = {
                        'Result': "SUCCESS",
                        'Type': "Login"
                    }
                    return jsonify(d)
                else:
                    e = {
                        'Result': "MISMATCH",
                        'Type': "Login"
                    }
                    return jsonify(e)


# 翻译API
@app.route('/api/image/')
def translation():
    b64_en = request.args.get("ImageBase64")
    b64_de = security.decrypt(b64_en)
    tran = translate_fullversion.translation(b64_de)
    return jsonify(tran)


if __name__ == '__main__':
    app.run(host=cp.get('flask', 'host'), port=cp.get('flask', 'port'), debug=cp.get('flask', 'debug'))
