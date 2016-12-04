package com.ht.xhttp.callback;


/**
 * Author HeTao
 * Date 2016-05-20
 * Time 19:45
 * Class
 */
public interface HttpCall<T> {

    Object send(ResponseCallBack<T> responseCallBack);


    HttpCall<T> asPullDown();

    HttpCall<T> tag(Object tag);

}

