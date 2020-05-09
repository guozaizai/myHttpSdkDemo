package com.xm.httpapi.BaseApi;

import com.xm.httpapi.BaseUtils.log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class BaseApi {
    /**
     * 请求默认超时时间
     */
    private int timeOut = 10;
    /**
     * 请求url
     */
    private String url;
    /**
     * 是否打印http请求日志
     */
    private boolean isPrintHttpInfo = true;
    /**
     * 是否添加http日志拦截器
     */
    private boolean isHttpLoggingInterceptor = true;

    public BaseApi(String url) {
        this.url = url;
    }

    public BaseApi(String url, int timeOut) {
        this.url = url;
        this.timeOut = timeOut;
    }

    public void setHttpLoggingInterceptor(boolean httpLoggingInterceptor) {
        this.isHttpLoggingInterceptor = httpLoggingInterceptor;
    }

    public void setPrintHttpInfo(boolean printHttpInfo) {
        this.isPrintHttpInfo = printHttpInfo;
    }

    private OkHttpClient.Builder okHttpClient() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(timeOut, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(timeOut, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(timeOut, TimeUnit.SECONDS);
        if (isHttpLoggingInterceptor) httpClientBuilder.addInterceptor(httpLoggingInterceptor());
        return httpClientBuilder;
    }

    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .client(okHttpClient().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .addConverterFactory(CustomGsonConverterFactory.create())
                .build();
    }

    private HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {
            if (isPrintHttpInfo) log.e("httpinfo= " + message);
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
}
