package com.example.administrator.wetherdemo.mvp.model;

import com.example.administrator.wetherdemo.mvp.BookBean;
import com.example.administrator.wetherdemo.mvp.WeatherBean;

/**
 * Created by Administrator on 2018/5/15.
 */

public interface ILoadBookListener {
    void onSuccess(BookBean bookBean);

    void onFailure(Throwable t);
}
