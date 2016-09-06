package com.example.anzhuo.weibo20.pageadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by anzhuo on 2016/8/24.
 */
public class PageAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    public PageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null ? 0 : list.size();
    }
}
