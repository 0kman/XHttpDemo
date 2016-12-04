package com.smax.xhttpdemo;

import android.app.Application;

import com.ht.xhttp.Xhttp;
import com.smax.xhttpdemo.builder.OkHttpBuilder;

import org.xutils.x;

/**
 * Author HeTao
 * Date 2016-06-16
 * Time 01:32
 * Class
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        Xhttp.get().setDefualtBuilder(OkHttpBuilder.class);
    }
}
