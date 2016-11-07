package com.nonamejx.ghidiemtienlen.database;

import com.nonamejx.ghidiemtienlen.model.Game;
import com.nonamejx.ghidiemtienlen.model.Player;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by noname
 * on 22/10/2016.
 */
public class RealmHelper implements IDatabaseManagement {
    private Realm mRealm;

    private static RealmHelper mInstance;

    private RealmHelper() {
        mRealm = Realm.getDefaultInstance();
    }

    public static RealmHelper getInstance() {
        if (mInstance == null) {
            mInstance = new RealmHelper();
        }
        return mInstance;
    }

    @Override
    public List<Game> getGames() {
        return mRealm.where(Game.class).findAll();
    }

    @Override
    public void addGame(Game game) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(game);
        mRealm.commitTransaction();
    }

    @Override
    public void deleteAllGames() {
        RealmResults<Game> games = mRealm.where(Game.class).findAll();
        mRealm.beginTransaction();
        games.clear();
        mRealm.commitTransaction();
    }

    @Override
    public String getPlayerName(String playerId) {
        return mRealm.where(Player.class).equalTo("playerId", playerId).findFirst().getPlayerName();
    }
}
