package com.example.boardgame_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddGameActivity extends AppCompatActivity {

    EditText editGameName;
    Button btnSaveGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        editGameName = findViewById(R.id.editGameName);
        btnSaveGame = findViewById(R.id.btnSaveGame);

        btnSaveGame.setOnClickListener(v -> {
            String gameName = editGameName.getText().toString();

            if (gameName.isEmpty()) {
                Toast.makeText(this, "Bitte Namen eingeben", Toast.LENGTH_SHORT).show();
                return;
            }

            // Spiel erstellen
            Game newGame = new Game(gameName);

            // In DataManager speichern
            DataManager.getInstance().getGames().add(newGame);

            Toast.makeText(this, "Spiel gespeichert!", Toast.LENGTH_SHORT).show();

            // Eingabefeld leeren
            editGameName.setText("");

            // Optional: Activity schließen
            finish();
        });
    }
}
