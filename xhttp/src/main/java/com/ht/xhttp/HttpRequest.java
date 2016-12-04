package com.ht.xhttp;

import com.ht.xhttp.callback.BuilderCallBack;
import com.ht.xhttp.callback.HttpCall;
import com.ht.xhttp.callback.ResponseCallBack;

/**
 * Author HeTao
 * Date 2016-06-15
 * Time 23:48
 * Class
 */
public class HttpRequest implements HttpCall {
    private HttpUrl httpUrl;
    private String json;


    private boolean asPullDown=false;
    private Object mTag;
    private ResponseCallBack mResponseCallBack;
    private BuilderCallBack mBuilderCallBack;






    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }



    public HttpUrl getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(HttpUrl httpUrl) {
        this.httpUrl = httpUrl;
    }


    public void setBuilderCallBack(BuilderCallBack builderCallBack) {
        mBuilderCallBack = builderCallBack;
    }



    public boolean isAsPullDown() {
        return asPullDown;
    }

    public Object getTag() {
        return mTag;
    }



    @Override
    public Object send(ResponseCallBack responseCallBack) {
        mResponseCallBack=responseCallBack;
        if (mBuilderCallBack!=null){
           return mBuilderCallBack.send(this,mResponseCallBack);
        }
        return null;
    }



    @Override
    public HttpCall asPullDown() {
        asPullDown=true;
        return this;
    }

    @Override
    public HttpCall tag(Object tag) {
        mTag=tag;
        return this;
    }
}
