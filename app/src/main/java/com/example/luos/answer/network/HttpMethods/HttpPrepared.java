package com.example.luos.answer.network.HttpMethods;

import com.example.luos.answer.contents.Contans;
import com.example.luos.answer.network.API.AnswerAPI;
import com.example.luos.answer.network.API.CommendAPI;
import com.example.luos.answer.network.API.QuestionAPI;
import com.example.luos.answer.network.API.UserAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luos on 2016/12/1.
 */

public class HttpPrepared {
    private static final int DEFAULT_TIMEOUT = 20;

    private Retrofit retrofit;
    private UserAPI userService;
    private QuestionAPI questionService;
    private AnswerAPI answerService;
    private CommendAPI commendService;

    private HttpPrepared() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(Contans.baseurl)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        createService();
    }

    public Retrofit getretrofit(){
        return retrofit;
    }

    private void createService() {
        userService = retrofit.create(UserAPI.class);
        questionService = retrofit.create(QuestionAPI.class);
        answerService = retrofit.create(AnswerAPI.class);
        commendService = retrofit.create(CommendAPI.class);
    }

    public UserAPI getUserAPI(){
        return userService;
    }

    public QuestionAPI getQuestionAPI(){
        return questionService;
    }

    public AnswerAPI getAnswerAPI(){
        return answerService;
    }

    public CommendAPI getCommendAPI(){
        return commendService;
    }

    private static class SingleHolder {
        private static final HttpPrepared INSTANCE = new HttpPrepared();
    }

    public static HttpPrepared getInstance() {
        return SingleHolder.INSTANCE;
    }


}
