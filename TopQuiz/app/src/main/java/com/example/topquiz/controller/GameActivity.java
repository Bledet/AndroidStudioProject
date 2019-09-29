package com.example.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.Question;
import com.example.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    private TextView mQuestion;
    private Button mbutton1;
    private Button mbutton2;
    private Button mbutton3;
    private Button mbutton4;

    private QuestionBank mQuestionBank;

    public QuestionBank generateQuestions(){
        Question question1 = new Question("Who is the creator of Android?",
                Arrays.asList("Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"),
                0);

        Question question2 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958",
                        "1962",
                        "1967",
                        "1969"),
                3);

        Question question3 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42",
                        "101",
                        "666",
                        "742"),
                3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /* On remplis la banque de question */
        mQuestionBank = this.generateQuestions();

        /*Connection des boutons*/
        mQuestion = (TextView) findViewById(R.id.activity_game_question_text);
        mbutton1 = (Button) findViewById(R.id.activity_game_answer1_btn);
        mbutton2 = (Button) findViewById(R.id.activity_game_answer2_btn);
        mbutton3 = (Button) findViewById(R.id.activity_game_answer3_btn);
        mbutton4 = (Button) findViewById(R.id.activity_game_answer4_btn);




    }
}
