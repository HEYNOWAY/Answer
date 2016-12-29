package com.example.luos.answer.network.HttpMethods;

import android.util.Log;

import com.example.luos.answer.module.HttpResult;
import com.example.luos.answer.network.ApiException;
import com.example.luos.answer.network.ResultCode;

import rx.functions.Func1;

/**
 * Created by luos on 2016/12/28.
 */

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> httpResult) {
        Log.d("Http:", "execute Call");
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
