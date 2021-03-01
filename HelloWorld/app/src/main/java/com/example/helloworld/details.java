package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class details extends AppCompatActivity {

    TextView phone;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        phone = (TextView)findViewById(R.id.display_email);
        email = (TextView)findViewById(R.id.display_phone);

        //display_email = findViewById(R.id.Email);
        //display_phone = findViewById(R.id.Phone);

        String display_email = getIntent().getStringExtra("email");
        String display_phone = getIntent().getStringExtra("phone");

        phone.setText("Phone:"+display_phone);
        email.setText("Email: "+display_email);

        System.out.println("details: "+display_email);
        System.out.println("details: "+display_phone);
    }
}