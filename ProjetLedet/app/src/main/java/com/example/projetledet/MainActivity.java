package com.example.projetledet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private TextView vPuissance;
    private TextView vVie;
    private TextView result;
    private TextView nbPiece;
    private TextView vResPartie;
    private String idButton;

    private ImageButton button;
    private ImageButton button1;
    private ImageButton button2;
    private ImageButton button3;
    private ImageButton button4;
    private ImageButton button5;
    private ImageButton button6;
    private ImageButton button7;
    private ImageButton button8;
    private ImageButton button9;
    private ImageButton button10;
    private ImageButton button11;
    private ImageButton button12;
    private ImageButton button13;
    private ImageButton button14;
    private ImageButton button15;
    private ImageButton button16;

    private MediaPlayer door;
    private MediaPlayer fuite;
    private MediaPlayer slash;
    private MediaPlayer hurt;

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

    private void reInitList(){
        powerList.clear();
        initList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        vPuissance = (TextView) findViewById(R.id.puissance);
        vVie = (TextView) findViewById(R.id.vie);
        result = (TextView) findViewById(R.id.resultat_combat);
        nbPiece = (TextView) findViewById(R.id.piece_non_explorer);
        vResPartie = (TextView) findViewById(R.id.res_partie);

        door = MediaPlayer.create(this,R.raw.door);
        fuite = MediaPlayer.create(this,R.raw.fuite);
        slash = MediaPlayer.create(this,R.raw.slash);
        hurt = MediaPlayer.create(this,R.raw.hurt);

        button1  = (ImageButton) findViewById(R.id.room01);
        button2  = (ImageButton) findViewById(R.id.room02);
        button3  = (ImageButton) findViewById(R.id.room03);
        button4  = (ImageButton) findViewById(R.id.room04);
        button5  = (ImageButton) findViewById(R.id.room05);
        button6  = (ImageButton) findViewById(R.id.room06);
        button7  = (ImageButton) findViewById(R.id.room07);
        button8  = (ImageButton) findViewById(R.id.room08);
        button9  = (ImageButton) findViewById(R.id.room09);
        button10 = (ImageButton) findViewById(R.id.room10);
        button11 = (ImageButton) findViewById(R.id.room11);
        button12 = (ImageButton) findViewById(R.id.room12);
        button13 = (ImageButton) findViewById(R.id.room13);
        button14 = (ImageButton) findViewById(R.id.room14);
        button15 = (ImageButton) findViewById(R.id.room15);
        button16 = (ImageButton) findViewById(R.id.room16);

    }

    // Fonction de gestion d'entrée dans une salle
    public void onClick(View v){

            button = findViewById(v.getId());
            if(button.getTag() == "vaincu"){
                Toast.makeText(MainActivity.this, "Boss déjà vaincu !", Toast.LENGTH_SHORT).show();
                return;
            }else if(!vResPartie.getText().toString().matches("Résultat du combat")){
                Toast.makeText(MainActivity.this, "Veuillez recommencer une partie.", Toast.LENGTH_SHORT).show();
                return;
            }

            door.start();
            String getRoom = getResources().getResourceEntryName(v.getId()); //On récupère le nom de l'ID : room+"i"
            String last = getRoom.substring(4);                  //On récupère le numéro de la pièce: "i"
            int index = Integer.parseInt(last)-1;                   //On convertit en int et on enlève 1 pour l'adapté au tableau

            Intent intent = new Intent(MainActivity.this, FightActivity.class);
            intent.putExtra("power", vPuissance.getText().toString());
            intent.putExtra("numPiece", last);
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
                restart();
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
            fuite.start();
            int vie = Integer.parseInt(vVie.getText().toString());
            vie -= 1;
            vVie.setText(Integer.toString(vie));
            result.setText("Vous avez pris la fuite...");
            button.setImageResource(R.drawable.icon_monster);
            return;
        }

        if (requestCode == FIGHT) {
            vPuissance.setText(data.getStringExtra("rPower"));
            vVie.setText(data.getStringExtra("rLife"));
            result.setText(data.getStringExtra("rRes"));
            nbPiece.setText(data.getStringExtra("rNbPiece"));
            idButton = data.getStringExtra("rIdButton");

            if(result.getText().toString().matches("VICTOIRE !!!")){
                slash.start();
                button = findViewById(Integer.parseInt(idButton));
                button.setImageResource(R.drawable.icon_cross);
                button.setTag("vaincu");
            }else if(result.getText().toString().matches("Vous avez pris la fuite...")){
                fuite.start();
                button = findViewById(Integer.parseInt(idButton));
                button.setImageResource(R.drawable.icon_monster);
            }else if(result.getText().toString().matches("DEFAITE...")){
                hurt.start();
                button = findViewById(Integer.parseInt(idButton));
                button.setImageResource(R.drawable.icon_monster);
            }
        }
        checkVictory();
    }

    private void checkVictory(){
        int vie = Integer.parseInt(vVie.getText().toString());
        int piece = Integer.parseInt(nbPiece.getText().toString());
        if(vie <=0){
            vie=0;
            vVie.setText(Integer.toString(vie));
            vResPartie.setText("Vous avez perdu la partie.");
        }else if (piece == 0){
            vResPartie.setText("Bravo ! Vous avez gagné !");
        }
    }

    private void restart(){
        vPuissance.setText("100");
        vVie.setText("10");
        result.setText("...");
        nbPiece.setText("16");
        vResPartie.setText("Résultat du combat");

        reInitList();
        int id = R.drawable.icon_inter;

        button1.setImageResource(id);
        button2.setImageResource(id);
        button3.setImageResource(id);
        button4.setImageResource(id);
        button5.setImageResource(id);
        button6.setImageResource(id);
        button7.setImageResource(id);
        button8.setImageResource(id);
        button9.setImageResource(id);
        button10.setImageResource(id);
        button11.setImageResource(id);
        button12.setImageResource(id);
        button13.setImageResource(id);
        button14.setImageResource(id);
        button15.setImageResource(id);
        button16.setImageResource(id);

        button1.setTag("nop");
        button2.setTag("nop");
        button3.setTag("nop");
        button4.setTag("nop");
        button5.setTag("nop");
        button6.setTag("nop");
        button7.setTag("nop");
        button8.setTag("nop");
        button9.setTag("nop");
        button10.setTag("nop");
        button11.setTag("nop");
        button12.setTag("nop");
        button13.setTag("nop");
        button14.setTag("nop");
        button15.setTag("nop");
        button16.setTag("nop");
    }
}