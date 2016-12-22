package com.example.luos.answer.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luos.answer.DoRefreshListener;
import com.example.luos.answer.module.Question;
import com.example.luos.answer.test.HttpMethods;
import com.example.luos.answer.test.ProcesserSuscriber;
import com.example.luos.answer.test.SubscriberOnNextListener;
import com.example.luos.answer.ui.activity.AnswerContentActivity;
import com.example.luos.answer.OnRecyclerViewItemClickListener;
import com.example.luos.answer.ui.activity.MainActivity;
import com.example.luos.demofortest.R;
import com.example.luos.answer.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements OnRecyclerViewItemClickListener,
        SwipeRefreshLayout.OnRefreshListener, SubscriberOnNextListener {
    public static final String EXTRA_ITEM = "com.exmaple.luos.demofortest.item";
    private static final String TAG = "MainFrament";
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Question> questionList = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private DoRefreshListener doRefreshListener;
    private ProcesserSuscriber suscriber;
    private SubscriberOnNextListener onNextListener;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new RecyclerViewAdapter(getActivity(),questionList);
        adapter.setOnItemClickListener(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(adapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        onNextListener = new SubscriberOnNextListener<List<Question>>() {
            @Override
            public void onNext(List<Question> subjects) {
                Log.d("subscriber","onNext()");
                dismissSwipeRefreshLayout();
                setQuestionList(subjects);
                updateQuestionList();
            }
        };

        return v;
    }


    @Override
    public void OnItemClick(final View view, Question data) {
        switch (view.getId()){
            case R.id.card_view:
                Intent intent = new Intent(getActivity(),AnswerContentActivity.class);
                intent.putExtra(EXTRA_ITEM,data);
                startActivity(intent);
                break;

        }
    }

    public void setQuestionList(List<Question> list){
        questionList = list;
    }

    public void updateQuestionList(){
        if(adapter!=null){
            adapter.updateDataList(questionList);
        }
    }

    public void setRefreshListener(DoRefreshListener doRefreshListener){
        this.doRefreshListener = doRefreshListener;
    }

    public void dismissSwipeRefreshLayout(){
        if(mSwipeRefreshLayout!=null){
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void onRefresh() {
//        doRefreshListener.doRefresh();
        ProcesserSuscriber<ArrayList<Question>> processerSuscriber = new ProcesserSuscriber<ArrayList<Question>>(onNextListener,getActivity());
        processerSuscriber.setFragment(this);
        HttpMethods.getInstance().getQuestionList(1,processerSuscriber);
        if(mSwipeRefreshLayout!=null){
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onNext(Object o) {

    }
}
