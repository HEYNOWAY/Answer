package com.example.luos.answer.ui.IView;

import com.example.luos.answer.module.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luos on 2016/12/9.
 */

public interface IMainView {
    void setDialogOfMessage(String message);
    void setHotFragmentQuestionList(List<Question> list);
    void updateHotFragmentQuestionList();
    void setLastFragmentQuestionList(List<Question> list);
    void updateLastFragmentQuestionList();
    void dismissSwipeRefreshLayout();
}
