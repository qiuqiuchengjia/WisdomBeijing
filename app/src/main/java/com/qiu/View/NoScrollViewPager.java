package com.qiu.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作者：qiu
 * 时间：2016/7/4:14:18
 * 博客：www.qiuchengjia.cn
 * 说明：自定义的ViewPager，用来禁用ViewPager的OnTouchEvent事件
 * 就是不能左右滑动的ViewPager
 */
public class NoScrollViewPager extends ViewPager{

    /**
     * 重写onTouchEvent方法，用来拦截Touch事件
     * @param ev 触摸事件
     * @return boolean 永远返回false
     * @author qiu 时间：2016-07-04 14-19
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    /**
     * 表示事件是否拦截,返回false表示不拦截
     * @param ev the ev
     * @return the boolean
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
