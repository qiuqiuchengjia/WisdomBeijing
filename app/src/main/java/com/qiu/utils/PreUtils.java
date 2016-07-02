package com.qiu.utils;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * 作者：qiu
 * 时间：2016/7/2:23:39
 * 博客：www.qiuchengjia.cn
 * 说明： SharedPreferences的工具封装
 */
public class PreUtils {
    public static final String PRE_NAME ="config";
    /**
     * 用来存储或者更新在SharedPreferences的boolean值
     * @param context 上下文对象
     * @param key 存储的key
     * @param value 需要存储的值
     * @author qiu 时间：2016-07-02 23-47
     */
    public static  void setBoolean(Context context,String key,boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PRE_NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key,value).commit();
    }
    /**
     * 用来获取存储在SharedPreferences的boolean值
     * @param context 上下文对象
     * @param key 存储的key
     * @param defaultValue 默认的值
     * @return boolean 返回存储的值
     * @author qiu 时间：2016-07-02 23-44
     */
    public static boolean getBoolean(Context context,String key,boolean defaultValue){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        boolean userGuide = sharedPreferences.getBoolean(key,defaultValue);
        return userGuide;
    }
}
