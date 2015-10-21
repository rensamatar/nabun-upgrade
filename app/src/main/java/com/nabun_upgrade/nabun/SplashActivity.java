package com.nabun_upgrade.nabun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by admin on 10/21/2015.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
