package com.nabun_upgrade.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.nabun_upgrade.nabun.R;

/**
 * Created by tar on 10/11/15 AD.
 */
public class CustomProgressDialog extends ProgressDialog {

    private AnimationDrawable animation = null;

    public CustomProgressDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_progress_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initComponents();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            return  true;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void initComponents() {
        ImageView imgProgress = (ImageView) findViewById(R.id.imgProgress);
        animation = (AnimationDrawable) imgProgress.getDrawable();
        animation.start();
    }

}
