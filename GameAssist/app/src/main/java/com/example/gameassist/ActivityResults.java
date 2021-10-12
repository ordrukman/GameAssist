package com.example.gameassist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Comparator;

public class ActivityResults extends AppCompatActivity {
    //fields:
    private ListView resultsList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resultsList = findViewById(R.id.resultsList);

        //Create comparator to compare by the times each player won
        MainActivity.players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                if (p1.getNumberOfWins() > p2.getNumberOfWins()) {
                    return -1;
                }
                //In case When both players have the same number of wins,
                //return the one with less games played
                if (p1.getNumberOfWins() == p2.getNumberOfWins()) {
                    return p1.getNumberOfGamesPlayed() - p2.getNumberOfGamesPlayed();
                }
                return 1;
            }
        });

        //Update the players rank
        for (Player p : MainActivity.players) {
            p.setRank(MainActivity.players.indexOf(p) + 1);
        }

        //Using ListView, create appropriate adapter to show the players rank
        ArrayAdapter resultsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, MainActivity.players);
        resultsList.setAdapter(resultsAdapter);

    }
}