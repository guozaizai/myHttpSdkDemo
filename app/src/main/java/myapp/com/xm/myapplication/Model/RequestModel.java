package myapp.com.xm.myapplication.Model;

import com.xm.httpapi.BaseApi.ComObserver;
import com.xm.httpapi.BaseView.BaseModel;

import myapp.com.xm.myapplication.Api;
import myapp.com.xm.myapplication.IRequest;

public class RequestModel extends BaseModel implements IRequest.IModel {
    public void getData(PwdLoginRequest request, ComObserver<LoginResult> comObserver) {
        new Api().getDefault().api(request).compose(comTransformer).subscribe(comObserver);
    }
}
