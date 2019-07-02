package cn.zhang.net_work.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * Author: Jack_zhang_quan
 * Email:  zhang_quan_888@163.com
 * Date:   2019/3/4
 * Explain:自定义吐司
 */
public class MyToast {

    private static boolean isShow = true;
    private static Toast toast;

    //短时间显示Toast
    @SuppressLint("ShowToast")
    public static void showShort(Context context, CharSequence message) {
        if ("登录已经过期".contentEquals(message) || "未知错误".contentEquals(message)) {
            return;
        }
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            } else {
                toast.cancel();//关闭吐司显示
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }

    //短时间显示Toast
    @SuppressLint("ShowToast")
    public static void showShort(Context context, int message) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            } else {
                toast.cancel();//关闭吐司显示
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }

    //长时间显示Toast
    @SuppressLint("ShowToast")
    public static void showLong(Context context, CharSequence message) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            } else {
                toast.cancel();//关闭吐司显示
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            }
            toast.show();
        }
    }

    //长时间显示Toast
    @SuppressLint("ShowToast")
    public static void showLong(Context context, int message) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            } else {
                toast.cancel();//关闭吐司显示
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            }
            toast.show();
        }
    }

    //自定义显示Toast时间
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow) {
            Toast.makeText(context, message, duration).show();
        }
    }

    //自定义显示Toast时间
    public static void show(Context context, int message, int duration) {
        if (isShow) {
            Toast.makeText(context, message, duration).show();
        }
    }

}
