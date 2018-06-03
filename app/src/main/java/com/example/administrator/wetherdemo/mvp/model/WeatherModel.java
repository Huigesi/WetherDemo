package com.example.administrator.wetherdemo.mvp.model;

import com.example.administrator.wetherdemo.mvp.BookBean;
import com.example.administrator.wetherdemo.mvp.WeatherBean;
import com.example.administrator.wetherdemo.mvp.utils.OkHttpUtils;
import com.example.administrator.wetherdemo.retrofit.Api;
import com.example.administrator.wetherdemo.retrofit.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/8.
 */

public class WeatherModel implements IWeatherModel {
    @Override
    public void loadWeather(String city, final ILoadListener loadListener) {
        RetrofitHelper retrofitHelper=new RetrofitHelper(Api.WEATHER_HOST);
       /* OkHttpUtils.ResultCallback resultCallback=new OkHttpUtils.ResultCallback() {
            @Override
            public void getWeather(WeatherBean weatherBean) {
                loadListener.onSuccess(weatherBean);
            }

            @Override
            public void onFailure(Exception e) {
                loadListener.onFailure(e);
            }
        };
        OkHttpUtils.getResultCallback(url,resultCallback);*/
       /*
        retrofitHelper.getWeather(city).enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                loadListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                loadListener.onFailure(t);
            }
        });*/
       retrofitHelper.getWeather(city)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Subscriber<WeatherBean>() {
                   @Override
                   public void onCompleted() {

                   }

                   @Override
                   public void onError(Throwable e) {
                       loadListener.onFailure(e);
                   }

                   @Override
                   public void onNext(WeatherBean weatherBean) {
                       loadListener.onSuccess(weatherBean);
                   }
               });


    }
}
