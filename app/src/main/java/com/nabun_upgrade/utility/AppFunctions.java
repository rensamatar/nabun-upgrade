package com.nabun_upgrade.utility;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tar on 10/6/15 AD.
 */
public class AppFunctions {

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getCount();
        int rows = 0;

        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if( items > columns ){
            x = items/columns;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }

    public static Date getDateFromString(String dateString) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH':'mm':'ss", Locale.getDefault());
            return dateFormatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDateToString(Date eventDate) {
        Locale locale = Locale.US;
        String timeString = null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH':'mm':'ss", Locale.getDefault());
        timeString = String.format(locale, "%s", dateFormatter.format(eventDate));
        return timeString;
    }

    public static boolean checkJSONObjectForKey(String key, JSONObject object) {
        if (key != null && !key.equals("") && object != null && object != JSONObject.NULL) {
            try {
                Object value = object.get(key);
                if (value != null && value != JSONObject.NULL) {
                    return true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
