package com.example.achopra_b51a03.game_logic;

import com.example.achopra_b51a03.R;

public class Game {
    private static final int NUM_PLAYERS = 2;
    private static final int NUM_ROWS = 3;
    private static final int NUM_COLUMNS = 3;
    private static final int PLAYER_ONE_IMAGE = R.drawable.x;
    private static final int PLAYER_TWO_IMAGE = R.drawable.o;
    private static final char PLAYER_ONE_MARK = 'X';
    private static final char PLAYER_TWO_MARK = 'O';
    private Player[] players = new Player[NUM_PLAYERS];
    private int currentPlayer = 0;
    private char[][] board = new char[NUM_ROWS][NUM_COLUMNS];
    public Game(){
        players[0] = new Player(PLAYER_ONE_IMAGE, PLAYER_ONE_MARK);
        players[1] = new Player(PLAYER_TWO_IMAGE,PLAYER_TWO_MARK);
        for(int i = 0; i < NUM_ROWS; i++){
            for(int j = 0; j < NUM_COLUMNS; j++){
                board[i][j]='-';
            }
        }
    }
    private void switchPlayer(){
        if(currentPlayer == 0)
            currentPlayer = 1;
        else
            currentPlayer = 0;
    }
    private boolean checkDiagonal(){
        boolean isWin = false;
        boolean toContinue = true;
        if(board[0][0] != '-') {
            int i = 1;
            for (int j = 1; i < NUM_ROWS && j < NUM_COLUMNS && toContinue; i++, j++) {
                if(board[0][0] != board[i][j])
                    toContinue = false;
            }
            isWin = toContinue;
        }
        if(board[0][NUM_COLUMNS-1] != '-' && !isWin){
            toContinue = true;
            int i = 1;
            for (int j = NUM_COLUMNS-2; i < NUM_ROWS && j >= 0 && toContinue; i++, j--) {
                if(board[0][NUM_COLUMNS-1] != board[i][j]) {
                    toContinue = false;
                }
            }
            isWin = toContinue;
        }
        return isWin;
    }
    private boolean checkHorizontal(){
        boolean isWin = false;
        for(int i= 0; i < NUM_ROWS && !isWin; i++){
            boolean toContinue = true;
            for(int j=0; j < NUM_COLUMNS-1 && toContinue; j++) {
                if(board[i][j] == '-' || board[i][j] != board[i][j+1])
                    toContinue = false;
            }
            isWin = toContinue;
        }
        return isWin;
    }
    private boolean checkVertical(){
        boolean isWin = false;
        for(int i= 0; i < NUM_COLUMNS && !isWin; i++){
            boolean toContinue = true;
            for(int j=0; j < NUM_ROWS-1 && toContinue; j++) {
                if(board[j][i] == '-' || board[j][i] != board[j+1][i])
                    toContinue = false;
            }
            isWin = toContinue;
        }
        return isWin;
    }
    private boolean checkFull(){
        for(int i = 0; i < NUM_ROWS; i ++){
            for(int j = 0; j < NUM_COLUMNS; j++)
                if(board[i][j] == '-')
                    return false;
        }
        return true;
    }
    private char checkWin(){
        if(checkDiagonal() || checkHorizontal() || checkVertical()){
            return players[currentPlayer].getMark();
        }
        else if(checkFull()){
            return 'T'; // returns 'Tie'
        }
        else
            return 'C';//'Continue'
    }
    public RoundReturnValue playGame(int row, int col){
        board[row][col] = players[currentPlayer].getMark();
        int img = players[currentPlayer].getImgResource();
        char gameVal = checkWin();
        switchPlayer();
        return new RoundReturnValue(gameVal, img);
    }
}
