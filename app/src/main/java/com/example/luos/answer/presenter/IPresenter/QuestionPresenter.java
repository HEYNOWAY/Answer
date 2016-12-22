package com.example.luos.answer.presenter.IPresenter;

import com.example.luos.answer.module.Question;

import java.util.List;

/**
 * Created by luos on 2016/12/9.
 */

public interface QuestionPresenter {
    void getQuestionList(int userid);
    void unSubscribe();
}
