package myapp.com.xm.myapplication;

import com.xm.httpapi.BaseApi.CustomGsonConverterFactory;
import java.util.concurrent.TimeUnit;

import myapp.com.xm.myapplication.Utils.log;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 *
 */

public class Api {

    public static ApiService SERVICE;

    /**
     * 请求超时时间
     */
 private static final int DEFAULT_TIMEOUT = 10;

    public static ApiService getDefault() {
        
        if (SERVICE == null) {
            HttpLoggingInterceptor loggingInterceptor=new  HttpLoggingInterceptor(message -> log.e("httpinfo= "+message));
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //手动创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

            httpClientBuilder .addInterceptor(loggingInterceptor);
            SERVICE = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                   .baseUrl(BuildConfig.URL)
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .build().create(ApiService.class);
        }
        return SERVICE;
    }


}
