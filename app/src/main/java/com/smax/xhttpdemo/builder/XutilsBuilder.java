package com.smax.xhttpdemo.builder;

import com.ht.xhttp.HttpRequest;
import com.ht.xhttp.HttpUrl;
import com.ht.xhttp.callback.BuilderCallBack;
import com.ht.xhttp.callback.ResponseCallBack;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Author HeTao
 * Date 2016-06-16
 * Time 10:13
 * Class
 */
public class XutilsBuilder implements BuilderCallBack<Callback.Cancelable> {
   RequestParams mRequestParams;
    @Override
    public void initUrl(HttpUrl httpUrl) {
        String url=httpUrl.getUrl()+httpUrl.getPath()+httpUrl.getMethod();
        mRequestParams=new RequestParams(url);
    }

    @Override
    public void addHeader(String key, String value) {
        mRequestParams.addHeader(key,value);
    }

    @Override
    public void addQuery(String key, String value) {
        mRequestParams.addQueryStringParameter(key,value);
    }

    @Override
    public void addBody(String key, Object value) {
        mRequestParams.addBodyParameter(key,String.valueOf(value));
    }

    @Override
    public Callback.Cancelable send(final HttpRequest httpRequest, final ResponseCallBack responseCallBack) {
        return x.http().post(mRequestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                responseCallBack.onSuccessed(result,httpRequest.isAsPullDown());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                responseCallBack.onFailed("错误",httpRequest.isAsPullDown(),0);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
