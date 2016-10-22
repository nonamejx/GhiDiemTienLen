package com.nonamejx.ghidiemtienlen.model;

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
        this.players = new RealmList<>();
        this.turns = new RealmList<>();
    }
}
