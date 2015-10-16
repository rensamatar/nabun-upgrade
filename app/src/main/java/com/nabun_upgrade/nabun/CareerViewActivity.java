package com.nabun_upgrade.nabun;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.nabun_upgrade.adapter.WageAdapter;
import com.nabun_upgrade.config.Application;
import com.nabun_upgrade.model.Career;
import com.nabun_upgrade.model.Staff;
import com.nabun_upgrade.model.Wage;
import com.nabun_upgrade.utility.CustomProgressDialog;
import com.nabun_upgrade.utility.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.grantland.widget.AutofitTextView;

/**
 * Created by admin on 9/30/2015.
 */
public class CareerViewActivity extends AppCompatActivity {

    public static final String CAREER_DATA = "career_data";
    private static final String REQ_CAREER_BY_ID = "request_career_by_id";
    private JsonObjectRequest request;
    private CustomProgressDialog pDialog;
    private String url;
    private String careerId;
    private Career career = null;
    private ArrayList<Career> allCareer = new ArrayList<>();
    private ArrayList<Wage> wageList = new ArrayList<>();
    private ArrayList<Staff> staffList = new ArrayList<>();
    private WageAdapter wageAdapter;

    private CollapsingToolbarLayout collapsingToolbar;
    private NestedScrollView scrollView;
    private NetworkImageView thumbnail;
    private AutofitTextView attrText;
    private TextView genderText;
    private TextView ageText;
    private TextView qualificationText;
    private ListView wageListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_view);
        pDialog = new CustomProgressDialog(this);
        careerId = getIntent().getStringExtra(CAREER_DATA);
        Toast.makeText(this, "Holy : " + careerId, Toast.LENGTH_LONG).show();

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
        scrollView = (NestedScrollView) findViewById(R.id.career_scrollview);
        scrollView.setNestedScrollingEnabled(true);
        thumbnail = (NetworkImageView) findViewById(R.id.career_banner);
        attrText = (AutofitTextView) findViewById(R.id.career_attribute);

        // Listing
        genderText = (TextView) findViewById(R.id.gender);
        ageText = (TextView) findViewById(R.id.age);
        qualificationText = (TextView) findViewById(R.id.qualifications);

        // Wage
        wageListView = (ListView) findViewById(R.id.listview_wage);
//        for (int i = 0; i < 10; i++) {
//            Wage test = new Wage();
//            test.setTitle("Test title");
//            wageList.add(test);
//        }

        wageAdapter = new WageAdapter(this, wageList);
        wageListView.setAdapter(wageAdapter);
    }

    private void initData() {
        pDialog.show();
        url = Application.CAREER_DETAIL + careerId;
        request = new JsonObjectRequest(Application.DUMMY, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                Log.d(Application.TAG, object.toString());
               try {
                   //setDataFromJson(object);
                   collapsingToolbar.setTitle(object.optString("title"));
                   thumbnail.setImageUrl(object.optString("banner"), VolleySingleton.getInstance().getImageLoader());
                   attrText.setText(object.optString("attribute"));
                   genderText.setText(object.optString("gender"));
                   ageText.setText(object.optString("age"));
                   qualificationText.setText(object.optString("qualifications"));
                   JSONArray wr = object.getJSONArray("wage");
                   for (int i = 0; i < wr.length(); i++) {
                       JSONObject data = wr.getJSONObject(i);
                       Wage wage = new Wage();
                       wage.setTitle(data.optString("title"));
                       wageList.add(wage);
                   }
                   wageAdapter.setWage(wageList);
                   setListViewHeightBasedOnChildren(wageListView);
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
        Application.getInstance().addToRequestQueue(request, REQ_CAREER_BY_ID);
    }

    private void setListViewAdapter(ArrayList<Wage> allWage) {
        Log.d(Application.TAG, "wageList : " + wageList.size());
        Log.d(Application.TAG, "Wage Adapter size : " + wageAdapter.getCount());
        //wageAdapter = new WageAdapter(this, allWage);
        //wageListView.setAdapter(wageAdapter);
    }

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

    private void setDataFromJson(JSONObject object) {
        career.setId(object.optString("id"));
        career.setTitle(object.optString("title"));
        career.setBanner(object.optString("banner"));
        career.setAuthor(object.optString("author"));
        career.setAttribute(object.optString("attribute"));
        career.setGender(object.optString("gender"));
        career.setAge(object.optString("age"));
        career.setQualifications(object.optString("qualifications"));
        try {
            JSONArray wr = object.getJSONArray("wage");
            for (int i = 0; i < wr.length(); i++) {
                JSONObject data = wr.getJSONObject(i);
                Wage wage = new Wage();
                wage.setTitle(data.optString("title"));
                wageList.add(wage);
                wageAdapter.setWage(wageList);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONArray array = object.getJSONArray("staff");
            for (int i = 0; i < array.length(); i++) {
                JSONObject ob = array.getJSONObject(i);
                Staff staff = new Staff();
                staff.setId(ob.optString("id"));
                staff.setNickname(ob.optString("nickname"));
                staff.setPhone(ob.optString("phone"));
                staffList.add(staff);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        career.setWage(wageList);
        career.setStaff(staffList);
        career.setPublished_date(object.optString("published_date"));
        career.setCreated_at(object.optString("created_at"));
        career.setUpdated_at(object.optString("updated_at"));
        allCareer.add(career);
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
