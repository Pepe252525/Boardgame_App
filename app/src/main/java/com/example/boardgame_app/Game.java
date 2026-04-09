package com.example.boardgame_app;

public class Game {
    private String name;
    private int votes;

    public Game(String name){
        this.name = name;
        this.votes = 0;
    }
    public String getName(){
        return name;
    }
    public int getVotes(){
        return votes;
    }
    public void addVote(){
        votes++;
    }
}
