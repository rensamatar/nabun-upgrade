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
    public static final String EVENT = BASE_URL + "event";
    public static final String CAREER = "http://www.json-generator.com/api/json/get/ccVztGYCCq?indent=2";
    public static final String CAREER_DETAIL = BASE_URL + "career/view/";
    public static final String DUMMY = "http://www.json-generator.com/api/json/get/bNXmimonfS?indent=2";
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
