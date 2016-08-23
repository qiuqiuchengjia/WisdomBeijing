package com.qiu.Base.menuDetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.qiu.Base.BaseMenuDetailPager;

/**
 * Description: 菜单详情页-新闻专题
 * Data：2016/8/23-16:25
 * Blog：www.qiuchengjia.cn
 * Author: qiu
 */
public class TopicMenuDetailPager extends BaseMenuDetailPager{
    /**
     * 构造函数
     * @param activity the activity
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    public TopicMenuDetailPager(Activity activity) {
        super(activity);
    }

    /**
     * 初始化界面
     * @return the view
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    @Override
    public View initViews() {
        TextView textView = new TextView(mActivity);
        textView.setText("菜单详情页-新闻专题");
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
