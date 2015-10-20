package com.nabun_upgrade.utility;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nabun_upgrade.nabun.R;

/**
 * Created by admin on 10/20/2015.
 */
public class CareerStaffDialog extends Dialog implements AdapterView.OnItemClickListener {

    private Context mContext;
    private ListView listView;
    private String[] mData =  {"one", "two", "three", "four"};

    public CareerStaffDialog(Context context) {
        super(context);
        this.mContext = context;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dismiss();
        Toast.makeText(mContext, "Click : " + mData[position],Toast.LENGTH_LONG).show();
    }
}
