package com.example.l4add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    public List<Alarm> alrmlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton set_alarm = findViewById(R.id.set_alarm);

//        alrmlist =  new ArrayList<Alarm>();
//        if

        set_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Creating new alarm");
//                System.out.println(alrmlist.size());
                Intent set_alarm_intent = new Intent(MainActivity.this,
                        SetAlarm.class);
                startActivity(set_alarm_intent);
                
            }
        });
    }
}