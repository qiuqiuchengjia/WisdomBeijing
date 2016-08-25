package com.qiu.domian;

import java.util.ArrayList;

/**
 * Description: 页签详情页数据对象
 * Data：2016/8/25-19:45
 * Blog：www.qiuchengjia.cn
 * Author: qiu
 */
public class TabData {
    public int retcode;
    public TabDetail data;

    public class TabDetail{
        public String title;
        public String more;
        public ArrayList<TabNewsData> news;
        public ArrayList<TopNewsData> topnews;
    }
    /**
     * Description: 头条新闻对象
     * Blog: www.qiuchengjia.cn
     * Data: 2016/8/25 19:50
     * @author: qiu
     */
    public class TopNewsData{
        public String id;
        public String topimage;
        public String pubdata;
        public String title;
        public String type;
        public String url;
        @Override
        public String toString() {
            return "TabData[ data="+title+"]";
        }
    }
    /**
     * Description: 列表新闻对象
     * Blog: www.qiuchengjia.cn
     * Data: 2016/8/25 19:50
     * @author: qiu
     */
    public class TabNewsData{
        public String id;
        public String listimage;
        public String pubdata;
        public String title;
        public String type;
        public String url;
        @Override
        public String toString() {
            return "TabData[ data="+title+"]";
        }
    }
    @Override
    public String toString() {
        return "TabData[ data="+data+"]";
    }
}
