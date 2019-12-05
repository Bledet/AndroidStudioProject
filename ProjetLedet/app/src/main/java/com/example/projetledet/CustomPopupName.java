package com.example.projetledet;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;

public class CustomPopupName extends Dialog {

    EditText setName;
    Button validButtonName;


    public CustomPopupName(Activity activity){
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog);
        setContentView(R.layout.popup_name);

        setName = findViewById(R.id.setName);
        validButtonName = findViewById(R.id.validName);
    }

    public Button getValidButtonName(){return validButtonName;}
    public EditText getSetName(){return setName;}

    public void build(){
        show();
    }
}
