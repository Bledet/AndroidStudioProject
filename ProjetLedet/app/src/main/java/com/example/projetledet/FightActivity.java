package com.example.projetledet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class FightActivity extends AppCompatActivity {

    private TextView vPuissance;
    private TextView vVie;
    private String result;
    private String nbPiece;
    private String idButton;
    private TextView vPowerEnnemis;

    private Button attaqueButton;
    private Button fuiteButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        vPuissance    = (TextView) findViewById(R.id.fpuissance);
        vVie          = (TextView) findViewById(R.id.fvie);
        attaqueButton = (Button) findViewById(R.id.attaqueButton);
        fuiteButton   = (Button) findViewById(R.id.fuiteButton);
        vPowerEnnemis = (TextView) findViewById(R.id.puissance_adverse);



        Intent data = getIntent();
        vPuissance.setText(data.getStringExtra("power"));
        vVie.setText(data.getStringExtra("life"));
        result = data.getStringExtra("res");
        nbPiece = data.getStringExtra("nbPiece");
        idButton = data.getStringExtra("idButton");
        vPowerEnnemis.setText(data.getStringExtra("powerEnnemis"));

        attaqueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Random rand = new Random();
                int randj = rand.nextInt(1 - 0 + 1) + 0;
                rand = new Random();
                int randa = rand.nextInt(1 - 0 + 1) + 0;
                int res = Integer.parseInt(vPuissance.getText().toString())*randj - Integer.parseInt(vPowerEnnemis.getText().toString())*randa;
                if(res>=0){ //Cas de victoire
                    result = "VICTOIRE !!!";
                    int puissance = Integer.parseInt(vPuissance.getText().toString());
                    puissance += 10;
                    vPuissance.setText(Integer.toString(puissance));

                    int nb = Integer.parseInt(nbPiece);
                    nb -= 1;
                    nbPiece = Integer.toString(nb);

                }else{ //Cas de défaite

                    int vie = Integer.parseInt(vVie.getText().toString());
                    vie -= 3;
                    vVie.setText(Integer.toString(vie));
                    result = "DEFAITE...";

                }

                Intent intent = new Intent(FightActivity.this, MainActivity.class);
                intent.putExtra("rPower", vPuissance.getText().toString());
                intent.putExtra("rLife", vVie.getText().toString());
                intent.putExtra("rRes", result);
                intent.putExtra("rNbPiece", nbPiece);
                intent.putExtra("rIdButton", idButton);
                setResult(Activity.RESULT_OK,intent);
                finish();

            }
        });

        fuiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vie = Integer.parseInt(vVie.getText().toString());
                vie -= 1;
                vVie.setText(Integer.toString(vie));
                result = "Vous avez pris la fuite...";

                Intent intent = new Intent(FightActivity.this, MainActivity.class);
                intent.putExtra("rPower", vPuissance.getText().toString());
                intent.putExtra("rLife", vVie.getText().toString());
                intent.putExtra("rRes", result);
                intent.putExtra("rNbPiece", nbPiece);
                intent.putExtra("rIdButton", idButton);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });


    }
}
