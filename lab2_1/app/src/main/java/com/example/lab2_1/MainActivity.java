package com.example.lab2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView groceries;
    String[] groc = {"Rice", "Flour", "SafeWash", "Corn Flakes","Maggie"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groceries = findViewById(R.id.groceries);
        ArrayAdapter<String> grocadap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, groc);

        groceries.setAdapter(grocadap);
        groceries.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String grocery =  parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), "Grocery selected:"+ grocery,Toast.LENGTH_SHORT ).show();
    }
}