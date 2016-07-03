package com.qiu.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 作者：qiu
 * 时间：2016/7/3:20:50
 * 博客：www.qiuchengjia.cn
 * 说明：屏幕工具类
 */
public class ScreenUtils {
    /**
     * 用来获取屏幕的宽度
     * @param context 上下文对象
     * @return int 屏幕宽度的值
     * @author qiu 时间：2016-07-03 20-55
     */
    public static int getWidth(Context context){
      WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
      return windowManager.getDefaultDisplay().getWidth();
  }

    /**
     * 用来获取屏幕的高度
     * @param context 上下文对象
     * @return int 屏幕高度的值
     * @author qiu 时间：2016-07-03 20-55
     */
    public static int getHeight(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getHeight();
    }
}
