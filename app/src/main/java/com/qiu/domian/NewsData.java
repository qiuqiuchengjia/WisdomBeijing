package com.qiu.domian;

import java.util.ArrayList;

/**
 * 作者：qiu
 * 时间：2016/7/10:18:55
 * 博客：www.qiuchengjia.cn
 * 说明：网络分类信息的封装(新闻中心标签内容)
 */
public class NewsData {
    public int recode;//返回值
    public ArrayList<NewsMenuData> data;//数据
    /**  
     * 新闻中心中侧边栏数据对象
     * 时间：2016/7/10 18:59
     * 博客：www.qiuchengjia.cn
     * @author qiu
    */
   public class NewsMenuData{
        public String id;
        public String title;
        public int type;
        public String url;
        public ArrayList<NewsTabData> children;

        /**
         * 重写toString方法
         * @return string 自定义的string
         * @author qiu 时间：2016-07-10 19-10
         */
        @Override
        public String toString() {
            return "NewsMenuData[title="+title+",children="+children+"]";
        }
    }
    /**  
     * 新闻页面下的子页签数据对象
     * 时间：2016/7/10 19:03
     * 博客：www.qiuchengjia.cn
     * @author qiu
    */
    public class NewsTabData{
        public String id;
        public String title;
        public int type;
        public String url;
        /**
         * 重写toString方法
         * @return string 自定义的string
         * @author qiu 时间：2016-07-10 19-10
         */
        @Override
        public String toString() {
            return "NewsTabData[title="+title+"]";
        }
    }
    /**
     * 重写toString方法
     * @return string 自定义的string
     * @author qiu 时间：2016-07-10 19-10
     */
    @Override
    public String toString() {
        return "NewsData[data="+data+"]";
    }
}
