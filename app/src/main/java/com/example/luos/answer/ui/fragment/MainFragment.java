package com.example.luos.answer.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luos.answer.DoRefreshListener;
import com.example.luos.answer.adapter.QuestionListRecyclerViewAdapter;
import com.example.luos.answer.module.Question;
import com.example.luos.answer.OnRecyclerViewItemClickListener;
import com.example.luos.answer.ui.activity.AnswerListActivity;
import com.example.luos.demofortest.R;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements OnRecyclerViewItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {
    public static final String EXTRA_ITEM = "com.exmaple.luos.demofortest.item";
    private static final String TAG = "MainFrament";
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Question> questionList = new ArrayList<>();
    private QuestionListRecyclerViewAdapter adapter;
    private DoRefreshListener doRefreshListener;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new QuestionListRecyclerViewAdapter(getActivity(),questionList);
        adapter.setOnItemClickListener(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.question_list_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(adapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        return v;
    }


    @Override
    public void OnItemClick(final View view, Object data) {
        switch (view.getId()){
            case R.id.card_view:
                Intent intent = new Intent(getActivity(), AnswerListActivity.class);
                intent.putExtra(EXTRA_ITEM,(Question)data);
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
        doRefreshListener.doRefresh();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
}
