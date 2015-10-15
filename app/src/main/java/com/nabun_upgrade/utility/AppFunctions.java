package com.nabun_upgrade.utility;

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
