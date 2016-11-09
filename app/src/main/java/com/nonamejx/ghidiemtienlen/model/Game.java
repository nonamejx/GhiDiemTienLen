package com.nonamejx.ghidiemtienlen.model;

import com.nonamejx.ghidiemtienlen.common.Constants;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by noname
 * on 22/10/2016.
 */
public class Game {
    @Getter
    private String gameId;

    @Getter
    @Setter
    private int numberOfPlayers;

    @Getter
    @Setter
    private int numberOfTurns;

    @Getter
    @Setter
    private String[] playerNames;

    @Getter
    @Setter
    private int[][] result;

    private int[] minResultPositions = {-1, -1, -1, -1};

    private int[] maxResultPositions = {-1, -1, -1, -1};

    public Game(int numberOfTurns) {
        this.gameId = UUID.randomUUID().toString();
        this.numberOfPlayers = Constants.NUMBER_OF_PLAYERS;
        this.numberOfTurns = numberOfTurns;
        this.result = new int[numberOfTurns][numberOfPlayers];
    }

    public Game(String[] playerNames, int numberOfTurns) {
        this.gameId = UUID.randomUUID().toString();
        this.numberOfPlayers = Constants.NUMBER_OF_PLAYERS;
        this.numberOfTurns = numberOfTurns;
        this.playerNames = playerNames;
        this.result = new int[numberOfTurns][numberOfPlayers];
    }

    public int[] calculateFinalResult() {
        int[] finalResult = new int[4];
        for (int i = 0; i < this.numberOfTurns; i++) {
            for (int j = 0; j < this.numberOfPlayers; j++) {
                finalResult[j] += this.result[i][j];
            }
        }
        return finalResult;
    }

    public GameRealmObject toGameRealmObject() {
        return new GameRealmObject(this);
    }

    public int[] getMinResultPositions() {
        final int[] finalResult = calculateFinalResult();
        int minVal = finalResult[0];
        for (int i = 1; i < Constants.NUMBER_OF_PLAYERS; i++) {
            if (finalResult[i] < minVal) {
                minVal = finalResult[i];
            }
        }
        for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
            if (finalResult[i] == minVal) {
                minResultPositions[i] = i;
            }
        }
        return minResultPositions;
    }

    public int[] getMaxResultPositions() {
        final int[] finalResult = calculateFinalResult();
        int maxVal = finalResult[0];
        for (int i = 1; i < Constants.NUMBER_OF_PLAYERS; i++) {
            if (finalResult[i] > maxVal) {
                maxVal = finalResult[i];
            }
        }
        for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
            if (finalResult[i] == maxVal) {
                maxResultPositions[i] = i;
            }
        }
        return maxResultPositions;
    }

}
