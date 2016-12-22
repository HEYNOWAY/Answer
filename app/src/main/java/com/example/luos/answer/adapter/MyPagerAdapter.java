package com.example.luos.answer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luos on 2016/9/27.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragment = new ArrayList<>();
    private List<String> mTitle = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    public void  addFragment(Fragment fragment,String title){
        mFragment.add(fragment);
        mTitle.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
