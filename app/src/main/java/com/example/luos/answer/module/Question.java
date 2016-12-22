package com.example.luos.answer.module;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by luos on 2016/11/30.
 */

public class Question implements Parcelable{
    private int question_id;
    private String title;
    private String question_desc;
    private String question_type;
    private int owner_id;
    private int concern_count;
    private int answer_count;
    private String create_time;
    private String update_time;

    protected Question(Parcel in) {
        question_id = in.readInt();
        title = in.readString();
        question_desc = in.readString();
        question_type = in.readString();
        create_time = in.readString();
        owner_id = in.readInt();
        concern_count = in.readInt();
        answer_count = in.readInt();
    }


    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion_desc() {
        return question_desc;
    }

    public void setQuestion_desc(String question_desc) {
        this.question_desc = question_desc;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getConcern_count() {
        return concern_count;
    }

    public void setConcern_count(int concern_count) {
        this.concern_count = concern_count;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(int answer_count) {
        this.answer_count = answer_count;
    }


    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(question_id);
        dest.writeString(title);
        dest.writeString(question_desc);
        dest.writeString(question_type);
        dest.writeInt(owner_id);
        dest.writeInt(concern_count);
        dest.writeInt(answer_count);
        dest.writeString(create_time);
        dest.writeString(update_time);
    }
}
