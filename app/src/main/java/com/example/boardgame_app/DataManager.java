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
    private List<Event> pastEvents;

    public interface GameChangeListener {
        void onGameListChanged();
    }

    private List<GameChangeListener> listeners;

    private DataManager() {
        games = new ArrayList<>();
        messages = new ArrayList<>();
        players = new ArrayList<>();
        pastEvents = new ArrayList<>();
        listeners = new ArrayList<>();

        // Spieler mit Locations
        players.add(new Player("Max", "Zuhause"));
        players.add(new Player("Anna", "Garage"));
        players.add(new Player("Tom", "Club"));
        players.add(new Player("Lisa", "Ferienhaus"));

        // Nächster kommender Termin
        currentEvent = createNextEvent();

        // Vergangene Spielabende dynamisch aus players.get(...)
        Event event1 = new Event("05.04.2026", players.get(1).getLocation(), players.get(1).getName());
        event1.setRating(4);

        Event event2 = new Event("29.03.2026", players.get(2).getLocation(), players.get(2).getName());
        event2.setRating(5);

        Event event3 = new Event("22.03.2026", players.get(3).getLocation(), players.get(3).getName());
        event3.setRating(3);

        Event event4 = new Event("15.03.2026", players.get(0).getLocation(), players.get(0).getName());
        event4.setRating(4);

        pastEvents.add(event1);
        pastEvents.add(event2);
        pastEvents.add(event3);
        pastEvents.add(event4);
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

    // --- Current Event ---
    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event event) {
        this.currentEvent = event;
    }

    // --- Past Events ---
    public List<Event> getPastEvents() {
        return pastEvents;
    }

    public void addPastEvent(Event event) {
        pastEvents.add(0, event);
    }

    // --- Kommende Termine ---
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