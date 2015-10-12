package com.nabun_upgrade.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nabun_upgrade.nabun.CareerViewActivity;
import com.nabun_upgrade.nabun.R;
import com.nabun_upgrade.utility.CustomSelectListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 9/24/2015.
 */
public class HomeFragment extends Fragment {

    public static final String KEY_ICON = "icon";
    public static final String KEY_COLOR = "color";
    public static final String KEY_TITLE = "title";
    public static final String KEY_SUMMARY = "summary";

    protected List<Map<String, Integer>> mHomeList;

    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Map<String, Integer> map;
        mHomeList = new ArrayList<>();

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

        int[] summary = {
                R.string.home_sub_about,
                R.string.home_sub_vision,
                R.string.home_sub_mission};

        for (int i = 0; i < icons.length; i++) {
            map = new HashMap<>();
            map.put(KEY_ICON, icons[i]);
            map.put(KEY_COLOR, colors[i]);
            map.put(KEY_TITLE, titles[i]);
            map.put(KEY_SUMMARY, summary[i]);
            mHomeList.add(map);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setAdapter(new HomeAdapter(getActivity(), R.layout.home_list_item, mHomeList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CustomSelectListView.class);
                getActivity().startActivity(intent);
            }
        });

        return rootView;
    }

    class HomeAdapter extends ArrayAdapter<Map<String, Integer>> {

        public static final String KEY_ICON = "icon";
        public static final String KEY_COLOR = "color";
        public static final String KEY_TITLE = "title";
        public static final String KEY_SUMMARY = "summary";

        private final LayoutInflater mInflater;
        private final List<Map<String, Integer>> mData;

        public HomeAdapter(Context context, int layoutResourceId, List<Map<String, Integer>> data) {
            super(context, layoutResourceId, data);
            mData = data;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.home_list_item, parent, false);
                viewHolder.imageViewIcon = (ImageView) convertView.findViewById(R.id.image_icon);
                viewHolder.titleView = (TextView) convertView.findViewById(R.id.title);
                viewHolder.summaryView = (TextView) convertView.findViewById(R.id.summary);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.imageViewIcon.setImageResource(mData.get(position).get(KEY_ICON));
            convertView.setBackgroundResource(mData.get(position).get(KEY_COLOR));
            viewHolder.titleView.setText(mData.get(position).get(KEY_TITLE));
            viewHolder.summaryView.setText(mData.get(position).get(KEY_SUMMARY));

            return convertView;
        }

        class ViewHolder {
            ImageView imageViewIcon;
            TextView titleView;
            TextView summaryView;
        }

    }

}
