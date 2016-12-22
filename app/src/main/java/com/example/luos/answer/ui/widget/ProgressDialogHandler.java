package com.example.luos.answer.ui.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.example.luos.answer.ProgresserCancelListener;

/**
 * Created by luos on 2016/11/29.
 */

public class ProgressDialogHandler extends Handler {
    public static final int SHOW_DIALOG = 1;
    public static final int DISMISS_DIALOG = 2;

    private ProgressDialog pd;

    private ProgresserCancelListener mProgressCancelListener;
    private Context mContext;
    private Boolean cancelable;

    public ProgressDialogHandler(Context context, Boolean flag, ProgresserCancelListener progresserCancelListener){
        mContext = context;
        cancelable = flag;
        mProgressCancelListener = progresserCancelListener;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case  SHOW_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

    private void initProgressDialog() {
        if(pd==null){
            pd = new ProgressDialog(mContext);
            pd.setCancelable(cancelable);
            pd.setMessage("正在加载中");
            if(cancelable){
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }
            if(!pd.isShowing()){
                pd.show();
            }
        }
    }

    private void dismissProgressDialog(){
        if(pd!=null){
            pd.dismiss();
            pd = null;
        }
    }
}
