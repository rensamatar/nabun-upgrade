package com.nabun_upgrade.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.nabun_upgrade.nabun.R;

import java.util.ArrayList;

/**
 * Created by tar on 10/6/15 AD.
 */
public class CareerListAdapter extends ArrayAdapter<Career> {

    private Context context;
    private ArrayList<Career> dataItems;
    private LayoutInflater mInflater;

    public CareerListAdapter(Context context, ArrayList<Career> dataItems) {
        super(context, 0, dataItems);
        this.context = context;
        this.dataItems = dataItems;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || convertView.getTag() == null) {
            ViewHolder holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            holder.imageViewIcon = (ImageView) convertView.findViewById(R.id.image_view_icon);
            convertView.setTag(holder);
        }

        final Career data = dataItems.get(position);
        if (data != null) {
            final ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.imageViewIcon.setImageResource(Integer.parseInt(dataItems.get(position).getBanner()));
        }

        return convertView;
    }

    class ViewHolder {
        ImageView imageViewIcon;
    }
}
