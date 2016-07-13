package com.qiu.Activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.qiu.Config.ConfigUI;
import com.qiu.Fragment.ContentFragment;
import com.qiu.Fragment.LeftMenuFragment;
import com.qiu.utils.ScreenUtils;

/**
 * 作者：qiu
 * 时间：2016/7/2:23:10
 * 博客：www.qiuchengjia.cn
 * 说明： 主界面
 */
public class MainActivity extends SlidingActivity {
    private static final String FRAGMENT_LEFT_MENU="fragment_left_menu";
    private static final String FRAGMENT_CONTENT="fragment_content";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题栏
        setContentView(R.layout.activity_main);
        //设置侧边栏
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();//获取侧边栏对象
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置全屏触摸
        slidingMenu.setBehindOffset((int) (ScreenUtils.getWidth(MainActivity.this) *
                ConfigUI.SlidingMenuBehindOffset));//设置侧边栏预留宽度
        initFragment();//初始化fragment
    }

    /**
     * 初始化fragment,将fragment数据填充给布局文件
     * @author qiu 时间：2016-07-03 21-37
     */
    private void initFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();//开启事务
        //用fragment替换frameLayout
        transaction.replace(R.id.fl_left_menu,new LeftMenuFragment(),FRAGMENT_LEFT_MENU);
        transaction.replace(R.id.fl_content,new ContentFragment(),FRAGMENT_CONTENT);
        transaction.commit();//提交事务
    }

    /**
     * 用来获取LeftMenuFragment，也就是获取侧边栏对象
     * @return left menu fragment
     * @author qiu 时间：2016-07-13 14-29
     */
    public LeftMenuFragment getLeftMenuFragment(){
        FragmentManager fragmentManager = getFragmentManager();
       LeftMenuFragment fragment = (LeftMenuFragment) fragmentManager.findFragmentByTag(FRAGMENT_LEFT_MENU);
        return fragment;
    }
}
