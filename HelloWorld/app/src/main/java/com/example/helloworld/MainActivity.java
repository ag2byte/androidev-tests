package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText emailid;
    EditText phoneno;
    EditText password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailid = findViewById(R.id.Email);
        phoneno = findViewById(R.id.Phone);
        password = findViewById(R.id.Password);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String phone_no = phoneno.getText().toString();
                String password_str = password.getText().toString();
                System.out.println(email);
                System.out.println(phone_no);
                System.out.println(password_str);
                Context context = getApplicationContext();
                CharSequence text;
                int duration = Toast.LENGTH_SHORT;
                Toast  toast;


                if(!isValidPhone(phone_no)) {
                    text = "The phone no is invalid";

                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;}


                    if (!isValidEmail(email)) {
                        text = "The email id  is invalid";

                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                        return;

                }
                Intent intent = new Intent(MainActivity.this, details.class);
                intent.putExtra("email",email);
                intent.putExtra("phone",phone_no);

                startActivity(intent);
            }
            public boolean isValidEmail(CharSequence target) {
                return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
            }
            public boolean isValidPhone(String target)
            {
                System.out.println(target.length());
                return (target.length() == 10);
            }
        });


    }
}
