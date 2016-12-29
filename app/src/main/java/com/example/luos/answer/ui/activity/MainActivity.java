package com.example.luos.answer.ui.activity;

import android.content.DialogInterface;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.luos.answer.DoRefreshListener;
import com.example.luos.answer.ProgresserCancelListener;
import com.example.luos.answer.module.Question;
import com.example.luos.answer.presenter.IPresenter.QuestionPresenter;
import com.example.luos.answer.presenter.Impl.QuestionPresenterImpl;
import com.example.luos.answer.ui.IView.IMainView;
import com.example.luos.answer.ui.fragment.MainFragment;
import com.example.luos.answer.adapter.MyPagerAdapter;
import com.example.luos.answer.ui.widget.ProgressDialogHandler;
import com.example.luos.demofortest.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IMainView,ProgresserCancelListener,DoRefreshListener{
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private DrawerLayout mDarwerLayout;
    private ActionBarDrawerToggle mDarwerToggle;
    private NavigationView mNavigationView;
    private QuestionPresenter questionPresenter;
    private MainFragment hotFragment;
    private MainFragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDatas();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.home));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTabLayout = (TabLayout) findViewById(R.id.main_activity_tabs);
        mViewpager = (ViewPager) findViewById(R.id.main_activity_viwepager);
        setupViewPager();
        mDarwerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //关联toolbar和DrawerLayou
        mDarwerToggle = new ActionBarDrawerToggle(this,mDarwerLayout,toolbar,
                R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                drawerView.bringToFront();
                super.onDrawerOpened(drawerView);
            }
        };
        //同步状态
        mDarwerToggle.syncState();
        //DrawerLayout监听关联事件
        mDarwerLayout.addDrawerListener(mDarwerToggle);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawContent(mNavigationView);
    }

    private void setupViewPager() {
        MyPagerAdapter adpter = new MyPagerAdapter(getSupportFragmentManager());
        hotFragment = new MainFragment();
        lastFragment = new MainFragment();
        hotFragment.setRefreshListener(this);
        lastFragment.setRefreshListener(this);
        adpter.addFragment(hotFragment,getString(R.string.hotest));
        adpter.addFragment(lastFragment,getString(R.string.lastest));
        mViewpager.setAdapter(adpter);
        mTabLayout.setupWithViewPager(mViewpager);
    }

    private void initDatas() {
        ProgressDialogHandler progressDialoghandler = new ProgressDialogHandler(this,true,this);
        questionPresenter = new QuestionPresenterImpl(this,progressDialoghandler);
        questionPresenter.getHotestQuestionList(1);
        questionPresenter.getLastQuestionList(1);
    }

    private void setupDrawContent(final NavigationView mNavigationView) {
        Log.i(TAG,"setupDrawContent");
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Log.i(TAG,"menu onClick");
                switch (item.getItemId()){
                    case R.id.navigation_menu_example1:
                        Log.i(TAG,"menu1 onClick");
                        buildDialog("menu_example1");
                        //do something...
                        break;
                    case R.id.navigation_menu_example2:
                        Log.i(TAG,"menu1 onClick");
                        buildDialog("menu_example2");
                        //do something...
                        break;
                    case R.id.navigation_menu_example3:
                        Log.i(TAG,"menu1 onClick");
                        buildDialog("menu_example3");
                        //do something...
                        break;
                    case R.id.navigation_menu_example4:
                        Log.i(TAG,"menu1 onClick");
                        buildDialog("menu_example4");
                        //do something...
                        break;
                }
                item.setChecked(true);
                mDarwerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_setting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.menu_system:
                buildDialog("设置中心");
                //do something
                break;
            case R.id.menu_help:
                buildDialog("帮助中心");
                //do something
                break;
        }
        return true;
    }


    @Override
    public void setDialogOfMessage(String message) {
        this.buildDialog(message);
    }

    @Override
    public void setHotFragmentQuestionList(List<Question> list) {
        hotFragment.setQuestionList(list);
    }

    @Override
    public void updateHotFragmentQuestionList() {
        hotFragment.updateQuestionList();
    }

    @Override
    public void setLastFragmentQuestionList(List<Question> list) {
        lastFragment.setQuestionList(list);
    }

    @Override
    public void updateLastFragmentQuestionList() {
        lastFragment.updateQuestionList();
    }

    @Override
    public void dismissSwipeRefreshLayout() {
        if(mViewpager==null)
            return;
        switch (mViewpager.getCurrentItem()){
            case 0 :
                hotFragment.dismissSwipeRefreshLayout();
                break;
            case 1 :
                lastFragment.dismissSwipeRefreshLayout();
                break;
        }

    }

    @Override
    public void onCancelProgress() {
        questionPresenter.unSubscribe();
    }

    @Override
    public void doRefresh() {
        if(mViewpager==null)
            return;
        switch (mViewpager.getCurrentItem()){
            case 0 :
                questionPresenter.getHotestQuestionList(1);
                break;
            case 1 :
                questionPresenter.getLastQuestionList(1);
                break;
        }
    }
}
