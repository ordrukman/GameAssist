package com.example.gameassist;

public class Player {
    //fields:
    private String name;
    private int countWins;
    private int gamesPlayed;
    private int rank;

    //contractor:
    public Player(String name) {
        this.name = name;
        this.gamesPlayed = 0;
        this.countWins = 0;
    }

    public String getName() {
        return this.name;
    }

    public void updateWins(int winsNumber) {
        countWins = winsNumber;
    }

    public void addWin() {
        countWins++;
    }

    //String of the results
    @Override
    public String toString() {
        return this.rank + ". " +this.name + " has won " + this.countWins + " of " + this.gamesPlayed + " games!";
    }

    public String nameString() {
        return this.name;
    }

    public void didPlay() {
        this.gamesPlayed++;
    }

    public boolean checkIfPlay() {
        if (this.gamesPlayed == 0){
           return false;
        }
        return true;
    }

    public int getNumberOfWins() {
        return countWins;
    }

    public int getNumberOfGamesPlayed() {
        return gamesPlayed;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}


