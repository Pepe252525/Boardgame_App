package com.example.boardgame_app;

public class Player {

    private String name;
    private String location;

    public Player(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}