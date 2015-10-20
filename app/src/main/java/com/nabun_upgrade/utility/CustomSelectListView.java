package com.nabun_upgrade.utility;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nabun_upgrade.adapter.StaffAdapter;
import com.nabun_upgrade.config.Application;
import com.nabun_upgrade.model.Staff;
import com.nabun_upgrade.nabun.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 10/12/2015.
 */
public class CustomSelectListView extends Activity {

    public static final String ID = "career_data";
    private static final String REQ_CAREER_STAFF  = "request_career_staff";
    private JsonArrayRequest requestArray;
    private CustomProgressDialog pDialog;
    private String careerId;
    private ArrayList<Staff> staffList = new ArrayList<>();
    private StaffAdapter staffAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_select_list_view);
        pDialog = new CustomProgressDialog(this);

        careerId = getIntent().getStringExtra(ID);

        initData();
        staffAdapter = new StaffAdapter(this, staffList);

        ListView listview = (ListView) findViewById(R.id.list_selected);
        listview.setAdapter(staffAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Staff staff = staffList.get(position);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + staff.getPhone()));
                startActivity(callIntent);
                finish();
            }
        });
    }

    private void initData() {
        pDialog.show();
        // get Staff
        requestArray = new JsonArrayRequest(Application.CAREER_STAFF + careerId, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(Application.TAG, response.toString());
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        Staff staff = new Staff();
                        staff.setId(obj.optString("id"));
                        staff.setNickname(obj.optString("nickname"));
                        staff.setPhone(obj.optString("phone"));
                        staffList.add(staff);
                    }
                    staffAdapter.notifyDataSetChanged();
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
        Application.getInstance().addToRequestQueue(requestArray, REQ_CAREER_STAFF);
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
