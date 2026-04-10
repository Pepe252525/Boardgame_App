package com.example.boardgame_app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameAdapter extends BaseAdapter {

    private Context context;
    private List<Game> gameList;

    public GameAdapter(Context context, List<Game> gameList) {
        this.context = context;
        this.gameList = gameList;

        // Sortierung nach Votes (höchste zuerst)
        Collections.sort(gameList, (g1, g2) -> g2.getVotes() - g1.getVotes());
    }

    @Override
    public int getCount() {
        return gameList.size();
    }

    @Override
    public Object getItem(int position) {
        return gameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.list_item_game, parent, false);
        }

        TextView txtRank = convertView.findViewById(R.id.txtRank);
        TextView txtName = convertView.findViewById(R.id.txtGameName);
        TextView txtVotes = convertView.findViewById(R.id.txtVotes);
        ProgressBar progressBar = convertView.findViewById(R.id.progressVotes);

        Game game = gameList.get(position);

        // Ranking anzeigen
        txtRank.setText("#" + (position + 1));

        txtName.setText(game.getName());
        txtVotes.setText(game.getVotes() + " Votes");

        // Max Votes berechnen
        int maxVotes = 1;
        for (Game g : gameList) {
            if (g.getVotes() > maxVotes) {
                maxVotes = g.getVotes();
            }
        }

        // Progress berechnen
        int progress = (int) ((game.getVotes() / (float) maxVotes) * 100);
        progressBar.setProgress(progress);

        // Gewinner hervorheben
        if (position == 0 && game.getVotes() > 0) {
            txtName.setTextColor(Color.parseColor("#4CAF50")); // grün
            txtRank.setTextColor(Color.parseColor("#4CAF50"));
        } else {
            txtName.setTextColor(Color.BLACK);
            txtRank.setTextColor(Color.DKGRAY);
        }

        return convertView;
    }
}
