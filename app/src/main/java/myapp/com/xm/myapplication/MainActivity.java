package myapp.com.xm.myapplication;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.widget.Button;

import com.xm.httpapi.BaseMyView.IosAlertDialog;
import com.xm.httpapi.BaseView.BaseActivity;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends BaseActivity {

    private Button btnCallPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        btnCallPhone = findViewById(R.id.btn_call_phone);
        btnCallPhone.setOnClickListener(v -> MainActivityPermissionsDispatcher.callPhoneWithCheck(this));
    }

    @Override
    protected String showTitle() {
        return "测试";
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "18502189235");
        intent.setData(data);
        startActivity(intent);
    }

//    @OnShowRationale(Manifest.permission.CALL_PHONE)
//    void showWhy(final PermissionRequest permissionRequest) {
//        permissionRequest.proceed();
//    }

//    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
//    void showDenied() {
//        toast("应用权限被拒绝");
//    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void onNeverAskAgain() {
        new IosAlertDialog(this).builder()
                .setMsg("应用权限被拒绝,为了不影响您的正常使用，请在 权限 中开启对应权限")
                .setPositiveButton("授权", v -> {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", MainActivity.this.getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }).setNegativeButton("取消", v -> {}).show();
    }

    /**
     * 权限回调，调用PermissionsDispatcher的回调方法
     * 类名+PermissionsDispatcher哦
     *
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
