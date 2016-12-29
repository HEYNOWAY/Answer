package com.example.luos.answer.network.API;

import com.example.luos.answer.module.Answer;
import com.example.luos.answer.module.HttpResult;

import java.util.ArrayList;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by luos on 2016/12/1.
 */

public interface AnswerAPI {

    @GET("/answer/detail")
    Observable<HttpResult<ArrayList<Answer>>> getAnswerList(@Query("questionid") int questionid);

    @POST("/answer/add")
    Observable<HttpResult<String>> addAnswer(@Body Answer answer);

    @POST("/answer/modify")
    Observable<HttpResult<String>> modifyAnswer(@Field("userid") int userid,@Field("answerid") int answerid,@Body Answer answer);

    @GET("/answer/detail")
    Observable<HttpResult<Answer>> getAnswerDetail(@Query("answerid") int answerid);

    @POST("/answer/delete")
    Observable<HttpResult<String>> deleteAnswer(@Field("answerid") int answerid);

    @POST("/answer/collected")
    Observable<HttpResult<String>> collectedAnswer(@Field("userid")int userid, @Field("answerid") int answerid);

    @POST("/answer/agree")
    Observable<HttpResult<String>> agreeAnswer(@Field("userid")int userid, @Field("answerid")int answerid);
}
