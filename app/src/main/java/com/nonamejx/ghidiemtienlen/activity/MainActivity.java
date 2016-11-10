package com.nonamejx.ghidiemtienlen.activity;

import android.support.v4.app.Fragment;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.fragment.GameFragment;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return GameFragment.newInstance();
    }

    @Override
    public void afterView() {
    }
}
