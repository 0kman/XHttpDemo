package com.smax.xhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ht.xhttp.Xhttp;
import com.ht.xhttp.callback.ResponseCallBack;
import com.smax.xhttpdemo.builder.BeanBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 获取字符串
         */
        Xhttp.with(ApiService.class).getString().send(new ResponseCallBack<String>() {

            @Override
            public void onSuccessed(String s, boolean isPullDown) {
                System.out.println("onSuccessed:"+s);
            }

            @Override
            public void onFailed(String msg, boolean isPullDown, int error) {
                System.out.println("onFailed:"+msg);
            }
        });


        /**
         * 获取用户,结合GSON使用
         */
        Xhttp.with(ApiService.class,BeanBuilder.class).getUser(0).asPullDown().send(new ResponseCallBack<User>() {

            @Override
            public void onSuccessed(User user, boolean isPullDown) {
                System.out.println("onSuccessed:"+user.getName());
            }

            @Override
            public void onFailed(String msg, boolean isPullDown, int error) {
                System.out.println("onFailed:"+msg);
            }
        });
    }
}
