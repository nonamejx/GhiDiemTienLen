package com.nonamejx.ghidiemtienlen.database;

import com.nonamejx.ghidiemtienlen.model.Game;

import java.util.List;

/**
 * Created by noname
 * on 22/10/2016.
 */
public interface IDatabaseManagement {
    List<Game> getGames();
    void addGame(Game game);
    void deleteAllGames();
}
