package com.qiu.Fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.qiu.Activity.R;
import com.qiu.Base.BasePager;
import com.qiu.Base.implement.GovAffairsPager;
import com.qiu.Base.implement.HomePager;
import com.qiu.Base.implement.NewsCenterPager;
import com.qiu.Base.implement.SettingPager;
import com.qiu.Base.implement.SmartServerPager;

import java.util.ArrayList;

/**
 * 作者：qiu
 * 时间：2016/7/3:21:27
 * 博客：www.qiuchengjia.cn
 * 说明： 主页面内容
 */
public class ContentFragment extends BaseFragment {
    @ViewInject(R.id.rg_group)
    private RadioGroup radioGroup;
    @ViewInject(R.id.vp_content)
    private ViewPager viewPager;
    private ArrayList<BasePager> pageList;

    /**
     * 初始化布局
     *
     * @return view
     * @author qiu 时间：2016-07-03 21-27
     */
    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_content_menu, null);
        ViewUtils.inject(this, view);//注入view和事件
        return view;
    }

    /**
     * 初始化数据
     *
     * @author qiu 时间：2016-07-03 23-40
     */
    @Override
    public void initDate() {
        radioGroup.check(R.id.rb_home);//默认勾选首页
        //初始化五个子页面
        pageList= new ArrayList<BasePager>();
        //初始化五个子页面
        pageList.add(new HomePager(mActivity));
        pageList.add(new NewsCenterPager(mActivity));
        pageList.add(new SmartServerPager(mActivity));
        pageList.add(new GovAffairsPager(mActivity));
        pageList.add(new SettingPager(mActivity));
        //设置适配器
        viewPager.setAdapter(new ContentAdapter());
    }

    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager= pageList.get(position);
            container.addView(pager.rootView);
            pager.initData();//初始化数据
            return pager.rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
