package com.nabun_upgrade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.nabun_upgrade.model.Staff;
import com.nabun_upgrade.nabun.R;
import java.util.ArrayList;

/**
 * Created by admin on 10/20/2015.
 */
public class StaffAdapter extends ArrayAdapter<Staff> {

    private LayoutInflater inflater;
    private ArrayList<Staff> mData = new ArrayList<>();

    public StaffAdapter(Context context, ArrayList<Staff> data) {
        super(context, 0, data);
        this.inflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null || convertView.getTag() == null) {
            convertView = inflater.inflate(R.layout.career_staff_list_item, parent, false);
            holder.nickname = (TextView) convertView.findViewById(R.id.nickname);
            holder.phone = (TextView) convertView.findViewById(R.id.phone);
            convertView.setTag(holder);
        }

        final Staff currentData = mData.get(position);
        if (currentData != null) {
            holder = (ViewHolder) convertView.getTag();
            holder.nickname.setText(currentData.getNickname());
            holder.phone.setText(currentData.getPhone());
        }
        return convertView;
    }

    class ViewHolder {
        TextView nickname;
        TextView phone;
    }
}
