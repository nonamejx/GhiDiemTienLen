package com.nonamejx.ghidiemtienlen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.activity.MainActivity;
import com.nonamejx.ghidiemtienlen.adapter.GameAdapter;
import com.nonamejx.ghidiemtienlen.common.DividerItemDecoration;
import com.nonamejx.ghidiemtienlen.database.RealmHelper;
import com.nonamejx.ghidiemtienlen.model.Game;
import com.nonamejx.ghidiemtienlen.model.GameResult;
import com.nonamejx.ghidiemtienlen.model.Player;
import com.nonamejx.ghidiemtienlen.model.Result;
import com.nonamejx.ghidiemtienlen.model.Turn;

import org.androidannotations.annotations.EFragment;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by noname
 * on 09/11/2016.
 */
@EFragment
public class GameFragment extends Fragment {

    public static GameFragment newInstance() {
        return GameFragment_.builder().build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // create sample data
         createSampleData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);
        RecyclerView mRecyclerViewGames = (RecyclerView) v.findViewById(R.id.recyclerViewGames);
        mRecyclerViewGames.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewGames.setAdapter(new GameAdapter(getContext(), getGameResults()));
        mRecyclerViewGames.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        Button btnNewGame = (Button) v.findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getContext()).switchFragment(NewGameFragment.newInstance(), true);
            }
        });
        return v;
    }

    private List<GameResult> getGameResults() {
        List<GameResult> gameResults = new RealmList<>();
        List<Game> games = RealmHelper.getInstance().getGames();
        Log.d("MainAppS", "games size: " + games.size());
        for (Game game : games) {
            gameResults.add(game.calculateGameResult());
        }
        return gameResults;
    }

    private void createSampleData() {
        // create 5 games
        for (int numGames = 0; numGames < 15; numGames++) {
            // create player
            RealmList<Player> players = new RealmList<>();
            for (int numPlayers = 0; numPlayers < 4; numPlayers++) {
                Player player = new Player();
                player.setPlayerName("player" + numGames + "" + numPlayers);
                players.add(player);
            }

            // create game
            Game game = new Game();
            game.setPlayers(players);
            game.setNumberOfTurns(20);

            // create 20 turns of the game
            RealmList<Turn> turns = new RealmList<>();
            for (int numTurns = 0; numTurns < 20; numTurns++) {
                Turn turn = new Turn();
                // generate sample result
                RealmList<Result> results = new RealmList<>();
                for (int numResults = 0; numResults < 4; numResults++){
                    Result result = new Result();
                    result.setPlayerId(players.get(numResults).getPlayerId());
                    result.setResult(numResults);

                    results.add(result);
                }
                turn.setResults(results);
                turns.add(turn);
            }
            game.setTurns(turns);

            // add game
            RealmHelper.getInstance().addGame(game);
        }
    }
}
