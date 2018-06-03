package com.example.administrator.wetherdemo.mvp.View;

import com.example.administrator.wetherdemo.mvp.WeatherBean;

/**
 * Created by Administrator on 2018/5/12.
 */

public interface IWeatherView {
    void showProgress();
    void hideProgress();
    void showWeatherData(WeatherBean weatherBean);
    void showLoadFailMsg(Throwable t);
}
