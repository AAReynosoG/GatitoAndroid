package com.example.gatito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    CountDownTimer ctimer = null;
    TextView txt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt2 = findViewById(R.id.txtS2);
        StartTimer();
    }

    final Context context = this;
    public void StartTimer() {
        ctimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long segundos) {
                txt2.setText("" + segundos / 1000);
            }

            @Override
            public void onFinish() {
                txt2.setText("Let's go!");
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
            }
        };

        ctimer.start();
    }


}