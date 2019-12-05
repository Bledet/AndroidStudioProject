package com.example.projetledet;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.ImageButton;
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
    private TextView vName;
    private TextView vNiveau;
    private String idButton;
    private String newLife;
    private String newPower;
    private String newName;

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

    int maxPower;
    int maxBonus1;
    int maxBonus2;
    int minBonus1;
    int minBonus2;

    int numPieceBonus01;
    int numPieceBonus02;
    String bonus1;
    String bonus2;
    boolean bonus1dispo = true;
    boolean bonus2dispo = true;
    CustomPopup customPopup;
    CustomPopupName customPopupName;

    boolean parametre = false;
    boolean nextLevel = false;

    // Référencement des puissance aléatoire
    Vector powerList = new Vector();

    private void initList(){
        for(int i=0; i<16; i++){

            Random rand = new Random();
            int puissance_ennemis = rand.nextInt(maxPower - 1 + 1) + 1;
            String power = Integer.toString(puissance_ennemis);

            powerList.add(power);
        }
    }

    private String converion(int n){
        String s = Integer.toString(n);
        if(s.length()<2)
            s = "0"+s;

        return s;
    }

    private void reInitList(){
        powerList.clear();
        initList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newLife = "10";
        newPower = "100";
        maxPower = 150;
        maxBonus1 = 3;
        maxBonus2 = 10;
        minBonus1 = 1;
        minBonus2 = 5;

        customPopupName = new CustomPopupName(this);
        customPopupName.build();

        initList();

        /* On choisit les deux pièce qui contiendront des bonus */
        initBonus();



        vPuissance = (TextView) findViewById(R.id.puissance);
        vVie = (TextView) findViewById(R.id.vie);
        result = (TextView) findViewById(R.id.resultat_combat);
        nbPiece = (TextView) findViewById(R.id.piece_non_explorer);
        vResPartie = (TextView) findViewById(R.id.res_partie);
        vName = (TextView) findViewById(R.id.nom_Joueur);
        vNiveau = (TextView) findViewById(R.id.niveau);

        //door = MediaPlayer.create(this,R.raw.door);
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

            //door.start();
            String getRoom = getResources().getResourceEntryName(v.getId()); //On récupère le nom de l'ID : room+"i"
            String last = getRoom.substring(4);                  //On récupère le numéro de la pièce: "i"
            int index = Integer.parseInt(last)-1;                //On convertit en int et on enlève 1 pour l'adapté au tableau


            /*Gestion des bonus*/
            String bonus  = "nop";
            int act1  = 0;
            int act2 = 0;
            if(last.matches(bonus1) && bonus1dispo==true){
                bonus = "bonus1";
                Random rand = new Random();
                act1 = rand.nextInt(maxBonus1 - minBonus1 + 1) + minBonus1;
                int viebonus = Integer.parseInt(vVie.getText().toString());
                viebonus += act1;
                vVie.setText(Integer.toString(viebonus));
                bonus1dispo=false;
            }else if (last.matches(bonus2) && bonus2dispo==true){
                bonus = "bonus2";
                Random rand = new Random();
                act2 = rand.nextInt(maxBonus2 - minBonus2 + 1) + minBonus2;
                int puissancebonus = Integer.parseInt(vPuissance.getText().toString());
                puissancebonus += act2;
                vPuissance.setText(Integer.toString(puissancebonus));
                bonus2dispo=false;
            }


            Intent intent = new Intent(MainActivity.this, FightActivity.class);
            intent.putExtra("power", vPuissance.getText().toString());
            intent.putExtra("numPiece", last);
            intent.putExtra("life", vVie.getText().toString());
            intent.putExtra("res", result.getText().toString());
            intent.putExtra("bonusvie", Integer.toString(act1));
            intent.putExtra("bonuspuis", Integer.toString(act2));
            intent.putExtra("idButton", Integer.toString(v.getId()));
            intent.putExtra("bonus", bonus);
            intent.putExtra("powerEnnemis", powerList.get(index).toString()); //On récupère la puissance de l'adversaire dans la powerList
            startActivityForResult(intent, FIGHT);
    }

    private void initBonus(){
        /* On choisit les deux pièce qui contiendront des bonus */
        Random rand = new Random();
        numPieceBonus01 = rand.nextInt(16 - 1 + 1) + 1;
        do { //On s'assure que ce soit deux pièces différentes.
            numPieceBonus02 = rand.nextInt(16 - 1 + 1) + 1;
        }while (numPieceBonus01 == numPieceBonus02);
        bonus1 = converion(numPieceBonus01);
        bonus2 = converion(numPieceBonus02);
    }

    public void validPop(View v){
        newLife = customPopup.getSetLife().getText().toString();
        newPower = customPopup.getSetPower().getText().toString();
        newName = customPopup.getSetNamePop().getText().toString();
        String puissanceMax = customPopup.getSetPowerMax().getText().toString();

        if (newLife.matches("") || newPower.matches("") || newName.matches("") || puissanceMax.matches("")){
            Toast.makeText(MainActivity.this, "Veuillez remplir tout les champs !", Toast.LENGTH_SHORT).show();
            return;
        }

        vName.setText(newName);
        vVie.setText(newLife);
        vPuissance.setText(newPower);
        maxPower = Integer.parseInt(puissanceMax);

        parametre = true;

        restart();

        customPopup.dismiss();
    }

    public void validPopName(View v){
        newName = customPopupName.getSetName().getText().toString();

        if(newName.matches("")){
            Toast.makeText(MainActivity.this, "Veuillez entrez un nom !", Toast.LENGTH_SHORT).show();
            return;
        }

        vName.setText(newName);
        customPopupName.dismiss();
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
                newPower = "100";
                newLife = "10";
                maxPower = 150;
                maxBonus1 = 3;
                minBonus1 = 1;
                maxBonus2 = 10;
                minBonus2 = 5;
                restart();
                return true;
            case R.id.quitter:
                finish();
                return true;
            case R.id.parametre:
                customPopup = new CustomPopup(this);
                customPopup.build();
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

            /* Seuille de vie critique, on la color en rouge */
            if(vie<=3)
            {
                vVie.setTextColor(Color.parseColor("#FF0000"));
            }
            /* Seuille de puissance supérieur à l'enemis, on le color en vert */
            int power = Integer.parseInt(vPuissance.getText().toString());
            if(power>maxPower)
            {
                vPuissance.setTextColor(Color.parseColor("#0FFF00"));
            }
            vVie.setText(Integer.toString(vie));
            result.setText("Vous avez pris la fuite...");
            button.setImageResource(R.drawable.icon_monster);
            checkVictory();
            return;
        }

        if (requestCode == FIGHT) {

            vPuissance.setText(data.getStringExtra("rPower"));
            vVie.setText(data.getStringExtra("rLife"));
            result.setText(data.getStringExtra("rRes"));
            idButton = data.getStringExtra("rIdButton");

            int vie = Integer.parseInt(vVie.getText().toString());
            if(vie<=3)
            {
                vVie.setTextColor(Color.parseColor("#FF0000"));
            }
            int power = Integer.parseInt(vPuissance.getText().toString());
            if(power>maxPower)
            {
                vPuissance.setTextColor(Color.parseColor("#0FFF00"));
            }


            if(result.getText().toString().matches("VICTOIRE !!!")){
                slash.start();
                pieceMoins();
                button = findViewById(Integer.parseInt(idButton));
                button.setImageResource(R.drawable.icon_cross);
                button.setTag("vaincu");
            }else if(result.getText().toString().matches("DEFAITE...")){
                hurt.start();
                button = findViewById(Integer.parseInt(idButton));
                button.setImageResource(R.drawable.icon_monster);
            }
        }
        checkVictory();
    }

    private void pieceMoins(){
        int nb = Integer.parseInt(nbPiece.getText().toString());
        nb--;
        nbPiece.setText(Integer.toString(nb));
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
            popLevel();
        }
    }

    private void popLevel(){
        AlertDialog.Builder myPopup = new AlertDialog.Builder(this);
        myPopup.setTitle("Bravo !");
        myPopup.setMessage("Vous avez vaincu tous les boss !\nPasser au niveau suivant ?");
        myPopup.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                nextLevel();

                restart();
            }
        });
        myPopup.setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        myPopup.show();
    }

    private void nextLevel(){
        int lvl = Integer.parseInt(vNiveau.getText().toString());
        lvl++;
        vNiveau.setText(Integer.toString(lvl));

        int var = Integer.parseInt(newPower);
        var+=50;
        newPower = Integer.toString(var);

        int var2 = Integer.parseInt(newLife);
        var2+=5;
        newLife = Integer.toString(var2);

        maxPower  += 50;
        maxBonus1 += 3;
        minBonus1 += 3;
        maxBonus2 += 5;
        minBonus2 += 5;

        parametre = true;
        nextLevel = true;
    }

    private void restart(){
        if (parametre==false){
            customPopupName = new CustomPopupName(this);
            customPopupName.build();
        }
        if(parametre==false){
            vNiveau.setText("1");
        }
        nextLevel = false;
        parametre = false;
        vPuissance.setTextColor(Color.parseColor("#000000"));
        vPuissance.setText(newPower);
        vVie.setTextColor(Color.parseColor("#000000"));
        vVie.setText(newLife);
        result.setText("...");
        nbPiece.setText("16");
        vResPartie.setText("Résultat du combat");
        bonus1dispo = true;
        bonus2dispo = true;
        initBonus();

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