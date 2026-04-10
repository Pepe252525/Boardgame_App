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