package com.nonamejx.ghidiemtienlen.model;

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
public class Turn extends RealmObject {
    @PrimaryKey
    @Getter
    private String turnId;

    @Getter
    @Setter
    public RealmList<Result> results; // the size of this array is 4 - the number of players

    public Turn() {
        this.turnId = UUID.randomUUID().toString();
        this.results = new RealmList<>();
    }
}
