package com.example.luos.answer.test;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import com.example.luos.answer.ProgresserCancelListener;
import com.example.luos.answer.ui.IView.IMainView;
import com.example.luos.answer.ui.fragment.MainFragment;
import com.example.luos.answer.ui.widget.ProgressDialogHandler;

import rx.Subscriber;

/**
 * Created by luos on 2016/11/29.
 */

public class ProcesserSuscriber<T> extends Subscriber<T> implements ProgresserCancelListener {
    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Context mContext;
    private MainFragment fragment;

    public ProcesserSuscriber(SubscriberOnNextListener subscriberOnNextListener, Context context){
        mSubscriberOnNextListener = subscriberOnNextListener;
        mContext = context;
        mProgressDialogHandler = new ProgressDialogHandler(mContext,true,this);
    }

    public void setFragment(MainFragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
        fragment.dismissSwipeRefreshLayout();
        Toast.makeText(mContext,"get movie completed!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        fragment.dismissSwipeRefreshLayout();
        Toast.makeText(mContext,"error:"+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(T t) {
        mSubscriberOnNextListener.onNext(t);
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
    @Override
    public void onCancelProgress() {
        if(!this.isUnsubscribed()){
            this.unsubscribe();
        }
    }
}
