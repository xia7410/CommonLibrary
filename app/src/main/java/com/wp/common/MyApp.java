package com.wp.common;

import com.wp.commonlibrary.CommonApplication;
import com.wp.commonlibrary.network.retrofit.RetrofitHelper;

/**
 * Created by WangPing on 2018/1/17.
 */

public class MyApp extends CommonApplication {
    private static final String BASE_URL = "http://182.150.20.24:10025/ZHFQWebService/";

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitHelper.init(BASE_URL);
    }
}
