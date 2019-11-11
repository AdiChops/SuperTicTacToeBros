package com.example.achopra_b51a03.game_logic;

import java.io.Serializable;

public class Player implements Serializable {
    private int imgResource;
    private char mark;
    private String name;
    public Player(){

    }
    protected Player(int res, char m, String n){
        imgResource = res;
        mark = m;
        name = n;
    }
    protected int getImgResource() {
        return imgResource;
    }

    protected void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    protected char getMark() {
        return mark;
    }

    protected void setMark(char mark) {
        this.mark = mark;
    }
    protected String getName(){
        return name;
    }
}
