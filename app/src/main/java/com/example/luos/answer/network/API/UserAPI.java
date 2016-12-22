package com.example.luos.answer.network.API;

import com.example.luos.answer.module.HttpResult;
import com.example.luos.answer.module.User;


import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by luos on 2016/11/30.
 */

public interface UserAPI {

    @GET("/users/login")
    Observable<HttpResult<User>> login(@Query("username") String name, @Query("password") String password);

    @FormUrlEncoded
    @POST("/users/register")
    Observable<HttpResult<String>> register(@Body User user);

    @FormUrlEncoded
    @POST("/users/modify")
    Observable<HttpResult<String>> modifyUserInfo(@Body User user);
}
