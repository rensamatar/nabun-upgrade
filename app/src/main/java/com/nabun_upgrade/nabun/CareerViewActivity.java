package com.nabun_upgrade.nabun;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.nabun_upgrade.model.Career;
import com.nabun_upgrade.utility.VolleySingleton;

import me.grantland.widget.AutofitTextView;

/**
 * Created by admin on 9/30/2015.
 */
public class CareerViewActivity extends AppCompatActivity {

    public static final String CAREER_DATA = "career_data";
    private Career career = null;
    private String title;
    private String banner;
    private String attribute;
    private String author;
    private String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_view);

        career = getIntent().getExtras().getParcelable(CAREER_DATA);
        if(career != null) {
            title = career.getTitle();
            banner = career.getBanner();
            attribute = career.getAttribute();
            author = career.getAuthor();
            gender = career.getGender();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(title);

        NetworkImageView nv = (NetworkImageView) findViewById(R.id.career_banner);
        nv.setDefaultImageResId(R.drawable.image);
        nv.setImageUrl(banner, VolleySingleton.getInstance().getImageLoader());

        AutofitTextView attr = (AutofitTextView) findViewById(R.id.career_attribute);
        attr.setText(attribute);

        // listing
        TextView gender_txt = (TextView) findViewById(R.id.gender);
        gender_txt.setText(gender);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
