package com.nabun_upgrade.nabun;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.nabun_upgrade.fragment.HomeFragment;
import com.nabun_upgrade.fragment.RecyclerViewFragment;

public class MainActivity extends FragmentActivity {

    private static final int TAB_ITEM = 3;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SectionPagerAdapter mSectionsPagerAdapter;
    private String tabTitles[] = new String[] { "ListView", "RecyclerView" };
    private int[] iconId = {R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);

        mSectionsPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new ListViewFragment();
                case 2:
                    return new RecyclerViewFragment();
                default:
                    return new HomeFragment();
            }
        }

        @Override
        public int getCount() {
            return TAB_ITEM;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // return tabTitles[position];
            // getDrawable(int i) is deprecated, use getDrawable(int i, Theme theme) for min SDK >=21
            // Drawable image = context.getResources().getDrawable(iconIds[position], context.getTheme());

            Drawable image;
            if(android.os.Build.VERSION.SDK_INT >= 21) {
                image = getResources().getDrawable(iconId[position], getTheme());
            } else {
                image = getResources().getDrawable(iconId[position]);
            }
            if (image != null) {
                image.setBounds(0, 0, 50, 50);
            }
            SpannableString sb = new SpannableString(" ");
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
