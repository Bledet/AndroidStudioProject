package com.example.todoledet;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.SparseBooleanArray;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    ListView lv;
    ArrayList<String> listItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lv = (ListView) findViewById(R.id.maListe);

        try {
            getFromInterne();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, listItems);
        lv.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
            return;
        }

        if(requestCode == 1){
            super.onActivityResult(requestCode, resultCode, data);
            String convert = data.getStringExtra("rListItem");
            listItems.add(convert);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, listItems);
            lv.setAdapter(adapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.delete) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, listItems);

            SparseBooleanArray checkedItemPositions = lv.getCheckedItemPositions();
            int itemCount = lv.getCount();

            for(int i=itemCount-1; i >= 0; i--){
                if(checkedItemPositions.get(i)){
                    adapter.remove(listItems.get(i));
                }
            }

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, listItems);
            lv.setAdapter(adapter);
            return true;
        }

        if(id == R.id.saveList){

            Toast.makeText(MainActivity.this, "List saved !", Toast.LENGTH_SHORT).show();

            try {
                FileOutputStream outputStream= openFileOutput("sauvegarde", MODE_PRIVATE);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);


                for (int i = 0; i < listItems.size(); i++) {
                    myOutWriter.append(listItems.get(i) +"\n");
                }
                myOutWriter.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void getFromInterne() throws IOException {

        String value = "";

        //FileInputStream inputStream = openFileInput("sauvegarde");
        FileInputStream inputStream = openFileInput("sauvegarde");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf8"), 8192);
        int content;

        while ((content=br.read())!=-1){


            if (content==10){
                listItems.add(value);
                System.out.println("VAAAAAAAAALUE : "+ value);
                value = "";
                continue;
            }

            value += (char) content;


        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, listItems);
        lv.setAdapter(adapter);
    }

}
