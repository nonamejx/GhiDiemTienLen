package com.nonamejx.ghidiemtienlen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.database.DataCenter;
import com.nonamejx.ghidiemtienlen.model.Game;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by noname
 * on 14/11/2016.
 */
@EFragment
public class BarChartResultFragment extends Fragment {
    @FragmentArg
    String mGameId;

    private Game mGame;

    public static BarChartResultFragment newInstance(String gameId) {
        return BarChartResultFragment_.builder().mGameId(gameId).build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGame = DataCenter.getInstance().getGame(mGameId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chart_result, container, false);
        BarChart barChart = (BarChart) v.findViewById(R.id.barChartResult);

        return v;
    }
}