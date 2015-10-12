package com.nabun_upgrade.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.nabun_upgrade.model.Event;
import com.nabun_upgrade.nabun.EventViewActivity;
import com.nabun_upgrade.nabun.R;
import com.nabun_upgrade.utility.Constants;
import com.nabun_upgrade.utility.VolleySingleton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.grantland.widget.AutofitTextView;

/**
 * Created by admin on 10/7/2015.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ViewHolder viewHolder;
    private ArrayList<Event> mEventList = new ArrayList<>();
    private ImageLoader mImageLoader;
    private VolleySingleton mVolleySingleton;
    private Context mContext;
    private Activity activity;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.event_list_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public EventAdapter(Context context, Activity activity) {
        this.mInflater = LayoutInflater.from(context);
        this.mVolleySingleton = VolleySingleton.getInstance();
        this.mImageLoader = mVolleySingleton.getImageLoader();
        this.mContext = context;
        this.activity = activity;
    }

    public void setEvent(ArrayList<Event> listEvent) {
        this.mEventList = listEvent;
        //update the adapter to reflect the new set of event
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Event currentEvent = mEventList.get(position);

        holder.title.setText(currentEvent.getTitle());
        holder.created_at.setText(currentEvent.getCreated_at());

        // Retrieve image file
        String banner = currentEvent.getBanner();
        loadImages(banner, holder);

    }

    private void loadImages(final String banner, final ViewHolder holder) {
        if (!banner.equals(Constants.NA)) {
            mImageLoader.get(banner, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    Glide.with(mContext)
                            .load(banner)
                            .centerCrop()
                            .placeholder(R.drawable.image)
                            .crossFade()
                            .into(holder.thumbnail);
                    holder.thumbnail.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        FrameLayout frameLayout;
        ImageView thumbnail;
        AutofitTextView title;
        TextView created_at;

        public ViewHolder(View itemView) {
            super(itemView);
            frameLayout = (FrameLayout) itemView.findViewById(R.id.frame_layout);
            thumbnail = (ImageView) itemView.findViewById(R.id.background);
            title = (AutofitTextView) itemView.findViewById(R.id.title);
            created_at = (TextView) itemView.findViewById(R.id.created_at);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            final Event event = mEventList.get(position);
            Toast.makeText(mContext, event.getPublished_date(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mContext, EventViewActivity.class);
            intent.putExtra(EventViewActivity.EVENT_DATA, event);
            mContext.startActivity(intent);
            activity.overridePendingTransition(R.anim.push_out_left, R.anim.pull_in_right);
        }
    }

}
