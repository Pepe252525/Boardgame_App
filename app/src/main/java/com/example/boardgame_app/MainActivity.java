package com.example.boardgame_app;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnVote = findViewById(R.id.btnSpielevote);
        Button btnTermine = findViewById(R.id.btnTermine);
        Button btnBewertung = findViewById(R.id.btnBewertung);
        Button btnChat = findViewById(R.id.btnChat);
        Button btnSpieler = findViewById(R.id.btnSpielerliste);

        btnVote.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, GameActivity.class));
        });

        btnTermine.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, EventActivity.class));
        });

        btnBewertung.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, BewertungActivity.class));
        });

        btnChat.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ChatActivity.class));
        });

        btnSpieler.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PlayerListActivity.class));
        });

    }
}