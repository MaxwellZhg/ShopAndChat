package shopandclient.ssf.com.shopandclient.net.services;

import retrofit2.Call;
import retrofit2.http.*;
import shopandclient.ssf.com.shopandclient.entity.*;

/**
 * Created by zhg on 2019/6/24.
 */
public interface UserService {
    @POST("/api/Users/Login")
    Call<UserLoginBean> postUserLogin(@Body User body);

    @GET("/api/Users/SendCode")
    Call<SendCodeBean> getSendCode(@Query("sPhone") String sPhone);

    @POST("/api/Users/Add")
    Call<AddUserResult> addRegisteryUser(@Body AddUser body);

    @GET("/api/Users/GetSingle")
    Call<UserInfo> getUserInfo(@Query("id") int id);

    @POST("/api/Users/UpdatePWD")
    Call<AddUserResult> updatePwd(@Body AddUser body);

    @POST("/api/Users/LoginOut ")
    Call<AddUserResult> loginOut();
}
