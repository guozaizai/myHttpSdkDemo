package com.xm.httpapi.BaseView;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xm.httpapi.BaseUtils.BaseUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import static com.google.gson.internal.$Gson$Types.getRawType;

/**
 *
 */

public abstract class BaseMVPFragment<T extends BasePresenter, V> extends Fragment {
    protected T presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        presenter.attach((V) this);
        View view = inflater.inflate(getLayoutId(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        initData(v, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null)
            presenter.deAttach();
    }


    private T createPresenter() {
        try {
            Type superClass = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            Class<?> clazz = getRawType(type);
            return (T) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    protected abstract int getLayoutId();

    protected abstract void initData(View view, Bundle savedInstanceState);

    protected void intent(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }

    protected void intent(Class activity, View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            intent(activity);
        } else {
            Intent intent = new Intent(getActivity(), activity);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(getActivity(), view, "test");
            startActivity(intent, options.toBundle());
        }
    }

    protected void intent(Class activity, Map<String, String> map) {
        Intent intent = new Intent(getActivity(), activity);
        for (String key : map.keySet()) {
            intent.putExtra(key, map.get(key));
        }
        startActivity(intent);
    }

    protected void intent(Class activity, int classType) {
        Intent intent = new Intent(getActivity(), activity);
        startActivityForResult(intent, classType);
    }

    protected void intent(Class activity, int classType, Map<String, String> map) {
        Intent intent = new Intent(getActivity(), activity);
        for (String key : map.keySet()) {
            intent.putExtra(key, map.get(key));
        }
        startActivityForResult(intent, classType);
    }

    protected void toast(String content) {
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }
}
