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

    public ApiService getDefault() {
        return service == null ? service = getRetrofit().create(ApiService.class) : service;
    }


}
