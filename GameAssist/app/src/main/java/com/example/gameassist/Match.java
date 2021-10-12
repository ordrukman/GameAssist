package com.example.gameassist;

public class Match {
    //fields:
    private Player player1;
    private Player player2;

    //contractor:
    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public String toString() {
        return this.player1.nameString() + "\n VS \n" + this.player2.nameString();
    }

    public String getPlayer1Name() {
        return this.player1.nameString();
    }
    public String getPlayer2Name() {
        return this.player2.nameString();
    }

}
