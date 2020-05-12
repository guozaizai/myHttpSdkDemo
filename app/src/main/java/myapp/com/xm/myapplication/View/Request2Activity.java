package myapp.com.xm.myapplication.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.xm.httpapi.BaseApi.ComTransformer;
import com.xm.httpapi.BaseView.BaseActivity;
import java.util.HashMap;
import java.util.Map;
import myapp.com.xm.myapplication.Api;
import myapp.com.xm.myapplication.Model.LoginResult;
import myapp.com.xm.myapplication.Model.PwdLoginRequest;
import myapp.com.xm.myapplication.R;

public class Request2Activity extends BaseActivity {
    private TextView tvName;
    private Button btnRequest, btnRequestHead;

    @Override
    protected int getLayoutId() {
        return R.layout.request2;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvName = findViewById(R.id.tv_name);
        btnRequest=findViewById(R.id.btn_request);
        btnRequestHead = findViewById(R.id.btn_request_head);
        btnRequest.setOnClickListener(v -> {
            Api.getInstance().clearHeadMap();
            Api.getApi().api(new PwdLoginRequest("13712345678", "123456"))
                    .compose(new ComTransformer<>())
                    .subscribe(request(data -> getData((LoginResult) data)));
        });
        btnRequestHead.setOnClickListener(v -> {
            Api.getInstance().clearApiService();
            Map<String, String> map = new HashMap<>();
            map.put("token", "123456");
            Api.getInstance().setHeadMap(map);
            Api.getApi().api(new PwdLoginRequest("13712345678", "123456"))
                    .compose(new ComTransformer<>())
                    .subscribe(request(data -> getData((LoginResult) data)));
        });
    }

    @Override
    protected String showTitle() {
        return "直接网络请求";
    }

    private void getData(LoginResult loginResult) {
        tvName.setText(loginResult.toString());
    }
}
