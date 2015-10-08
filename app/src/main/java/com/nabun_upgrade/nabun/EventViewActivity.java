package com.nabun_upgrade.nabun;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.nabun_upgrade.model.Event;
import com.nabun_upgrade.utility.VolleySingleton;

import java.io.IOException;
import java.net.URL;

/**
 * Created by admin on 10/7/2015.
 */
public class EventViewActivity extends AppCompatActivity {

    public static final String EVENT_DATA = "event_data";
    private Event event = null;
    private String title;
    private String banner;
    private String body_text;
    private String author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        event = getIntent().getExtras().getParcelable(EVENT_DATA);
        if (event != null) {
            title = event.getTitle();
            banner = event.getBanner();
            body_text = event.getBody();
            author = event.getAuthor();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(title);

        NetworkImageView nv = (NetworkImageView) findViewById(R.id.event_banner);
        nv.setDefaultImageResId(R.drawable.image);
        nv.setImageUrl(banner, VolleySingleton.getInstance().getImageLoader());

        TextView date = (TextView) findViewById(R.id.event_author);
        date.setText(author);
        TextView body = (TextView) findViewById(R.id.event_body);
        body.setText(body_text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
