package com.example.tiktaktoe;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //Player Representation
    //  0 -> X
    //  1 -> O
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    // States Keywords
    //  1 -> O
    //  0 -> X
    //  2 -> Null
    int[][] winingPosition = { {0,1,2},{3,4,5},{6,7,8},
                                {0,3,6},{1,4,7},{2,5,8},
                                {0,4,8},{2,4,6}};
    public void playing_tap(View view){
        ImageView img = (ImageView)  view;
        int tapped_image = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            GameReset(view);
        }
        if(gameState[tapped_image]==2){
            gameState[tapped_image] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.thisx);
                activePlayer = 1;
                TextView status = findViewById(R.id.stauts);
                status.setText("O's Turn tap to Play");
            }
            else{
                img.setImageResource(R.drawable.thiso);
                activePlayer = 0;
                TextView status = findViewById(R.id.stauts);
                status.setText("X's Turn tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //Check Result

        for(int[] winingPosition : winingPosition){
            if(gameState[winingPosition[0]] == gameState[winingPosition[1]] &&
                    gameState[winingPosition[1]] == gameState[winingPosition[2]] &&
                    gameState[winingPosition[0]] != 2){
                //SomeBody Won Lets find Who
                String winningStr ;
                gameActive = false;
                if(gameState[winingPosition[0]] == 0){
                    winningStr = "X, Won the Game.";

                }
                else {
                    winningStr = "O, Won the Game.";
                }
                //Updating Result
                TextView status = findViewById(R.id.stauts);
                status.setText(winningStr);

            }
        }
    }

    public void GameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i = 0 ; i < gameState.length;i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        TextView status = findViewById(R.id.stauts);
        status.setText("X's Turn tap to Play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}