package com.example.administrator.wetherdemo.mvp;

/**
 * Created by Administrator on 2018/5/8.
 */

public interface IWeatherModel {
    void loadWeather(String url,ILoadListener loadListener);

}
