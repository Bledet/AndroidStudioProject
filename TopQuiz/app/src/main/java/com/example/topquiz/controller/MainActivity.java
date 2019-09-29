package com.example.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.Question;
import com.example.topquiz.model.QuestionBank;
import com.example.topquiz.model.User;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private User mUser;
    public TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Référencement et initialisation */
        mUser = new User();
        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        mPlayButton.setEnabled(false); //Désactivation du bouton






        /* Paramètre de changement de text */
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This is where we'll check the user input
                mPlayButton.setEnabled(s.toString().length() != 0); //On active le bouton si il a bien entré son nom
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /* Paramètre de cliquabilité du bouton */
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                mUser.setFirstName(mNameInput.getText().toString());

                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivity);

                System.out.println("NOM DU JOUEUR : "+ mUser.getFirstName());
            }
        });




    }
}
