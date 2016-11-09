package com.nonamejx.ghidiemtienlen.utils;

import com.nonamejx.ghidiemtienlen.model.Game;
import com.nonamejx.ghidiemtienlen.model.GameRealmObject;

/**
 * Created by noname
 * on 10/11/2016.
 */
public class MyUtils {
    public static GameRealmObject convertRealmObject(Game game) {
        return new GameRealmObject(game);
    }
}
