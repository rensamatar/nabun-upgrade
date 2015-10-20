package com.nabun_upgrade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.nabun_upgrade.model.Photos;
import com.nabun_upgrade.nabun.R;
import com.nabun_upgrade.utility.Constants;
import com.nabun_upgrade.utility.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by admin on 10/19/2015.
 */
public class PhotoAdapter extends ArrayAdapter {

    private LayoutInflater mInflater;
    private Context context;
    private ArrayList<Photos> mData = new ArrayList();
    private ImageLoader mImageLoader;
    private VolleySingleton mVolleySingleton;

    public PhotoAdapter(Context context, ArrayList<Photos> allPhoto) {
        super(context, 0, allPhoto);
        this.mVolleySingleton = VolleySingleton.getInstance();
        this.mImageLoader = mVolleySingleton.getImageLoader();
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = allPhoto;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = new ViewHolder();
        final Photos currentPhoto = mData.get(position);
        if (convertView == null || convertView.getTag() == null) {
            convertView = mInflater.inflate(R.layout.photo_list_item, parent, false);
            holder.placeCard = (RelativeLayout) convertView.findViewById(R.id.placeCard);
            holder.photo = (ImageView) convertView.findViewById(R.id.photoItem);
            convertView.setTag(holder);
        }

        String url = currentPhoto.getPhoto();
        loadImages(url, holder);

        return convertView;
    }

    private void loadImages(String image, final ViewHolder holder) {
        if (!image.equals(Constants.NA)) {
            mImageLoader.get(image, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.photo.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
    }

    static class ViewHolder {
        ImageView photo;
        RelativeLayout placeCard;
    }
}
