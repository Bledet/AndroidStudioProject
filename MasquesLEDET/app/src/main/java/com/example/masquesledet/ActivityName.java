package com.example.masquesledet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityName extends AppCompatActivity {

    private EditText enom;
    private EditText eprenom;
    private EditText enumero;

    private Button valider1;
    private Button annuler1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        enom = findViewById(R.id.enom);
        eprenom = findViewById(R.id.eprenom);
        enumero = findViewById(R.id.enumero);
        valider1 = findViewById(R.id.valider1);
        annuler1 = findViewById(R.id.annuler1);

        Intent data = getIntent();
        enom.setText(data.getStringExtra("nom"));
        eprenom.setText(data.getStringExtra("prenom"));
        enumero.setText(data.getStringExtra("numero"));



        annuler1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityName.this,MainActivity.class);
                setResult(Activity.RESULT_CANCELED,intent);
                finish();
            }
        });

        valider1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityName.this,MainActivity.class);
                intent.putExtra("nnom",enom.getText().toString());
                intent.putExtra("nprenom",eprenom.getText().toString());
                intent.putExtra("nnumero",enumero.getText().toString());
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }

}
