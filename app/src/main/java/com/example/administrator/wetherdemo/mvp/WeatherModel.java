package com.example.administrator.wetherdemo.mvp;

import com.example.administrator.wetherdemo.demo.OkHttpUtils;
import com.example.administrator.wetherdemo.demo.WeatherBean;

/**
 * Created by Administrator on 2018/5/8.
 */

public class WeatherModel implements IWeatherModel {
    @Override
    public void loadWeather(String url, final ILoadListener loadListener) {
        OkHttpUtils.ResultCallback resultCallback=new OkHttpUtils.ResultCallback() {
            @Override
            public void getWeather(WeatherBean weatherBean) {
                loadListener.onSuccess(weatherBean);
            }

            @Override
            public void onFailure(Exception e) {
                loadListener.onFailure(e);
            }
        };
        OkHttpUtils.getResultCallback(url,resultCallback);
    }
}
