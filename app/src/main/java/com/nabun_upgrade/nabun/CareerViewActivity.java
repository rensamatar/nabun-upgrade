package com.nabun_upgrade.nabun;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.nabun_upgrade.adapter.WageAdapter;
import com.nabun_upgrade.config.Application;
import com.nabun_upgrade.model.Wage;
import com.nabun_upgrade.utility.AppFunctions;
import com.nabun_upgrade.utility.CustomProgressDialog;
import com.nabun_upgrade.utility.CustomSelectListView;
import com.nabun_upgrade.utility.VolleySingleton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by admin on 9/30/2015.
 */
public class CareerViewActivity extends AppCompatActivity {

    public static final String CAREER_DATA = "career_data";
    private static final String REQ_CAREER_BY_ID = "request_career_by_id";
    private static final String REQ_CAREER_WAGE  = "request_career_wage";

    private JsonObjectRequest requestObject;
    private JsonArrayRequest requestArray;
    private CustomProgressDialog pDialog;
    private String careerId;
    private ArrayList<Wage> wageList = new ArrayList<>();
    private WageAdapter wageAdapter;

    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fab;
    private NetworkImageView thumbnail;
    private TextView attrText;
    private TextView genderText;
    private TextView ageText;
    private TextView qualificationText;
    private TextView published_date;
    private ListView wageListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_view);
        pDialog = new CustomProgressDialog(this);
        careerId = getIntent().getStringExtra(CAREER_DATA);

        initComponentData();

        initData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void initComponentData() {
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        int lightVibrantColor = getResources().getColor(android.R.color.white);
        int vibrantColor = (getResources().getColor(R.color.sienna));
        // Dialog staff

        // Floating Action Button
        fab = (FloatingActionButton) findViewById(R.id.contactBtn);
        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CustomSelectListView.class);
                intent.putExtra(CustomSelectListView.ID, careerId);
                startActivity(intent);
            }
        });

        thumbnail = (NetworkImageView) findViewById(R.id.career_banner);

        // Listing
        attrText = (TextView) findViewById(R.id.attribute);
        genderText = (TextView) findViewById(R.id.gender);
        ageText = (TextView) findViewById(R.id.age);
        qualificationText = (TextView) findViewById(R.id.qualifications);
        published_date = (TextView) findViewById(R.id.published_date);

        // Wage
        wageListView = (ListView) findViewById(R.id.listview_wage);
        wageListView.setEnabled(false);

        wageAdapter = new WageAdapter(this, wageList);
        wageListView.setAdapter(wageAdapter);
    }

    private void initData() {
        pDialog.show();
        requestObject = new JsonObjectRequest(Application.CAREER + careerId, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                Log.d(Application.TAG, object.toString());
               try {
                   collapsingToolbar.setTitle(object.optString("title"));
                   thumbnail.setImageUrl(object.optString("banner"), VolleySingleton.getInstance().getImageLoader());
                   attrText.setText(object.optString("attribute"));
                   genderText.setText(object.optString("gender"));
                   ageText.setText(object.optString("age"));
                   qualificationText.setText(object.optString("qualifications"));
                   published_date.setText(object.optString("published_date"));
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
        Application.getInstance().addToRequestQueue(requestObject, REQ_CAREER_BY_ID);

        // get Wage
        requestArray = new JsonArrayRequest(Application.CAREER_WAGE + careerId, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(Application.TAG, response.toString());
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        Wage wage = new Wage();
                        wage.setTitle(obj.optString("title"));
                        wageList.add(wage);
                    }
                    wageAdapter.notifyDataSetChanged();
                    AppFunctions.setListViewHeightBasedOnChildren(wageListView);
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
        Application.getInstance().addToRequestQueue(requestArray, REQ_CAREER_WAGE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.activity_open_scal, R.anim.activity_close_translate);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_open_scal, R.anim.activity_close_translate);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pDialog.dismiss();
    }
}
