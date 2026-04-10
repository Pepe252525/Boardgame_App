package com.example.boardgame_app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements DataManager.MessageChangeListener {

    ListView listViewMessages;
    EditText editMessage;
    Button btnSend;

    List<Message> messageList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        listViewMessages = findViewById(R.id.listViewMessages);
        editMessage = findViewById(R.id.editMessage);
        btnSend = findViewById(R.id.btnSend);

        messageList = DataManager.getInstance().getMessages();

        updateMessages();

        btnSend.setOnClickListener(v -> {
            String text = editMessage.getText().toString().trim();

            if (!text.isEmpty()) {
                DataManager.getInstance().addMessage(
                        new Message(text, "Ich")
                );
                editMessage.setText("");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        DataManager.getInstance().addMessageListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        DataManager.getInstance().removeMessageListener(this);
    }

    // Wird automatisch aufgerufen bei neuen Nachrichten!
    @Override
    public void onMessageListChanged() {
        runOnUiThread(this::updateMessages);
    }

    private void updateMessages() {
        List<String> displayList = new ArrayList<>();

        for (Message msg : messageList) {
            displayList.add(msg.getSender() + ": " + msg.getText());
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                displayList
        );

        listViewMessages.setAdapter(adapter);
    }
}
