package com.qiu.Base.implement;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.qiu.Activity.R;
import com.qiu.Base.BaseMenuDetailPager;
import com.qiu.domian.NewsData;

/**
 * Description: 页签详情页
 * Data：2016/8/23-20:16
 * Blog：www.qiuchengjia.cn
 * Author: qiu
 */
public class TabDetailPager extends BaseMenuDetailPager {
    private NewsData.NewsTabData mTabData;
    private  TextView textView;
    /**
     * 构造函数
     * @param activity the activity
     * @param newsTabData
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    public TabDetailPager(Activity activity, NewsData.NewsTabData newsTabData) {
        super(activity);
        mTabData=newsTabData;
    }

    @Override
    public View initViews() {
        View view= View.inflate(mActivity, R.layout.tab_detail_pager,null);
        return view;
    }
    /**
     * 初始化数据
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    @Override
    public void initData() {
        //textView.setText(mTabData.title);
    }
}
