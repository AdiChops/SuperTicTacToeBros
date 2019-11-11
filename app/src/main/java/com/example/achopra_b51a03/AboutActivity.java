package com.example.achopra_b51a03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
    public void getAbout() {
        Intent i = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(i);
    }
}
