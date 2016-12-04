package com.ht.xhttp.callback;

/**
 * Author HeTao
 * Date 2016-06-15
 * Time 23:56
 * Class
 */
public interface ResponseCallBack<T> {

    /**
     * 获取数据成功
     * @param t 数据
     * @param isPullDown 是否是下拉刷新
     */
     void onSuccessed(T t, boolean isPullDown);

    /**
     * 获取数据失败
     * @param msg 失败信息
     */
     void onFailed(String msg, boolean isPullDown, int error);

}
