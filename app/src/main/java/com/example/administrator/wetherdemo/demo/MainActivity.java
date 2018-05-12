package com.example.administrator.wetherdemo.demo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wetherdemo.R;

import java.util.List;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_search;
    private TextView tv_weather;
    private String url = "https://www.sojson.com/open/api/weather/json.shtml?city=";
    private ProgressDialog dialog;
    private OkHttpUtils okHttpUtils = new OkHttpUtils();
    private Button btn_beijing_search;
    private TextView tv_weather_yesterday;
    private TextView tv_weather_forecast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_search = (Button) findViewById(R.id.btn_search);
        tv_weather = (TextView) findViewById(R.id.tv_weather);
        btn_search.setOnClickListener(this);
        btn_beijing_search = (Button) findViewById(R.id.btn_beijing_search);
        btn_beijing_search.setOnClickListener(this);
        tv_weather_yesterday = (TextView) findViewById(R.id.tv_weather_yesterday);
        tv_weather_yesterday.setOnClickListener(this);
        tv_weather_forecast = (TextView) findViewById(R.id.tv_weather_forecast);
        tv_weather_forecast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                setRequest("广州");
                break;
            case R.id.btn_beijing_search:
                setRequest("北京");
                break;
        }
    }

    private void setRequest(final String city) {
        showDialog();

        OkHttpUtils.ResultCallback resultCallback = new OkHttpUtils.ResultCallback() {
            @Override
            public void getWeather(final WeatherBean weatherBean) {
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        if (weatherBean.getStatus() == 304) {
                            Toast.makeText(MainActivity.this, weatherBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            tv_weather.setText("城市：" + weatherBean.getCity() + " 日期：" + weatherBean.getDate()
                                    + " 温度:" + weatherBean.getData().getWendu());
                            tv_weather_yesterday.setText("昨日天气：" + weatherBean.getData().getYesterday().getLow() + " "
                                    + weatherBean.getData().getYesterday().getHigh());
                            List<WeatherBean.DataBean.ForecastBean> forecastBeanList = weatherBean.getData().getForecast();

                            /*tv_weather_forecast.setText( forecastBeanList.get(4).getDate()+" "
                                    +forecastBeanList.get(4).getHigh()+""+forecastBeanList.get(4).getLow());
*/
                        }
                        dismissDialog();
                    }
                });
            }

            @Override
            public void onFailure(final Exception e) {
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        dismissDialog();
                        tv_weather.setText(e.toString());
                    }
                });
            }
        };
        okHttpUtils.getResultCallback(url+city,resultCallback);
    }


    public void showDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                dialog = ProgressDialog.show(MainActivity.this, "", "正在获取");
            }
        });

    }

    public void dismissDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });

    }


}
