package com.nonamejx.ghidiemtienlen.database;

import io.realm.Realm;

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
    public void getGames() {

    }
}
