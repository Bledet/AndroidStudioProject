package com.example.masquesledet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView nom;
    private TextView prenom;
    private TextView numero;
    private Button modifier1;

    private TextView nrue;
    private TextView rue;
    private TextView codep;
    private TextView ville;
    private Button modifier2;


    public static final int EDIT_Name = 1;
    public static final int EDIT_Add = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        numero = findViewById(R.id.numero);
        modifier1 = findViewById(R.id.modifier1);

        nrue = findViewById(R.id.nrue);
        rue = findViewById(R.id.rue);
        codep = findViewById(R.id.codep);
        ville = findViewById(R.id.ville);
        modifier2 = findViewById(R.id.modifier2);

        modifier1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityName.class);
                intent.putExtra("nom",nom.getText().toString());
                intent.putExtra("prenom",prenom.getText().toString());
                intent.putExtra("numero",numero.getText().toString());
                startActivityForResult(intent,EDIT_Name);
            }
        });

        modifier2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityAdd.class);
                intent.putExtra("nrue",nrue.getText().toString());
                intent.putExtra("rue",rue.getText().toString());
                intent.putExtra("codep",codep.getText().toString());
                intent.putExtra("ville",ville.getText().toString());
                startActivityForResult(intent,EDIT_Add);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "Cancelled.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == EDIT_Name) {
            nom.setText(data.getStringExtra("nnom"));
            prenom.setText(data.getStringExtra("nprenom"));
            numero.setText(data.getStringExtra("nnumero"));
        } else if (requestCode == EDIT_Add) {
            nrue.setText(data.getStringExtra("nnrue"));
            rue.setText(data.getStringExtra("nrue"));
            codep.setText(data.getStringExtra("ncodep"));
            ville.setText(data.getStringExtra("nville"));
        }
    }
}
