package com.nonamejx.ghidiemtienlen.utils;

import com.nonamejx.ghidiemtienlen.common.Constants;
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

    public static int[] getMinPositions(int[] result) {
        int[] minResultPositions = {-1, -1, -1, -1};
        int minVal = result[0];
        for (int i = 1; i < Constants.NUMBER_OF_PLAYERS; i++) {
            if (result[i] < minVal) {
                minVal = result[i];
            }
        }
        for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
            if (result[i] == minVal) {
                minResultPositions[i] = i;
            }
        }
        return minResultPositions;
    }

    public static int[] getMaxPositions(int[] result) {
        int[] maxResultPositions = {-1, -1, -1, -1};
        int maxVal = result[0];
        for (int i = 1; i < Constants.NUMBER_OF_PLAYERS; i++) {
            if (result[i] > maxVal) {
                maxVal = result[i];
            }
        }
        for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
            if (result[i] == maxVal) {
                maxResultPositions[i] = i;
            }
        }
        return maxResultPositions;
    }
}
