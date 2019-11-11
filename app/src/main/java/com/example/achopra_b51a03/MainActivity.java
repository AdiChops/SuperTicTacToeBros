package com.example.achopra_b51a03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAbout = findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAbout();
            }
        });
        Button btnNew = findViewById(R.id.btnPlay);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewGame();
            }
        });
        Button btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });
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
            exit();
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
    public void exit(){
        finish();
        System.exit(0);
    }
}
