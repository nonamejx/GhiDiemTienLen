package com.nonamejx.ghidiemtienlen.activity;

import android.support.v4.app.Fragment;

import com.nonamejx.ghidiemtienlen.R;

import org.androidannotations.annotations.EActivity;

/**
 * Created by noname
 * on 10/11/2016.
 */
@EActivity(R.layout.activity_main)
public class ResultActivity extends BaseActivity {
    @Override
    protected Fragment createFragment() {
        return null;
    }

    @Override
    public void afterView() {
    }
}
