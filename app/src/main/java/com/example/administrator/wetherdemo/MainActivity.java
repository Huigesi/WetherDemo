package com.example.administrator.wetherdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_search;
    private TextView tv_weather;
    private String url = "https://www.sojson.com/open/api/weather/json.shtml?city=";
    private Thread thread;
    private ProgressDialog dialog;
    private OkHttpUtils okHttpUtils = new OkHttpUtils();
    private Button btn_beijing_search;


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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                startThread("广州");
                break;
            case R.id.btn_beijing_search:
                startThread("北京");
                break;
        }
    }

    private void startThread(final String city) {
        thread = new Thread() {
            @Override
            public void run() {
                super.run();
                showDialog();

                okHttpUtils.sendRequest(MainActivity.this, url + city);

                OkHttpUtils.ResultCallback resultCallback = new OkHttpUtils.ResultCallback() {
                    @Override
                    public void getWendu(final int wendu) {
                        runOnUiThread(new TimerTask() {
                            @Override
                            public void run() {
                                if (wendu == 01) {
                                    Toast.makeText(MainActivity.this, "只能3秒调用一次请求", Toast.LENGTH_SHORT).show();
                                } else {
                                    tv_weather.setText("温度:" + wendu);
                                }
                                dismissDialog();
                            }
                        });
                    }
                };
                okHttpUtils.getResultCallback(resultCallback);
            }
        };
        thread.start();
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
