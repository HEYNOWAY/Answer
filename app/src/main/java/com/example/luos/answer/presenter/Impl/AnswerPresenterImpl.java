package com.example.luos.answer.presenter.Impl;

import com.example.luos.answer.module.Answer;
import com.example.luos.answer.network.HttpMethods.HttpAnswerMethods;
import com.example.luos.answer.presenter.IPresenter.AnswerPresenter;
import com.example.luos.answer.ui.IView.IAnswerListView;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by luos on 2016/12/28.
 */

public class AnswerPresenterImpl implements AnswerPresenter {
    private HttpAnswerMethods answerMethods = new HttpAnswerMethods();
    private IAnswerListView mIAnswerListView;
    private Subscriber<ArrayList<Answer>> subscriber;

    public AnswerPresenterImpl(IAnswerListView view){
        mIAnswerListView = view;
    }

    @Override
    public void getAnswerList(int questionId) {
        subscriber = new Subscriber<ArrayList<Answer>>() {
            @Override
            public void onCompleted() {
                mIAnswerListView.dismissSwipeRefreshLayout();
                mIAnswerListView.updateAnswerList();
            }

            @Override
            public void onError(Throwable e) {
                mIAnswerListView.dismissSwipeRefreshLayout();
                mIAnswerListView.setDialogOfMessage(e.getMessage());
            }

            @Override
            public void onNext(ArrayList<Answer> answers) {
                mIAnswerListView.dismissSwipeRefreshLayout();
                if(answers!=null){
                    mIAnswerListView.setAnswerList(answers);
                }
            }
        };
        answerMethods.getAnswerList(questionId,subscriber);
    }

    @Override
    public void unSubscribe() {
        if(!subscriber.isUnsubscribed()){
            unSubscribe();
        }
    }
}
