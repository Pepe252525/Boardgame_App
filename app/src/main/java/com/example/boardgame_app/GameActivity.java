package com.example.boardgame_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class GameActivity extends AppCompatActivity implements DataManager.GameChangeListener {

    private ListView listViewGames;
    private EditText editGameName;
    private Button btnCreateGame;

    private List<Game> gameList;
    private GameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Views
        listViewGames = findViewById(R.id.listViewGames);
        editGameName = findViewById(R.id.editGameName);
        btnCreateGame = findViewById(R.id.btnCreateGame);

        // Daten holen
        gameList = DataManager.getInstance().getGames();

        // Adapter initialisieren
        adapter = new GameAdapter(this, gameList);
        listViewGames.setAdapter(adapter);

        // Spiel erstellen
        btnCreateGame.setOnClickListener(v -> createGame());

        // Voting
        listViewGames.setOnItemClickListener((parent, view, position, id) -> {
            Game game = gameList.get(position);
            game.addVote();

            DataManager.getInstance().notifyListeners();
        });
    }

    private void createGame() {
        String name = editGameName.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Bitte Namen eingeben", Toast.LENGTH_SHORT).show();
            return;
        }

        DataManager.getInstance().addGame(new Game(name));

        editGameName.setText("");

        Toast.makeText(this, "Spiel erstellt", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onGameListChanged() {
        runOnUiThread(() -> adapter.notifyDataSetChanged());
    }
}
