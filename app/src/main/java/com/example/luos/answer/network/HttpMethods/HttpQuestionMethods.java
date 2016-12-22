package com.example.luos.answer.network.HttpMethods;

import android.util.Log;

import com.example.luos.answer.module.Answer;
import com.example.luos.answer.module.HttpResult;
import com.example.luos.answer.module.Question;
import com.example.luos.answer.network.API.QuestionAPI;
import com.example.luos.answer.network.ApiException;
import com.example.luos.answer.network.ResultCode;

import java.util.ArrayList;


import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static java.lang.Thread.currentThread;

/**
 * Created by luos on 2016/12/1.
 */

public class HttpQuestionMethods {
    private QuestionAPI questionService;

    public HttpQuestionMethods(){
        questionService = HttpPrepared.getInstance().getQuestionAPI();
    }

    public void getQuestionList(int userid, Subscriber<ArrayList<Question>> subscriber){
        questionService.getQuestionList(userid)
                .map(new HttpResultFunc<ArrayList<Question>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getQuestionDetail(int questionid, Subscriber<ArrayList<Answer>> subscriber){
        questionService.getQuestionDetail(questionid)
                .map(new Func1<HttpResult<ArrayList<Answer>>, ArrayList<Answer>>() {
                    @Override
                    public ArrayList<Answer> call(HttpResult<ArrayList<Answer>> arrayListHttpResult) {
                        //doSomething
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

   public void askQuestion(Question question, Subscriber<String> subscriber){
       questionService.askQuestion(question)
               .map(new Func1<HttpResult<String>, String>() {
                   @Override
                   public String call(HttpResult<String> stringHttpResult) {
                       //do something
                       return null;
                   }
               })
               .subscribeOn(Schedulers.io())
               .unsubscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(subscriber);
   }

    public void concernQuestion(int userid,int questionid,Subscriber<String> subscriber){
        questionService.concernQuestion(userid,questionid)
                .map(new Func1<HttpResult<String>, String>() {
                    @Override
                    public String call(HttpResult<String> stringHttpResult) {
                        //do something
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void deleteQuestion(int questionid,Subscriber<String> subscriber){
        questionService.deleteQuestion(questionid)
                .map(new Func1<HttpResult<String>, String>() {
                    @Override
                    public String call(HttpResult<String> stringHttpResult) {
                        //do something...
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private void throwApiException(int code){
        try {
            throw new ApiException(code);
        } catch (ApiException e) {
            e.printStackTrace();
        }
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
