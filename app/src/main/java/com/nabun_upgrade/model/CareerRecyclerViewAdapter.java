package com.nabun_upgrade.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nabun_upgrade.nabun.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tar on 10/6/15 AD.
 */
public class CareerRecyclerViewAdapter extends RecyclerView.Adapter<Activity> {

    public static final String KEY_ICON = "icon";
    public static final String KEY_COLOR = "color";
    public static final String KEY_TITLE = "title";

    protected List<Map<String, Integer>> mSampleList;

    @Override
    public Activity onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        Map<String, Integer> map;
        mSampleList = new ArrayList<>();

        int[] icons = {
                R.drawable.icon_1,
                R.drawable.icon_2,
                R.drawable.icon_3};

        int[] colors = {
                R.color.saffron,
                R.color.eggplant,
                R.color.sienna};

        int[] titles = {
                R.string.home_about,
                R.string.home_vision,
                R.string.home_mission};

        for (int i = 0; i < icons.length; i++) {
            map = new HashMap<>();
            map.put(KEY_ICON, icons[i]);
            map.put(KEY_COLOR, colors[i]);
            //map.put((KEY_TITLE, titles[i]);
            mSampleList.add(map);
        }
        return new Activity(view);
    }

    @Override
    public void onBindViewHolder(Activity activity, int i) {
        Map<String, Integer> data = mSampleList.get(i);
        activity.bindData(data);
    }

    @Override
    public int getItemCount() {
        return mSampleList.size();
    }
}
