package com.xm.httpapi.BaseMyView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import com.xm.httpapi.R;


/**
 *
 */

public class LoadingView extends ProgressDialog {
    private boolean flag;
    private Activity activity;

    public LoadingView(Context context, boolean flag) {
        super(context);
        this.activity=(Activity) context;
        this.flag = flag;
    }

    public LoadingView(Context context, int theme, boolean flag) {
        super(context, theme);
        this.activity=(Activity) context;
        this.flag = flag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        setCancelable(flag);
        setCanceledOnTouchOutside(flag);
        setContentView(R.layout.loading);//loading的xml文件
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {//开启
        super.show();
    }

    @Override
    public void dismiss() {//关闭
        if(!activity.isFinishing())
             super.dismiss();
    }
}
