package com.example.luos.answer.ui.IView;

import com.example.luos.answer.module.Answer;

import java.util.ArrayList;

/**
 * Created by luos on 2016/12/28.
 */

public interface IAnswerListView {
    void setDialogOfMessage(String message);
    void dismissSwipeRefreshLayout();
    void setAnswerList(ArrayList<Answer> answers);
    void updateAnswerList();
}
