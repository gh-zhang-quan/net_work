package cn.zhang.net_work.constant;

import cn.zhang.net_work.BuildConfig;

/**
 * Author: Jack_zhang_quan
 * Email:  zhang_quan_888@163.com
 * Date:   2019/3/4
 * Explain:联网常量类
 */
public class Constant {

    //测试环境《====》正式环境
    public static final String BASEURL = BuildConfig.DEBUG ? "http://测试域名/" : "http://正式域名/";

    //设置联网超时时间
    public static class Net {
        public static final long CONNECT_TIMEOUT = 30;
        public static final long READ_TIMEOUT = 30;
        public static final long WRITE_TIMEOUT = 30;
    }

}
