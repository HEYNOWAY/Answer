package com.example.luos.answer.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.luos.answer.OnRecyclerViewItemClickListener;
import com.example.luos.answer.adapter.AnswerListRecyclerViewAdapter;
import com.example.luos.answer.module.Answer;
import com.example.luos.answer.module.Question;
import com.example.luos.answer.presenter.IPresenter.AnswerPresenter;
import com.example.luos.answer.presenter.Impl.AnswerPresenterImpl;
import com.example.luos.answer.ui.IView.IAnswerListView;
import com.example.luos.answer.ui.fragment.MainFragment;
import com.example.luos.demofortest.R;

import java.util.ArrayList;
import java.util.List;

public class AnswerListActivity extends BaseActivity implements SwipeRefreshLayout
        .OnRefreshListener,OnRecyclerViewItemClickListener,IAnswerListView {
    public static final String ANSWER_ITEM = "answer_item";
    private TextView questionTitle;
    private TextView questionDesc;
    private RecyclerView answerListRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Question question;
    private AnswerListRecyclerViewAdapter recyclerViewAdapter;
    private List<Answer> mAnswerList = new ArrayList<>();
    private AnswerPresenter answerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_list);
        initView();
        initDatas();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        answerPresenter.unSubscribe();
    }

    private void initView() {
        questionTitle = (TextView) findViewById(R.id.question_title_textView);
        questionDesc = (TextView) findViewById(R.id.question_desc_textView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.answerlist_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        answerListRecyclerView = (RecyclerView) findViewById(R.id.question_list_recyclerview);
        recyclerViewAdapter = new AnswerListRecyclerViewAdapter(this,mAnswerList);
        recyclerViewAdapter.setOnItemClickListener(this);
        answerListRecyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initDatas() {
        Intent datas = getIntent();
        question = datas.getParcelableExtra(MainFragment.EXTRA_ITEM);
        questionTitle.setText(question.getTitle());
        questionDesc.setText(question.getQuestion_desc());
        answerPresenter = new AnswerPresenterImpl(this);
        answerPresenter.getAnswerList(question.getQuestion_id());
    }


    @Override
    public void onRefresh() {
        answerPresenter.getAnswerList(question.getQuestion_id());
    }

    @Override
    public void OnItemClick(View view, Object data) {
        Answer answer = (Answer)data;
        Intent intent = new Intent(this,AnswerContentActivity.class);
        intent.putExtra(ANSWER_ITEM,answer);
    }

    @Override
    public void setDialogOfMessage(String message) {
        this.buildDialog(message);
    }

    @Override
    public void dismissSwipeRefreshLayout() {
        if(mSwipeRefreshLayout!=null)
            mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setAnswerList(ArrayList<Answer> answers) {
        mAnswerList = answers;
    }

    @Override
    public void updateAnswerList() {
        recyclerViewAdapter.updateDataList(mAnswerList);
    }
}
