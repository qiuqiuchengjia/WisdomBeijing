package com.qiu.Fragment;

import android.view.View;
import android.widget.RadioGroup;

import com.qiu.Activity.R;

/**
 * 作者：qiu
 * 时间：2016/7/3:21:27
 * 博客：www.qiuchengjia.cn
 * 说明： 主页面内容
 */
public class ContentFragment extends BaseFragment {
    private RadioGroup radioGroup;
    /**
     * 初始化布局
     * @return view
     * @author qiu 时间：2016-07-03 21-27
     */
    @Override
    public View initViews() {
        View view=  View.inflate(mActivity, R.layout.fragment_content_menu,null);
        radioGroup= (RadioGroup) view.findViewById(R.id.rg_group);
        return view;
    }

    /**
     * 初始化数据
     * @author qiu 时间：2016-07-03 23-40
     */
    @Override
    public void initDate() {
        radioGroup.check(R.id.rb_home);//默认勾选首页
        super.initDate();
    }
}
