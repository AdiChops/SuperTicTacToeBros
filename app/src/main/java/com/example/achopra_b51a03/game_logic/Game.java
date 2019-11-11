package com.example.achopra_b51a03.game_logic;

import com.example.achopra_b51a03.R;

import java.io.Serializable;

public class Game implements Serializable {
    private static final int NUM_PLAYERS = 2;
    public static final int NUM_ROWS = 3;
    public static final int NUM_COLUMNS = 3;
    private static final int PLAYER_ONE_IMAGE = R.drawable.x;
    private static final int PLAYER_TWO_IMAGE = R.drawable.o;
    private static final char PLAYER_ONE_MARK = 'X';
    private static final char PLAYER_TWO_MARK = 'O';
    private Player[] players = new Player[NUM_PLAYERS];
    private int currentPlayer = 0;
    private char[][] board = new char[NUM_ROWS][NUM_COLUMNS];
    private char winningMode;
    private int winningRow;
    private int winningCol;
    private Player winner;
    public Game(){
        players[0] = new Player(PLAYER_ONE_IMAGE, PLAYER_ONE_MARK, "Mario");
        players[1] = new Player(PLAYER_TWO_IMAGE,PLAYER_TWO_MARK, "King Boo");
        for(int i = 0; i < NUM_ROWS; i++){
            for(int j = 0; j < NUM_COLUMNS; j++){
                board[i][j]='-';
            }
        }
        winningMode = '-';
    }
    public char getWinningMode(){
        return winningMode;
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
            if(isWin)
                winningMode = 'R';
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
            if(isWin)
                winningMode = 'L';
        }
        return isWin;
    }
    private boolean checkHorizontal(){
        boolean isWin = false;
        int i;
        for(i = 0; i < NUM_ROWS && !isWin; i++){
            boolean toContinue = true;
            for(int j=0; j < NUM_COLUMNS-1 && toContinue; j++) {
                if(board[i][j] == '-' || board[i][j] != board[i][j+1])
                    toContinue = false;
            }
            isWin = toContinue;
            if(isWin) {
                winningMode = 'H';
                winningRow = i;
            }
        }
        return isWin;
    }
    private boolean checkVertical(){
        boolean isWin = false;
        int i;
        for(i= 0; i < NUM_COLUMNS && !isWin; i++){
            boolean toContinue = true;
            for(int j=0; j < NUM_ROWS-1 && toContinue; j++) {
                if(board[j][i] == '-' || board[j][i] != board[j+1][i])
                    toContinue = false;
            }
            isWin = toContinue;

            if(isWin) {
                winningMode = 'V';
                winningCol = i;
            }
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
            winner = players[currentPlayer];
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
    public int getPlayerDrawable(int row, int col){
        if(board[row][col] != '-'){
            if(board[row][col] == PLAYER_ONE_MARK)
                return PLAYER_ONE_IMAGE;
            return PLAYER_TWO_IMAGE;
        }
        return 0;
    }
    public int getWinningRow(){
        return winningRow;
    }
    public int getWinningCol(){
        return winningCol;
    }
    public String getWinnerName(){
        return winner.getName();
    }
}
