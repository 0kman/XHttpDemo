package com.ht.xhttp.callback;

import com.ht.xhttp.HttpRequest;
import com.ht.xhttp.HttpUrl;

/**
 * Author HeTao
 * Date 2016-06-16
 * Time 01:02
 * Class
 */
public interface BuilderCallBack<T>  {

     void initUrl(HttpUrl httpUrl);
     void addHeader(String key ,String value);
     void addQuery(String key ,String value);
     void addBody(String key ,Object value);
     T send(HttpRequest httpRequest, ResponseCallBack responseCallBack);
}
