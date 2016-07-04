package com.qiu.Base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qiu.Activity.R;

/**
 * 作者：qiu
 * 时间：2016/7/4:13:03
 * 博客：www.qiuchengjia.cn
 * 说明：主页面下五个子页面的基类
 */
public class BasePager {
    public  View rootView;//布局对象
    public Activity mActivity;
    public TextView tv_title;//标题对象
    public FrameLayout fl_content;//内容对象
    public BasePager(Activity activity){
        mActivity=activity;
        initViews();//初始化布局
    }
    /**
     * 初始化数据
     * @author qiu 时间：2016-07-04 13-05
     */
    public void initData(){

    }
    /**
     * 初始化布局
     * @author qiu 时间：2016-07-04 13-05
     */
    public void initViews(){
        rootView = View.inflate(mActivity, R.layout.base_pager,null);
        tv_title= (TextView) rootView.findViewById(R.id.tv_title);
        fl_content= (FrameLayout) rootView.findViewById(R.id.fl_content);
    }
}
