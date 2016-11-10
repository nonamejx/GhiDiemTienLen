package com.nonamejx.ghidiemtienlen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.adapter.TableResultAdapter;
import com.nonamejx.ghidiemtienlen.common.Constants;
import com.nonamejx.ghidiemtienlen.common.DividerItemDecoration;
import com.nonamejx.ghidiemtienlen.database.DataCenter;
import com.nonamejx.ghidiemtienlen.model.Game;

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

    private Game mGame;

    public static TableResultFragment newInstance(String gameId) {
        return TableResultFragment_.builder().mGameId(gameId).build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGame = DataCenter.getInstance().getGame(mGameId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_table_result, container, false);
        RecyclerView mRecyclerViewTableResult = (RecyclerView) v.findViewById(R.id.recyclerViewTableResult);
        mRecyclerViewTableResult.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewTableResult.setAdapter(new TableResultAdapter(getContext(), mGame));
        mRecyclerViewTableResult.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        TextView[] tvPlayers = new TextView[4];
        tvPlayers[0] = (TextView) v.findViewById(R.id.tvPlayer1);
        tvPlayers[1] = (TextView) v.findViewById(R.id.tvPlayer2);
        tvPlayers[2] = (TextView) v.findViewById(R.id.tvPlayer3);
        tvPlayers[3] = (TextView) v.findViewById(R.id.tvPlayer4);
        for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
            tvPlayers[i].setText(mGame.getPlayerNames()[i]);
        }

        return v;
    }
}
