package com.nabun_upgrade.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.nabun_upgrade.model.FeedEvent;
import com.nabun_upgrade.nabun.EventViewActivity;
import com.nabun_upgrade.nabun.R;
import com.nabun_upgrade.utility.Constants;
import com.nabun_upgrade.utility.VolleySingleton;
import java.util.ArrayList;

import me.grantland.widget.AutofitTextView;

/**
 * Created by admin on 10/7/2015.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ViewHolder viewHolder;
    private ArrayList<FeedEvent> mEventList = new ArrayList<>();
    private ImageLoader mImageLoader;
    private VolleySingleton mVolleySingleton;
    private Context mContext;
    private Activity activity;
    private int lastPosition = -1;

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

    public void setEvent(ArrayList<FeedEvent> listEvent) {
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

        final FeedEvent currentEvent = mEventList.get(position);

        holder.title.setText(currentEvent.getTitle());
        //holder.created_at.setText(currentEvent.getCreated_at());

        // Retrieve image file
        String banner = currentEvent.getBanner();
        loadImages(banner, holder);

        setAnimation(holder.frameLayout, position);

    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.up_from_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private void loadImages(final String banner, final ViewHolder holder) {
        if (!banner.equals(Constants.NA)) {
            mImageLoader.get(banner, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
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
            final FeedEvent event = mEventList.get(position);
            Intent intent = new Intent(mContext, EventViewActivity.class);
            intent.putExtra(EventViewActivity.EVENT_DATA, event.getId());
            mContext.startActivity(intent);
            activity.overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scal);
        }
    }

}
