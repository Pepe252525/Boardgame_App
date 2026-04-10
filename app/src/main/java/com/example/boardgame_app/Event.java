package com.example.boardgame_app;

public class Event {

    private String date;
    private String location;
    private String host;
    private float rating;

    public Event(String date, String location, String host) {
        this.date = date;
        this.location = location;
        this.host = host;
        this.rating = 0;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getHost() {
        return host;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}