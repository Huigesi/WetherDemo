package com.example.administrator.wetherdemo.mvp.model;

import com.example.administrator.wetherdemo.mvp.WeatherBean;

/**
 * Created by Administrator on 2018/5/8.
 */

public interface ILoadListener {
    void onSuccess(WeatherBean bean);

    void onFailure(Exception e);
}
