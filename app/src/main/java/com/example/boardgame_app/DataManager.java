package com.example.boardgame_app;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager instance;

    private List<Game> games;
    private List<Message> messages;
    private List<Player> players;
    private Event currentEvent;

    private DataManager() {
        games = new ArrayList<>();
        messages = new ArrayList<>();
        players = new ArrayList<>();

        // Beispiel-Daten
        currentEvent = new Event("10.04.2026", "Dortmund", "Max");
        players.add(new Player("Max"));
        players.add(new Player("Anna"));
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    // --- Games ---
    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    // --- Messages ---
    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    // --- Players ---
    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    // --- Event ---
    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event event) {
        this.currentEvent = event;
    }
}
