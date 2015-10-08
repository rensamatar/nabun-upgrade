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
import com.nabun_upgrade.adapter.EventAdapter;
import com.nabun_upgrade.config.Application;
import com.nabun_upgrade.model.Event;
import com.nabun_upgrade.model.Photos;
import com.nabun_upgrade.nabun.EventViewActivity;
import com.nabun_upgrade.nabun.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by admin on 10/7/2015.
 */
public class EventFragment extends Fragment {

    private static final String REQ_EVENT = "request_event";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<Event> listEvent = new ArrayList<>();
    private ArrayList<Photos> listPhoto = new ArrayList<>();
    private EventAdapter mAdapter;

    private JsonArrayRequest request;

    public EventFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);
        mAdapter = new EventAdapter(getActivity(), getActivity());

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

        request = new JsonArrayRequest(Application.EVENT, new Response.Listener<JSONArray>() {
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
        Application.getInstance().addToRequestQueue(request, REQ_EVENT);
    }

    private void setDataFromJson(JSONObject object) {
        Event ev = new Event();
        ev.setId(object.optInt("id"));
        ev.setTitle(object.optString("title"));
        ev.setBanner(object.optString("banner"));
        ev.setAuthor(object.optString("author"));
        ev.setBody(object.optString("body"));
        try {
            JSONObject obj = object.getJSONObject("photos");
            for (int i = 0; i < obj.length(); i++) {
                Photos photo = new Photos();
                photo.setImg_01(obj.optString("img_01"));
                photo.setImg_02(obj.optString("img_02"));
                photo.setImg_03(obj.optString("img_03"));
                photo.setImg_04(obj.optString("img_04"));
                photo.setImg_05(obj.optString("img_05"));
                photo.setImg_06(obj.optString("img_06"));
                photo.setImg_07(obj.optString("img_07"));
                photo.setImg_08(obj.optString("img_08"));
                listPhoto.add(photo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ev.setPublished_date(object.optString("published_date"));
        ev.setCreated_at(object.optString("created_at"));
        ev.setUpdated_at(object.optString("updated_at"));
        listEvent.add(ev);
        mAdapter.setEvent(listEvent);
    }

}
