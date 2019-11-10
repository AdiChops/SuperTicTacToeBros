package com.example.achopra_b51a03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.achopra_b51a03.game_logic.Game;
import com.example.achopra_b51a03.game_logic.RoundReturnValue;

public class GameActivity extends AppCompatActivity {
    private Game game = new Game();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TableLayout tblButtons = findViewById(R.id.tblButtons);
        int length = tblButtons.getChildCount();
        for(int i = 0; i < length; i++){
            TableRow currentRow = (TableRow)tblButtons.getChildAt(i);
            int lgth = currentRow.getChildCount();
            for(int j = 0; j < lgth; j++){
                final int row = i;
                final int col = j;
                final ImageButton btnCurrent = (ImageButton)currentRow.getChildAt(j);
                btnCurrent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RoundReturnValue round = game.playGame(row, col);
                        int img = round.getImgResource();
                        btnCurrent.setForeground(getDrawable(img));
                        btnCurrent.setEnabled(false);
                        if(round.getWinResult() != 'C' && round.getWinResult() != 'T'){
                            Toast.makeText(getApplicationContext(), round.getWinResult() + " won", Toast.LENGTH_SHORT).show();
                        }
                        else if(round.getWinResult() == 'T'){
                            Toast.makeText(getApplicationContext(), "It's a tie!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}
