package com.example.bashirbin.real_v20;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;

public class ScoreActivity extends AppCompatActivity {

    Button restartGame;
    Button highestScore;
    Button currentScore;
    Button onlineCompete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        restartGame = (Button)findViewById(R.id.restartgameButton);
        highestScore = (Button)findViewById(R.id.highestSCoreText);
        currentScore = (Button)findViewById(R.id.currentScoreText);
        onlineCompete = (Button)findViewById(R.id.updateONline);

        // to get the result or score from the other activity

       Intent getScoreIntent = getIntent();
        int score = getScoreIntent.getIntExtra("score",0);
        currentScore.setText(" your score => " + score);

       /* // set the  ur current score that the score recieved to the other activity
        currentScore.setText(" your score => " + score);

        // we try to store our highset score in  the shared prefence
        // on line 36 we are declaring our sharedPrefence as "highestScore and assigning 0"
        SharedPreferences mySharedPrefence = getPreferences(MODE_PRIVATE);
        int highest = mySharedPrefence.getInt("highestScore",0);

        // we compare
        if(highest >= score){
            highestScore.setText("Your Highest remains => " + highestScore);
        }
        else if(score >= highest){

            highestScore.setText("*****  Congratulations ***** \n New High-Score = >" + score);
            SharedPreferences.Editor editor = mySharedPrefence.edit();
            editor.putInt("highestScore",score);
            editor.commit();*/



       SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE",0);

        if(score >= highScore){

            highestScore.setText("******* Congratulation ************ \n NEW HIGH_SCORE => " + highScore);
            Toast.makeText(this, "New High score Reached ", Toast.LENGTH_SHORT).show();
            // save

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        }
        else{

            highestScore.setText("Your High Score \n Remains => :" + score);
        }













        restartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent StartGame = new Intent(getApplicationContext(),SelectGameModeActivity.class);
             //   finish();
                startActivity(StartGame);
            }
        });

        onlineCompete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateProfile = new Intent(getApplicationContext(),ProfilePicActivity.class);
                //   finish();
                startActivity(updateProfile);
            }
        });

    }
}
