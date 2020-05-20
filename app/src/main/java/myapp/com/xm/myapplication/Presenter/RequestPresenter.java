package myapp.com.xm.myapplication.Presenter;

import com.xm.httpapi.BaseView.BasePresenter;
import java.util.List;
import java.util.Map;

import myapp.com.xm.myapplication.IRequest;
import myapp.com.xm.myapplication.Model.LoginResult;
import myapp.com.xm.myapplication.Model.PwdLoginRequest;
import myapp.com.xm.myapplication.Model.RequestModel;
import myapp.com.xm.myapplication.View.RequestActivity;

public class RequestPresenter extends BasePresenter<RequestActivity,RequestModel>  implements IRequest.IPresenter {


    @Override
    public void load(PwdLoginRequest pwdLoginRequest) {
        model.getData(pwdLoginRequest,request(data -> getView().setData((LoginResult)data)));
    }

}
