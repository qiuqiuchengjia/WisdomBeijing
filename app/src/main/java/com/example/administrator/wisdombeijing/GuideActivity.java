package com.example.administrator.wisdombeijing;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;

/**
*  新手引导页
* @author qiu
* create at 2016/6/30 15:01
*/
public class GuideActivity extends Activity {
    private ArrayList<ImageView> imageViewArrayList;
    private static final int[] mInageIds = new int[]{R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        initViews();
        viewPager = (ViewPager) findViewById(R.id.vp_guide);
        viewPager.setAdapter(new GuideAdapter());
    }

   /**
   *  初始化界面
   * @author qiu
   * create at 2016/6/30 15:01
   */
    private void initViews() {
        imageViewArrayList = new ArrayList<ImageView>();
        //初始化引导页的3个页面
        for (int i = 0; i < mInageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(mInageIds[i]);//设置引导页背景
            imageViewArrayList.add(imageView);
        }
    }

    /**
    *  viewPager的适配器
    * @author qiu
    * create at 2016/6/30 15:01
    */
    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mInageIds.length;
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
}
