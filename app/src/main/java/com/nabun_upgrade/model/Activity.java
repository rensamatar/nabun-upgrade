package com.nabun_upgrade.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.nabun_upgrade.nabun.R;
import java.util.Map;

/**
 * Created by tar on 10/6/15 AD.
 */
public class Activity extends RecyclerView.ViewHolder {

    private View mRootView;
    private ImageView mImageViewIcon;

    private Map<String, Integer> mData;

    public Activity(View itemView) {
        super(itemView);

        mRootView = itemView;
        mImageViewIcon = (ImageView) itemView.findViewById(R.id.image_view_icon);
    }

    public void bindData(Map<String, Integer> data) {
        mData = data;

    }

}
