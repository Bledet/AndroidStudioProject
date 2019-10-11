package com.example.masquesledet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAdd extends AppCompatActivity {


    private EditText enrue;
    private EditText erue;
    private EditText ecodep;
    private EditText eville;

    private Button valider2;
    private Button annuler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        enrue = findViewById(R.id.enrue);
        erue = findViewById(R.id.erue);
        ecodep = findViewById(R.id.ecodep);
        eville = findViewById(R.id.eville);

        valider2 = findViewById(R.id.valider2);
        annuler2 = findViewById(R.id.annuler2);


        Intent data = getIntent();

        enrue.setText(data.getStringExtra("nrue"));
        erue.setText(data.getStringExtra("rue"));
        ecodep.setText(data.getStringExtra("codep"));
        eville.setText(data.getStringExtra("ville"));

        annuler2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAdd.this,MainActivity.class);
                setResult(Activity.RESULT_CANCELED,intent);
                finish();
            }
        });

        valider2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAdd.this,MainActivity.class);
                intent.putExtra("nnrue",enrue.getText().toString());
                intent.putExtra("nrue",erue.getText().toString());
                intent.putExtra("ncodep",ecodep.getText().toString());
                intent.putExtra("nville",eville.getText().toString());
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }
}
