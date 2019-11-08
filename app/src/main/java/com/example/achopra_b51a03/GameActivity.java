package com.example.achopra_b51a03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
    private Player[] players = new Player[2];
    private int currentPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        players[0] = new Player(R.drawable.x, 'X');
        players[1] = new Player(R.drawable.o,'O');
    }
}
