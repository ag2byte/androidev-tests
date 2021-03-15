package com.example.l2q2;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {

    EditText text;
    Button enc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.Text);
        enc = findViewById(R.id.Submit);
        enc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = text.getText().toString();
                System.out.println(s);
                try {
                    KeyGenerator keygen = KeyGenerator.getInstance("AES");
                    keygen.init(256);
                    SecretKey key = keygen.generateKey();
                    Cipher cipher =  Cipher.getInstance("AES/CBC/PKCS5PADDING");
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    byte[] es = cipher.doFinal(s.getBytes(StandardCharsets.UTF_8));
                    if(es.toString().length() >0)
                    {
                        Toast.makeText(getApplicationContext(), es.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("es:"+es);}


                } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                    e.printStackTrace();
                }


            }
        });


    }
}