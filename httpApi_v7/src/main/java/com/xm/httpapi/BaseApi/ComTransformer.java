package com.xm.httpapi.BaseApi;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ComTransformer<T> implements ObservableTransformer<BaseHttpResult<T>, T> {

    @Override
    public ObservableSource<T> apply(Observable<BaseHttpResult<T>> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(ErrorTransformer.<T>getInstance());
    }

}

