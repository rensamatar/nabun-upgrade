package com.nabun_upgrade.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.nabun_upgrade.model.Career;
import com.nabun_upgrade.nabun.R;
import com.nabun_upgrade.utility.Constants;
import com.nabun_upgrade.utility.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by admin on 10/7/2015.
 */
public class CareerAdapter extends RecyclerView.Adapter<CareerAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ViewHolder viewHolder;
    private ArrayList<Career> mCareerList = new ArrayList<>();
    private ImageLoader mImageLoader;
    private VolleySingleton mVolleySingleton;

    @Override
    public CareerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.career_list_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public CareerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mVolleySingleton = VolleySingleton.getInstance();
        mImageLoader = mVolleySingleton.getImageLoader();
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;
        TextView attribute;

        public ViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.background);
            title = (TextView) itemView.findViewById(R.id.title);
            attribute = (TextView) itemView.findViewById(R.id.attribute);
        }
    }
}
