package com.example.ruinan.animation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // 0 == red 1 == black
    int activePlayer = 0;
    int[] gameStatus = new int[9];
    int[][] winPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int count = 9;
    boolean clickable = true;
    public void dropIn (View view) {
        ImageView counter = (ImageView) view;
        Integer tag = Integer.parseInt(counter.getTag().toString());
        if (this.gameStatus[tag] != 2 || !clickable) { // if somebody win. the n
            return;
        }

        Log.i("count", count + "");
        this.gameStatus[tag] = this.activePlayer;
        this.count--;

        counter.setTranslationY(-1000f);
        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.blackcross);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.reddot);
            activePlayer = 0;
        }
        counter.animate().translationYBy(1000f).setDuration(300);
        for (int[] win : this.winPosition) {
            if (this.gameStatus[win[0]] == this.gameStatus[win[1]] && this.gameStatus[win[1]] == this.gameStatus[win[2]] && this.gameStatus[win[0]] != 2) {
                Log.i("Winner", "Player: " + Integer.toString(activePlayer) + "Win!");
                TextView text = (TextView) findViewById(R.id.winner);
                if (activePlayer == 0) {
                    text.setTextColor(Color.RED);
                    text.setTranslationX(-1000f);
                    text.setText("Winner is Red Dot");
                    text.animate().translationXBy(1000f).setDuration(300);
                } else {
                    text.setTextColor(Color.BLACK);
                    text.setTranslationX(-1000f);
                    text.setText("Winner is Black Cross");
                    text.animate().translationXBy(1000f).setDuration(300);
                }
                clickable = false;
            }
        }
        if (count == 0) { // tie

            TextView text = (TextView) findViewById(R.id.winner);
            text.setTranslationX(-1000f);
            text.setTextColor(Color.BLUE);
            text.setText("Tie!");
            text.animate().translationXBy(1000f).setDuration(300);
            Log.i("Winner", "Tie!!");
        }
    }

    public void playAgain (View view) {
        // remove winner
        TextView text = (TextView) findViewById(R.id.winner);
        text.setText("");
        // reset status
        Arrays.fill(this.gameStatus, 2);

        GridLayout grid = (GridLayout)findViewById(R.id.grid);
        for (int i = 0; i < grid.getChildCount(); i++) {
            ImageView v = (ImageView)grid.getChildAt(i);
            v.setImageResource(0);
        }
        clickable = true;
        count = 9;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Arrays.fill(this.gameStatus, 2);
    }
}
