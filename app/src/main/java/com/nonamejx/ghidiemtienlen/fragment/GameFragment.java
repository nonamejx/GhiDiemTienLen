package com.nonamejx.ghidiemtienlen.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.activity.MainActivity;
import com.nonamejx.ghidiemtienlen.activity.ResultActivity_;
import com.nonamejx.ghidiemtienlen.adapter.GameAdapter;
import com.nonamejx.ghidiemtienlen.common.Constants;
import com.nonamejx.ghidiemtienlen.common.DividerItemDecoration;
import com.nonamejx.ghidiemtienlen.common.RecyclerTouchListener;
import com.nonamejx.ghidiemtienlen.database.DataCenter;
import com.nonamejx.ghidiemtienlen.model.Game;

import org.androidannotations.annotations.EFragment;

import java.util.List;
import java.util.Random;

/**
 * Created by noname
 * on 09/11/2016.
 */
@EFragment
public class GameFragment extends Fragment {

    private List<Game> mGames;
    private GameAdapter mAdapter;

    public static GameFragment newInstance() {
        return GameFragment_.builder().build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGames = DataCenter.getInstance().getAllGames();
        mAdapter = new GameAdapter(getContext(), mGames);
        // create sample data
        // createSampleData();
    }

    @Override
    public void onResume() {
        super.onResume();
        mGames = DataCenter.getInstance().getAllGames();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);
        RecyclerView mRecyclerViewGames = (RecyclerView) v.findViewById(R.id.recyclerViewGames);
        mRecyclerViewGames.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewGames.setAdapter(mAdapter);
        mRecyclerViewGames.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerViewGames.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerViewGames, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getContext(), ResultActivity_.class);
                i.putExtra(Constants.INTENT_KEY_GAME_ID, DataCenter.getInstance().getAllGames().get(position).getGameId());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        Button btnNewGame = (Button) v.findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getContext()).switchFragment(NewGameFragment.newInstance(), true);
            }
        });
        return v;
    }

    public void createSampleData() {
        Random random = new Random();
        for (int games = 0; games < 15; games++) {
            String[] players = new String[4];
            for (int i = 0; i < 4; i++) {
                players[i] = "player" + games + "" + i;
            }
            int numberOfTurns = 20;
            int[][] result = new int[numberOfTurns][players.length];
            for (int i = 0; i < numberOfTurns; i++) {
                for (int j = 0; j < players.length; j++) {
                    result[i][j] = random.nextInt(4);
                }
            }
            Game game = new Game(players, numberOfTurns);
            game.setResult(result);

            DataCenter.getInstance().addGame(game);
        }
    }
}
