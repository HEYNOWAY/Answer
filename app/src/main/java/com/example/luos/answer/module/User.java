package com.example.luos.answer.module;

/**
 * Created by luos on 2016/11/30.
 */

public class User {
    private Integer user_id;

    private String username;

    private String nickname;

    private String password;

    private String email;

    private String photo;

    private String create_time;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getCreateTime() {
        return create_time;
    }

    public void setCreateTime(String createTime) {
        this.create_time = createTime;
    }

    @Override
    public String toString() {
        return "UserName:"+username+"\n"
                +"NickName:"+nickname+"\n"
                +"Email:"+email+"\n"
                +"Photo:"+photo+"\n"
                +"CreateTime:"+create_time;
    }
}
