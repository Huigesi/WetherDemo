package com.example.administrator.wetherdemo.mvp.presenter;

import com.example.administrator.wetherdemo.mvp.View.IWeatherView;
import com.example.administrator.wetherdemo.mvp.WeatherBean;
import com.example.administrator.wetherdemo.mvp.model.ILoadListener;
import com.example.administrator.wetherdemo.mvp.model.IWeatherModel;
import com.example.administrator.wetherdemo.mvp.model.WeatherModel;

/**
 * Created by Administrator on 2018/5/12.
 */

public class WeatherPresenter implements IWeatherPresenter,ILoadListener {

    private String url = "https://www.sojson.com/open/api/weather/json.shtml?city=";

    private IWeatherView iWeatherView;
    private IWeatherModel iWeatherModel;

    public WeatherPresenter(IWeatherView iWeatherView) {
        this.iWeatherView = iWeatherView;
        this.iWeatherModel=new WeatherModel();
    }

    @Override
    public void loadWeather(String city) {
        iWeatherView.showProgress();
        iWeatherModel.loadWeather(city,this);
    }

    @Override
    public void onSuccess(WeatherBean bean) {
        iWeatherView.hideProgress();
        iWeatherView.showWeatherData(bean);
    }

    @Override
    public void onFailure(Throwable t) {
        iWeatherView.hideProgress();
        iWeatherView.showLoadFailMsg(t);
    }
}
