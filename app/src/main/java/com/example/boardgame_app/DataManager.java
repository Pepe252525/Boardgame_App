package com.example.boardgame_app;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager instance;

    private List<Game> games;
    private List<Message> messages;

    // --- GAME LISTENER ---
    public interface GameChangeListener {
        void onGameListChanged();
    }

    private List<GameChangeListener> gameListeners;

    // --- MESSAGE LISTENER ---
    public interface MessageChangeListener {
        void onMessageListChanged();
    }

    private List<MessageChangeListener> messageListeners;

    private DataManager() {
        games = new ArrayList<>();
        messages = new ArrayList<>();
        gameListeners = new ArrayList<>();
        messageListeners = new ArrayList<>();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    // --- GAMES ---
    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
        notifyGameListeners();
    }

    public void addGameListener(GameChangeListener listener) {
        gameListeners.add(listener);
    }

    public void removeGameListener(GameChangeListener listener) {
        gameListeners.remove(listener);
    }

    public void notifyGameListeners() {
        for (GameChangeListener l : gameListeners) {
            l.onGameListChanged();
        }
    }

    // --- MESSAGES ---
    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
        notifyMessageListeners();
    }

    public void addMessageListener(MessageChangeListener listener) {
        messageListeners.add(listener);
    }

    public void removeMessageListener(MessageChangeListener listener) {
        messageListeners.remove(listener);
    }

    private void notifyMessageListeners() {
        for (MessageChangeListener l : messageListeners) {
            l.onMessageListChanged();
        }
    }
}
