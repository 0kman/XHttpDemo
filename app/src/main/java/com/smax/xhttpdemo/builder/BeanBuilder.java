package com.smax.xhttpdemo.builder;

import com.google.gson.Gson;
import com.ht.xhttp.HttpRequest;
import com.ht.xhttp.HttpUrl;
import com.ht.xhttp.callback.BuilderCallBack;
import com.ht.xhttp.callback.ResponseCallBack;
import com.smax.xhttpdemo.TypeUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author HeTao
 * Date 2016-06-16
 * Time 10:13
 * Class
 */
public class BeanBuilder implements BuilderCallBack{
    Request.Builder mBuilder;
    FormBody.Builder mFormBodyBuilder;
    @Override
    public void initUrl(HttpUrl httpUrl) {
        String url=httpUrl.getUrl()+httpUrl.getPath()+httpUrl.getMethod();
        mBuilder=new Request.Builder();
        mBuilder.url(url);
        mFormBodyBuilder=new FormBody.Builder();
    }

    @Override
    public void addHeader(String key, String value) {
        mBuilder.addHeader(key,value);
    }

    @Override
    public void addQuery(String key, String value) {

    }

    @Override
    public void addBody(String key, Object value) {
        mFormBodyBuilder.add(key,String.valueOf(value));
    }

    @Override
    public Object send(final HttpRequest httpRequest, final ResponseCallBack responseCallBack) {
        OkHttpClient okHttpClient=new OkHttpClient();
        mBuilder.post(mFormBodyBuilder.build());
        okHttpClient.newCall(mBuilder.build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                responseCallBack.onFailed("错误",httpRequest.isAsPullDown(),0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Object o=new Gson().fromJson(response.body().string(), TypeUtils.getTypeParameter(responseCallBack.getClass()));
                responseCallBack.onSuccessed(o,httpRequest.isAsPullDown());
            }
        });
        return null;
    }
}
