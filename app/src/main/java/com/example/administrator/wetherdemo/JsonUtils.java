package com.example.administrator.wetherdemo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/5/2.
 */

public class JsonUtils {
    public static int getWeatherTm(String res) throws JSONException {
        int wendu=0;
        JSONObject jsonObject=new JSONObject(res);
        int jsStatus=jsonObject.getInt("status");
        if (jsStatus==200){
            String js=jsonObject.getString("data");
            JSONObject jsonObject1=new JSONObject(js);
           wendu = jsonObject1.getInt("wendu");

        }else if (jsStatus==304){
             wendu = 01;
        }
        return wendu;
    }
}
