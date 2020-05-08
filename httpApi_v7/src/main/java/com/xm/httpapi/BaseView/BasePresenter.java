package com.xm.httpapi.BaseView;


import android.app.Activity;
import android.content.Context;

import com.xm.httpapi.BaseApi.ComObserver;
import com.xm.httpapi.BaseApiInterface.OnLoadingListener;
import com.xm.httpapi.BaseApiInterface.ProgressManageError;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static com.google.gson.internal.$Gson$Types.getRawType;

/**
 *
 */

public abstract class BasePresenter<T, M extends BaseModelInter> {
    private WeakReference<T> weakReference;
    protected M model;

    public void attach(T t) {
        if (weakReference == null) {
            weakReference = new WeakReference<>(t);
        }
        if (model == null) {
            model = createModel();
        }
    }

    public void deAttach() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
        if (model != null) {
            model = null;
        }
    }

    public boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    private M createModel() {
        try {
            Type superClass = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType) superClass).getActualTypeArguments()[1];
            Class<?> clazz = getRawType(type);
            return (M) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public T getView() {
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    // protected abstract M getModel();

    protected abstract Map<String, List<String>> getMap();

    protected ComObserver request(OnLoadingListener onLoadingListener) {
        return request(onLoadingListener, true);
    }

    protected ComObserver request(OnLoadingListener onLoadingListener, boolean isLoading) {
        return new ComObserver(onLoadingListener, (Activity) getView(), isLoading);
    }

    protected ComObserver request(OnLoadingListener onLoadingListener, Context context, boolean isLoading) {
        return new ComObserver(onLoadingListener, context, isLoading);
    }

    protected ComObserver request(OnLoadingListener onLoadingListener, ProgressManageError progressManageError) {
        return request(onLoadingListener, progressManageError, true);
    }

    protected ComObserver request(OnLoadingListener onLoadingListener, ProgressManageError progressManageError, boolean isLoading) {
        return new ComObserver(onLoadingListener, progressManageError, (Activity) getView(), isLoading);
    }

    protected ComObserver request(OnLoadingListener onLoadingListener, ProgressManageError progressManageError, Context context, boolean isLoading) {
        return new ComObserver(onLoadingListener, progressManageError, context, isLoading);
    }


    protected void createBaseData() {
    }
}
