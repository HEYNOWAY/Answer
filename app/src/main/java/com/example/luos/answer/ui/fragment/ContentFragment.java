package com.example.luos.answer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luos.demofortest.R;

/**
 * Created by luos on 2016/9/25.
 */
public class ContentFragment extends Fragment {
    private static final String EXTRA_TEXT = "extra_text";

    public static Fragment newInstance(String text){
        Fragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TEXT,text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content,container,false);
        TextView textView = (TextView) view.findViewById(R.id.contentInViewPager);
        String content = getArguments().getString(EXTRA_TEXT);
        textView.setText(content);
        return view;
    }
}
