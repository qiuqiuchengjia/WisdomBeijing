package com.qiu.Base.menuDetail;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiu.Activity.R;
import com.qiu.Base.BaseMenuDetailPager;
import com.qiu.Base.implement.TabDetailPager;
import com.qiu.domian.NewsData;

import java.util.ArrayList;

/**
 * Description: 菜单详情页-新闻
 * Data：2016/8/23-16:25
 * Blog：www.qiuchengjia.cn
 * Author: qiu
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager{
    private ViewPager mViewPager;
    private  ArrayList<TabDetailPager>  mViewPagerList;
    private ArrayList<NewsData.NewsTabData> mNewTabData;//页签网络数据
    /**
     * 构造函数
     * @param activity the activity
     * @param children
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    public NewsMenuDetailPager(Activity activity, ArrayList<NewsData.NewsTabData> children) {
        super(activity);
        mNewTabData=children;
    }

    /**
     * 初始化界面
     * @return the view
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    @Override
    public View initViews() {
        View view=View.inflate(mActivity, R.layout.news_menu_detail,null);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_menu_detail);
        return view;
    }

    /**
     * 初始化数据
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    @Override
    public void initData() {
        mViewPagerList = new ArrayList<TabDetailPager>();

        //初始化页签数据
        for(int i=0;i<mNewTabData.size();i++){
            TabDetailPager pager = new TabDetailPager(mActivity,mNewTabData.get(i));
            mViewPagerList.add(pager);
        }
        mViewPager.setAdapter(new MenuDetailAdapter());//设置适配器
    }
    /**
     * Description: 设置数据适配器
     * Blog: www.qiuchengjia.cn
     * Data: 2016/8/23 20:14
     * @author: qiu
     */
    class MenuDetailAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return mViewPagerList.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager pager = mViewPagerList.get(position);
            container.addView(pager.mRootView);
            pager.initData();
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView((View) object);
        }
    }
}
