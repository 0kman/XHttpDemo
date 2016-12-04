package com.ht.xhttp;


import com.ht.xhttp.callback.BuilderCallBack;
import com.ht.xhttp.http.BaseUrl;
import com.ht.xhttp.http.Body;
import com.ht.xhttp.http.GET;
import com.ht.xhttp.http.Header;
import com.ht.xhttp.http.Json;
import com.ht.xhttp.http.Multipart;
import com.ht.xhttp.http.POST;
import com.ht.xhttp.http.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Author HeTao
 * Date 2016-05-20
 * Time 16:53
 * Class
 */
public class Xhttp {
    private static Xhttp xhttp;
    private static Class<BuilderCallBack> mBuilderClass;
    private static Class<BuilderCallBack> mDefualtBuilderClass;
    static Map<Class,Object> objectMap=new HashMap<>();

    public static <T> T with(Class<T> service) {
        return with(service, mDefualtBuilderClass);
    }
    public static <T> T with(Class<T> service,Class builderClass) {
        mBuilderClass =builderClass;
        Object mObject=objectMap.get(service.getClass());
        if (mObject == null) {
            mObject = new Xhttp().create(service);
            objectMap.put(service.getClass(),mObject);
        }
        return (T) mObject;
    }

    public static Xhttp get(){
        if (xhttp ==null){
            xhttp =new Xhttp();
        }
        return xhttp;
    }

    private <T> T create(final Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object... args)
                            throws Throwable {
                        if (mDefualtBuilderClass ==null|| mBuilderClass ==null) throw new NullPointerException("BuilderCallBack is null");
                        BuilderCallBack callBack=mBuilderClass.newInstance();

                        HttpRequest httpRequest = builderUrl(callBack,method,service);

                        builderParameter(callBack,httpRequest,method.getParameterAnnotations(), args);

                        return httpRequest;
                    }
                });
    }


    /**
     * 配置基础url和服务器响应样式
     * @param callBack
     * @param method
     * @param service
     * @return
     */
    private HttpRequest builderUrl(BuilderCallBack callBack,Method method, Class service) {
        HttpRequest httpRequest =new HttpRequest();
        HttpUrl httpUrl=new HttpUrl();
        httpRequest.setHttpUrl(httpUrl);
        Annotation baseRequest=service.getAnnotation(BaseUrl.class);

        if (baseRequest!=null&&baseRequest instanceof BaseUrl){
            httpUrl.setUrl(((BaseUrl) baseRequest).value());
        }

        if (method.isAnnotationPresent(GET.class)) {
            GET get=method.getAnnotation(GET.class);
            httpUrl.setPath(get.path());
            httpUrl.setMethod(get.method());
            httpUrl.setHttpMethod(HttpMethod.GET);
        }else if (method.isAnnotationPresent(POST.class)){
            POST post=method.getAnnotation(POST.class);
            httpUrl.setPath(post.path());
            httpUrl.setMethod(post.method());
            httpUrl.setHttpMethod(HttpMethod.POST);
        }

        if (method.isAnnotationPresent(Multipart.class)){
            httpUrl.setMultipart(true);
        }

        httpRequest.setBuilderCallBack(callBack);
        callBack.initUrl(httpUrl);

        return httpRequest;
    }

    /**
     * 配置参数
     * @param callBack
     * @param httpRequest
     * @param annotationArray
     * @param args
     */
    private void builderParameter(BuilderCallBack callBack,HttpRequest httpRequest, Annotation[][] annotationArray, Object[] args) {
        for (int i = 0; i < annotationArray.length; i++) {
            for (Annotation a : annotationArray[i]) {
                if (a instanceof Query) {
                    callBack.addQuery(((Query) a).value(),toString(args[i]));
                }else if (a instanceof Body) {
                    callBack.addBody(((Body) a).value(),args[i]);
                }else if (a instanceof Header){
                    callBack.addHeader(((Header) a).value(),toString(args[i]));
                }else if (a instanceof Json){
                    httpRequest.setJson(toString(args[i]));
                }

            }

        }
    }


    /**
     * 配置默认配置Builder回调
     * @param builderClass
     */
    public  void setDefualtBuilder(Class builderClass){
        mDefualtBuilderClass =builderClass;
        mBuilderClass =builderClass;
    }

    private String toString(Object o) {
        return String.valueOf(o);
    }






}
