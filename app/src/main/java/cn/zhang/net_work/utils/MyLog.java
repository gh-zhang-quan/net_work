package cn.zhang.net_work.utils;

import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.jetbrains.annotations.NotNull;

import cn.zhang.net_work.BuildConfig;
import cn.zhang.net_work.constant.StaticConfig;

/**
 * Author: Jack_zhang_quan
 * Email:  zhang_quan_888@163.com
 * Date:   2019/3/4
 * Explain:日志输出工具类
 */
public class MyLog {
    /**
     * 默认TAG（值固定）输出级别:verbose级别
     *
     * @param info 日志输出内容
     * @param args 用于格式化输出可以为空
     */
    public static void v(@NotNull String info, Object... args) {
        Logger.v(info, args);
    }

    /**
     * @param tag  自定义TAG 输出级别:verbose级别
     * @param info 日志输出内容
     * @param args args用于格式化输出可以为空
     */
    public static void v(@NotNull String tag, @NotNull String info, Object... args) {
        setLogTag(tag);
        Logger.v(info, args);
        setLogTag(StaticConfig.LOG_DEFUALT_TAG);
    }

    /**
     * @param tag       自定义TAG 输出级别:verbose级别
     * @param info      日志输出内容
     * @param useLogCat 是否使用系统LogCat
     */
    public static void v(@NotNull String tag, @NotNull String info, boolean useLogCat) {
        if (TextUtils.isEmpty(info)) {
            info = "not allow info null";
        }

        if (BuildConfig.DEBUG && useLogCat) {
            Log.v(tag, info);
        }
    }

    /**
     * 默认TAG（值固定）输出级别:debug级别
     *
     * @param info 日志输出内容
     * @param args 用于格式化输出可以为空
     */
    public static void d(@NotNull String info, Object... args) {
        Logger.d(info, args);
    }

    /**
     * @param tag  自定义TAG 输出级别:debug级别
     * @param info 日志输出内容
     * @param args args用于格式化输出可以为空
     */
    public static void d(@NotNull String tag, @NotNull String info, Object... args) {
        setLogTag(tag);
        Logger.d(info, args);
        setLogTag(StaticConfig.LOG_DEFUALT_TAG);
    }


    /**
     * @param tag       自定义TAG 输出 debug级别
     * @param info      日志输出内容
     * @param useLogCat 是否使用系统LogCat
     */
    public static void d(@NotNull String tag, @NotNull String info, boolean useLogCat) {
        if (TextUtils.isEmpty(info)) {
            info = "not allow info null";
        }

        if (BuildConfig.DEBUG && useLogCat) {
            Log.d(tag, info);
        }
    }

    /**
     * 默认TAG（值固定）输出级别:info级别
     *
     * @param info 日志输出内容
     * @param args 用于格式化输出可以为空
     */
    public static void i(@NotNull String info, Object... args) {
        Logger.i(info, args);
    }

    /**
     * @param tag  自定义TAG 输出级别:info级别
     * @param info 日志输出内容
     * @param args args用于格式化输出可以为空
     */
    public static void i(@NotNull String tag, @NotNull String info, Object... args) {
        setLogTag(tag);
        Logger.i(info, args);
        setLogTag(StaticConfig.LOG_DEFUALT_TAG);
    }

    /**
     * @param tag       自定义TAG 输出级别:info级别
     * @param info      日志输出内容
     * @param useLogCat 是否使用系统LogCat
     */
    public static void i(@NotNull String tag, @NotNull String info, boolean useLogCat) {
        if (TextUtils.isEmpty(info)) {
            info = "not allow info null";
        }

        if (BuildConfig.DEBUG && useLogCat) {
            Log.i(tag, info);
        }
    }

    /**
     * 默认TAG（值固定）输出级别:warn级别
     *
     * @param info 日志输出内容
     * @param args 用于格式化输出可以为空
     */
    public static void w(@NotNull String info, Object... args) {
        Logger.w(info, args);
    }

    /**
     * @param tag  自定义TAG 输出级别:warn级别
     * @param info 日志输出内容
     * @param args args用于格式化输出可以为空
     */
    public static void w(@NotNull String tag, @NotNull String info, Object... args) {
        setLogTag(tag);
        Logger.w(info, args);
        setLogTag(StaticConfig.LOG_DEFUALT_TAG);
    }

    /**
     * @param tag       自定义TAG 输出级别:warn级别
     * @param info      日志输出内容
     * @param useLogCat 是否使用系统LogCat
     */
    public static void w(@NotNull String tag, @NotNull String info, boolean useLogCat) {
        if (TextUtils.isEmpty(info)) {
            info = "not allow info null";
        }

        if (BuildConfig.DEBUG && useLogCat) {
            Log.w(tag, info);
        }
    }

    /**
     * 默认TAG（值固定）输出级别:error级别
     *
     * @param info 日志输出内容
     * @param args 用于格式化输出可以为空
     */
    public static void e(@NotNull String info, Object... args) {
        Logger.e(info, args);
    }

    /**
     * @param tag  自定义TAG 输出级别:error级别
     * @param info 日志输出内容
     * @param args args用于格式化输出可以为空
     */
    public static void e(@NotNull String tag, @NotNull String info, Object... args) {
        setLogTag(tag);
        Logger.e(info, args);
        setLogTag(StaticConfig.LOG_DEFUALT_TAG);
    }

    /**
     * @param tag       自定义TAG 输出级别:error级别
     * @param info      日志输出内容
     * @param useLogCat 是否使用系统LogCat
     */
    public static void e(@NotNull String tag, @NotNull String info, boolean useLogCat) {
        if (TextUtils.isEmpty(info)) {
            info = "not allow info null";
        }

        if (BuildConfig.DEBUG && useLogCat) {
            Log.e(tag, info);
        }
    }

    /**
     * error级别 用于Throwable
     */
    public static void e(@NotNull Throwable throwable, String info, Object... args) {
        Logger.e(throwable, info, args);
    }

    /**
     * json级别 默认Tag为
     */
    public static void json(String json) {
        Logger.json(json);
    }

    /**
     * json级别 自定义Tag
     */
    public static void json(@NotNull String tag, String json) {
        setLogTag(tag);
        Logger.json(json);
        setLogTag(StaticConfig.LOG_DEFUALT_TAG);
    }

    //设置日志Tag,同时保证release版本不打印日志
    private static void setLogTag(String tag) {
        //清除之前的LogAdapter
        Logger.clearLogAdapters();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag(tag)  //默认打印标记
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;//release版本不打印日志
            }
        });
    }
}
