package com.example.l3q1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

// this is actually q2
public class MainActivity extends AppCompatActivity {

    EditText name,regno,comments;
    FloatingActionButton submit;
    RatingBar star;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        regno = findViewById(R.id.regno);
        comments = findViewById(R.id.comments);
        submit = findViewById(R.id.floatingActionButton2);
        star = findViewById(R.id.ratingBar);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("NAME:"+name.getText().toString());
                System.out.println("REGNO:"+regno.getText().toString());
                System.out.println("COMMENTS:"+comments.getText().toString());
                System.out.println("RATING:"+star.getRating());
                Toast.makeText(getApplicationContext(), "Your feedback has been recorded", Toast.LENGTH_SHORT).show();
                name.setText("");
                regno.setText("");

                comments.setText("");

                star.setRating(0);

            }
        });
    }
}