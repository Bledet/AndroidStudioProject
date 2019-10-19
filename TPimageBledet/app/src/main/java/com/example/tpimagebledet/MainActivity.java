package com.example.tpimagebledet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button loadButton;
    private Button hztlButton;
    private Button vertButton;
    private TextView textPath;
    private ImageView imageView;

    private Bitmap image;

    public static final int LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadButton = findViewById(R.id.buttonLoad);
        hztlButton = findViewById(R.id.hztl);
        hztlButton.setEnabled(false);
        vertButton = findViewById(R.id.vert);
        vertButton.setEnabled(false);
        textPath = findViewById(R.id.pathView);
        imageView = findViewById(R.id.imageView);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Chargement de l'image");
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Choix image"), LOAD_IMAGE);
            }
        });
        registerForContextMenu(imageView);

        hztlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Flip horizontal !", Toast.LENGTH_SHORT).show();
                Bitmap newImage = horizontalMirror(image);
                imageView.setImageBitmap(newImage);
                image = newImage;

            }
        });

        vertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Flip vertical !", Toast.LENGTH_SHORT).show();
                Bitmap newImage = verticalMirror(image);
                imageView.setImageBitmap(newImage);
                image = newImage;

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri imageURI = data.getData();
        textPath.setText(imageURI.toString());

        BitmapFactory.Options option = new BitmapFactory.Options();
        try {
            Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageURI), null, option);
            imageView.setImageBitmap(bm);
            image = bm;
            hztlButton.setEnabled(true);
            vertButton.setEnabled(true);
        } catch (Exception e) {

        }
    }

    private Bitmap horizontalMirror(Bitmap bitmap)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();


        /* Bitmap newImg = ((BitmapDrawable)imageView.getDrawable()).getBitmap(); */

        Bitmap newImg = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);

        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                newImg.setPixel(width-i-1, j, bitmap.getPixel(i,j));
            }
        }

        return newImg;
    }

    private Bitmap verticalMirror(Bitmap bitmap)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();


        /* Bitmap newImg = ((BitmapDrawable)imageView.getDrawable()).getBitmap(); */
        Bitmap newImg = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);

        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                newImg.setPixel(i, height-j-1, bitmap.getPixel(i,j));
            }
        }

        return newImg;
    }

}
