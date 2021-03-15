package com.example.gallery_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


    }
    public void clickHandler(View v){
        Toast.makeText(this,  String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();
        int[] images= {
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e
        };
        Intent intent = new Intent(this, Fullimage.class);
        for(int i=0; i<5; i++){
            if(v.getId() == i+2131230910){
                intent.putExtra("imgId", images[i]);
                break;
            }
        }

        startActivity(intent);

    }
}