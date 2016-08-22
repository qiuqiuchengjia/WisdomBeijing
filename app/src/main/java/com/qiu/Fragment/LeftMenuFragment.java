package com.qiu.Fragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qiu.Activity.R;
import com.qiu.domian.NewsData;

import java.util.ArrayList;

/**
 * 作者：qiu
 * 时间：2016/7/3:21:26
 * 博客：www.qiuchengjia.cn
 * 说明：侧边栏的fragment
 */
public class LeftMenuFragment extends BaseFragment {
    private ListView lvList;
    private   ArrayList<NewsData.NewsMenuData> mMenuList;
    private int mCurrentPos;//当前被点击的菜单栏项
    private  MenuAdapter adapter;
    /**
     * 初始化布局
     * @return view
     * @author qiu 时间：2016-07-03 21-28
     */
    @Override
    public View initViews() {
        View view=  View.inflate(mActivity, R.layout.fragment_left_menu,null);
        lvList= (ListView) view.findViewById(R.id.lv_list);
        return view;
    }

    /**
     * 用来初始化数据
     * @author qiu 时间：2016-07-13 14-14
     */
    @Override
    public void initDate() {
        //设置item点击事件
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPos=position;
                adapter.notifyDataSetChanged();//刷新
            }
        });
    }
    /**  
     * 侧边栏数据的适配器
     * 时间：2016/7/13 14:14
     * 博客：www.qiuchengjia.cn
     * @author qiu
    */
    class MenuAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return mMenuList.size();
        }
        @Override
        public Object getItem(int position) {
            return mMenuList.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity,R.layout.list_menu_item,null);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            NewsData.NewsMenuData newsMenuData = mMenuList.get(position);
            tvTitle.setText(newsMenuData.title);
            if(mCurrentPos==position){//判断当前绘制的view是否被选中
                //显示红色

            }else{
                //显示白色
            }
            return view;
        }
    }

    /**
     * 设置menu的网络数据
     * @author qiu 时间：2016-07-13 14-24
     */
    public void setMenuData(NewsData data){
        Log.d("LeftMenuFragment", "侧边栏拿到数据了"+data);
        mMenuList = data.data;
        adapter = new MenuAdapter();
        lvList.setAdapter(adapter);
    }
}
