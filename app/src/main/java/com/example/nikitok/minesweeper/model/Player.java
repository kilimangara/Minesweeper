package com.example.nikitok.minesweeper.model;


import android.content.Intent;

public class Player {
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer score;
    private String name;
    public Player(String name, Integer score){
        this.name= name;
        this.score=score;
    }
}
