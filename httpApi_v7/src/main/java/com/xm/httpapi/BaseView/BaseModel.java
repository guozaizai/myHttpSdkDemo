package com.xm.httpapi.BaseView;


import com.xm.httpapi.BaseApi.BaseHttpResult;
import com.xm.httpapi.BaseApi.ComTransformer;

import io.reactivex.Observable;


/**
 *
 */

public abstract class BaseModel {
    protected ComTransformer comTransformer;
    public BaseModel() {
        if(comTransformer==null)comTransformer=new ComTransformer<>();
    }

}
