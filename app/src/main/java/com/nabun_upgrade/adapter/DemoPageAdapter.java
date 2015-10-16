package com.nabun_upgrade.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nabun_upgrade.fragment.ColorFragment;

import java.util.Random;

/**
 * Created by admin on 10/16/2015.
 */

public class DemoPageAdapter extends FragmentPagerAdapter {

    private int pagerCount = 5;

    private Random random = new Random();

    public DemoPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public Fragment getItem(int i) {
        return ColorFragment.newInstance(0xff000000 | random.nextInt(0x00ffffff));
    }

    @Override public int getCount() {
        return pagerCount;
    }
}