package com.qiu.Base.implement;


import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qiu.Activity.MainActivity;
import com.qiu.Activity.R;
import com.qiu.Base.BaseMenuDetailPager;
import com.qiu.Base.BasePager;
import com.qiu.Base.menuDetail.InteractMenuDetailPager;
import com.qiu.Base.menuDetail.NewsMenuDetailPager;
import com.qiu.Base.menuDetail.PhotoMenuDetailPager;
import com.qiu.Base.menuDetail.TopicMenuDetailPager;
import com.qiu.Config.ConfigNet;
import com.qiu.Fragment.LeftMenuFragment;
import com.qiu.domian.NewsData;

import java.util.ArrayList;

/**
 * 作者：qiu
 * 时间：2016/7/4:13:29
 * 博客：www.qiuchengjia.cn
 * 说明：新闻内容页面，也是BasePager的子类实现
 */
public class NewsCenterPager extends BasePager {
    private ArrayList<BaseMenuDetailPager> mPagers;//4个菜单详情页的集合
    private   NewsData data;
    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    /**
     * 初始化数据
     * @author qiu 时间：2016-07-04 13-30
     */
    @Override
    public void initData() {
        tv_title.setText(R.string.news_center_pager_title);
        setSlidingMenuEnable(true);//设置侧边栏可用
        TextView textView = new TextView(mActivity);
       // textView.setText("");//新闻中心
        //textView.setTextColor(Color.RED);
       // textView.setTextSize(25);
       // textView.setGravity(Gravity.CENTER);
        //像fragment中添加动态布局
        fl_content.addView(textView);
        getDataFromServer();//从服务器获取数据
    }

    /**
     * 从服务器获取网络数据
     *
     * @author qiu 时间：2016-07-04 23-04
     */
    private void getDataFromServer(){
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, ConfigNet.CATEGORIES_URL, new RequestCallBack<String>(){
            @Override
            public void onSuccess(ResponseInfo responseInfo) {
                Log.d("成功", (String) responseInfo.result);
                parseData((String) responseInfo.result);
            }
            @Override
            public void onFailure(HttpException error, String msg) {
                Toast.makeText(mActivity,"访问失败"+msg,Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
    }

    /**
     * 解析网络数据
     * @param result 需要解析的网络数据
     * @author qiu 时间：2016-07-10 18-53
     */
    protected void parseData(String result){
        Gson gson = new Gson();

        data = gson.fromJson(result, NewsData.class);
        Log.d("json数据",data.toString());
        //刷新侧边栏的数据
        MainActivity mainActivity = (MainActivity) mActivity;
        LeftMenuFragment leftMenuFragment = mainActivity.getLeftMenuFragment();
        leftMenuFragment.setMenuData(data);
        //准备4个菜单详情页的数据
        mPagers= new ArrayList<BaseMenuDetailPager>();
        mPagers.add(new NewsMenuDetailPager(mainActivity));
        mPagers.add(new TopicMenuDetailPager(mainActivity));
        mPagers.add(new PhotoMenuDetailPager(mainActivity));
        mPagers.add(new InteractMenuDetailPager(mainActivity));

        setCurrentMenuDetailPager(0);//设置菜单详情页-新闻为默认当前页
    }


    /**
     * 设置当前菜单详情页
     * @param position 当前要显示的菜单栏详情页
     * @author qiu  博客：www.qiuchengjia.cn 时间：2016-08-23
     */
    public void setCurrentMenuDetailPager(int position){
        BaseMenuDetailPager pager = mPagers.get(position);//获取当前要显示的菜单栏详情页
        fl_content.removeAllViews();//清除之前的布局
        fl_content.addView(pager.mRootView);//将菜单详情页的布局设置给帧布局

        //设置当前页的标题
        NewsData.NewsMenuData menuData= data.data.get(position);
        tv_title.setText(menuData.title);
    }
}
