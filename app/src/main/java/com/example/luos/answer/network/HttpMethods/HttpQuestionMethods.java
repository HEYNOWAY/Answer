package com.example.luos.answer.network.HttpMethods;


import com.example.luos.answer.module.HttpResult;
import com.example.luos.answer.module.Question;
import com.example.luos.answer.network.API.QuestionAPI;
import com.example.luos.answer.network.ApiException;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;



/**
 * Created by luos on 2016/12/1.
 */

public class HttpQuestionMethods {
    private QuestionAPI questionService;

    public HttpQuestionMethods(){
        questionService = HttpPrepared.getInstance().getQuestionAPI();
    }

    public void getLastestQuestionList(int userid, Subscriber<ArrayList<Question>> subscriber){
        questionService.getLastestQuestionList(userid)
                .map(new HttpResultFunc<ArrayList<Question>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getHotestQuestionList(int userid, Subscriber<ArrayList<Question>> subscriber){
        questionService.getHostestQuestionList(userid)
                .map(new HttpResultFunc<ArrayList<Question>>())
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


}
