package com.example.testintent01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intention = getIntent();
        String message = intention.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView text = findViewById(R.id.activity_second_text);
        text.setText(message);
    }
}
