package com.example.projetledet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FightActivity extends AppCompatActivity {

    private TextView vPuissance;
    private TextView vVie;
    private String result;
    private String idButton;
    private String numPiece;
    private String bonus;
    private String viebonus;
    private String puissancebonus;
    private TextView vPowerEnnemis;
    private TextView vMonsterName;
    private ImageView vImgMonster;

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
        vImgMonster   = (ImageView) findViewById(R.id.imgMonster);
        vMonsterName = (TextView) findViewById(R.id.monsterName);




        Intent data = getIntent();
        vPuissance.setText(data.getStringExtra("power"));
        vVie.setText(data.getStringExtra("life"));
        result = data.getStringExtra("res");
        idButton = data.getStringExtra("idButton");
        numPiece = data.getStringExtra("numPiece");
        bonus = data.getStringExtra("bonus");
        vPowerEnnemis.setText(data.getStringExtra("powerEnnemis"));
        viebonus = data.getStringExtra("bonusvie");
        puissancebonus = data.getStringExtra("bonuspuis");

        /* maj du nom de l'adversaire */
        String idName = "monstre"+numPiece;
        int id = getResources().getIdentifier(idName, "string", getPackageName());
        vMonsterName.setText(getString(id));

        /* maj de l'image du monstre */
        id = getResources().getIdentifier(idName, "drawable", getPackageName());
        vImgMonster.setImageResource(id);


        /* gestions des bonus */
        if(bonus.matches("bonus1")){
            Toast.makeText(FightActivity.this, "Potion de vie: +"+viebonus+" de vie !", Toast.LENGTH_SHORT).show();
        }else if(bonus.matches("bonus2")){
            Toast.makeText(FightActivity.this, "Potion de puissance: +"+puissancebonus+" de puissance !", Toast.LENGTH_SHORT).show();
        }

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
                intent.putExtra("rIdButton", idButton);
                setResult(Activity.RESULT_OK,intent);
                finish();

            }
        });

        fuiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FightActivity.this, MainActivity.class);

                setResult(Activity.RESULT_CANCELED,intent);
                finish();
            }
        });


    }
}
