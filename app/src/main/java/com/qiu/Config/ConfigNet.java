package com.qiu.Config;

/**
 * 作者：qiu
 * 时间：2016/7/3:21:58
 * 博客：www.qiuchengjia.cn
 * 说明：网络config，用来存放与网络有关的配置
 */
public class ConfigNet {
    public static final String SERVER_URL="https://qiuqiuchengjia.github.io/";//服务器的URL
    public static final String SERVER_FILE_NAME="WisdomBeijing/";//智慧北京服务器的文件夹
    public static final String CATEGORIES_URL=SERVER_URL+SERVER_FILE_NAME+"categories.json";//获取主页面新闻中心内容分类信息
    public static String SERVER_URL_NAME=SERVER_URL+SERVER_FILE_NAME;//网络请求前缀URL
}
