package myapp.com.xm.myapplication.View;


import android.app.Application;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;


public class MyApplication extends TinkerApplication {

    public MyApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "myapp.com.xm.myapplication.MyApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
