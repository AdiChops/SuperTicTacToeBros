package com.example.achopra_b51a03;

public class Player {
    private int imgResource;
    private char mark;
    public Player(){

    }
    public Player(int res, char m){
        imgResource = res;
        mark = m;
    }
    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }
}
