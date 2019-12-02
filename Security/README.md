# 远程安全管理系统

![env](https://img.shields.io/badge/env-windows-green)
![version](https://img.shields.io/badge/version-1.0-green)
![license](https://img.shields.io/github/license/lxy1152/SecurityWathcer)
![author](https://img.shields.io/badge/author-XiangyuLi-green)

## 展示

![屏幕截图](/Security/screenshots.png)

## 目标

提供一个支持如下功能的远程管理程序：

1. 远程关闭指定进程
2. 远程清理系统垃圾文件
3. 远程屏蔽或重定向某个网站
4. 远程关闭计算机
5. 远程启动或关闭指定的某个服务
6. IP-MAC绑定
7. 查看网络状态并输出为TXT和CSV文件
8. 测试带宽
9. 关闭或复位网络适配器
10. 查看进程状态并输出为TXT和CSV文件

## 环境

默认认为你是在Windows电脑上，远程管理Linux服务器中的内容。如果你的本地环境也是Linux环境请自行修改代码。

因为使用Shell脚本，所以请提前在你的电脑上安装Git Bash或babun或其他的软件，以确保你可以正确的执行脚本。

## 快速开始

执行如下代码：

```
git clone https://github.com/lxy1152/SecurityWatcher.git ./security_watcher/
cd security_watcher/
./run.sh
```

## 目录与文件说明

### 目录结构说明

* /functions：所有提供的功能都应在这个目录中进行实现
* /utils：提供了在实现功能中常用的一些方法
* /output：输出文件目录

### 根目录文件说明

* run.sh：执行该脚本以开始程序
* welcome.sh
  * 程序欢迎页，推荐自行修改banner
  * 配置远程连接参数，包括用户名、端口以及服务器IP地址
* menu.sh：提供了一种通用的创建的主菜单的方式，它可以自行扫描functions目录下的文件来动态创建主菜单，详细使用说明请查看该文件

### utils目录文件说明

* function_menu.sh：提供了一种通用的创建功能菜单的方式
* function_tool.sh：提供了功能执行过程中可能用到的操作
* string.sh：提供了字符串替换字符的操作
* validity_check.sh：提供了一些常用的合法性检测，如检查目录是否存在，输入是否为数字等

### functions目录文件说明

该目录下的文件均为所提供的功能，文件命名方式应为：`xx_xx.sh`。如：`find_file.sh`。

## 许可

基于[MIT](LICENSE)许可开源



