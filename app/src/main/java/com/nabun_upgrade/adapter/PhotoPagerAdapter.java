package com.nabun_upgrade.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
public class PhotoPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Photos> mData = new ArrayList<>();
    private ImageView photo;
    private TextView description;
    private TextView currentItem;
    private TextView totalItem;
    private ImageLoader mImageLoader;
    private VolleySingleton mVolleySingleton;


    public PhotoPagerAdapter(Context context, ArrayList<Photos> allPhotos) {
        this.context = context;
        this.mVolleySingleton = VolleySingleton.getInstance();
        this.mImageLoader = mVolleySingleton.getImageLoader();
        this.layoutInflater = LayoutInflater.from(context);
        this.mData = allPhotos;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.photo_view_pager_item, container, false);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.activity_open_translate);

        final Photos data = mData.get(position);
        String image = data.getPhoto();

        description = (TextView) itemView.findViewById(R.id.description);
        description.setText(data.getDescription());
        description.startAnimation(animation);
        currentItem = (TextView) itemView.findViewById(R.id.currentItem);
        currentItem.setText(String.valueOf(position+1));
        totalItem = (TextView) itemView.findViewById(R.id.totalItem);
        totalItem.setText(String.valueOf(mData.size()));
        photo = (ImageView) itemView.findViewById(R.id.photo);
        loadImages(image);

        container.addView(itemView);

        return itemView;
    }

    private void loadImages(String image) {
        if (!image.equals(Constants.NA)) {
            mImageLoader.get(image, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    photo.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
