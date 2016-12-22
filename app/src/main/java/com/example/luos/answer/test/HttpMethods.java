package com.example.luos.answer.test;

import android.util.Log;

import com.example.luos.answer.module.HttpResult;
import com.example.luos.answer.module.Question;
import com.example.luos.answer.network.API.QuestionAPI;
import com.example.luos.answer.network.ApiException;
import com.example.luos.answer.network.HttpMethods.HttpQuestionMethods;
import com.example.luos.answer.network.ResultCode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by luos on 2016/11/29.
 */

public class HttpMethods {
    public static final String BASEURL = "http://192.168.130.224:8080";
    private static final int DEFAULT_TIMEOUT = 20;

    private Retrofit retrofit;
    private QuestionAPI questionService;

    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        questionService = retrofit.create(QuestionAPI.class);
    }

    private static class SingleHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingleHolder.INSTANCE;
    }

    public void getQuestionList(int userid, Subscriber<ArrayList<Question>> subscriber) {
        questionService.getQuestionList(userid)
                .map(new HttpResultFunc<ArrayList<Question>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T>{

        @Override
        public T call(HttpResult<T> httpResult) {
            Log.d("Http:","execute Call");
            if (httpResult.getError_code() != ResultCode.SUCCESS) {
                try {
                    throw new ApiException(httpResult.getError_code());
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
            return httpResult.getResult();
        }
    }

}
