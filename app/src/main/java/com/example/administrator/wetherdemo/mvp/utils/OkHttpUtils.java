package com.example.administrator.wetherdemo.mvp.utils;

import android.util.Log;

import com.example.administrator.wetherdemo.mvp.WeatherBean;

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
    private static OkHttpUtils okHttpUtils;

    private synchronized static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            okHttpUtils = new OkHttpUtils();
        }
        return okHttpUtils;
    }

    public static void getResultCallback(String url, ResultCallback resultCallback) {
        getInstance().sendRequest(url, resultCallback);
    }

    public void sendRequest(String url, final ResultCallback resultCallback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (resultCallback != null) {
                    resultCallback.onFailure(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = response.body().string();
                Log.i("res", res);
                WeatherBean bean = JsonUtils.getWeather(res);
                if (resultCallback != null) {
                    resultCallback.getWeather(bean);
                }
            }
        });
    }

    public interface ResultCallback {
        void getWeather(WeatherBean weatherBean);

        void onFailure(Exception e);
    }
}
