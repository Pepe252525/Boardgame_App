package com.example.boardgame_app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    ListView listViewGames;
    ArrayAdapter<String> adapter;
    List<Game> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        listViewGames = findViewById(R.id.listViewGames);

        gameList = DataManager.getInstance().getGames();

        updateList();

        listViewGames.setOnItemClickListener((parent, view, position, id) -> {
            Game selectedGame = gameList.get(position);

            // Vote erhöhen
            selectedGame.addVote();

            // Liste aktualisieren
            updateList();
        });
    }

    private void updateList() {
        List<String> displayList = new ArrayList<>();

        for (Game game : gameList) {
            String text = game.getName() + " (Votes: " + game.getVotes() + ")";
            displayList.add(text);
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                displayList
        );

        listViewGames.setAdapter(adapter);
    }
}
