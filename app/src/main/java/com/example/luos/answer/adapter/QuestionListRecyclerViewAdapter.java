package com.example.luos.answer.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luos.answer.module.Question;
import com.example.luos.answer.ui.fragment.MainFragment;
import com.example.luos.answer.OnRecyclerViewItemClickListener;
import com.example.luos.demofortest.R;

import java.util.ArrayList;
import java.util.List;


public class QuestionListRecyclerViewAdapter extends RecyclerView.Adapter<QuestionListRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "QuestionListRecyclerViewAdapter";
    private List<Question> mDataList = new ArrayList<>();
    private Context context;
    private OnRecyclerViewItemClickListener mItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView;
        public TextView mContentTextView;
        public CardView mCardView;


        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.title_textview);
            mContentTextView = (TextView) itemView.findViewById(R.id.content_textview);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    public QuestionListRecyclerViewAdapter(Context context, List<Question> list) {
        this.mDataList = list;
        this.context = context;
    }

    public void setmDataList(List<Question> list){
        this.mDataList = list;
    }

    public void updateDataList(List<Question> list){
        setmDataList(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Question currentItem = mDataList.get(position);
        holder.mTitleTextView.setText(currentItem.getTitle());
        holder.mContentTextView.setText(currentItem.getQuestion_desc());
        holder.mCardView.setOnClickListener(this);
        holder.mCardView.setTag(currentItem);
    }

    @Override
    public void onClick(View v) {
        Question item = (Question) v.getTag();
        mItemClickListener.OnItemClick(v, item);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    public void setOnItemClickListener(MainFragment listener) {
        mItemClickListener = listener;
    }


}
