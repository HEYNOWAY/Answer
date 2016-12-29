package com.example.luos.answer.network.API;

import com.example.luos.answer.module.Answer;
import com.example.luos.answer.module.HttpResult;
import com.example.luos.answer.module.Question;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by luos on 2016/12/1.
 */

public interface QuestionAPI {
    @GET("/question/getLastestQuestionList")
    Observable<HttpResult<ArrayList<Question>>> getLastestQuestionList(@Query("userid") int userid);

    @GET("/question/getHotestQuestionList")
    Observable<HttpResult<ArrayList<Question>>> getHostestQuestionList(@Query("userid") int userid);

    @POST("/question/ask")
    Observable<HttpResult<String>> askQuestion(@Body Question question);

    @POST("/question/concern")
    Observable<HttpResult<String>> concernQuestion(@Field("userid") int userid,@Field("questionid")int questionid);

    @POST("/question/delete/")
    Observable<HttpResult<String>> deleteQuestion(@Field("questionid")int questionid);
}
