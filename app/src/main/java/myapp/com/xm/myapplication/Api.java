package myapp.com.xm.myapplication;

import com.xm.httpapi.BaseApi.BaseApi;
/**
 *
 */

public class Api extends BaseApi {

    private ApiService service;

    public Api() {
        super(BuildConfig.URL);
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
