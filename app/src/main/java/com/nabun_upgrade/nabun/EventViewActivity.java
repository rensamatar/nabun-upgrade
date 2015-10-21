package com.nabun_upgrade.nabun;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.nabun_upgrade.adapter.PhotoAdapter;
import com.nabun_upgrade.config.Application;
import com.nabun_upgrade.model.Photos;
import com.nabun_upgrade.utility.AppFunctions;
import com.nabun_upgrade.utility.CustomProgressDialog;
import com.nabun_upgrade.utility.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import me.grantland.widget.AutofitTextView;

/**
 * Created by admin on 10/7/2015.
 */
public class EventViewActivity extends AppCompatActivity {

    public static final String EVENT_DATA = "event_data";
    public static final String REQ_EVENT_BY_ID = "request_event_by_id";
    public static final String REQ_EVENT_PHOTO = "request_event_photo";
    private JsonObjectRequest requestObject;
    private JsonArrayRequest requestArray;
    private CustomProgressDialog pDialog;
    private String eventId;
    private CollapsingToolbarLayout collapsingToolbar;
    private NetworkImageView thumbnail;
    private AutofitTextView title;
    private AutofitTextView body;
    private ListView photoGridView;
    private ArrayList<Photos> photosList = new ArrayList<>();
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        pDialog = new CustomProgressDialog(this);
        eventId = getIntent().getStringExtra(EVENT_DATA);

        initData();
        initComponentData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initComponentData() {
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        thumbnail = (NetworkImageView) findViewById(R.id.event_banner);
        title = (AutofitTextView) findViewById(R.id.event_title);
        body = (AutofitTextView) findViewById(R.id.event_body);

        // Photos
        photoAdapter = new PhotoAdapter(this, photosList);
        photoGridView = (ListView) findViewById(R.id.listView);
        photoGridView.setAdapter(photoAdapter);
        photoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(EventViewActivity.this, PhotoViewActivity.class);
                intent.putExtra(PhotoViewActivity.EVENT_ID, eventId);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scal);
            }
        });
    }

    private void initData() {
        requestObject = new JsonObjectRequest(Application.EVENT + eventId, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                Log.d(Application.TAG, object.toString());
                try {
                    collapsingToolbar.setTitle(object.optString("published_date"));
                    thumbnail.setImageUrl(object.optString("banner"), VolleySingleton.getInstance().getImageLoader());
                    title.setText(object.optString("title"));
                    body.setText(object.optString("body"));
                    //published_date.setText(object.optString("published_date"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(Application.TAG, "Error: " + volleyError.getMessage());
                pDialog.hide();
            }
        });
        Application.getInstance().addToRequestQueue(requestObject, REQ_EVENT_BY_ID);

        // get Photos
        requestArray = new JsonArrayRequest(Application.EVENT_PHOTO + eventId, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(Application.TAG, response.toString());
                //setDataFromJson(response);
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        Photos photos = new Photos();
                        photos.setId(obj.optString("id"));
                        photos.setDescription(obj.optString("description"));
                        photos.setPhoto(obj.optString("photo"));
                        photosList.add(photos);
                    }
                    Log.d(Application.TAG, "photosList : " + photosList.size());
                    AppFunctions.setListViewHeightBasedOnChildren(photoGridView);
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
        Application.getInstance().addToRequestQueue(requestArray, REQ_EVENT_PHOTO);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.activity_open_scal, R.anim.activity_close_translate);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_open_scal, R.anim.activity_close_translate);
    }
}
