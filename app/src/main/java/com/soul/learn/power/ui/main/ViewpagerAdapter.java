package com.soul.learn.power.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjianhua on 2017/6/13.
 */

public class ViewpagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<Fragment>();

    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewpagerAdapter(FragmentManager fm, List<Fragment> lists) {
        super(fm);
        this.fragmentList = lists;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
