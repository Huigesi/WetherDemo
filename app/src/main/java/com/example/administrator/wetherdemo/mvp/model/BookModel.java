package com.example.administrator.wetherdemo.mvp.model;

import com.example.administrator.wetherdemo.mvp.BookBean;
import com.example.administrator.wetherdemo.mvp.WeatherBean;
import com.example.administrator.wetherdemo.mvp.utils.OkHttpUtils;

/**
 * Created by Administrator on 2018/5/15.
 */

public class BookModel implements IBookModel{


    @Override
    public void loadBook(String url, final ILoadBookListener iLoadBookListener) {
        OkHttpUtils.ResultBookCallback resultBookCallback=new OkHttpUtils.ResultBookCallback() {
            @Override
            public void getBook(BookBean bookBean) {
                iLoadBookListener.onSuccess(bookBean);

            }

            @Override
            public void onFailure(Exception e) {
                iLoadBookListener.onFailure(e);
            }
        };
        OkHttpUtils.getResultBookCallback(url,resultBookCallback);
    }
}
