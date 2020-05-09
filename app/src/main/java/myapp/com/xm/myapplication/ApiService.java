package myapp.com.xm.myapplication;
import com.xm.httpapi.BaseApi.BaseHttpResult;
import io.reactivex.Observable;
import myapp.com.xm.myapplication.Model.LoginResult;
import myapp.com.xm.myapplication.Model.PwdLoginRequest;
import retrofit2.http.Body;
import retrofit2.http.POST;
/**
 *
 */

public interface ApiService {

    @POST("login.do")
    Observable<BaseHttpResult<LoginResult>> api(@Body PwdLoginRequest request);
}
