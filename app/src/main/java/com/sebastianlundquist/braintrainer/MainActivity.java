package com.sebastianlundquist.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    TextView resultTextView;
    ConstraintLayout gameLayout;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        restart(timerTextView);
    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct! :)");
            score++;
        }
        else {
            resultTextView.setText("Wrong! :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(score + "/" + numberOfQuestions);
        newQuestion();
    }

    public void newQuestion() {
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(a + " + " + b);

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a+b);
            }
            else {
                int wrongAnswer = random.nextInt(41);
                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void restart(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(score + "/" + numberOfQuestions);
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        newQuestion();

        new CountDownTimer(5100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(Math.round(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Finished!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        resultTextView = findViewById(R.id.resultTextView);
        gameLayout = findViewById(R.id.gameLayout);

        button0 = findViewById(R.id.answer1Button);
        button1 = findViewById(R.id.answer2Button);
        button2 = findViewById(R.id.answer3Button);
        button3 = findViewById(R.id.answer4Button);
        goButton = findViewById(R.id.goButton);
        playAgainButton = findViewById(R.id.playAgainButton);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}
