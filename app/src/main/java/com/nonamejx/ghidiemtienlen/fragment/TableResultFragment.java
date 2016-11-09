package com.nonamejx.ghidiemtienlen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.adapter.TableResultAdapter;
import com.nonamejx.ghidiemtienlen.common.DividerItemDecoration;
import com.nonamejx.ghidiemtienlen.database.DataCenter;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by noname
 * on 10/11/2016.
 */
@EFragment
public class TableResultFragment extends Fragment {
    @FragmentArg
    String mGameId;

    public static TableResultFragment newInstance(String gameId) {
        return TableResultFragment_.builder().mGameId(gameId).build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_table_result, container, false);
        RecyclerView mRecyclerViewTableResult = (RecyclerView) v.findViewById(R.id.recyclerViewTableResult);
        mRecyclerViewTableResult.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewTableResult.setAdapter(new TableResultAdapter(getContext(), DataCenter.getInstance().getAllGames().get(0)));
        mRecyclerViewTableResult.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        return v;
    }
}
