package com.qiu.Fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
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
    private RadioGroup radioGroup;
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
        radioGroup= (RadioGroup) view.findViewById(R.id.rg_group);
        viewPager= (ViewPager) view.findViewById(R.id.vp_content);
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
        pageList = new ArrayList<BasePager>();
        //初始化五个子页面
        pageList.add(new HomePager(mActivity));
        pageList.add(new NewsCenterPager(mActivity));
        pageList.add(new SmartServerPager(mActivity));
        pageList.add(new GovAffairsPager(mActivity));
        pageList.add(new SettingPager(mActivity));
        //设置适配器
        viewPager.setAdapter(new ContentAdapter());
        //设置radioGroup选择事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        viewPager.setCurrentItem(0,false);//设置当前页面，并去掉页面切换动画
                        pageList.get(0).initData();//当前被选中的页面，初始化数据
                        break;
                    case R.id.rb_news:
                        viewPager.setCurrentItem(1,false);//设置当前页面
                        pageList.get(1).initData();//当前被选中的页面，初始化数据
                        break;
                    case R.id.rb_smart:
                        viewPager.setCurrentItem(2,false);//设置当前页面
                        pageList.get(2).initData();//当前被选中的页面，初始化数据
                        break;
                    case R.id.rb_gow:
                        viewPager.setCurrentItem(3,false);//设置当前页面
                        pageList.get(3).initData();//当前被选中的页面，初始化数据
                        break;
                    case R.id.rb_setting:
                        viewPager.setCurrentItem(4,false);//设置当前页面
                        pageList.get(4).initData();//当前被选中的页面，初始化数据
                        break;
                    default:
                        break;

                }
            }
        });
        pageList.get(0).initData();//手动初始化首页
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
            BasePager pager = pageList.get(position);
            container.addView(pager.rootView);
          //  pager.initData();//初始化数据//不能初始化，否则会预加载下一个页面
            return pager.rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
