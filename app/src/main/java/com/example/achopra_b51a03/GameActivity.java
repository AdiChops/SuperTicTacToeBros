package com.example.achopra_b51a03;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.achopra_b51a03.game_logic.Game;
import com.example.achopra_b51a03.game_logic.RoundReturnValue;
import com.google.android.material.snackbar.Snackbar;

public class GameActivity extends AppCompatActivity {
    private Game game = new Game();
    private TableLayout tblButtons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tblButtons = findViewById(R.id.tblButtons);
        if(savedInstanceState != null){
            game = (Game)savedInstanceState.getSerializable("Game");
            restoreGame();
        }
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
                        btnCurrent.setBackgroundResource(img);
                        btnCurrent.setEnabled(false);
                        if(round.getWinResult() != 'C' && round.getWinResult() != 'T'){
                            Snackbar mySnackbar = Snackbar.make(view,
                                    game.getWinnerName() + " won the game!", Snackbar.LENGTH_LONG);
                            mySnackbar.show();
                            tblButtons.setEnabled(false);
                            stopGame();
                            gameWinDisplay();
                        }
                        else if(round.getWinResult() == 'T'){
                            Snackbar mySnackbar = Snackbar.make(view,
                                    "It's a tie!", Snackbar.LENGTH_LONG);
                            mySnackbar.show();
                            stopGame();
                        }
                    }
                });
            }
        }
    }
    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Game", game);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new) {
            startNewGame();
            return true;
        }
        else if (item.getItemId() == R.id.action_about) {
            getAbout();
            return true;
        }

        else if(item.getItemId() == R.id.action_exit){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void startNewGame(){
        Intent i = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(i);
    }
    public void getAbout(){
        Intent i = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(i);
    }
    private void stopGame(){
        int length = tblButtons.getChildCount();
        for(int i = 0; i < length; i++){
            TableRow trow = (TableRow)tblButtons.getChildAt(i);
            int lgth = trow.getChildCount();
            for(int j = 0; j < lgth; j++){
                trow.getChildAt(j).setEnabled(false);
            }
        }
    }
    private void restoreGame(){
        int length = tblButtons.getChildCount();
        for(int i = 0; i < length; i++){
            TableRow trow = (TableRow)tblButtons.getChildAt(i);
            int lgth = trow.getChildCount();
            for(int j = 0; j < lgth; j++){
                int img = game.getPlayerDrawable(i,j);
                if(img != 0) {
                    trow.getChildAt(j).setBackgroundResource(img);
                    trow.getChildAt(j).setEnabled(false);
                }
                else {
                    trow.getChildAt(j).setEnabled(game.getWinningMode() == '-');
                }
            }
        }
        if(game.getWinningMode() != '-'){
            gameWinDisplay();
        }
    }
    private void gameWinDisplay(){
        switch(game.getWinningMode()){
            case 'H':
                TableRow r = (TableRow)tblButtons.getChildAt(game.getWinningRow());
                for(int k = 0; k < r.getChildCount(); k++) {
                    ImageButton btn = (ImageButton) r.getChildAt(k);
                    btn.setForeground(getDrawable(R.drawable.horizontal));
                }
                break;
            case 'V':
                for(int k = 0; k < tblButtons.getChildCount(); k++){
                    TableRow tr = (TableRow) tblButtons.getChildAt(k);
                    ImageButton btn = (ImageButton) tr.getChildAt(game.getWinningCol());
                    btn.setForeground(getDrawable(R.drawable.vertical));
                }
                break;
            case 'R':
                int j = 0;
                for(int k = 0; k < tblButtons.getChildCount() && j < Game.NUM_COLUMNS; k++, j++ ){
                    TableRow tr = (TableRow) tblButtons.getChildAt(k);
                    ImageButton btn = (ImageButton) tr.getChildAt(j);
                    btn.setForeground(getDrawable(R.drawable.right));
                }
                break;
            case 'L':
                int i = 0;
                for (int k = Game.NUM_COLUMNS-1; i < Game.NUM_ROWS && k >= 0; i++, k--) {
                    TableRow tr = (TableRow) tblButtons.getChildAt(i);
                    ImageButton btn = (ImageButton) tr.getChildAt(k);
                    btn.setForeground(getDrawable(R.drawable.left));
                }
                break;
            default:
                break;
        }
    }
}
