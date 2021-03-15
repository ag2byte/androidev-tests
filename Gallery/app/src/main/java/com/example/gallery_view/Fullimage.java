package com.example.gallery_view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Fullimage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullimage);
        Intent intent = getIntent();
        int imgId = intent.getExtras().getInt("imgId");
        ImageView img = findViewById(R.id.imageView);
        img.setImageResource(imgId);

    }
}
