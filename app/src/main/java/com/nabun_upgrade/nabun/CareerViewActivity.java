package com.nabun_upgrade.nabun;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.nabun_upgrade.model.Career;
import com.nabun_upgrade.model.Wage;
import com.nabun_upgrade.utility.VolleySingleton;

import java.util.ArrayList;

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
    private String gender;
    private String author;
    private String age;
    private String qualifications;
    private ArrayList<Wage> wageList = new ArrayList<>();

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
            age = career.getAge();
            qualifications = career.getQualifications();
            wageList = career.getWage();
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
        nv.setImageUrl(banner, VolleySingleton.getInstance().getImageLoader());

        AutofitTextView attr = (AutofitTextView) findViewById(R.id.career_attribute);
        attr.setText(attribute);

        // Listing
        TextView gender_txt = (TextView) findViewById(R.id.gender);
        gender_txt.setText(gender);
        TextView age_txt = (TextView) findViewById(R.id.age);
        age_txt.setText(age);
        TextView qualification_txt = (TextView) findViewById(R.id.qualifications);
        qualification_txt.setText(qualifications);

        ListView wageListView = (ListView) findViewById(R.id.wage_listview);
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.activity_open_scal, R.anim.activity_close_translate);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_open_scal, R.anim.activity_close_translate);
    }
}
