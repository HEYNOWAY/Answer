package com.example.luos.answer.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.luos.answer.module.Question;
import com.example.luos.answer.ui.fragment.ContentFragment;
import com.example.luos.answer.ui.fragment.MainFragment;
import com.example.luos.answer.adapter.MyPagerAdapter;
import com.example.luos.demofortest.R;

public class AnswerContentActivity extends BaseActivity {
    private Toolbar mToolbar;
    private ImageView mImageView;
    private CollapsingToolbarLayout collapsingToolbar;
    private ViewPager mViewPager;
    private Question news;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_content_layout);
        initData();
        init();
    }

    private void initData() {
        Intent intent = getIntent();
        news = (Question) intent.getExtras().get(MainFragment.EXTRA_ITEM);
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.content_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbar.setTitle(news.getTitle());

        mViewPager = (ViewPager) findViewById(R.id.viwepager);
        setupAdapter(mViewPager);

    }

    private void  setupAdapter(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(ContentFragment.newInstance(news.getQuestion_desc()),null);
        mViewPager.setAdapter(adapter);
    }
}
