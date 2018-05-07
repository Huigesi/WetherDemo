package com.example.administrator.wetherdemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/5/2.
 */

public class OkHttpUtils {
    String res = null;
    private ResultCallback resultCallback;

    public void sendRequest(final Context context, String url) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (resultCallback!=null){
                    resultCallback.onFailure(e);
                }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = response.body().string();
                Log.i("res", res);
                WeatherBean bean=JsonUtils.getWeather(res);
                if (resultCallback != null) {
                    resultCallback.getWeather(bean);
                }
            }
        });

    }

/* public static boolean isNetworkAvailable(Context context) {
     ConnectivityManager cm = (ConnectivityManager) context
             .getSystemService(Context.CONNECTIVITY_SERVICE);

         //如果仅仅是用来判断网络连接


     return cm.getActiveNetworkInfo().isAvailable();
 }*/


    public void getResultCallback(ResultCallback resultCallback) {
        this.resultCallback = resultCallback;
    }

    public interface ResultCallback {
        void getWeather(WeatherBean weatherBean);
        void onFailure(Exception e);
    }
}
