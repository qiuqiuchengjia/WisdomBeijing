package com.qiu.Base.implement;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.viewpagerindicator.CirclePageIndicator;

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
    private CirclePageIndicator mIndicator;//头条新闻位置指示器
    private ListView lvList;//新闻列表
    private NewAdapter newAdapter;
    private  ArrayList<TabData.TabNewsData> mNewList;//新闻数据集合
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
        mIndicator= (CirclePageIndicator) view.findViewById(R.id.indicator);
        lvList= (ListView) view.findViewById(R.id.lv_list);
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
        mNewList = mTabDetailData.data.news;
        if(topnews!=null){
            mViewPager.setAdapter(new TopNewsAdapter());
            mIndicator.setViewPager(mViewPager);
            mIndicator.setSnap(true);//支持快照显示
            mIndicator.setOnPageChangeListener(this);
            mIndicator.onPageSelected(0);//让指示器重新定位到第一个点
            mTextView.setText(topnews.get(0).title);//头条新闻标题
        }
        //填充新闻列表数据
        if(mNewList!=null){
            newAdapter = new NewAdapter();
            lvList.setAdapter(newAdapter);
        }

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
     * Description: 新闻列表适配器
     * Blog: www.qiuchengjia.cn
     * Data: 2016/9/4 13:05
     * @author: qiu
     */
    class NewAdapter extends BaseAdapter{
        private  BitmapUtils utils;
        public NewAdapter(){
            utils = new BitmapUtils(mActivity);
            utils.configDefaultLoadingImage(R.mipmap.pic_item_list_default);
        }
        @Override
        public int getCount() {
            return mNewList.size();
        }
        @Override
        public Object getItem(int position) {
            return mNewList.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){//加载view
                convertView=View.inflate(mActivity,R.layout.list_news_item,null);
                holder=new ViewHolder();
                holder.ivPic= (ImageView) convertView.findViewById(R.id.iv_pic);
                holder.tvData= (TextView) convertView.findViewById(R.id.tv_date);
                holder.tvTitle= (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            TabData.TabNewsData item = (TabData.TabNewsData) getItem(position);
            holder.tvTitle.setText(item.title);
            holder.tvData.setText(item.pubdata);
            utils.display(holder.ivPic,ConfigNet.SERVER_URL_NAME+item.listimage);
            return convertView;
        }
    }
    /**
     * Description: NewAdapter适配器的ViewHolder
     * Blog: www.qiuchengjia.cn
     * Data: 2016/9/6 19:07
     * @author: qiu
     */
    static class ViewHolder{
        public  TextView tvTitle;
        public  TextView tvData;
        public  ImageView ivPic;
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
