package com.nabun_upgrade.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nabun_upgrade.adapter.DemoPageAdapter;
import com.nabun_upgrade.nabun.R;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by admin on 10/21/2015.
 */
public class IntroFragment extends Fragment {

    public IntroFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_intro, container, false);

        // DEFAULT
        ViewPager defaultViewpager = (ViewPager) rootView.findViewById(R.id.viewpager_default);
        CircleIndicator defaultIndicator = (CircleIndicator) rootView.findViewById(R.id.indicator_default);
        DemoPageAdapter defaultPagerAdapter = new DemoPageAdapter(getFragmentManager());
        defaultViewpager.setAdapter(defaultPagerAdapter);
        defaultIndicator.setViewPager(defaultViewpager);

        return rootView;
    }
}
