package com.example.luos.answer.network.HttpMethods;

import com.example.luos.answer.module.Answer;
import com.example.luos.answer.module.HttpResult;
import com.example.luos.answer.network.API.AnswerAPI;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by luos on 2016/12/1.
 */

public class HttpAnswerMethods {
    private AnswerAPI answerService;

    public HttpAnswerMethods(){
        answerService = HttpPrepared.getInstance().getAnswerAPI();
    }

    public void getAnswerList(int questionid, Subscriber<ArrayList<Answer>> subscriber){
        answerService.getAnswerList(questionid)
                .map(new HttpResultFunc<ArrayList<Answer>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void addAnswer(Answer answer, Subscriber<String> subscriber){
        answerService.addAnswer(answer)
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

    public void modifyAnswer(int userid, int answerid, Answer answer, Subscriber<String> subscriber){
        answerService.modifyAnswer(userid,answerid,answer)
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

    public void getAnswerDetail(int answerid,Subscriber<Answer> subscriber){
        answerService.getAnswerDetail(answerid)
                .map(new Func1<HttpResult<Answer>, Answer>() {
                    @Override
                    public Answer call(HttpResult<Answer> answerHttpResult) {
                        //do something...
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void deleteAnswer(int answerid,Subscriber<String> subscriber){
        answerService.deleteAnswer(answerid)
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

    public void collectedAnswer(int userid,int answerid,Subscriber<String> subscriber){
        answerService.collectedAnswer(userid,answerid)
                .map(new HttpResultFunc<String>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public void agreeAnswer(int userid, int answerid,Subscriber<String> subscriber){
        answerService.agreeAnswer(userid,answerid)
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
}
