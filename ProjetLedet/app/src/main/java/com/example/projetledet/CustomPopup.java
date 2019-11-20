package com.example.projetledet;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;

public class CustomPopup extends Dialog {

    EditText setLife;
    EditText setPower;
    EditText setPowerMax;
    Button   validButton;

    public CustomPopup(Activity activity){
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog);
        setContentView(R.layout.popup_template);

        setLife = findViewById(R.id.setLife);
        setPower = findViewById(R.id.setPower);
        setPowerMax = findViewById(R.id.setPowerMax);
        validButton = findViewById(R.id.validButton);
    }

    public Button getValidButton(){return validButton;}
    public EditText getSetLife(){return setLife;}
    public EditText getSetPower(){return setPower;}
    public EditText getSetPowerMax(){return setPowerMax;}

    public void build(){
        show();
    }
}
