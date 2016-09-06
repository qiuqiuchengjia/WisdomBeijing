package com.qiu.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.qiu.Activity.R;

/**
 * Description: 下拉刷新的ListView
 * Data：2016/9/6-20:22
 * Blog：www.qiuchengjia.cn
 * Author: qiu
 */
public class RefreshListView extends ListView{
    public RefreshListView(Context context) {
        super(context);
        initHeaderView();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeaderView();
    }
    /**
     * 初始化布局
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-09-06
     */
    private void initHeaderView() {
        View mHeaderView = View.inflate(getContext(), R.layout.refresh_header,null);
        this.addHeaderView(mHeaderView);
        mHeaderView.measure(0,0);
        int measuredHeight = mHeaderView.getMeasuredHeight();//测量mHeaderView的高度
        mHeaderView.setPadding(0,-measuredHeight,0,0);//隐藏头布局
    }

}
