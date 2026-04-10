package com.example.boardgame_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements DataManager.GameChangeListener {

    ListView listViewGames;
    Button btnAddGame;

    ArrayAdapter<String> adapter;
    List<Game> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        listViewGames = findViewById(R.id.listViewGames);
        btnAddGame = findViewById(R.id.btnAddGame);

        gameList = DataManager.getInstance().getGames();

        updateList();

        // Öffnet AddGameActivity
        btnAddGame.setOnClickListener(v -> {
            startActivity(new Intent(GameActivity.this, AddGameActivity.class));
        });

        // Voting
        listViewGames.setOnItemClickListener((parent, view, position, id) -> {
            Game game = gameList.get(position);
            game.addVote();
            updateList();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        DataManager.getInstance().addListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        DataManager.getInstance().removeListener(this);
    }

    // Wird automatisch aufgerufen bei neuen Spielen!
    @Override
    public void onGameListChanged() {
        runOnUiThread(this::updateList);
    }

    private void updateList() {
        List<String> displayList = new ArrayList<>();

        for (Game game : gameList) {
            displayList.add(game.getName() + " (Votes: " + game.getVotes() + ")");
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                displayList
        );

        listViewGames.setAdapter(adapter);
    }
}
