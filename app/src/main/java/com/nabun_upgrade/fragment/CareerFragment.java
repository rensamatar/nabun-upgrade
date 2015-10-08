package com.nabun_upgrade.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nabun_upgrade.adapter.CareerAdapter;
import com.nabun_upgrade.config.Application;
import com.nabun_upgrade.model.Career;
import com.nabun_upgrade.model.Staff;
import com.nabun_upgrade.model.Wage;
import com.nabun_upgrade.nabun.EventViewActivity;
import com.nabun_upgrade.nabun.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by admin on 10/7/2015.
 */

public class CareerFragment extends Fragment {

    private static final String REQ_CAREER = "request_career";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<Career> listEvent = new ArrayList<>();
    private ArrayList<Wage> listWage = new ArrayList<>();
    private ArrayList<Staff> listStaff = new ArrayList<>();
    private CareerAdapter mAdapter;

    private JsonArrayRequest request;

    public CareerFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_career, container, false);
        mAdapter = new CareerAdapter(getActivity());

        // RecyclerView
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Init json data
        initData();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventViewActivity.class);
                startActivity(intent);
            }
        });

        // Swipe refresh
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.sienna, R.color.saffron, R.color.eggplant);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        return rootView;
    }

    private void initData() {
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        request = new JsonArrayRequest(Application.CAREER, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(Application.TAG, response.toString());
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        setDataFromJson(obj);
                    }
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
        Application.getInstance().addToRequestQueue(request, REQ_CAREER);
    }

    private void setDataFromJson(JSONObject object) {
        Career career = new Career();
        career.setId(object.optInt("id"));
        career.setTitle(object.optString("title"));
        career.setBanner(object.optString("banner"));
        career.setAuthor(object.optString("author"));
        career.setAttribute(object.optString("attribute"));
        career.setGender(object.optString("gender"));
        career.setAge(object.optString("age"));
        career.setQualifications(object.optString("qualifications"));
        try {
            JSONObject wr = object.getJSONObject("wage");
            for (int i = 0; i < wr.length(); i++) {
                Wage wage = new Wage();
                wage.setW1(wr.optString("w1"));
                wage.setW2(wr.optString("w2"));
                wage.setW3(wr.optString("w3"));
                wage.setW4(wr.optString("w4"));
                wage.setW5(wr.optString("w5"));
                wage.setW6(wr.optString("w6"));
                wage.setW7(wr.optString("w7"));
                wage.setW8(wr.optString("w8"));
                wage.setW9(wr.optString("w9"));
                wage.setW10(wr.optString("w10"));
                wage.setW11(wr.optString("w11"));
                wage.setW12(wr.optString("w12"));
                wage.setW13(wr.optString("w13"));
                wage.setW14(wr.optString("w14"));
                wage.setW15(wr.optString("w15"));
                wage.setW16(wr.optString("w16"));
                listWage.add(wage);
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
                listStaff.add(staff);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        career.setPublished_date(object.optString("published_date"));
        career.setCreated_at(object.optString("created_at"));
        career.setUpdated_at(object.optString("updated_at"));
        listEvent.add(career);
        mAdapter.setCareer(listEvent);
    }

}
