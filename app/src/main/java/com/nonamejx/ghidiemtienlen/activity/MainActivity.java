package com.nonamejx.ghidiemtienlen.activity;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.fragment.GameFragment;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById(R.id.recyclerViewGames)
    RecyclerView mRecyclerViewGames;

    @Override
    protected Fragment createFragment() {
        return GameFragment.newInstance();
    }

    @Override
    public void afterView() {
    }
}
