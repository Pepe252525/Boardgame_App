package com.example.boardgame_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Button btnVote = findViewById(R.id.btnSpielevote);
    Button btnTermine = findViewById(R.id.btnTermine);
    Button btnVorschlag = findViewById(R.id.btnSpielvorschlaege);
    Button btnChat = findViewById(R.id.btnChat);
    Button btnSpieler = findViewById(R.id.btnSpielerliste);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnVote.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, GameVoteActivity.class));
        });

        btnTermine.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, EventActivity.class));
        });

        btnVorschlag.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddGameActivity.class));
        });

        btnChat.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ChatActivity.class));
        });

        btnSpieler.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PlayerListActivity.class));
        });

    }
}