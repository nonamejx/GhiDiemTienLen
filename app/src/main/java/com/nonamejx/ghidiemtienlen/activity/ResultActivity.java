package com.nonamejx.ghidiemtienlen.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.common.Constants;
import com.nonamejx.ghidiemtienlen.fragment.TableResultFragment;

import org.androidannotations.annotations.EActivity;

/**
 * Created by noname
 * on 10/11/2016.
 */
@EActivity(R.layout.activity_main)
public class ResultActivity extends BaseActivity {
    private String gameId;

    @Override
    protected Fragment createFragment() {
        return TableResultFragment.newInstance(gameId);
    }

    @Override
    public void afterView() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gameId = extras.getString(Constants.INTENT_KEY_GAME_ID);
        }
    }
}
