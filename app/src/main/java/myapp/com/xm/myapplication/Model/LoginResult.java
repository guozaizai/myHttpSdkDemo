package myapp.com.xm.myapplication.Model;

import lombok.Data;

/**
 *
 */

@Data
public class LoginResult<T> {
    private String username;
    private String token;
    private String mobile;
    private UserInfo userInfo;

    @Override
    public String toString() {
        return "LoginResultData{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
