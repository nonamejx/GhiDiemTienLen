package com.nonamejx.ghidiemtienlen.activity;

import android.support.v4.app.Fragment;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.database.DataCenter;
import com.nonamejx.ghidiemtienlen.fragment.TableResultFragment;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return TableResultFragment.newInstance(DataCenter.getInstance().getAllGames().get(0).getGameId());
    }

    @Override
    public void afterView() {
    }
}
