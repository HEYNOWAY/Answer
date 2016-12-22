package com.example.luos.answer.network.API;

import com.example.luos.answer.module.Commend;
import com.example.luos.answer.module.HttpResult;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by luos on 2016/12/1.
 */

public interface CommendAPI {
    @POST("/commend/add")
    Observable<HttpResult<String>> addCommend(@Body Commend commend);

    @GET("/commend/getList")
    Observable<HttpResult<List<Commend>>> getCommendList(@Field("commendid")int commendid);

    @POST("/commend/delete")
    Observable<HttpResult<String>> deleteCommend(@Field("commendid")int commendid);

    @POST("/commend/agree")
    Observable<HttpResult<String>> agreeCommend(@Field("useid")int userid,@Field("commendid")int commendid);
}
