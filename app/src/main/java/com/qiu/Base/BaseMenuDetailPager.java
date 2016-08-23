package com.qiu.Base;

import android.app.Activity;
import android.view.View;

/**
 * Description: 菜单详情页的基类
 * Data：2016/8/23-16:08
 * Blog：www.qiuchengjia.cn
 * Author: qiu
 */
public abstract class BaseMenuDetailPager {
    public Activity mActivity;
    public View mRootView;//根布局对象
    /**
     * 构造函数
     * @param activity the activity
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    public  BaseMenuDetailPager(Activity activity){
            mActivity=activity;
            mRootView=initViews();
    }

    /**
     * 初始化界面
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    public abstract  View initViews();

    /**
     * 初始化数据
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    public void initData(){

    }

}
