package myapp.com.xm.myapplication.View;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.xm.httpapi.BaseUtils.Toast;
import com.xm.httpapi.BaseView.BaseActivity;
import myapp.com.xm.myapplication.R;


@Route(path = "/ui/loginActivity")
public class TokeninvalidActivity extends BaseActivity {
    @Autowired(name = "message")
    public String errorMessage;

    @Override
    protected int getLayoutId() {
        return R.layout.token_invalid;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Toast.show(errorMessage);
    }

    @Override
    protected String showTitle() {
        return "";
    }
}
