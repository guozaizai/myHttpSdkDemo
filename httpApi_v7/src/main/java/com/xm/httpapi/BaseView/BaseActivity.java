package com.xm.httpapi.BaseView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xm.httpapi.BaseApi.ComObserver;
import com.xm.httpapi.BaseApiInterface.OnLoadingListener;
import com.xm.httpapi.BaseApiInterface.ProgressManageError;
import com.xm.httpapi.BaseUtils.BaseUtils;
import com.xm.httpapi.R;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;


import static com.google.gson.internal.$Gson$Types.getRawType;

/**
 *
 */

public abstract class BaseActivity extends AppCompatActivity {
    private FrameLayout mContentContainer;//内容 容器
    private FrameLayout mContentContainertitle;//title 容器
    private boolean isShouwTitle = false;//是否显示title
    private TextView textTitle;//title 中间文字控件
    private String titleStr;//title 中间文字
    private LinearLayout ll_btn_left;//返回箭
    private TextView rl_title_rightname;//title 右边文字控件
    private RelativeLayout rl_title;
    public static boolean statusBarIsBlack=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(statusBarIsBlack){
            BaseUtils.setImmersiveStatusBarColorBlack(this);
        }else{
            BaseUtils.setImmersiveStatusBar(this);
        }
        initView(this);
        String s=showTitle();
        isShowTitle(s);
        setContentView(getLayoutId());
        initData( savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract String showTitle();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView(Context context) {
        LinearLayout root = new LinearLayout(context);
        root.setOrientation(LinearLayout.VERTICAL);
        super.setContentView(root);

        LinearLayout.LayoutParams paramTitle = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mContentContainertitle = new FrameLayout(context);
        root.addView(mContentContainertitle, paramTitle);

        LinearLayout.LayoutParams paramContent = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0);
        paramContent.weight = 1;
        mContentContainer = new FrameLayout(context);
        root.addView(mContentContainer, paramContent);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (isShouwTitle) {
            View viewtTitle = LayoutInflater.from(this).inflate(getTitleLayoutId(), mContentContainertitle);
            init(viewtTitle);
        }
       LayoutInflater.from(this).inflate(layoutResID, mContentContainer);
    }

    @Override
    public void setContentView(View view) {
        FrameLayout.LayoutParams paramtitle = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mContentContainertitle.addView(view, paramtitle);

        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContentContainer.addView(view, param);
    }

    private int getTitleLayoutId() {
        return R.layout.title_bar;
    }

    private void init(View view) {
        ll_btn_left = view.findViewById(R.id.ll_btn_left);
        rl_title = view.findViewById(R.id.rl_title);
        textTitle =  view.findViewById(R.id.tv_title);
        rl_title_rightname =  view.findViewById(R.id.tv_right_txt);
        if (!TextUtils.isEmpty(titleStr))
            textTitle.setText(titleStr);
        ll_btn_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rl_title_rightname.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                titleRightOnclick();
            }
        });
//        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) rl_title.getLayoutParams();
//        params.height = ScreenUtils.getScreenHeight(this)/10;
//        rl_title.setLayoutParams(params);
    }

    private void isShowTitle(boolean isShouwTitle) {
        this.isShouwTitle = isShouwTitle;
    }

    private void isShowTitle(String text) {
        if (TextUtils.isEmpty(text)) {
            this.isShouwTitle = false;
        } else {
            this.isShouwTitle = true;
            this.titleStr = text;
        }
    }

    public void setTitle(String text) {
        if (!TextUtils.isEmpty(text)) {
            textTitle.setText(text);
            rl_title.setVisibility(View.VISIBLE);
        }else{
            rl_title.setVisibility(View.GONE);
        }
    }

    public void setTitleTextRight(String text) {
        if (rl_title_rightname != null) {
            rl_title_rightname.setVisibility(View.VISIBLE);
            rl_title_rightname.setText(text);
        }
    }

    public void isShowTitleLeft(int isShow) {
        if (ll_btn_left != null)
            ll_btn_left.setVisibility(isShow);
    }

    public void titleRightOnclick() {
    }//给子类复写


    protected void intent(Class activity) {
        Intent intent = new Intent(getApplicationContext(), activity);
        startActivity(intent);
    }


    protected void intent(Class activity, int classType) {
        Intent intent = new Intent(getApplicationContext(), activity);
        startActivityForResult(intent, classType);
    }

    protected void intent(Class activity, Map<String, Object> map) {
        intent(activity,-100,map);
    }

    protected void intent(Class activity, int classType, Map<String, Object> map) {
        Intent intent = new Intent(getApplicationContext(), activity);
        for (String key : map.keySet()) {
            if (map.get(key) instanceof Integer) {
                intent.putExtra(key, ((Integer) map.get(key)).intValue());
            } else if (map.get(key) instanceof String) {
                intent.putExtra(key, map.get(key).toString());
            } else if (map.get(key) instanceof Float) {
                intent.putExtra(key, ((Float) map.get(key)).floatValue());
            } else if (map.get(key) instanceof Double) {
                intent.putExtra(key, ((Double) map.get(key)).doubleValue());
            } else if (map.get(key) instanceof Serializable) {
                intent.putExtra(key, ((Serializable) map.get(key)));
            }
        }
        if(classType==-100){
            startActivity(intent);
        }else{
            startActivityForResult(intent, classType);
        }
    }

    protected void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected void toast(String content, int time) {
        Toast.makeText(this, content, time).show();
    }

    /**
     * 点击空白区域隐藏键盘.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (this.getCurrentFocus() != null) {
                if (this.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    protected ComObserver request(OnLoadingListener onLoadingListener) {
        return request(onLoadingListener, true);
    }

    protected ComObserver request(OnLoadingListener onLoadingListener, boolean isLoading) {
        return new ComObserver(onLoadingListener, this, isLoading);
    }

    protected ComObserver request(OnLoadingListener onLoadingListener, ProgressManageError progressManageError) {
        return request(onLoadingListener, progressManageError, true);
    }

    protected ComObserver request(OnLoadingListener onLoadingListener, ProgressManageError progressManageError, boolean isLoading) {
        return new ComObserver(onLoadingListener, progressManageError, this, isLoading);
    }

}
