package com.nabun_upgrade.nabun;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nabun_upgrade.adapter.PhotoPagerAdapter;
import com.nabun_upgrade.config.Application;
import com.nabun_upgrade.model.Photos;
import com.nabun_upgrade.utility.CustomProgressDialog;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by admin on 10/19/2015.
 */
public class PhotoViewActivity extends Activity {

    public static final String EVENT_ID = "event_id";
    public static final String REQ_EVENT_PHOTOS = "req_photos";
    private JsonArrayRequest requestArray;
    private CustomProgressDialog pDialog;
    private String eventId;
    private ViewPager pager;
    private PhotoPagerAdapter photoAdapter;
    private ArrayList<Photos> allPhoto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        pDialog = new CustomProgressDialog(this);
        eventId = getIntent().getStringExtra(EVENT_ID);

        initData();

        pager = (ViewPager) findViewById(R.id.pager);
        photoAdapter = new PhotoPagerAdapter(this, allPhoto);
    }

    private void initData() {
        pDialog.show();
        requestArray = new JsonArrayRequest(Application.EVENT_PHOTO + eventId, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(Application.TAG, response.toString());
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        Photos photos = new Photos();
                        photos.setId(obj.optString("id"));
                        photos.setDescription(obj.optString("description"));
                        photos.setPhoto(obj.optString("photo"));
                        allPhoto.add(photos);
                    }
                    pager.setAdapter(photoAdapter);

                    Log.d(Application.TAG, "photosList : " + allPhoto.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Application.TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });
        Application.getInstance().addToRequestQueue(requestArray, REQ_EVENT_PHOTOS);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_open_scal, R.anim.activity_close_translate);
    }
}
