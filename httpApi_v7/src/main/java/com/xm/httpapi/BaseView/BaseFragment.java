package com.xm.httpapi.BaseView;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;


import static com.google.gson.internal.$Gson$Types.getRawType;

/**
 *
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    protected abstract int getLayoutId();

    protected abstract void initData(Bundle savedInstanceState);

    protected void intent(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
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
