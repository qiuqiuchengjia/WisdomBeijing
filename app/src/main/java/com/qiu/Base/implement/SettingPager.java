package com.qiu.Base.implement;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.qiu.Activity.R;
import com.qiu.Base.BasePager;

/**
 * 作者：qiu
 * 时间：2016/7/4:13:29
 * 博客：www.qiuchengjia.cn
 * 说明：设置内容页面，也是BasePager的子类实现
 */
public class SettingPager extends BasePager {
    public SettingPager(Activity activity) {
        super(activity);
    }

    /**
     * 初始化数据
     * @author qiu 时间：2016-07-04 13-30
     */
    @Override
    public void initData() {
        tv_title.setText(R.string.setting_pager_title);
        imageButton.setVisibility(View.GONE);//隐藏菜单按钮
        setSlidingMenuEnable(false);//设置侧边栏不可用
        TextView textView = new TextView(mActivity);
        textView.setText("设置");
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        //像fragment中添加动态布局
        fl_content.addView(textView);
    }
}
