package com.example.administrator.wetherdemo.retrofit;

import android.content.Context;

import com.example.administrator.wetherdemo.mvp.WeatherBean;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/12.
 */

public class RetrofitHelper {

    private static RetrofitHelper instance = null;
    private Context context;

    public RetrofitHelper(Context context) {
        this.context = context;
    }

    public static RetrofitHelper getInstance(Context context){
        if (instance == null){
            instance = new RetrofitHelper(context);
        }
        return instance;
    }

    public static void getResultCallbackRetro(String city,ResultCallbackRetro resultCallbackRetro){
        sendRetrofit(city,resultCallbackRetro);
    }

    public static void sendRetrofit(String city, final ResultCallbackRetro resultCallbackRetro){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://www.sojson.com/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<WeatherBean> call = retrofitService.gerSearchBack(city);
        call.enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                if (resultCallbackRetro!=null){
                    resultCallbackRetro.getWeather(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {

            }
        });
    }
    public interface ResultCallbackRetro {
        void getWeather(WeatherBean weatherBean);

        void onFailure(Exception e);
    }


}
