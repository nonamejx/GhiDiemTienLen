package com.nonamejx.ghidiemtienlen.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by noname
 * on 22/10/2016.
 */
public class Player extends RealmObject {
    @PrimaryKey
    @Getter
    private String playerId;

    @Getter
    @Setter
    private String playerName;

    public Player() {
        this.playerId = UUID.randomUUID().toString();
    }
}
