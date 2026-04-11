package com.example.boardgame_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PlayerListActivity extends AppCompatActivity {

    private EditText etPlayerName;
    private EditText etPlayerLocation;
    private LinearLayout layoutPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        etPlayerName = findViewById(R.id.etPlayerName);
        etPlayerLocation = findViewById(R.id.etPlayerLocation);
        layoutPlayers = findViewById(R.id.layoutPlayers);
        Button btnAddPlayer = findViewById(R.id.btnAddPlayer);

        loadPlayers();

        btnAddPlayer.setOnClickListener(v -> {
            String name = etPlayerName.getText().toString().trim();
            String location = etPlayerLocation.getText().toString().trim();

            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(location)) {
                Player newPlayer = new Player(name, location);
                DataManager.getInstance().addPlayer(newPlayer);

                etPlayerName.setText("");
                etPlayerLocation.setText("");

                loadPlayers();
            }
        });
    }

    private void loadPlayers() {
        List<Player> players = DataManager.getInstance().getPlayers();

        layoutPlayers.removeAllViews();

        for (Player player : players) {
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setPadding(0, 0, 0, 30);

            TextView tvPlayer = new TextView(this);
            tvPlayer.setText(
                    getString(R.string.player_item_text, player.getName(), player.getLocation())
            );
            tvPlayer.setTextSize(18);

            Button btnDelete = new Button(this);
            btnDelete.setText(getString(R.string.delete_player_button));
            btnDelete.setOnClickListener(v -> {
                DataManager.getInstance().removePlayer(player);
                loadPlayers();
            });

            itemLayout.addView(tvPlayer);
            itemLayout.addView(btnDelete);

            layoutPlayers.addView(itemLayout);
        }
    }
}