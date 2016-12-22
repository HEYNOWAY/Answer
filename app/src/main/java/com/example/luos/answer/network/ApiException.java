package com.example.luos.answer.network;

/**
 * Created by luos on 2016/11/29.
 */

public class ApiException extends Exception {

    public static final int USER_NOT_EXIST = 101;
    public static final int USERNAME_OR_PASSWORD_ERROR = 102;
    public static final int REQUEST_ERROR = 201;

    public ApiException(int resultCode){
        this(getApiException(resultCode));
    }

    public ApiException(String detialMessage){
        super(detialMessage);
    }


    private static String getApiException(int code){
        String message = "";
        switch (code){
            case USER_NOT_EXIST:
                message = "用户不存在";
                break;
            case USERNAME_OR_PASSWORD_ERROR:
                message = "用户名或密码错误";
                break;
            default:
                message = "未知错误";
        }
        return message;
    }


}
