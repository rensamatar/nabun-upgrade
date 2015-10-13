package com.nabun_upgrade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nabun_upgrade.model.Wage;
import com.nabun_upgrade.nabun.R;

/**
 * Created by admin on 10/13/2015.
 */
public class WageAdapter extends ArrayAdapter<Wage> {

    private LayoutInflater mInflater;
    private ArrayAdapter<Wage> wageAdapter;

    public WageAdapter(Context context, int resource, ArrayAdapter<Wage> mWage) {
        super(context, resource);
        mInflater = LayoutInflater.from(context);
        wageAdapter = mWage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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

//        viewHolder.imageViewIcon.setImageResource(mData.get(position).get(KEY_ICON));
//        convertView.setBackgroundResource(mData.get(position).get(KEY_COLOR));
//        viewHolder.titleView.setText(mData.get(position).get(KEY_TITLE));
//        viewHolder.summaryView.setText(mData.get(position).get(KEY_SUMMARY));

        return convertView;
    }

    class ViewHolder {
        ImageView imageViewIcon;
        TextView titleView;
        TextView summaryView;
    }

}
