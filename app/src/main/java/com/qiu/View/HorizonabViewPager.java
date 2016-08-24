package com.qiu.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Description: 新闻中心中的新闻下的子页签水平滑动的ViewPager
 * Data：2016/8/24-21:25
 * Blog：www.qiuchengjia.cn
 * Author: qiu
 */
public class HorizonabViewPager extends ViewPager {
    /**
     * 事件分发,请求父控件及祖宗控件不要拦截事件
     * @param ev the ev
     * @return the boolean
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-24
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(getCurrentItem()!=0){
            getParent().requestDisallowInterceptTouchEvent(true);//用getParent去请求,不拦截
        }else{//如果是第一个页面，需要显示侧边栏，请求父控件拦截
            getParent().requestDisallowInterceptTouchEvent(false);//用getParent去请求，拦截
        }
        return super.dispatchTouchEvent(ev);
    }

    public HorizonabViewPager(Context context) {
        super(context);
    }

    public HorizonabViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
