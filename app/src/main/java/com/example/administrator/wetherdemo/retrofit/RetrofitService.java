package com.example.administrator.wetherdemo.retrofit;

import com.example.administrator.wetherdemo.mvp.WeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/5/10.
 */

public interface RetrofitService {
    @GET("open/api/weather/json.shtml")
    Call<WeatherBean> gerSearchBack(@Query("city") String city);

}
