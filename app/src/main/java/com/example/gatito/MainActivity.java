package com.example.gatito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, BTNFIN;
    TextView TXT1;
    int rondas = 0, PuntosX = 0, PuntosO = 0;
    String[] Historial;
    boolean PlayerStatus = true, gameOver = false;
    String CurrentPlayer = "X";

    String[][] tablero = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        B1 = findViewById(R.id.b1);
        B2 = findViewById(R.id.b2);
        B3 = findViewById(R.id.b3);
        B4 = findViewById(R.id.b4);
        B5 = findViewById(R.id.b5);
        B6 = findViewById(R.id.b6);
        B7 = findViewById(R.id.b7);
        B8 = findViewById(R.id.b8);
        B9 = findViewById(R.id.b9);
        B10 = findViewById(R.id.b10);
        B11 = findViewById(R.id.b11);
        BTNFIN = findViewById(R.id.btnFin);

        B1.setOnClickListener(this);
        B2.setOnClickListener(this);
        B3.setOnClickListener(this);
        B4.setOnClickListener(this);
        B5.setOnClickListener(this);
        B6.setOnClickListener(this);
        B7.setOnClickListener(this);
        B8.setOnClickListener(this);
        B9.setOnClickListener(this);
        B10.setOnClickListener(this);
        B11.setOnClickListener(this);
        BTNFIN.setOnClickListener(this);

        TXT1 = findViewById(R.id.txt1);
        TXT1.setText("Turno de: " + CurrentPlayer);

    }

    @Override
    public void onClick(View view){
        int rowIndex = -1;
        int cellIndex = -1;

            if (view.getId() == R.id.b1){
                rowIndex = 0;
                cellIndex = 0;
            } else if (view.getId() == R.id.b2) {
                rowIndex = 0;
                cellIndex = 1;
            } else if (view.getId() == R.id.b3) {
                rowIndex = 0;
                cellIndex = 2;
            } else if (view.getId() == R.id.b4) {
                rowIndex = 1;
                cellIndex = 0;
            } else if (view.getId() == R.id.b5) {
                rowIndex = 1;
                cellIndex = 1;
            } else if (view.getId() == R.id.b6) {
                rowIndex = 1;
                cellIndex = 2;
            } else if (view.getId() == R.id.b7) {
                rowIndex = 2;
                cellIndex = 0;
            }else if (view.getId() == R.id.b8) {
                rowIndex = 2;
                cellIndex = 1;
            }else if (view.getId() == R.id.b9) {
                rowIndex = 2;
                cellIndex = 2;
            }

            if (rowIndex != -1 && cellIndex != -1) {
                Movimiento(rowIndex, cellIndex);
            } else if (view.getId() == R.id.b10) {
                LimpiarTablero();
            } else if (view.getId() == R.id.b11) {
                ReiniciarJuego();
            } else if (view.getId() == R.id.btnFin) {
                TerminarJuego();
            }

    }

    private Button findButtonByRowAndCellIndex(int rowIndex, int cellIndex) {
        int id = getResources().getIdentifier("b" + (rowIndex * 3 + cellIndex + 1), "id", getPackageName());
        return findViewById(id);
    }

    public void Movimiento(int rowIndex, int cellIndex){
        if (!gameOver && tablero[rowIndex][cellIndex].equals("")) {
            tablero[rowIndex][cellIndex] = CurrentPlayer;

            Button button = findButtonByRowAndCellIndex(rowIndex, cellIndex);
            if (button != null) {
                button.setText(CurrentPlayer);
            }

            checkGameStatus();
            CurrentPlayer = CurrentPlayer.equals("X") ? "O" : "X";
            if (CurrentPlayer.equals("X")){
                PlayerStatus = true;
                TXT1.setText("Turno de: " + CurrentPlayer);
            } else if (CurrentPlayer.equals("O")) {
                PlayerStatus = false;
                TXT1.setText("Turno de: " + CurrentPlayer);
            }
        }
    }

    public void checkGameStatus(){
        int[][][] winningConditions = {
                {{0, 0}, {0, 1}, {0, 2}},
                {{1, 0}, {1, 1}, {1, 2}},
                {{2, 0}, {2, 1}, {2, 2}},
                {{0, 0}, {1, 0}, {2, 0}},
                {{0, 1}, {1, 1}, {2, 1}},
                {{0, 2}, {1, 2}, {2, 2}},
                {{0, 0}, {1, 1}, {2, 2}},
                {{0, 2}, {1, 1}, {2, 0}}
        };

        for (int[][] condicion : winningConditions){
            int[] a = condicion[0];
            int[] b = condicion[1];
            int[] c = condicion[2];

            if(
                    tablero[a[0]][a[1]].equals(tablero[b[0]][b[1]]) &&
                    tablero[a[0]][a[1]].equals(tablero[c[0]][c[1]]) &&
                    !tablero[a[0]][a[1]].equals("")
            ){
                gameOver = true;
                if(CurrentPlayer.equals("X")){
                    Toast.makeText(this, "Gano el jugador X", Toast.LENGTH_LONG).show();

                } else if (CurrentPlayer.equals("O")) {
                    Toast.makeText(this, "Gano el jugador O", Toast.LENGTH_LONG).show();

                }
                rondas = rondas + 1;
                if (CurrentPlayer.equals("X")){
                    PuntosX = PuntosX + 1;
                } else if (CurrentPlayer.equals("O")) {
                    PuntosO = PuntosO + 1;
                }
                break;
            }
        }

        if (!gameOver && TableroLLeno()){
            gameOver = true;
            Toast.makeText(this, "Empate", Toast.LENGTH_LONG).show();
            rondas = rondas + 1;
        }
    }

    public boolean TableroLLeno() {
        for (String[] row : tablero) {
            for (String cell : row) {
                if (cell.equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void LimpiarTablero(){
        CurrentPlayer = "X";
        for (int i = 0; i < tablero.length; i++){
            for (int j = 0; j < tablero.length; j++){
                tablero[i][j] = "";
                Button button = findButtonByRowAndCellIndex(i, j);
                if (button != null) {
                    button.setText("");
                }
            }
        }
        gameOver = false;
        PlayerStatus = true;
        TXT1.setText("Turno de: " + CurrentPlayer);
    }

    public void ReiniciarJuego(){
        CurrentPlayer = "X";
        for (int i = 0; i < tablero.length; i++){
            for (int j = 0; j < tablero.length; j++){
                tablero[i][j] = "";
            }
        }

        gameOver = false;
        rondas = 0;
        PuntosX = 0;
        PuntosO = 0;
        PlayerStatus = true;

        TXT1.setText("Turno de: " + CurrentPlayer);

    }

    public void TerminarJuego(){
        Intent i = new Intent(this, MainActivity3.class);
        Bundle bundle = new Bundle();
        bundle.putInt("PuntosX", PuntosX);
        bundle.putInt("PuntosO", PuntosO);
        bundle.putInt("rondas", rondas);

        i.putExtras(bundle);
        startActivity(i);

    }


}

/*
// Crear un nuevo array que es uno más grande que el original
String[] newHistorial = new String[Historial.length + 1];
// Copiar los elementos del array original al nuevo
System.arraycopy(Historial, 0, newHistorial, 0, Historial.length);
// Agregar el nuevo elemento al final del nuevo array
newHistorial[newHistorial.length - 1] = "X";
// Hacer que el array original apunte al nuevo array
Historial = newHistorial;
*/