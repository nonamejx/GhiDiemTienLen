package com.nonamejx.ghidiemtienlen.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by noname
 * on 08/11/2016.
 */
public class GameResult extends RealmObject {
    @Getter
    @Setter
    private RealmList<Result> results; // the size of this array is 4 - the number of players

    public GameResult() {
        results = new RealmList<>();
    }
}
