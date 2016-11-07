package com.nonamejx.ghidiemtienlen.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.adapter.GameAdapter;
import com.nonamejx.ghidiemtienlen.common.DividerItemDecoration;
import com.nonamejx.ghidiemtienlen.database.RealmHelper;
import com.nonamejx.ghidiemtienlen.model.Game;
import com.nonamejx.ghidiemtienlen.model.GameResult;
import com.nonamejx.ghidiemtienlen.model.Player;
import com.nonamejx.ghidiemtienlen.model.Result;
import com.nonamejx.ghidiemtienlen.model.Turn;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import io.realm.RealmList;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.recyclerViewGames)
    RecyclerView mRecyclerViewGames;

    @AfterViews
    void init() {
        // create sample data
        // createSampleData();

        Log.d("MainAppS", "size: " + getGameResults().size());

        mRecyclerViewGames.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewGames.setAdapter(new GameAdapter(this, getGameResults()));
        mRecyclerViewGames.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
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
        for (int numGames = 0; numGames < 5; numGames++) {
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
