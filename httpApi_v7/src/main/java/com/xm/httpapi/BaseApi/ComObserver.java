package com.xm.httpapi.BaseApi;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.xm.httpapi.BaseApiInterface.OnLoadingListener;
import com.xm.httpapi.BaseApiInterface.ProgressManageError;
import com.xm.httpapi.BaseApiStatus.ErrorType;
import com.xm.httpapi.BaseProgress.ProgressCancelListener;
import com.xm.httpapi.BaseProgress.ProgressDialogHandler;
import com.xm.httpapi.BaseUtils.NetworkUtil;
import com.xm.httpapi.BaseUtils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import io.reactivex.disposables.Disposable;

/**
 *
 */

public class ComObserver<T> extends BaseObserver<T> implements ProgressCancelListener {
    private Context context;
    private ProgressDialogHandler mProgressDialogHandler;
    private OnLoadingListener onLoadingListener;
    private ProgressManageError progressManageError;

    public ComObserver(OnLoadingListener onLoadingListener, Context context, boolean isLoding) {
        this.context = context;
        this.onLoadingListener = onLoadingListener;
        if (isLoding) {
            mProgressDialogHandler = new ProgressDialogHandler(context, this, false);
        }
    }

    public ComObserver(OnLoadingListener onLoadingListener, ProgressManageError progressManageError, Context context, boolean isLoding) {
        this.context = context;
        this.onLoadingListener = onLoadingListener;
        this.progressManageError = progressManageError;
        if (isLoding) {
            mProgressDialogHandler = new ProgressDialogHandler(context, this, false);
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (!NetworkUtil.isNetworkAvailable(context)) {
            ToastUtil.show(context, ErrorType.NETWORK_ERROR.getMessage());
            d.dispose();
        }
        super.onSubscribe(d);
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        if (progressManageError != null) {
            progressManageError.error(e.getMessage());
            return;
        }
        String errorMsg=e.getMessage();
        if(e.getMessage().matches(".*[a-zA-z].*")){
            errorMsg=ErrorType.SERVICE_ERROR.getMessage();
        }
        if (e instanceof SocketTimeoutException) {
            ToastUtil.show(context, ErrorType.TIME_OUT.getMessage());
        } else if (e instanceof ConnectException) {
            ToastUtil.show(context, ErrorType.TIME_ERROR.getMessage());
        } else if (e instanceof UnknownHostException) {
            ToastUtil.show(context, ErrorType.TIME_CANCEL.getMessage());
        } else {
            ToastUtil.show(context,errorMsg);
        }

    }


    @Override
    public void onNexted(T t) {
        if (onLoadingListener != null)
            onLoadingListener.onNext(t);
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onCancelProgress() {
        dismissProgressDialog();
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

}
