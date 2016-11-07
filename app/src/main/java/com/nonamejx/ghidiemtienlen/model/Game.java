package com.nonamejx.ghidiemtienlen.model;

import com.nonamejx.ghidiemtienlen.common.Constants;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by noname
 * on 22/10/2016.
 */
public class Game extends RealmObject {
    @PrimaryKey
    @Getter
    private String gameId;

    @Getter
    @Setter
    private Date createdDate;

    @Getter
    @Setter
    private int numberOfTurns = 0;

    @Getter
    @Setter
    public RealmList<Player> players;

    @Getter
    @Setter
    public RealmList<Turn> turns;

    public Game() {
        this.gameId = UUID.randomUUID().toString();
        this.createdDate = new Date();
        this.players = new RealmList<>();
        this.turns = new RealmList<>();
    }

    public GameResult calculateGameResult() {
        GameResult gameResult = new GameResult();
        for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
            Result result = new Result();
            result.setPlayerId(players.get(i).getPlayerId());
            gameResult.getResults().add(result);
        }
        for (Turn turn: turns) {
            gameResult.getResults().get(0).setResult(gameResult.getResults().get(0).getResult() + turn.getResults().get(0).getResult());
            gameResult.getResults().get(1).setResult(gameResult.getResults().get(1).getResult() + turn.getResults().get(1).getResult());
            gameResult.getResults().get(2).setResult(gameResult.getResults().get(2).getResult() + turn.getResults().get(2).getResult());
            gameResult.getResults().get(3).setResult(gameResult.getResults().get(3).getResult() + turn.getResults().get(3).getResult());
        }
        return gameResult;
    }
}
