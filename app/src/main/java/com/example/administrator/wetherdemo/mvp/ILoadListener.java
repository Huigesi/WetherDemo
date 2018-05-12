package com.example.administrator.wetherdemo.mvp;

import com.example.administrator.wetherdemo.demo.WeatherBean;

/**
 * Created by Administrator on 2018/5/8.
 */

public interface ILoadListener {
    void onSuccess(WeatherBean bean);

    void onFailure(Exception e);
}
