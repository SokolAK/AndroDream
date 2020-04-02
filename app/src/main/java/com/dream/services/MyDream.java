package com.dream.services;

import android.service.dreams.DreamService;
import android.view.View;

import com.dream.R;

public class MyDream extends DreamService {

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        //setContentView(R.layout.main_activity);
    }

    public void onDreamingStarted() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}