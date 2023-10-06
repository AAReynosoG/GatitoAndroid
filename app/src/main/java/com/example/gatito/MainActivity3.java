package com.example.gatito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {


    TextView TXT1;
    TextView TXT2;
    TextView TXT3;
    int PuntosX, PuntosO, rondas;
    Button B1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TXT1 = findViewById(R.id.txt1);
        TXT2 = findViewById(R.id.txt2);
        TXT3 = findViewById(R.id.txt3);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
             PuntosX = bundle.getInt("PuntosX");
             PuntosO = bundle.getInt("PuntosO");
             rondas = bundle.getInt("rondas");
        }

        TXT1.setText("Puntos jugador X: " + PuntosX);
        TXT2.setText("Puntos jugador O: " + PuntosO);
        TXT3.setText("Rondas Jugadas: " + rondas);

        if (PuntosX > PuntosO){
            Toast.makeText(this, "EL JUGADOR 'X' ES EL GANADOR", Toast.LENGTH_LONG).show();
        } else if (PuntosO > PuntosX) {
            Toast.makeText(this, "EL JUGADOR 'O' ES EL GANADOR", Toast.LENGTH_LONG).show();
        } else if (PuntosX == PuntosO) {
            Toast.makeText(this, "EL JUEGO TERMINO EN EMPATE", Toast.LENGTH_LONG).show();
        }

        B1 = findViewById(R.id.volver);
        B1.setOnClickListener(this);


    }

    @Override
    public void onClick(View view){
        Volver();
    }

    public void Volver(){
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }
}