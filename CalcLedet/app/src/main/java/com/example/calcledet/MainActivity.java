package com.example.calcledet;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eValeur1;
    private EditText eValeur2;

    private RadioButton plus;
    private RadioButton moins;
    private RadioButton mult;
    private RadioButton div;

    private TextView vResultat;

    private Button razButton;
    private Button equalButton;
    private Button quiteButton;

    private float valeur1;
    private float valeur2;
    private float resultat;
    private int nbOpération = 0;

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_plus:
                if (checked)
                    nbOpération = 1; // Addition
                    break;
            case R.id.radio_moins:
                if (checked)
                    nbOpération = 2; // Soustraction
                    break;
            case R.id.radio_mult:
                if (checked)
                    nbOpération = 3; // Multiplication
                    break;
            case R.id.radio_div:
                if (checked)
                    nbOpération = 4; // Division
                    break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eValeur1 = (EditText) findViewById(R.id.activity_main_valeur1);
        eValeur2 = (EditText) findViewById(R.id.activity_main_valeur2);

        plus     = (RadioButton) findViewById(R.id.radio_plus);
        moins    = (RadioButton) findViewById(R.id.radio_moins);
        mult     = (RadioButton) findViewById(R.id.radio_mult);
        div      = (RadioButton) findViewById(R.id.radio_div);

        vResultat = (TextView) findViewById(R.id.activity_main_resultat);

        razButton  = (Button) findViewById(R.id.button1);
        equalButton  = (Button) findViewById(R.id.button2);
        quiteButton  = (Button) findViewById(R.id.button3);

        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Vérification avant calcul
                if(eValeur1.getText().toString().matches("") || eValeur1.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),"Veuillez entrer tout les champs svp",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    valeur1 = Float.parseFloat(eValeur1.getText().toString());
                    valeur2 = Float.parseFloat(eValeur2.getText().toString());
                }

                //Phase de calcul
                if(nbOpération == 0){
                    Toast.makeText(getApplicationContext(),"Veuillez choisir une opération svp",Toast.LENGTH_SHORT).show();
                    return;
                }else if(nbOpération == 1){
                    resultat = valeur1+valeur2;
                }else if(nbOpération == 2){
                    resultat = valeur1-valeur2;
                }else if(nbOpération == 3){
                    resultat = valeur1*valeur2;
                }else if(nbOpération == 4){
                    resultat = valeur1/valeur2;
                }

                vResultat.setText(Float.toString(resultat));

                //Test console
                System.out.println("Valeur1 = "+ valeur1);
                System.out.println("Valeur2 = "+ valeur2);
                System.out.println("Resultat = "+ resultat);
            }
        });

        razButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eValeur1.setText(null);
                eValeur2.setText(null);
                vResultat.setText("Résultat");

            }
        });

        quiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }
}

