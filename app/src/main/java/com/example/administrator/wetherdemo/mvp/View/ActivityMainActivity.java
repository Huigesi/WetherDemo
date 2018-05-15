package com.example.administrator.wetherdemo.mvp.View;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wetherdemo.R;
import com.example.administrator.wetherdemo.mvp.BookBean;
import com.example.administrator.wetherdemo.mvp.WeatherBean;
import com.example.administrator.wetherdemo.mvp.presenter.BookPresenter;
import com.example.administrator.wetherdemo.mvp.presenter.WeatherPresenter;

import java.util.TimerTask;

public class ActivityMainActivity extends Activity implements IBookView, IWeatherView, View.OnClickListener {

    private TextView tvWeather;
    private TextView tvWeatherYesterday;
    private ProgressDialog progressDialog;
    private WeatherPresenter presenter;
    private BookPresenter bookPresenter;
    private Button btn_book;
    private TextView tv_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        findViewById(R.id.btn_search).setOnClickListener(this);
        findViewById(R.id.btn_beijing_search).setOnClickListener(this);
        tvWeather = (TextView) findViewById(R.id.tv_weather);
        tvWeatherYesterday = (TextView) findViewById(R.id.tv_weather_yesterday);
        presenter = new WeatherPresenter(this);
        bookPresenter = new BookPresenter(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                presenter.loadWeather("广州");
                break;
            case R.id.btn_beijing_search:
                presenter.loadWeather("北京");
                break;
            case R.id.btn_book:
                    bookPresenter.loadBook("金瓶梅");
                break;
        }
    }

    @Override
    public void showBook(final BookBean bookBean) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                tv_book.setText(bookBean.getBooks().get(0).getAuthor()+" "+bookBean.getBooks().get(0).getSubtitle());
            }
        });
    }

    @Override
    public void showProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = ProgressDialog.show(ActivityMainActivity.this,
                "", "正在获取");
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showWeatherData(final WeatherBean weatherBean) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                if (weatherBean.getStatus() == 304) {
                    Toast.makeText(ActivityMainActivity.this, weatherBean.getMessage(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    tvWeather.setText("城市：" + weatherBean.getCity()
                            + " 日期：" + weatherBean.getDate()
                            + " 温度:" + weatherBean.getData().getWendu());
                    tvWeatherYesterday.setText(
                            "昨日天气：" + weatherBean.getData().getYesterday().getLow() + " "
                                    + weatherBean.getData().getYesterday().getHigh());
                }
            }
        });
    }

    @Override
    public void showLoadFailMsg(final Exception e) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                tvWeather.setText("加载数据失败:" + e.toString());
            }
        });

    }

    private void initView() {
        btn_book = (Button) findViewById(R.id.btn_book);
        tv_book = (TextView) findViewById(R.id.tv_book);

        btn_book.setOnClickListener(this);
    }
}
