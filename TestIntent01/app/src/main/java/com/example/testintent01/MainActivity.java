package com.example.testintent01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edit;
    private Button envoyer;

    private String enrg;
    public static final String EXTRA_MESSAGE = "com.example.testintent01.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit     = (EditText) findViewById(R.id.activity_main_edit_text);
        envoyer  = (Button) findViewById(R.id.buttonEnvoyer);


        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Récupération text */
                enrg = edit.getText().toString();
                System.out.println("Text du EditText : " + enrg);

                if(edit.getText().toString().matches("")){
                    enrg = "Saisie vide";
                }



                /*Création intention puis envoie*/
                Intent intention = new Intent(MainActivity.this, SecondActivity.class); // création
                intention.putExtra(EXTRA_MESSAGE, enrg);                                              // Inclusion du message
                startActivity(intention);                                                             // Démarage de la seconde activité (envoie)
            }
        });

    }
}
