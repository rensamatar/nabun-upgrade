package com.nabun_upgrade.nabun;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.nabun_upgrade.adapter.DemoPageAdapter;
import com.nabun_upgrade.model.Event;
import com.nabun_upgrade.utility.VolleySingleton;

import me.grantland.widget.AutofitTextView;
import me.relex.circleindicator.CircleIndicator;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(title);

        NetworkImageView nv = (NetworkImageView) findViewById(R.id.event_banner);
        nv.setImageUrl(banner, VolleySingleton.getInstance().getImageLoader());

        AutofitTextView date = (AutofitTextView) findViewById(R.id.event_author);
        date.setText(author);
        AutofitTextView body = (AutofitTextView) findViewById(R.id.event_body);
        body.setText(Html.fromHtml(body_text).toString());

        // DEFAULT
        ViewPager defaultViewpager = (ViewPager) findViewById(R.id.viewpager_default);
        CircleIndicator defaultIndicator = (CircleIndicator) findViewById(R.id.indicator_default);
        DemoPageAdapter defaultPagerAdapter = new DemoPageAdapter(getSupportFragmentManager());
        defaultViewpager.setAdapter(defaultPagerAdapter);
        defaultIndicator.setViewPager(defaultViewpager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.activity_open_scal, R.anim.activity_close_translate);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_open_scal, R.anim.activity_close_translate);
    }
}
