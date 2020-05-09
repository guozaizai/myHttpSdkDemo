package myapp.com.xm.myapplication.View;

import android.os.Bundle;
import android.widget.TextView;

import com.xm.httpapi.BaseView.BaseMVPActivity;
import myapp.com.xm.myapplication.IRequest;
import myapp.com.xm.myapplication.Model.LoginResult;
import myapp.com.xm.myapplication.Model.PwdLoginRequest;
import myapp.com.xm.myapplication.R;
import myapp.com.xm.myapplication.Presenter.RequestPresenter;

public class RequestActivity extends BaseMVPActivity<RequestPresenter, RequestActivity> implements IRequest.IView {

    private TextView tvName;
    @Override
    protected int getLayoutId() {
        return R.layout.request;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvName=findViewById(R.id.tv_name);
        PwdLoginRequest pwdLoginRequest = new PwdLoginRequest("18502189235", "yf123456");
        presenter.load(pwdLoginRequest);
    }

    @Override
    protected String showTitle() {
        return "MVP网络请求";
    }

    @Override
    public void setData(LoginResult loginResult) {
        toast("成功");
        tvName.setText(loginResult.toString());
    }

}
