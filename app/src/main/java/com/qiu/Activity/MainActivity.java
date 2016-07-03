package com.qiu.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.qiu.Config;
import com.qiu.utils.ScreenUtils;

/**
 * 作者：qiu
 * 时间：2016/7/2:23:10
 * 博客：www.qiuchengjia.cn
 * 说明： 主界面
 */
public class MainActivity extends SlidingActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置侧边栏
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();//获取侧边栏对象
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置全屏触摸
        slidingMenu.setBehindOffset((int) (ScreenUtils.getWidth(MainActivity.this) *
                Config.SlidingMenuBehindOffset));//设置侧边栏预留宽度
    }
}
