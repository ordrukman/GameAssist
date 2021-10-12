package com.example.gameassist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatchesRecViewAdapter extends RecyclerView.Adapter<MatchesRecViewAdapter.ViewHolder> {
    //fields:
    private ArrayList<Match> matches = new ArrayList<>();
    private Context context;

    public MatchesRecViewAdapter(Context context) {
        this.context = context;
    }
    //Implements all the methods from the RecyclerView.Adapter<MatchesRecViewAdapter.ViewHolder> Class
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = position;
        holder.txtName.setText(matches.get(position).toString());
        //Once the add button clicked
        holder.checkBtn.setOnClickListener(new View.OnClickListener() {
            //Validity check
            @Override
            public void onClick(View v) {
                if ((!holder.edtWhoWins.getText().toString().equals(matches.get(pos).getPlayer1Name()))
                    &&(!holder.edtWhoWins.getText().toString().equals(matches.get(pos).getPlayer2Name()))) {
                    holder.edtWhoWins.setError("Player name isn't in the current match");
                } else {
                    //Update that game has been played
                    getPlayerByName(holder.edtWhoWins.getText().toString()).addWin();
                    getPlayerByName(matches.get(pos).getPlayer1Name()).didPlay();
                    getPlayerByName(matches.get(pos).getPlayer2Name()).didPlay();
                    Toast.makeText(context, holder.edtWhoWins.getText().toString() + " wins", Toast.LENGTH_SHORT).show();
                    holder.checkBtn.setEnabled(false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println(matches.size());
        return matches.size();
    }

    //Update the matches list
    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    //creating inner Class
    public class ViewHolder extends RecyclerView.ViewHolder {
        //fields:
        private TextView txtName;
        private EditText edtWhoWins;
        private Button checkBtn;

        //Create all the needed characteristics
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            edtWhoWins = itemView.findViewById(R.id.edtWhoWins);
            checkBtn = itemView.findViewById(R.id.checkBtn);
        }
    }

    public Player getPlayerByName(String playerName) {
        for (Player p : MainActivity.players) {
            if (playerName.equals(p.getName())) {
                return p;
            }
        }
        return null;
    }
}
