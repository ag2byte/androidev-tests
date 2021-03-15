package com.example.l2q3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import top.defaults.colorpicker.ColorPickerPopup;

public class MainActivity extends AppCompatActivity {

    Button pick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pick = findViewById(R.id.pick);
        pick.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ColorPickerPopup.Builder(MainActivity.this).initialColor(Color.BLUE)
                                .enableAlpha(false)
                                .enableBrightness(false)
                                .okTitle("Confirm")
                                .cancelTitle("CANCEL")
                                .showIndicator(true)
                                .showValue(false)
                                .build()
                                .show(v, new ColorPickerPopup.ColorPickerObserver() {
                                    @Override
                                    public void onColorPicked(int color) {

                                        System.out.println();
                                        findViewById(R.id.BG).setBackgroundColor(color);
                                    }

//                                    @Override
//                                    public void onColor(int color, boolean fromUser) {
//
//                                    }
                                });

                    }
                }
        );
    }
}