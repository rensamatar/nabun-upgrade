package com.nabun_upgrade.fragment;

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
import com.nabun_upgrade.adapter.WageAdapter;
import com.nabun_upgrade.config.Application;
import com.nabun_upgrade.model.Career;
import com.nabun_upgrade.model.FeedCareer;
import com.nabun_upgrade.model.Staff;
import com.nabun_upgrade.model.Wage;
import com.nabun_upgrade.nabun.EventViewActivity;
import com.nabun_upgrade.nabun.R;
import com.nabun_upgrade.utility.CustomProgressDialog;

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
    private ArrayList<FeedCareer> listCareer = new ArrayList<>();
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
        mAdapter = new CareerAdapter(getActivity(), getActivity());

        // RecyclerView
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                initData();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.notifyDataSetChanged();
                listCareer.clear();
                listWage.clear();
                listStaff.clear();
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
        final CustomProgressDialog pDialog = new CustomProgressDialog(getActivity());
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
        FeedCareer career = new FeedCareer();
        career.setId(object.optString("id"));
        career.setTitle(object.optString("title"));
        career.setBanner(object.optString("banner"));
        career.setAttribute(object.optString("attribute"));
        listCareer.add(career);

        // set to adapter
        mAdapter.setCareer(listCareer);
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
