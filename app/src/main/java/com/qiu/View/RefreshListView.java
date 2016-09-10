package com.qiu.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qiu.Activity.R;

/**
 * Description: 下拉刷新的ListView
 * Data：2016/9/6-20:22
 * Blog：www.qiuchengjia.cn
 * Author: qiu
 */
public class RefreshListView extends ListView{
    private  static final int STATE_PULL_PEFRSH=0;//下拉刷新
    private  static final int STATE_RELEASE_PEFRSH=1;//松开刷新
    private  static final int STATE_PEFRSHING=2;//正在刷新
    private   int startY=-1;//滑动起点的Y坐标
    private int mCurrentState=STATE_PULL_PEFRSH;//当前的状态
    private     View mHeaderView;
    private  int measuredHeight;
    private RotateAnimation animUp;
    private RotateAnimation animDowm;
    private TextView tvTitle;
    private TextView tvTime;
    private ImageView ivArrow;
    private ProgressBar pbProgress;
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

        mHeaderView = View.inflate(getContext(), R.layout.refresh_header,null);
        this.addHeaderView(mHeaderView);
        mHeaderView.measure(0,0);
        tvTitle = (TextView) mHeaderView.findViewById(R.id.tv_title);
        tvTime = (TextView) mHeaderView.findViewById(R.id.tv_time);
        ivArrow = (ImageView) mHeaderView.findViewById(R.id.iv_arr);
        pbProgress = (ProgressBar) mHeaderView.findViewById(R.id.pb_progress);
        // 测量mHeaderView的高度
        measuredHeight = mHeaderView.getMeasuredHeight();
        mHeaderView.setPadding(0,-measuredHeight,0,0);//隐藏头布局
        initArrowAnim();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(startY==-1){//确保startY的值是有效的
                    startY = (int) ev.getRawY();
                }
                if(mCurrentState==STATE_PEFRSHING){//正在刷新时不做处理
                    break;
                }
                int endY= (int) ev.getRawY();
                int dy = endY-startY;//移动偏移量
                if(dy>0 && getFirstVisiblePosition()==0){//只有下拉并且当前是第一个item，才允许下拉
                    int padding= dy-measuredHeight;
                    mHeaderView.setPadding(0,padding,0,0);//设置当前padding

                    if(padding>0 && mCurrentState!=STATE_RELEASE_PEFRSH){//将状态改为松开刷新
                        mCurrentState=STATE_RELEASE_PEFRSH;
                        refreshState();
                    }else if(padding<0 && mCurrentState!=STATE_PULL_PEFRSH){//改为下拉刷新状态
                        mCurrentState=STATE_PULL_PEFRSH;
                        refreshState();
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                startY=-1;//重置
                if(mCurrentState==STATE_RELEASE_PEFRSH){
                    mCurrentState=STATE_PEFRSHING;//正在刷新
                    mHeaderView.setPadding(0,0,0,0);//显示
                    refreshState();
                }else if(mCurrentState==STATE_PULL_PEFRSH){
                    mHeaderView.setPadding(0,-measuredHeight,0,0);//隐藏
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 刷新下拉控件布局
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-09-10
     */
    private void refreshState() {
        switch (mCurrentState){
            case STATE_PULL_PEFRSH:
                tvTitle.setText("下拉刷新");
                ivArrow.startAnimation(animDowm);
                ivArrow.setVisibility(View.VISIBLE);
                pbProgress.setVisibility(View.INVISIBLE);

                break;
            case STATE_RELEASE_PEFRSH:
                tvTitle.setText("松开刷新");
                ivArrow.startAnimation(animUp);
                ivArrow.setVisibility(View.VISIBLE);
                pbProgress.setVisibility(View.INVISIBLE);

                break;
            case STATE_PEFRSHING:
                tvTitle.setText("正在刷新");
                ivArrow.clearAnimation();//必须先清除动画才能隐藏
                ivArrow.setVisibility(View.INVISIBLE);
                pbProgress.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 初始化箭头动画
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-09-10
     */
    private  void initArrowAnim(){
        //箭头向上的动画

        animUp = new
                RotateAnimation(0,-180, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animUp.setDuration(200);
        animUp.setFillAfter(true);

        //箭头向下的动画

        animDowm = new
                RotateAnimation(-180,0, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animDowm.setDuration(200);
        animDowm.setFillAfter(true);
    }


}
