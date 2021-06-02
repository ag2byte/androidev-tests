package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.nio.channels.InterruptedByTimeoutException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
ListView lists ;
String [] songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        try {
//            songs = getResources(R.raw);
            songs = getAssets().list("songs");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String s:songs){
            System.out.println(s);
        }
        lists = findViewById(R.id.songlists);

        ArrayAdapter<String> songsadap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songs);
        lists.setAdapter(songsadap);
        lists.setOnItemClickListener(this);

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String song = parent.getItemAtPosition(position).toString();
        System.out.println("Song:"+ song);
        Intent i = new Intent(MainActivity.this, SongPage.class);
        i.putExtra("song",song);
        i.putExtra("songlist",songs);

        startActivity(i);

    }
}