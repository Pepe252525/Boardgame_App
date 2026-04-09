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

        Button btnSpielevote = findViewById(R.id.btnSpielevote);
        Button btnTermine = findViewById(R.id.btnTermine);
        Button btnSpielvorschlaege = findViewById(R.id.btnSpielvorschlaege);
        Button btnChat = findViewById(R.id.btnChat);
        Button btnPlayerList = findViewById(R.id.btnPlayerList);

        btnSpielevote.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, GameVoteActivity.class)));

        btnTermine.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, EventActivity.class)));

        btnSpielvorschlaege.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AddGameActivity.class)));

        btnChat.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ChatActivity.class)));

        btnPlayerList.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, PlayerListActivity.class)));
    }
}