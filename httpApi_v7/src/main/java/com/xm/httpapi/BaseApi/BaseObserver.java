package com.xm.httpapi.BaseApi;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *
 */

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        onStart();
    }

    @Override
    public void onNext(T value) {
        onNexted(value);
    }


    @Override
    public void onComplete() {
        onCompleted();
    }



    public abstract void onStart();



    public abstract void onNexted(T t);

    public abstract void onCompleted();
}
