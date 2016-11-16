package com.nonamejx.ghidiemtienlen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.common.Constants;
import com.nonamejx.ghidiemtienlen.database.DataCenter;
import com.nonamejx.ghidiemtienlen.model.Game;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        final int[] finalResults = mGame.calculateFinalResult();
        for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
            barEntries.add(new BarEntry(finalResults[i], i));
        }
        BarDataSet dataSet = new BarDataSet(barEntries, null);

        List<String> labels = Arrays.asList(mGame.getPlayerNames());

        BarData barData = new BarData(labels, dataSet);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        barChart.setData(barData);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setScaleEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.setDescription("");
        barChart.setDrawGridBackground(false);
        barChart.getLegend().setEnabled(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setTextSize(12);
        barChart.animateY(500);
        barChart.invalidate();

        return v;
    }
}