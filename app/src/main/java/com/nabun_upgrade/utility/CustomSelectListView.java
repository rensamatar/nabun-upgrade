package com.nabun_upgrade.utility;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nabun_upgrade.nabun.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 10/12/2015.
 */
public class CustomSelectListView extends Activity {

    public static final String _COLOR = "color";
    public static final String _TITLE = "title";
    public static final String _SYMBOL = "symbol";
    protected List<Map<String, Integer>> mStationList;
    private StationAdapter stationAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_select_list_view);

        Map<String, Integer> map;
        mStationList = new ArrayList<>();

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

        for (int i = 0; i < colors.length; i++) {
            map = new HashMap<>();
            map.put(_COLOR, colors[i]);
            map.put(_TITLE, titles[i]);
            map.put(_SYMBOL, summary[i]);
            mStationList.add(map);
        }

        stationAdapter = new StationAdapter(this, R.layout.custom_select_list_view_item, mStationList);

        ListView listview = (ListView) findViewById(R.id.list_selected);
        listview.setAdapter(stationAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                finish();
            }
        });

        Log.d("", "station list " + mStationList.size());

    }

    class StationAdapter extends ArrayAdapter<Map<String, Integer>> {

        private final LayoutInflater mInflater;
        private final List<Map<String, Integer>> mData;

        public StationAdapter(Context context,int resID, List<Map<String, Integer>> data) {
            super(context, resID, data);
            mData = data;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.custom_select_list_view_item, parent, false);
                viewHolder.titleText = (TextView) convertView.findViewById(R.id.title_text);
                viewHolder.symbolText = (TextView) convertView.findViewById(R.id.symbol_text);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            convertView.setBackgroundResource(mData.get(position).get(_COLOR));
            viewHolder.titleText.setText(mData.get(position).get(_TITLE));
            viewHolder.symbolText.setText(mData.get(position).get(_SYMBOL));
            return convertView;
        }

        class ViewHolder {
            TextView titleText;
            TextView symbolText;
        }
    }
}
