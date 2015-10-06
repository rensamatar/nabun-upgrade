package com.nabun_upgrade.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nabun_upgrade.config.Application;
import com.nabun_upgrade.nabun.BaseRefreshFragment;
import com.nabun_upgrade.nabun.R;
import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by admin on 9/24/2015.
 */
public class RecyclerViewFragment extends BaseRefreshFragment {

    private PullToRefreshView mPullToRefreshView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recycler_view, container, false);

        // Request data array
        getData();

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(new SampleAdapter());

        mPullToRefreshView = (PullToRefreshView) rootView.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });

        return rootView;
    }

    private class SampleAdapter extends RecyclerView.Adapter<SampleHolder> {

        @Override
        public SampleHolder onCreateViewHolder(ViewGroup parent, int pos) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new SampleHolder(view);
        }

        @Override
        public void onBindViewHolder(SampleHolder holder, int pos) {
            Map<String, Integer> data = mSampleList.get(pos);
            holder.bindData(data);
        }

        @Override
        public int getItemCount() {
            return mSampleList.size();
        }
    }

    private class SampleHolder extends RecyclerView.ViewHolder {

        private View mRootView;
        private ImageView mImageViewIcon;

        private Map<String, Integer> mData;

        public SampleHolder(View itemView) {
            super(itemView);

            mRootView = itemView;
            mImageViewIcon = (ImageView) itemView.findViewById(R.id.image_view_icon);
        }

        public void bindData(Map<String, Integer> data) {
            mData = data;

            mRootView.setBackgroundResource(mData.get(KEY_COLOR));
            mImageViewIcon.setImageResource(mData.get(KEY_ICON));
        }
    }

    private void getData() {
        String tag_json_array = "json_array_req";

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(Application.BASE_URL, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(Application.TAG, response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Application.TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

        // Adding request to request queue
        Application.getInstance().addToRequestQueue(req, tag_json_array);
    }
}
