package com.smax.xhttpdemo;

import com.ht.xhttp.callback.HttpCall;
import com.ht.xhttp.http.BaseUrl;
import com.ht.xhttp.http.Body;
import com.ht.xhttp.http.POST;

/**
 * Author HeTao
 * Date 2016-06-16
 * Time 00:01
 * Class
 */
@BaseUrl("http://xxxxxxxxx")
public interface ApiService {

    /**
     *获取用户
     * @return
     */
    @POST(path ="/aa",method = "/getUser")
    HttpCall<User> getUser(@Body("id") long id);
    /**
     *获取String
     * @return
     */
    @POST(path ="/bb",method = "/getString")
    HttpCall<String> getString();

}
