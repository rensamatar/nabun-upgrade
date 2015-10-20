package com.nabun_upgrade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.nabun_upgrade.model.Wage;
import com.nabun_upgrade.nabun.R;

import java.util.ArrayList;

/**
 * Created by admin on 10/13/2015.
 */
public class WageAdapter extends ArrayAdapter<Wage> {

    private LayoutInflater mInflater;
    private ArrayList<Wage> wageList = new ArrayList<>();

    public WageAdapter(Context context, ArrayList<Wage> allWage) {
        super(context, 0, allWage);
        mInflater = LayoutInflater.from(context);
        wageList = allWage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();

        if (convertView == null || convertView.getTag() == null) {
            convertView = mInflater.inflate(R.layout.wage_list_item, parent, false);
            holder.wageTitle = (TextView) convertView.findViewById(R.id.wage_title);
            convertView.setTag(holder);
        }

        final Wage currentWage = wageList.get(position);
        if (currentWage != null) {
            holder = (ViewHolder) convertView.getTag();
            holder.wageTitle.setText(currentWage.getTitle());
        }

        return convertView;
    }

    class ViewHolder {
        TextView wageTitle;
    }

}
