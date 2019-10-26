package com.example.tpimagebledet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button loadButton;
    private Button restore;
    private TextView textPath;
    private ImageView imageView;

    private Bitmap image;
    private Bitmap firstImage;

    public static final int LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadButton = findViewById(R.id.buttonLoad);
        restore = findViewById(R.id.restoreButton);
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
            firstImage = bm;
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

    public Bitmap flipHoraire(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        Bitmap newBM = Bitmap.createBitmap(height,width,Bitmap.Config.ARGB_8888);

        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                newBM.setPixel(height - 1 - j,i,bm.getPixel(i,j));
            }
        }

        return newBM;
    }

    public Bitmap flipAntiHoraire(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        Bitmap newBM = Bitmap.createBitmap(height,width,Bitmap.Config.ARGB_8888);

        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                newBM.setPixel(j,width - 1 - i,bm.getPixel(i,j));
            }
        }

        return newBM;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.fhrzt :
                Toast.makeText(getApplicationContext(), "Flip Horizontal !", Toast.LENGTH_SHORT).show();
                Bitmap newImageHrzt = horizontalMirror(image);
                imageView.setImageBitmap(newImageHrzt);
                image = newImageHrzt;
                return true;
            case R.id.fvert :
                Toast.makeText(getApplicationContext(), "Flip Vertical !", Toast.LENGTH_SHORT).show();
                Bitmap newImageVert = verticalMirror(image);
                imageView.setImageBitmap(newImageVert);
                image = newImageVert;
                return true;
            case R.id.fhoraire :
                Toast.makeText(getApplicationContext(), "Flip Horaire !", Toast.LENGTH_SHORT).show();
                Bitmap newImageHor = flipHoraire(image);
                imageView.setImageBitmap(newImageHor);
                image = newImageHor;
                return true;
            case R.id.fantihoraire :
                Toast.makeText(getApplicationContext(), "Flip Anti-Horaire !", Toast.LENGTH_SHORT).show();
                Bitmap newImageAnt = flipAntiHoraire(image);
                imageView.setImageBitmap(newImageAnt);
                image = newImageAnt;
                return true;
        }
        return false;
    }

    private Bitmap greylevel(Bitmap bm)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();

        Bitmap newBM = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);

        int color = -66;
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                int pixel = bm.getPixel(i,j);
                int avg = (Color.red(pixel) + Color.blue(pixel) + Color.green(pixel)) / 3;
                color = Color.rgb(avg,avg,avg);
                newBM.setPixel(i, j, color);
            }
        }

        return newBM;
    }

    private Bitmap greylevel2(Bitmap bm)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();

        Bitmap newBM = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);

        int color = -66;
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                int pixel = bm.getPixel(i,j);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);
                int avg = Math.max(Math.max(r,g),b) + Math.min(Math.min(r,g),b);
                avg /= 2;
                color = Color.rgb(avg,avg,avg);
                newBM.setPixel(i, j, color);
            }
        }

        return newBM;
    }

    private Bitmap greylevel3(Bitmap bm)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();

        Bitmap newBM = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);

        int color = -66;
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                int pixel = bm.getPixel(i,j);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);
                int avg = (int)(0.21*r + 0.72*g + 0.07*b);
                color = Color.rgb(avg,avg,avg);
                newBM.setPixel(i, j, color);
            }
        }

        return newBM;
    }


    private Bitmap negate(Bitmap bm)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();

        Bitmap newBM = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);

        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                int pixel = bm.getPixel(i,j);
                int r = 255 - Color.red(pixel);
                int g = 255 - Color.green(pixel);
                int b = 255 - Color.blue(pixel);
                newBM.setPixel(i, j, Color.rgb(r,g,b));
            }
        }

        return newBM;
    }

    public void restore()
    {
        imageView.setImageBitmap(firstImage);
        image = firstImage;
    }

    public void onClickRestore(View v){
        Toast.makeText(getApplicationContext(), "Reset !", Toast.LENGTH_SHORT).show();
        restore();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.inverse_colors:
                Toast.makeText(getApplicationContext(), "Négatif !", Toast.LENGTH_SHORT).show();
                Bitmap newImageNeg = negate(image);
                imageView.setImageBitmap(newImageNeg);
                image = newImageNeg;
                return true;
            case R.id.grey_level:
                Toast.makeText(getApplicationContext(), "Niveau de gris 1 !", Toast.LENGTH_SHORT).show();
                Bitmap newImageGrey = greylevel(image);
                imageView.setImageBitmap(newImageGrey);
                image = newImageGrey;
                return true;
            case R.id.grey_level_2:
                Toast.makeText(getApplicationContext(), "Niveau de gris 2 !", Toast.LENGTH_SHORT).show();
                Bitmap newImageGrey2 = greylevel2(image);
                imageView.setImageBitmap(newImageGrey2);
                image = newImageGrey2;
                return true;
            case R.id.grey_level_3:
                Toast.makeText(getApplicationContext(), "Niveau de gris 3 !", Toast.LENGTH_SHORT).show();
                Bitmap newImageGrey3 = greylevel3(image);
                imageView.setImageBitmap(newImageGrey3);
                image = newImageGrey3;
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
