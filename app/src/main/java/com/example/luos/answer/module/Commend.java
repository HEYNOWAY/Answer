package com.example.luos.answer.module;

import java.util.Date;

/**
 * Created by luos on 2016/11/30.
 */

public class Commend {
    private int commendId;
    private Date createTime;
    private String content;
    private int answerId;
    private int userId;

    public int getCommendId() {
        return commendId;
    }

    public void setCommendId(int commendId) {
        this.commendId = commendId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
