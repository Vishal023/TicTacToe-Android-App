package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    /**
     *O - O
     *1 - X
     *2 - BLANK
     * */
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPos = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };

    public void playerTap(View view){
        ImageView imageView = (ImageView) view;
        int tappedImage = Integer.parseInt(imageView.getTag().toString());
        if (!gameActive){
            gameReset(view);
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            imageView.setTranslationY(-1000f);
            if (activePlayer == 0) {
                imageView.setImageResource(R.drawable.tic_tac_toe_o);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("X's TURN TAP TO PLAY");
            } else {
                imageView.setImageResource(R.drawable.tic_tac_toe_x);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("O's TURN TAP TO PLAY");
            }
            imageView.animate().translationYBy(1000f).setDuration(300);
        }

        for (int[] winPosition:winningPos){
            if((gameState[winPosition[0]] == gameState[winPosition[1]]) &&
                    (gameState[winPosition[1]] == gameState[winPosition[2]]) &&
                    (gameState[winPosition[0]] != 2)
            ){
              //win
                gameActive = false;
                String winnerStr="";
              if (gameState[winPosition[0]]== 0){
                  winnerStr = "O HAS WON";
              }
              else {
                  winnerStr = "X HAS WON";
              }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
    }
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        gameState = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's TURN TAP TO PLAY");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
