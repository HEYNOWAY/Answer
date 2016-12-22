package com.example.luos.answer.network.HttpMethods;

import com.example.luos.answer.module.Commend;
import com.example.luos.answer.module.HttpResult;
import com.example.luos.answer.network.API.CommendAPI;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by luos on 2016/12/1.
 */

public class HttpCommendMethods {
    private CommendAPI commendService;

    public HttpCommendMethods(){
        commendService = HttpPrepared.getInstance().getCommendAPI();
    }

    public void addCommend(Commend commend, Subscriber<String> subscriber){
        commendService.addCommend(commend)
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

    public void getCommendList(int commendid, Subscriber<List<Commend>> subscriber){
        commendService.getCommendList(commendid)
                .map(new Func1<HttpResult<List<Commend>>, List<Commend>>() {
                    @Override
                    public List<Commend> call(HttpResult<List<Commend>> listHttpResult) {
                        //do something....
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void deleteCommend(int commendid, Subscriber<String> subscriber){
        commendService.deleteCommend(commendid)
                .map(new Func1<HttpResult<String>, String>() {
                    @Override
                    public String call(HttpResult<String> stringHttpResult) {
                        //do something..
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void agreeCommend(int userid, int commendid, Subscriber<String> subscriber){
        commendService.agreeCommend(userid,commendid)
                .map(new Func1<HttpResult<String>, String>() {
                    @Override
                    public String call(HttpResult<String> stringHttpResult) {
                        //do something....
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
