package com.example.luos.answer;

import android.view.View;
import com.example.luos.answer.module.Question;

/**
 * Created by luos on 2016/11/30.
 */

public interface OnRecyclerViewItemClickListener {
    void OnItemClick(View view, Question data);
}
