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
public class Result extends RealmObject {
    @PrimaryKey
    @Getter
    private String resultId;

    @Getter
    @Setter
    private String playerId;

    @Getter
    @Setter
    private int result;

    public Result() {
        this.resultId = UUID.randomUUID().toString();
    }
}
