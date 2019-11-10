package com.example.achopra_b51a03.game_logic;

public class Player {
    private int imgResource;
    private char mark;
    public Player(){

    }
    protected Player(int res, char m){
        imgResource = res;
        mark = m;
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
}
