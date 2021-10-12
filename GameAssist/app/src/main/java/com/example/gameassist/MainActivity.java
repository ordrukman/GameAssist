package com.example.gameassist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //fields:
    public static ArrayList<Player> players = new ArrayList<>();
    private EditText edtPlayerName;
    private Button btnAdd;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPlayerName = findViewById(R.id.edtPlayerName);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubmit = findViewById(R.id.btnSubmit);

        //Once the add button clicked
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            //Validity check
            public void onClick(View v) {
                if (edtPlayerName.getText().toString().equals("")) {
                    edtPlayerName.setError("Pleas enter player name");
                } else {
                    //Check if player name already exist
                    if (checkIfNameExist(edtPlayerName.getText().toString())) {
                        edtPlayerName.setError("Player name already exist");
                    } else {
                        String playerName = edtPlayerName.getText().toString();
                        players.add(new Player(playerName));
                        Toast.makeText(MainActivity.this, playerName + " added", Toast.LENGTH_SHORT).show();
                        edtPlayerName.getText().clear();
                    }
                }
            }
        });

        //Once the submit button clicked
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(players.size() < 2)) {
                    openActivityMatches();
                } else {
                    edtPlayerName.setError("Pleas enter at least two players");
                }
            }
        });
    }

    private boolean checkIfNameExist(String newName) {
        for (Player p : players) {
            if (p.getName().equals(newName)) {
                return true;
            }
        }
        return false;

    }

    //Open the matches activity
    public void openActivityMatches() {
        Intent intent = new Intent(this, ActivityMatches.class);
        startActivity(intent);
    }

}