package com.example.projetledet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import static java.lang.Math.random;

public class MainActivity extends AppCompatActivity {


    private TextView vPuissance;
    private TextView vVie;
    private TextView result;
    private TextView nbPiece;
    private TextView vResPartie;
    private String idButton;

    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    private Button button15;
    private Button button16;

    public static final int FIGHT = 1;

    // Référencement des puissance aléatoire
    Vector powerList = new Vector();

    private void initList(){

        for(int i=0; i<16; i++){

            Random rand = new Random();
            int puissance_ennemis = rand.nextInt(150 - 1 + 1) + 1;
            String power = Integer.toString(puissance_ennemis);

            powerList.add(power);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        for(int i=0; i<powerList.size(); i++){
            System.out.println("Case"+i+" : " + powerList.get(i));
        }

        vPuissance = (TextView) findViewById(R.id.puissance);
        vVie = (TextView) findViewById(R.id.vie);
        result = (TextView) findViewById(R.id.resultat_combat);
        nbPiece = (TextView) findViewById(R.id.piece_non_explorer);
        vResPartie = (TextView) findViewById(R.id.res_partie);

        button1 = (Button) findViewById(R.id.room1);
        button2 = (Button) findViewById(R.id.room2);
        button3 = (Button) findViewById(R.id.room3);
        button4 = (Button) findViewById(R.id.room4);
        button5 = (Button) findViewById(R.id.room5);
        button6 = (Button) findViewById(R.id.room6);
        button7 = (Button) findViewById(R.id.room7);
        button8 = (Button) findViewById(R.id.room8);
        button9 = (Button) findViewById(R.id.room9);
        button10 = (Button) findViewById(R.id.room10);
        button11 = (Button) findViewById(R.id.room11);
        button12 = (Button) findViewById(R.id.room12);
        button13 = (Button) findViewById(R.id.room13);
        button14 = (Button) findViewById(R.id.room14);
        button15 = (Button) findViewById(R.id.room15);
        button16 = (Button) findViewById(R.id.room16);

    }

    // Fonction de gestion d'entrée dans une salle
    public void onClick(View v){

            button = findViewById(v.getId());
            if(button.getText().toString().matches("X")){
                Toast.makeText(MainActivity.this, "Boss déjà vaincu !", Toast.LENGTH_SHORT).show();
                return;
            }

            String getRoom = getResources().getResourceEntryName(v.getId()); //On récupère le nom de l'ID : room+"i"
            char last = getRoom.charAt(getRoom.length()-1);                  //On récupère le numéro de la pièce: "i"
            int index = Character.getNumericValue(last)-1;                   //On convertit en int et on enlève 1 pour l'adapté au tableau

            System.out.println("IDENTITEEEEEEEEEE :" + last);

            Intent intent = new Intent(MainActivity.this, FightActivity.class);
            intent.putExtra("power", vPuissance.getText().toString());
            intent.putExtra("life", vVie.getText().toString());
            intent.putExtra("res", result.getText().toString());
            intent.putExtra("nbPiece", nbPiece.getText().toString());
            intent.putExtra("idButton", Integer.toString(v.getId()));
            intent.putExtra("powerEnnemis", powerList.get(index).toString()); //On récupère la puissance de l'adversaire dans la powerList
            startActivityForResult(intent, FIGHT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.redemarrer:
                //TODO New Game
                return true;
            case R.id.quitter:
                finish();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_CANCELED) {
            int vie = Integer.parseInt(vVie.getText().toString());
            vie -= 1;
            vVie.setText(Integer.toString(vie));
            result.setText("Vous avez pris la fuite...");
            return;
        }

        if (requestCode == FIGHT) {
            vPuissance.setText(data.getStringExtra("rPower"));
            vVie.setText(data.getStringExtra("rLife"));
            result.setText(data.getStringExtra("rRes"));
            nbPiece.setText(data.getStringExtra("rNbPiece"));
            idButton = data.getStringExtra("rIdButton");

            if(result.getText().toString().matches("VICTOIRE !!!")){
                button = findViewById(Integer.parseInt(idButton));
                button.setText("X");
            }
        }
        checkVictory();
    }

    private void checkVictory(){
        int vie = Integer.parseInt(vVie.getText().toString());
        int piece = Integer.parseInt(nbPiece.getText().toString());
        if(vie <=0){
            vResPartie.setText("Vous avez perdu la partie.");
        }else if (piece == 0){
            vResPartie.setText("Bravo ! Vous avez gagné !");
        }
    }
}