package com.ht.xhttp;

/**
 * Author HeTao
 * Date 2016-06-15
 * Time 23:17
 * Class
 */
public enum  HttpMethod {
    GET("GET"),
    POST("POST");


    private final String value;

    HttpMethod(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
