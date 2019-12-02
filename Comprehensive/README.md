#  软件项目综合设计课程设计

## 项目名称

利用现有图像文字识别及翻译软件的语言翻译软件

## 截图

![演示](/Comprehensive/example/example.gif)

## 开发目的

旨在不通过软件解包的情况下，识别用户所选定的区域的图片中的文字并将其翻译，最终将结果显示在用户屏幕上。

## 环境

* 服务器：Python3.5.0

* 客户端：Python3.7.3

## 其他库依赖

* pycryptodome：AES加密
* pyqt：qt图形界面
* qtawesome：图标字体
* flask：服务器
* tencentcloud-sdk-python：腾讯云提供的sdk

## 第三方接口调用

* 文字识别使用了腾讯云提供的[通用印刷体识别API](https://cloud.tencent.com/document/product/866/33526)
* 文字翻译使用了百度翻译的[通用翻译API](http://api.fanyi.baidu.com/api/trans/product/apidoc)

## 使用

1. 服务器的使用
   * 将server目录下的内容拷贝到你的本地或者服务器上
   * 修改config.cfg中的IP与端口信息
   * 在腾讯云与百度翻译上购买或申请服务，获得id与key
   * 修改ocr.py和translate_fullversion.py中的appid与key
   * 使用`python main.py`即可启动服务器
2. 客户端的使用
   * 请确保在服务器启动的情况下再启动客户端
   * 修改config/config.cfg中的IP与端口信息
   * 使用`python main.py`启动客户端
   * 在第一次登陆时需要注册，之后即可正常登录
   * 在登录后会出现一个工具栏，三个按钮的功能如下：
     * 最左侧的矩形图标:white_large_square:：选定截图区域
     * 中间的放大镜图标:mag:：识别文字与翻译文字并将翻译后的信息显示在屏幕上（默认为白色字体）
     * 右侧的关闭图标:x:：关闭软件
   * 请确保选定区域后再进行识别与翻译
   * 按:x:关闭软件​
