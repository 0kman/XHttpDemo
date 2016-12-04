package com.ht.xhttp;

/**
 * Author HeTao
 * Date 2016-06-16
 * Time 10:21
 * Class
 */
public class HttpUrl  {
    private String url;
    private String path;
    private String method;
    private HttpMethod httpMethod;
    private boolean multipart=false;


    public boolean isMultipart() {
        return multipart;
    }

    public void setMultipart(boolean multipart) {
        this.multipart = multipart;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
