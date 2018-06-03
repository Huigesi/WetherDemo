package com.example.administrator.wetherdemo.mvp.View;

import com.example.administrator.wetherdemo.mvp.BookBean;

/**
 * Created by Administrator on 2018/5/15.
 */

public interface IBookView {
    void showBook(BookBean bookBean);

    void showProgress();

    void hideProgress();

    void showLoadFailMsg(Throwable t);

}
