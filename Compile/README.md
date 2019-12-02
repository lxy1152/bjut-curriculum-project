# 编译原理大作业

## 题目

C语言实现词语分析、语法分析以及三地址代码生成

## 截图

![](/Compile/example/taddr.jpg)

## 简述

1. 词法分析与语法分析使用flex和bison自动生成，三地址代码是我自己写的（但好像可以合并），flexbison的使用说明详见/doc/flex-bison_zh.pdf文件
2. 虽然是cpp文件，但是使用的是C语言​

## 环境

VisualStudio2017

## 使用

1. 编写你自己的myflex.l文件或者打开/src/ll.l文件，将`filein=fopen("filepath","r");`中的`filepath`修改为你自己的测试文件路径。编写你自己的mybison.y文件或者使用/src/analys.y文件
2. flex与bison在win_flex_bison-latest目录下，使用方法自己找吧，年代久远我也记不清了
3. 源码在src目录下，添加到你的工程中编译执行即可​

## 实验要求

实验要求详见/doc/requirements.pdf文件

## 测试

测试文件可以使用/test/test.txt文件或者参考实验要求中的样例