package com.example.luos.answer.module;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by luos on 2016/11/30.
 */

public class Answer implements Parcelable {
    private int answerId;
    private String content;
    private Date createTime;
    private Date updateTime;
    private int questionId;
    private int userId;
    private String userName;
    private int agreeNum;

    protected Answer(Parcel in) {
        answerId = in.readInt();
        content = in.readString();
        questionId = in.readInt();
        userId = in.readInt();
        userName = in.readString();
        agreeNum = in.readInt();
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(int agreeNum) {
        this.agreeNum = agreeNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(answerId);
        dest.writeString(content);
        dest.writeInt(questionId);
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeInt(agreeNum);
    }
}
