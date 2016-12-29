package com.example.luos.answer.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luos.answer.OnRecyclerViewItemClickListener;
import com.example.luos.answer.module.Answer;
import com.example.luos.answer.module.Question;
import com.example.luos.answer.ui.fragment.MainFragment;
import com.example.luos.demofortest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luos on 2016/12/26.
 */

public class AnswerListRecyclerViewAdapter extends RecyclerView.Adapter<AnswerListRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private List<Answer> mDataList = new ArrayList<>();
    private Context mContext;
    private OnRecyclerViewItemClickListener mItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView answerser;
        public TextView answer;
        public CardView mCardVew;

        public ViewHolder(View itemView) {
            super(itemView);
            answerser = (TextView) itemView.findViewById(R.id.answerser_textView);
            answer = (TextView) itemView.findViewById(R.id.answer_textView);
            mCardVew = (CardView) itemView.findViewById(R.id.answer_card_view);
        }
    }

    public AnswerListRecyclerViewAdapter(Context context,List<Answer> dataList){
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.answer_list_cardview,null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Answer answer = mDataList.get(position);
        holder.answerser.setText(answer.getUserName());
        holder.answer.setText(answer.getContent());
        holder.mCardVew.setOnClickListener(this);
        holder.mCardVew.setTag(answer);
    }

    @Override
    public void onClick(View v) {
        Answer item = (Answer) v.getTag();
        mItemClickListener.OnItemClick(v,item);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setmDataList(List<Answer> list){
        this.mDataList = list;
    }

    public void updateDataList(List<Answer> list){
        setmDataList(list);
        notifyDataSetChanged();
    }
}
