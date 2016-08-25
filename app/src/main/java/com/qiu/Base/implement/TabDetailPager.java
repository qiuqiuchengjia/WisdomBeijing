package com.qiu.Base.implement;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qiu.Activity.R;
import com.qiu.Base.BaseMenuDetailPager;
import com.qiu.Config.ConfigNet;
import com.qiu.domian.NewsData;
import com.qiu.domian.TabData;

import java.util.ArrayList;

/**
 * Description: 页签详情页
 * Data：2016/8/23-20:16
 * Blog：www.qiuchengjia.cn
 * Author: qiu
 */
public class TabDetailPager extends BaseMenuDetailPager implements ViewPager.OnPageChangeListener{
    private NewsData.NewsTabData mTabData;
    private  TextView textView;
    private String mUrl;
    private  TabData mTabDetailData;
    private ViewPager mViewPager;
    private TextView mTextView;//头条新闻标题
    private    ArrayList<TabData.TopNewsData> topnews;//头条新闻数据列表集合
    /**
     * 构造函数
     * @param activity the activity
     * @param newsTabData
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    public TabDetailPager(Activity activity, NewsData.NewsTabData newsTabData) {
        super(activity);
        mTabData=newsTabData;
        mUrl= ConfigNet.SERVER_URL_NAME+mTabData.url;
    }

    @Override
    public View initViews() {
        View view= View.inflate(mActivity, R.layout.tab_detail_pager,null);
        mViewPager= (ViewPager) view.findViewById(R.id.vp_news);
        mTextView= (TextView) view.findViewById(R.id.tv_title);
        return view;
    }
    /**
     * 初始化数据
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    @Override
    public void initData() {
        //textView.setText(mTabData.title);
        getDataFromServer();
        mViewPager.setOnPageChangeListener(this);
    }

    private void getDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result=(String) responseInfo.result;
                Log.d("TAG","页签详情页返回结果："+result);
                parseData(result);
            }
            /**
             * 网络请求失败，打印Toast提示,在主线程中运行
             * @param e the e
             * @param s the s
             * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-25
             */
            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mActivity,s,Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    /**
     * 解析页签详情页数据
     * @param result the result
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-25
     */
    private void parseData(String result) {
        Gson gson = new Gson();
        mTabDetailData = gson.fromJson(result, TabData.class);
        Log.d("页签详情解析:",mTabDetailData.toString());
        topnews = mTabDetailData.data.topnews;
        mViewPager.setAdapter(new TopNewsAdapter());
        mTextView.setText(topnews.get(0).title);//头条新闻标题
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 头条新闻滑动事件处理，当页面滑动之后
     * @param position the position
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-25
     */
    @Override
    public void onPageSelected(int position) {
        mTextView.setText(topnews.get(position).title);//头条新闻标题
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * Description: 头条新闻的数据适配器
     * Blog: www.qiuchengjia.cn
     * Data: 2016/8/25 20:16
     * @author: qiu
     */
    class TopNewsAdapter extends PagerAdapter{
        private BitmapUtils bitmapUtils;
        public TopNewsAdapter(){
            bitmapUtils = new BitmapUtils(mActivity);
            bitmapUtils.configDefaultLoadingImage(R.mipmap.topnews_item_default);//设置默认图片

        }
        @Override
        public int getCount() {
            return mTabDetailData.data.topnews.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView= new ImageView(mActivity);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//基于控件大小去填充图片
            TabData.TopNewsData topNewsData = mTabDetailData.data.topnews.get(position);
            //传递imageView对象和图片地址
            bitmapUtils.display(imageView,ConfigNet.SERVER_URL_NAME+topNewsData.topimage);
            container.addView(imageView);
            return imageView;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
