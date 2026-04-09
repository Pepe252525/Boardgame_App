package com.example.boardgame_app;

public class Event {
    private String date;
    private String location;
    private String host;

    public Event(String date,String location, String host){
        this.date = date;
        this.location = location;
        this.host = host;
    }
    public String getDate(){
        return date;
    }
    public String getLOcation(){
        return location;
    }
    public String getHost(){
        return host;
    }
    public void setHost(String host ){
        this.host = host;
    }
}
