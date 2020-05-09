package myapp.com.xm.myapplication;

import com.xm.httpapi.BaseView.BaseModelInter;

import myapp.com.xm.myapplication.Model.LoginResult;
import myapp.com.xm.myapplication.Model.PwdLoginRequest;

public interface IRequest {
    interface IModel extends BaseModelInter {
    }

    interface IPresenter {
        void load(PwdLoginRequest pwdLoginRequest);
    }

    interface IView {
        void setData(LoginResult loginResult)throws Exception;
    }
}
