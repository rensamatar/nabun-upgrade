package com.nabun_upgrade.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.nabun_upgrade.model.Career;
import com.nabun_upgrade.nabun.CareerViewActivity;
import com.nabun_upgrade.nabun.EventViewActivity;
import com.nabun_upgrade.nabun.R;
import com.nabun_upgrade.utility.Constants;
import com.nabun_upgrade.utility.VolleySingleton;

import java.util.ArrayList;

import me.grantland.widget.AutofitTextView;

/**
 * Created by admin on 10/7/2015.
 */
public class CareerAdapter extends RecyclerView.Adapter<CareerAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ViewHolder viewHolder;
    private ArrayList<Career> mCareerList = new ArrayList<>();
    private ImageLoader mImageLoader;
    private VolleySingleton mVolleySingleton;
    private Context mContext;
    private Activity activity;

    @Override
    public CareerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.career_list_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public CareerAdapter(Context context, Activity activity) {
        this.mInflater = LayoutInflater.from(context);
        this.mVolleySingleton = VolleySingleton.getInstance();
        this.mImageLoader = mVolleySingleton.getImageLoader();
        this.mContext = context;
        this.activity = activity;
    }

    public void setCareer(ArrayList<Career> listCareer) {
        this.mCareerList = listCareer;
        //update the adapter to reflect the new set of event
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCareerList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Career currentItem = mCareerList.get(position);

        holder.title.setText(currentItem.getTitle());
        holder.attribute.setText(currentItem.getAttribute());
//        Date createdDate = currentItem.getCreated_at();
//        if (createdDate != null) {
//            String formattedDate = mFormatter.format(createdDate);
//            holder.created_at.setText(formattedDate);
//        } else {
//            holder.created_at.setText(Constants.NA);
//        }

        // Retrieve image file
        String banner = currentItem.getBanner();
        loadImages(banner, holder);
    }

    private void loadImages(String banner, final ViewHolder holder) {
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
        ImageView thumbnail;
        AutofitTextView title;
        TextView attribute;

        public ViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.background);
            title = (AutofitTextView) itemView.findViewById(R.id.title);
            attribute = (TextView) itemView.findViewById(R.id.attribute);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            final Career career = mCareerList.get(position);
            Intent intent = new Intent(mContext, CareerViewActivity.class);
            intent.putExtra(CareerViewActivity.CAREER_DATA, career);
            mContext.startActivity(intent);
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

        }
    }
}
