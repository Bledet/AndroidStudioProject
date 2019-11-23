package com.example.todoledet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText edit;
    private Button cancel;
    private Button valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        cancel = (Button)findViewById(R.id.cancelButton);
        valid = (Button)findViewById(R.id.validButton);
        edit = (EditText)findViewById(R.id.editView);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });

        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editValue;
                editValue = edit.getText().toString();
                if(editValue.matches("")) {
                    Toast.makeText(AddActivity.this, "Saisie vide", Toast.LENGTH_SHORT).show();
                }else{
                    editValue = edit.getText().toString();
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    intent.putExtra("rListItem", editValue);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
