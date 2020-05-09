package myapp.com.xm.myapplication.View;

import android.os.Bundle;
import android.widget.TextView;

import com.xm.httpapi.BaseApi.ComTransformer;
import com.xm.httpapi.BaseView.BaseActivity;

import myapp.com.xm.myapplication.Api;
import myapp.com.xm.myapplication.Model.LoginResult;
import myapp.com.xm.myapplication.Model.PwdLoginRequest;
import myapp.com.xm.myapplication.R;

public class Request2Activity extends BaseActivity {
    private TextView tvName;

    @Override
    protected int getLayoutId() {
        return R.layout.request2;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvName = findViewById(R.id.tv_name);
        new Api().getDefault()
                .api(new PwdLoginRequest("13712345678", "123456"))
                .compose(new ComTransformer<>())
                .subscribe(request(data -> getData((LoginResult) data)));
    }

    @Override
    protected String showTitle() {
        return "直接网络请求";
    }

    private void getData(LoginResult loginResult) {
        tvName.setText(loginResult.toString());
    }
}
