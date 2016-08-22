package com.qiu.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.qiu.utils.PreUtils;

import java.util.ArrayList;

/**
 * 新手引导页
 * 时间：2016/7/2 17:55
 * 博客：www.qiuchengjia.cn
 * @author qiuqiu
 */
public class GuideActivity extends Activity {

    private ArrayList<ImageView> imageViewArrayList;
    private static final int[] mImageIds = new int[]{R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private ViewPager viewPager;
    private LinearLayout llPointGroup;
    private int mPointWidth;//圆点之间的距离
    private View viewRedPoint;//小红点
    private Button btnStart;//开始体验按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        //引导圆点的父控件
        llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
        viewRedPoint = findViewById(R.id.view_red_point);
        btnStart = (Button) findViewById(R.id.bt_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新sp,表示已经展示了新手引导页
                 PreUtils.setBoolean(GuideActivity.this,"is_user_guide_showed",true);
                //跳转到主页面
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
        initViews();
        viewPager = (ViewPager) findViewById(R.id.vp_guide);
        viewPager.setAdapter(new GuideAdapter());
        viewPager.setOnPageChangeListener(new GuidePageListener());
    }

    /**
     * 初始化界面
     * 时间：2016/7/2 17:59
     * 博客：www.qiuchengjia.cn
     * @author qiu
     */
    private void initViews() {
        imageViewArrayList = new ArrayList<ImageView>();
        //初始化引导页的3个页面
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(mImageIds[i]);//设置引导页背景
            imageViewArrayList.add(imageView);
        }
        //初始化引导页的小圆点
        for (int i = 0; i < mImageIds.length; i++) {
            View point = new View(this);
            //设置引导页默认圆点
            point.setBackgroundResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            if (i > 0) {
                params.leftMargin = 10;//设置圆点间隔
            }
            //设置引导圆点的大小
            point.setLayoutParams(new LinearLayout.LayoutParams(10, 10));
            llPointGroup.addView(point);//将圆点添加给线性布局
        }
        //获取视图树,对layout结束事件进行监听
        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //当layout执行结束后回调此方法
            @Override
            public void onGlobalLayout() {
                //将自己移除
                llPointGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mPointWidth = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();
            }
        });
    }

    /**
     * viewPager的适配器
     * 时间：2016/7/2 23:16
     * 博客：www.qiuchengjia.cn
     * @author qiu
     */
    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViewArrayList.get(position));
            return imageViewArrayList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * viewPager滑动事件监听
     * 时间：2016/7/2 16:02
     * 博客：www.qiuchengjia.cn
     * @author qiu
     */
    class GuidePageListener implements ViewPager.OnPageChangeListener {

        // 滑动事件
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int len = (int) (mPointWidth * positionOffset) + position * mPointWidth;
            //获取小红点的布局参数
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewRedPoint.getLayoutParams();
            params.leftMargin = len;//设置左边距
            viewRedPoint.setLayoutParams(params);//重新给小红点设置布局参数
        }
        // 某个页面被选中
        @Override
        public void onPageSelected(int position) {
             if(position== mImageIds.length-1){//最后一个页面
                   btnStart.setVisibility(View.VISIBLE);
             }else{
                 btnStart.setVisibility(View.INVISIBLE);
             }
        }
        // 滑动状态发生变化
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
