package com.example.luos.answer.presenter.Impl;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.luos.answer.ProgresserCancelListener;
import com.example.luos.answer.module.Question;
import com.example.luos.answer.network.HttpMethods.HttpQuestionMethods;
import com.example.luos.answer.presenter.IPresenter.QuestionPresenter;
import com.example.luos.answer.ui.IView.IMainView;
import com.example.luos.answer.ui.widget.ProgressDialogHandler;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by luos on 2016/12/9.
 */

public class QuestionPresenterImpl implements QuestionPresenter {

    private HttpQuestionMethods questionMethods = new HttpQuestionMethods();
    private IMainView mView;
    private ProgressDialogHandler mProgressDialogHandler;
    private Subscriber<ArrayList<Question>> subscriber;

    public QuestionPresenterImpl(IMainView view,ProgressDialogHandler handler){
        mView = view;
        mProgressDialogHandler = handler;

    }

    @Override
    public void getLastQuestionList(int userid) {
        subscriber = new Subscriber<ArrayList<Question>>() {

            @Override
            public void onStart() {
                super.onStart();
                Log.d("subscriber","onStart()");
                showProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("subscriber","onError()");
                mView.setDialogOfMessage(e.getMessage());
                mView.dismissSwipeRefreshLayout();
                dismissProgressDialog();
            }

            @Override
            public void onNext(ArrayList<Question> questions) {
                Log.d("subscriber","onNext()");
                dismissProgressDialog();
                mView.dismissSwipeRefreshLayout();
                if(questions!=null){
                    mView.setLastFragmentQuestionList(questions);
                }
            }

            @Override
            public void onCompleted() {
                mView.updateLastFragmentQuestionList();
                mView.dismissSwipeRefreshLayout();
                Log.d("subscriber","onCompleted()");

            }
        };
        questionMethods.getLastestQuestionList(userid, subscriber);
    }

    @Override
    public void getHotestQuestionList(int userid) {
        subscriber = new Subscriber<ArrayList<Question>>() {

            @Override
            public void onStart() {
                super.onStart();
                Log.d("subscriber","onStart()");
                showProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("subscriber","onError()");
                mView.setDialogOfMessage(e.getMessage());
                mView.dismissSwipeRefreshLayout();
                dismissProgressDialog();
            }

            @Override
            public void onNext(ArrayList<Question> questions) {
                Log.d("subscriber","onNext()");
                dismissProgressDialog();
                mView.dismissSwipeRefreshLayout();
                if(questions!=null){
                    mView.setHotFragmentQuestionList(questions);
                }
            }

            @Override
            public void onCompleted() {
                mView.updateHotFragmentQuestionList();
                mView.dismissSwipeRefreshLayout();
                Log.d("subscriber","onCompleted()");

            }
        };
        questionMethods.getHotestQuestionList(userid,subscriber);
    }

    @Override
    public void unSubscribe() {
        if(!subscriber.isUnsubscribed())
            subscriber.unsubscribe();
    }


    private void showProgressDialog() {
        if(mProgressDialogHandler!=null){
            Message message = mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_DIALOG);
            message.sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if(mProgressDialogHandler!=null){
            Message message = mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_DIALOG);
            message.sendToTarget();
            mProgressDialogHandler = null;
        }
    }
}
