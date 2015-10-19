package com.nabun_upgrade.config;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by admin on 10/6/2015.
 */
public class Application extends android.app.Application {

    public static final String TAG = Application.class.getSimpleName();
    public static final String BASE_URL = "http://nabun-upgrade.com/api/";
    public static final String EVENT = BASE_URL + "event/";
    public static final String EVENT_PHOTO = BASE_URL + "event/photos/";
    public static final String CAREER = BASE_URL + "career/";
    public static final String CAREER_WAGE = BASE_URL + "career/wage/";
    public static final String CAREER_STAFF = BASE_URL + "career/staff/";
    private static Application mInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized Application getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }


}
