package com.example.boardgame_app;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager instance;

    private List<Game> games;

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

    public void notifyListeners() {
        for (GameChangeListener l : listeners) {
            l.onGameListChanged();
        }
    }

    public void addListener(GameChangeListener l) {
        listeners.add(l);
    }

    public void removeListener(GameChangeListener l) {
        listeners.remove(l);
    }
}
