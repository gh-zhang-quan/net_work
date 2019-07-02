package cn.zhang.net_work.net.nethelper;


import org.json.JSONException;

import java.net.ConnectException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * Author: kuencheung
 * Date:   2018/6/21
 * Des:    后台返回错误码
 */

class ExceptionHandle {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    private static final int FAIL_QUEST = 406;//无法使用请求的内容特性来响应请求的网页
    private static final int BAD_REQUEST = 400;

    static String handleException(Throwable e) {
        ResponeThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponeThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    break;
                case FORBIDDEN:
                    ex.message = "服务器已经理解请求，但是拒绝执行它";
                    break;
                case NOT_FOUND:
                    ex.message = "服务器异常，请稍后再试";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "请求超时";
                    break;
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                    ex.message = "网络连接异常，无法完成对请求的处理";
                    break;
                case BAD_REQUEST:
                    break;
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                case FAIL_QUEST:
                    ex.message = "网关出错，请检查！";
                    break;
                default:
                    ex.message = "网络错误";
                    break;
            }
            return ex.message;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ResponeThrowable(resultException, resultException.code);
            ex.message = resultException.message;
            return ex.message;
        } else if (e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ResponeThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex.message;
        } else if (e instanceof ConnectException) {
            ex = new ResponeThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = "连接失败";
            return ex.message;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponeThrowable(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex.message;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ResponeThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "当前网络连接不顺畅，请稍后再试！";
            return ex.message;
        } else if (e instanceof java.net.UnknownHostException) {
            ex = new ResponeThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "网络中断，请检查网络状态！";
            return ex.message;
        } else if (e instanceof javax.net.ssl.SSLException) {
            ex = new ResponeThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "网络中断，请检查网络状态！";
            return ex.message;
        } else if (e instanceof java.io.EOFException) {
            ex = new ResponeThrowable(e, ERROR.PARSE_EmptyERROR);
            ex.message = "1007";
            return ex.message;
        } else if (e instanceof NullPointerException) {
            ex = new ResponeThrowable(e, ERROR.PARSE_EmptyERROR);
//            ex.message = "数据为空，显示失败";
            ex.message = "当前网络连接不顺畅,请切换网络";
            return ex.message;
        } else {
            ex = new ResponeThrowable(e, ERROR.UNKNOWN);
            ex.message = "未知错误";
            return ex.message;
        }
    }


    //约定异常
    public class ERROR {
        //未知错误
        static final int UNKNOWN = 1000;
        //解析错误
        static final int PARSE_ERROR = 1001;
        ///解析no content错误
        static final int PARSE_EmptyERROR = 1007;
        //网络错误
        static final int NETWORD_ERROR = 1002;
        //协议出错
        static final int HTTP_ERROR = 1003;
        //证书出错
        static final int SSL_ERROR = 1005;
        //连接超时
        static final int TIMEOUT_ERROR = 1006;
    }

    public static class ResponeThrowable extends Exception {
        int code;
        String message;

        ResponeThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }

        public ResponeThrowable(String message, int code) {
            this.code = code;
            this.message = message;
        }
    }

    public class ServerException extends RuntimeException {
        int code;
        String message;

        public ServerException(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
