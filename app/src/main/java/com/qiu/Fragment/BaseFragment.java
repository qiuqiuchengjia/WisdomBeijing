package com.qiu.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：qiu
 * 时间：2016/7/3:21:12
 * 博客：www.qiuchengjia.cn
 * 说明：fragment基类
 */
public abstract class BaseFragment extends Fragment{
    public Activity mActivity;
    //fragment被创建
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }
    //处理fragment的布局
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return initViews();
    }
   //依附的activity创建完成
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();//初始化数据
    }
    /**
     * 子类必须实现初始化布局的方法
     * @return view
     * @author qiu 时间：2016-07-03 21-22
     */
    public abstract View initViews();

    /**
     * 初始化数据,可以不实现
     * @author qiu 时间：2016-07-03 21-23
     */
    public void initDate(){

    }
}
