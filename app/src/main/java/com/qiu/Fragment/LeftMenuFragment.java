package com.qiu.Fragment;

import android.view.View;

import com.qiu.Activity.R;

/**
 * 作者：qiu
 * 时间：2016/7/3:21:26
 * 博客：www.qiuchengjia.cn
 * 说明：侧边栏的fragment
 */
public class LeftMenuFragment extends BaseFragment {
    /**
     * 初始化布局
     * @return view
     * @author qiu 时间：2016-07-03 21-28
     */
    @Override
    public View initViews() {
        View view=  View.inflate(mActivity, R.layout.fragment_left_menu,null);
        return view;
    }
}
