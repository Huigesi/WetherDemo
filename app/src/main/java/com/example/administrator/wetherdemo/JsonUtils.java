package com.example.administrator.wetherdemo;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */

public class JsonUtils {
    public static WeatherBean getWeatherTm(String res) throws JSONException {
        WeatherBean bean = new WeatherBean();
        WeatherBean.DataBean dataBean = new WeatherBean.DataBean();
        WeatherBean.DataBean.ForecastBean forecastBean = new WeatherBean.DataBean.ForecastBean();
        List<WeatherBean.DataBean.ForecastBean> forecastBeanList = new ArrayList<>();

        WeatherBean.DataBean.YesterdayBean yesterdayBean = new WeatherBean.DataBean.YesterdayBean();
        JSONObject jsonObject = new JSONObject(res);
        int jsStatus = jsonObject.getInt("status");
        bean.setStatus(jsStatus);

        String data = jsonObject.getString("data");
        JSONObject jsonObject1 = new JSONObject(data);
        String wendu = jsonObject1.getString("wendu");
        dataBean.setWendu(wendu);

        JSONObject jsonObject2 = jsonObject1.getJSONObject("yesterday");
        String high = jsonObject2.getString("high");
        yesterdayBean.setHigh(high);

        JSONArray jsonArray = jsonObject1.getJSONArray("forecast");
        for (int i = 0; i < jsonArray.length(); i++) {
            forecastBean.setHigh(jsonArray.getJSONObject(i).getString("high"));
            forecastBeanList.add(forecastBean);
        }

        dataBean.setForecast(forecastBeanList);
        dataBean.setYesterday(yesterdayBean);
        bean.setData(dataBean);
        return bean;
    }

    public static WeatherBean getWeather(String res) {
        Gson gson = new Gson();
        WeatherBean wetherBean = gson.fromJson(res, WeatherBean.class);
        return wetherBean;
    }
}
