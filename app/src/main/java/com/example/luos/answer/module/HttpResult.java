package com.example.luos.answer.module;

/**
 * Created by luos on 2016/11/30.
 */

public class HttpResult<T> {
    private int error_code;
    private String reason;
    private T result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("error_code:"+error_code+"\n");
        sb.append("reason:"+reason+"\n");
        if(result!=null){
            sb.append("result:"+result.toString());
        } else {
            sb.append("result:null");
        }
        return sb.toString();
    }
}
