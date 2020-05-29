package myapp.com.xm.myapplication;

import com.xm.httpapi.BaseApi.BaseApi;

/**
 *
 */

public class Api extends BaseApi {

    private ApiService service;
    public static String url = "http://api.yuanfusc.com:82/tmsapp1/";
    public static int timeOut = 100;
    public static int ReponseSucessCode = 0;
    public static int ReponseTokenInvalidCode = 2508;

    public Api() {
        super(url, timeOut, ReponseSucessCode, ReponseTokenInvalidCode);
    }

    public static class ApiSingle {
        private static Api api = new Api();
    }

    public static Api getInstance() {
        return ApiSingle.api;
    }

    public static ApiService getApi() {
        return getInstance().getDefault();
    }

    public void clearApiService() {
        if (service != null) service = null;
    }

    public ApiService getDefault() {
        return service == null ? service = getRetrofit().create(ApiService.class) : service;
    }


}
