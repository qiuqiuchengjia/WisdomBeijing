package com.qiu.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Description: 头条新闻的ViewPager
 * Data：2016/8/25-20:17
 * Blog：www.qiuchengjia.cn
 * Author: qiu
 */
public class TopNewsViewPager extends ViewPager{
    public TopNewsViewPager(Context context) {
        super(context);
    }

    public TopNewsViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 事件分发，请求父控件祖宗控件是否拦截事件
     * @param ev the ev
     * @return the boolean
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-25
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);//用getParent去请求
        return super.dispatchTouchEvent(ev);
    }
}
