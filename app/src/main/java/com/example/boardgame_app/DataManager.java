package com.example.boardgame_app;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager instance;

    private List<Game> games;

    // Listener Interface
    public interface GameChangeListener {
        void onGameListChanged();
    }

    private List<GameChangeListener> listeners;

    private DataManager() {
        games = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
        notifyListeners();
    }

    // Listener hinzufügen
    public void addListener(GameChangeListener listener) {
        listeners.add(listener);
    }

    // Listener entfernen
    public void removeListener(GameChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (GameChangeListener listener : listeners) {
            listener.onGameListChanged();
        }
    }
}

