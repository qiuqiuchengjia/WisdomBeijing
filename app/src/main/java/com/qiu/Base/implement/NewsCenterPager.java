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
import com.qiu.Base.BasePager;
import com.qiu.Config.ConfigNet;
import com.qiu.Fragment.LeftMenuFragment;
import com.qiu.domian.NewsData;

/**
 * 作者：qiu
 * 时间：2016/7/4:13:29
 * 博客：www.qiuchengjia.cn
 * 说明：新闻内容页面，也是BasePager的子类实现
 */
public class NewsCenterPager extends BasePager {
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
        textView.setText("新闻中心");
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
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
        NewsData data = gson.fromJson(result, NewsData.class);
        Log.d("json数据",data.toString());

        MainActivity mainActivity = (MainActivity) mActivity;
        LeftMenuFragment leftMenuFragment = mainActivity.getLeftMenuFragment();
        leftMenuFragment.setMenuData(data);
    }
}
