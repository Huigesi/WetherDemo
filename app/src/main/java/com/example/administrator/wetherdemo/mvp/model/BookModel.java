package com.example.administrator.wetherdemo.mvp.model;

import com.example.administrator.wetherdemo.mvp.BookBean;
import com.example.administrator.wetherdemo.retrofit.Api;
import com.example.administrator.wetherdemo.retrofit.RetrofitHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/15.
 */

public class BookModel implements IBookModel {

    @Override
    public void loadBook(String name, final ILoadBookListener iLoadBookListener) {
        /*OkHttpUtils.ResultBookCallback resultBookCallback=new OkHttpUtils.ResultBookCallback()
        {
            @Override
            public void getBook(BookBean bookBean) {

                iLoadBookListener.onSuccess(bookBean);

            }

            @Override
            public void onFailure(Exception e) {
                iLoadBookListener.onFailure(e);
            }
        };
        OkHttpUtils.getResultBookCallback(url,resultBookCallback);*/

        /*RetrofitHelper retrofitHelper = new RetrofitHelper(Api.BOOK_HOST);
        retrofitHelper.getBook(name).enqueue(new Callback<BookBean>() {
            @Override
            public void onResponse(Call<BookBean> call, Response<BookBean> response) {
                iLoadBookListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BookBean> call, Throwable t) {
                iLoadBookListener.onFailure(t);
            }
        });*/
        RetrofitHelper retrofitHelper=new RetrofitHelper(Api.BOOK_HOST);
        retrofitHelper.getBook(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BookBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iLoadBookListener.onFailure(e);
                    }

                    @Override
                    public void onNext(BookBean bookBean) {
                        iLoadBookListener.onSuccess(bookBean);
                    }
                });

    }
}
