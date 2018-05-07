package com.example.administrator.wetherdemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
                .connectTimeout(3, TimeUnit.SECONDS)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (e instanceof SocketTimeoutException) {
                    Toast.makeText(context, "网络连接超时", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = response.body().string();
                Log.i("res", res);
                try {
                    int wendu = JsonUtils.getWeatherTm(res);
                    if (resultCallback != null) {
                        resultCallback.getWendu(wendu);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getResultCallback(ResultCallback resultCallback) {
        this.resultCallback = resultCallback;
    }

    public interface ResultCallback {
        void getWendu(int wendu);
    }
}
