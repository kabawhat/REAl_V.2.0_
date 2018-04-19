package com.example.bashirbin.real_v20;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    // creating the instance the of the Question bank
    QuestionBank questionBank = new QuestionBank();

    // boolean this is use to set if the there should be timer or not
    boolean yesTimer;


    //declaring  our fields to holds our views
    private TextView questionText;
    private TextView scoresView;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;
    private ProgressBar progressBar;
    private TextView timeToPlay;

    // this is use for storing the time recieved from the selectGameAcivity(intent)
    long timePlayedFromGameSelect;

    // this is countDownTimer variable is declared as a global variable
    CountDownTimer counterTimer;


    //
    private int scores = 0; // to record the score of the game intilizes to zero
    private int questionNumber = 0; //to record the questionNumber intilizes to zero
    private String correctAnswer; // to hold the correct correctAnswer


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //wiring the view together
        questionText = (TextView) findViewById(R.id.questionText);
        scoresView = (TextView) findViewById(R.id.scoreView);
        option1 = (Button) findViewById(R.id.ans1_button);
        option2 = (Button) findViewById(R.id.ans2_button);
        option3 = (Button) findViewById(R.id.ans3_button);
        option4 = (Button) findViewById(R.id.ans4_button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        timeToPlay = (TextView) findViewById(R.id.playtimer);


        // this automatically intialize the view if the database is empty
        // and it been called
        questionBank.intitializeQuestion(getApplication());

        // recieving the timeplayed from the select game Activity
        // and the result is passed into the timer method below
        Intent getTimePlayed = getIntent();
        timePlayedFromGameSelect = getTimePlayed.getLongExtra("timePlayed", 0);

        timerMethod(20000);


        //////////////////////////////////////////////////////////////////////////////////////////////////

        // if the intent recieved is noTime then the yesTimer is set to false
        if (timePlayedFromGameSelect == 1000 * 15 * 60) {

            yesTimer = false;
            timeToPlay.setText("NO-Time");


            // if the intent recieved is not noTime then the yesTimer is set to true the number of milliseconds recieved by the intent
        } else {
            // sending the Intent and chaining to
            // if there is time start counting
            // cancel the counterTimer
            yesTimer = true;
            counterTimer.cancel();
            //this is the timer method set with the value got from the intent
            timerMethod(timePlayedFromGameSelect);

        }


        // this method is used to update the questions
        updateQuestion(yesTimer);

        // to update the scoreView text
        upadateScore(scores);

        // the onClicknester the test what button is clicked
        View.OnClickListener listerner = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Button answer = (Button) view;

                // this property below is for the animate
                animate(answer);


                if (answer.getText().equals(correctAnswer)) {

                    scores = scores + 1;
                    upadateProgress();
                    Toast.makeText(MainActivity.this, "Guru you got ", Toast.LENGTH_SHORT).show();
                    //counterTimer.cancel();
                } else {
                    //  int progrss  =(int) Math.floor(questionBank.getLenth()/scores));
                    upadateProgress();
                    Toast.makeText(MainActivity.this, "Olodo try -again ", Toast.LENGTH_SHORT).show();
                    // counterTimer.cancel();

                }
                updateQuestion(yesTimer);
                upadateScore(scores);


            }


        };
        option1.setOnClickListener(listerner);
        option2.setOnClickListener(listerner);
        option3.setOnClickListener(listerner);
        option4.setOnClickListener(listerner);


    }

    private void upadateScore(int scores) {

        scoresView.setText("SCORES :" + scores + " / " + questionBank.getLenth());
    }

    private void updateQuestion(boolean yesTimer) {
        //the animate method is called here
        animate(questionText);
        //first we check if we are not outside the array bound
        //checking if the questionNumber < questionBank size
        if (questionNumber < questionBank.getLenth()) {
            // set questionText and correctAnswer to our view
            questionText.setText(questionBank.getQuestion(questionNumber));
            option1.setText(questionBank.getChoice(questionNumber, 1));
            option2.setText(questionBank.getChoice(questionNumber, 2));
            option3.setText(questionBank.getChoice(questionNumber, 3));
            option4.setText(questionBank.getChoice(questionNumber, 4));
            correctAnswer = questionBank.getCorrectAnswer(questionNumber);
            questionNumber = questionNumber + 1;
            // the couneter is calles here

            counterTimer.cancel();

            if (yesTimer == true) {

                timerMethod(timePlayedFromGameSelect);

            }

            // timerMethod(timePlayedFromGameSelect);


            //this is the timer method set with the value got from the intent
            //  timerMethod(timePlayedFromGameSelect);


        } else {
            // when the questions are finished the else part is called
            // and here we take our scoreView and pass it to the next intent
            //
            Intent scoreBoard = new Intent(getApplicationContext(), ScoreActivity.class);
            scoreBoard.putExtra("score", scores);
            finish();
            startActivity(scoreBoard);


        }
    }


    public void animate(View view) {
        // this is simple animate method that zoom in a view and view it out back
        view.setScaleX(0);
        view.setScaleY(0);
        view.animate().scaleX(1).scaleY(1).start();

    }

    // timer method id here
    public void timerMethod(long time) {
        counterTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long l) {
                // long temp =l/1000;
                long temp = l;
                /*String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(temp), TimeUnit.MILLISECONDS.toMinutes(temp)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(temp)), TimeUnit.MILLISECONDS.toSeconds(temp)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(temp)));
                timeToPlay.setText(hms);*/
                //  int formatTimer = (int)timePlayedFromGameSelect/1000;
                // timeToPlay.setText(formatTimer +"");
                long hms = (l);
                String f = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(hms), TimeUnit.MILLISECONDS.toMinutes(hms), TimeUnit.MILLISECONDS.toSeconds(hms));
                timeToPlay.setText(f);
            }

            @Override
            public void onFinish() {
                timeToPlay.setText("No-Time");

                updateQuestion(yesTimer);
                upadateScore(scores);
                upadateProgress();

            }
        }.start();
    }

    private void upadateProgress() {
        progressBar.incrementProgressBy((int) Math.floor(100 / questionBank.getLenth()));
    }

}

