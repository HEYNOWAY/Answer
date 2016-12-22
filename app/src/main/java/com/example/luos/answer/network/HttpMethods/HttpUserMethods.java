package com.example.luos.answer.network.HttpMethods;

import com.example.luos.answer.module.HttpResult;
import com.example.luos.answer.module.User;
import com.example.luos.answer.network.API.UserAPI;
import com.example.luos.answer.network.ApiException;
import com.example.luos.answer.network.ResultCode;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by luos on 2016/12/1.
 */

public class HttpUserMethods {
    private UserAPI userService;

    public HttpUserMethods(){
        userService = HttpPrepared.getInstance().getUserAPI();
    }

    public void login(String username, String password, Action1<User> subscriber){
        userService.login(username,password)
                .map(new Func1<HttpResult<User>, User>() {
                    @Override
                    public User call(HttpResult<User> userHttpResult) {
                        int error_code = userHttpResult.getError_code();
                        User user = new User();
                        switch (error_code){
                            case ResultCode.SUCCESS:
                                user =  userHttpResult.getResult();
                                break;
                            case ResultCode.USERNAME_OR_PASSWORD_ERROR:
                                try {
                                    throw new ApiException(ApiException.USERNAME_OR_PASSWORD_ERROR);
                                } catch (ApiException e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                        return user;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void register(User user,Subscriber<String> subscriber){
        userService.register(user)
                .map(new Func1<HttpResult<String>, String>() {
                    @Override
                    public String call(HttpResult<String> stringHttpResult) {
                        //do something here.....
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void modifyUserInfo(User user,Subscriber<String> subscriber){
        userService.modifyUserInfo(user)
                .map(new Func1<HttpResult<String>, String>() {
                    @Override
                    public String call(HttpResult<String> stringHttpResult) {
                        //do something here.....
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
