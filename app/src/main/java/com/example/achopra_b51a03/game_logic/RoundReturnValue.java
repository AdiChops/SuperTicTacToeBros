package com.example.achopra_b51a03.game_logic;

public class RoundReturnValue {
    private char winResult;
    private int imgResource;

    public RoundReturnValue(char winResult, int imgResource) {
        this.winResult = winResult;
        this.imgResource = imgResource;
    }
    public int getImgResource(){
        return imgResource;
    }
    public char getWinResult(){
        return winResult;
    }

}
