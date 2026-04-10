package com.example.boardgame_app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
        players.add(new Player("Max", "Zuhause"));
        players.add(new Player("Anna", "Garage"));
        players.add(new Player("Tom", "Club"));
        players.add(new Player("Lisa", "Ferienhaus"));

        currentEvent = createNextEvent();
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
        currentEvent = createNextEvent();
    }

    // --- Event ---
    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event event) {
        this.currentEvent = event;
    }

    public List<Event> getUpcomingEvents(int count) {
        List<Event> events = new ArrayList<>();

        if (players.isEmpty()) {
            return events;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);

        for (int i = 0; i < count; i++) {
            Player hostPlayer = players.get(i % players.size());
            String date = formatter.format(calendar.getTime());

            Event event = new Event(date, hostPlayer.getLocation(), hostPlayer.getName());
            events.add(event);

            calendar.add(Calendar.DAY_OF_MONTH, 7);
        }

        return events;
    }

    private Event createNextEvent() {
        List<Event> events = getUpcomingEvents(1);
        if (!events.isEmpty()) {
            return events.get(0);
        }
        return null;
    }
}