package com.example.gameassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityMatches extends AppCompatActivity {
    //fields:
    private RecyclerView matchesRecView;
    private Button resultsBtn;
    private TextView txtInfoError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        matchesRecView = findViewById(R.id.matchesRV);
        resultsBtn = findViewById(R.id.resultsBtn);
        txtInfoError = findViewById(R.id.txtInfo);
        //The matches array
        ArrayList<Match> matches = createMatches(MainActivity.players);
        System.out.println(matches);
        //Using RecycleView, create appropriate adapter to show the matches details
        MatchesRecViewAdapter adapter = new MatchesRecViewAdapter(this);
        adapter.setMatches(matches);

        matchesRecView.setAdapter(adapter);
        matchesRecView.setLayoutManager(new LinearLayoutManager(this));

        //Once the submit button clicked
        resultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //Validity check
            public void onClick(View v) {
                if (checkIfAllThePlayersPlay()) {
                    openActivityResults();
                    txtInfoError.setVisibility(View.GONE);
                } else {
                    txtInfoError.setVisibility(View.VISIBLE);
                    resultsBtn.setError("Not all the players played yet");
                }
            }
        });

    }

    //Create all the possibilities matches between all the players
    public ArrayList<Match> createMatches(ArrayList<Player> players) {
        ArrayList<Match> matches = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            for (int j =  i + 1; j < players.size(); j++) {
                matches.add(new Match(players.get(i), players.get(j)));
            }
        }
        return matches;
    }

    //Open the results activity
    public void openActivityResults() {
        Intent intent = new Intent(this, ActivityResults.class);
        startActivity(intent);
    }

    public boolean checkIfAllThePlayersPlay() {
        for (Player p : MainActivity.players){
            if (!p.checkIfPlay()) {
                return false;
            }
        }
        return true;
    }
}