package com.example.usuario.gatoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btn11;
    private Button btn12;
    private Button btn13;
    private Button btn21;
    private Button btn22;
    private Button btn23;
    private Button btn31;
    private Button btn32;
    private Button btn33;
    private Tablero b = new Tablero();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn11 = (Button) findViewById(R.id.btn11);
        btn12 = (Button) findViewById(R.id.btn12);
        btn13 = (Button) findViewById(R.id.btn13);
        btn21 = (Button) findViewById(R.id.btn21);
        btn22 = (Button) findViewById(R.id.btn22);
        btn23 = (Button) findViewById(R.id.btn23);
        btn31 = (Button) findViewById(R.id.btn31);
        btn32 = (Button) findViewById(R.id.btn32);
        btn33 = (Button) findViewById(R.id.btn33);

        b.showTablero();
    }

    public void jugadaSeleccionada(int x, int y){
        if (!b.juegoTerminado()) {
            Posicion jugada = new Posicion(x, y);
            b.setJugada(jugada, 2); //2 for O and O is the user
            b.showTablero();
            if (!b.juegoTerminado()) {
            b.callMinimax(0, 1);
            b.setJugada(b.mejorJugada(), 1);
            b.showTablero();
            }
        }
        if (b.juegoTerminado()) {
            if(!btn11.getText().equals("") &&!btn12.getText().equals("") &&!btn13.getText().equals("")
                    && !btn21.getText().equals("") &&!btn22.getText().equals("") &&!btn23.getText().equals("")
                    && !btn31.getText().equals("") &&!btn32.getText().equals("") &&!btn33.getText().equals("")){

                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
                dialog.setTitle("Juego terminado");
                dialog.setMessage("Ha sido un empate");
                dialog.show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Juego terminado")
                        .setMessage("Has perdido :(")
                        .setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn11:
                if (btn11.getText().equals(""))
                btn11.setText("X");
                jugadaSeleccionada(0,0);
                break;
            case R.id.btn12:
                if (btn12.getText().equals(""))
                    btn12.setText("X");
                jugadaSeleccionada(0,1);
                break;
            case R.id.btn13:
                if (btn13.getText().equals(""))
                btn13.setText("X");
                jugadaSeleccionada(0,2);
                break;
            case R.id.btn21:
                if (btn21.getText().equals(""))
                btn21.setText("X");
                jugadaSeleccionada(1,0);
                break;
            case R.id.btn22:
                if (btn22.getText().equals(""))
                btn22.setText("X");
                jugadaSeleccionada(1,1);
                break;
            case R.id.btn23:
                if (btn23.getText().equals(""))
                btn23.setText("X");
                jugadaSeleccionada(1,2);
                break;
            case R.id.btn31:
                if (btn31.getText().equals(""))
                btn31.setText("X");
                jugadaSeleccionada(2,0);
                break;
            case R.id.btn32:
                if (btn32.getText().equals(""))
                btn32.setText("X");
                jugadaSeleccionada(2,1);
                break;
            case R.id.btn33:
                if (btn33.getText().equals(""))
                btn33.setText("X");
                jugadaSeleccionada(2,2);
                break;
            case R.id.btn_reiniciar:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                break;
            case R.id.btnMaquina:
                Random random = new Random();
                Posicion p = new Posicion(random.nextInt(3), random.nextInt(3));
                b.setJugada(p, 1);
                b.showTablero();
                break;
            default:
                break;
        }
    }

    public void printCasillas(int[][] tablero){
        if(tablero[0][0] == 1){
            btn11.setText("X");
        } else if (tablero[0][0] == 2){
            btn11.setText("O");
        }
        if(tablero[0][1] == 1){
            btn12.setText("X");
        } else if (tablero[0][1] == 2){
            btn12.setText("O");
        }
        if(tablero[0][2] == 1){
            btn13.setText("X");
        } else if (tablero[0][2] == 2){
            btn13.setText("O");
        }
        if(tablero[1][0] == 1){
            btn21.setText("X");
        } else if (tablero[1][0] == 2){
            btn21.setText("O");
        }
        if(tablero[1][1] == 1){
            btn22.setText("X");
        } else if (tablero[1][1] == 2){
            btn22.setText("O");
        }
        if(tablero[1][2] == 1){
            btn23.setText("X");
        } else if (tablero[1][2] == 2){
            btn23.setText("O");
        }
        if(tablero[2][0] == 1){
            btn31.setText("X");
        } else if (tablero[2][0] == 2){
            btn31.setText("O");
        }
        if(tablero[2][1] == 1){
            btn32.setText("X");
        } else if (tablero[2][1] == 2){
            btn32.setText("O");
        }
        if(tablero[2][2] == 1){
            btn33.setText("X");
        } else if (tablero[2][2] == 2){
            btn33.setText("O");
        }
    }

    class Posicion {

        int x;
        int y;

        public Posicion(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class PosicionMarcador {

        int marcador;
        Posicion posicion;

        PosicionMarcador(int marcador, Posicion posicion) {
            this.marcador = marcador;
            this.posicion = posicion;
        }
    }

    class Tablero {

        List<Posicion> posicionesDisponibles;
        int[][] tablero = new int[3][3];

        public Tablero() {
        }

        public boolean juegoTerminado() {
            //El juego termina si gano X u O o si hay empate
            return (xGana() || oGana() || getEstadosDisponibles().isEmpty());
        }

        public boolean xGana() {
            if ((tablero[0][0] == tablero[1][1] && tablero[0][0] == tablero[2][2] && tablero[0][0] == 1) || (tablero[0][2] == tablero[1][1] && tablero[0][2] == tablero[2][0] && tablero[0][2] == 1)) {
                return true;
            }
            for (int i = 0; i < 3; ++i) {
                if (((tablero[i][0] == tablero[i][1] && tablero[i][0] == tablero[i][2] && tablero[i][0] == 1)
                        || (tablero[0][i] == tablero[1][i] && tablero[0][i] == tablero[2][i] && tablero[0][i] == 1))) {
                    return true;
                }
            }
            return false;
        }

        public boolean oGana() {
            if ((tablero[0][0] == tablero[1][1] && tablero[0][0] == tablero[2][2] && tablero[0][0] == 2) || (tablero[0][2] == tablero[1][1] && tablero[0][2] == tablero[2][0] && tablero[0][2] == 2)) {
                return true;
            }
            for (int i = 0; i < 3; ++i) {
                if ((tablero[i][0] == tablero[i][1] && tablero[i][0] == tablero[i][2] && tablero[i][0] == 2)
                        || (tablero[0][i] == tablero[1][i] && tablero[0][i] == tablero[2][i] && tablero[0][i] == 2)) {
                    return true;
                }
            }

            return false;
        }

        public List<Posicion> getEstadosDisponibles() {
            posicionesDisponibles = new ArrayList<>();
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (tablero[i][j] == 0) {
                        posicionesDisponibles.add(new Posicion(i, j));
                    }
                }
            }
            return posicionesDisponibles;
        }

        public void setJugada(Posicion posicion, int jugada) {
            tablero[posicion.x][posicion.y] = jugada;   //jugada = 1 para X, 2 para O
        }

        public Posicion mejorJugada() {
            int MAX = -100000;
            int mejor = -1;

            for (int i = 0; i < marcadorHijo.size(); ++i) {
                if (MAX < marcadorHijo.get(i).marcador) {
                    MAX = marcadorHijo.get(i).marcador;
                    mejor = i;
                }
            }

            return marcadorHijo.get(mejor).posicion;
        }

        public void showTablero() {
            printCasillas(tablero);
        }

        public int returnMin(List<Integer> list) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i) < min) {
                    min = list.get(i);
                    index = i;
                }
            }
            return list.get(index);
        }

        public int returnMax(List<Integer> list) {
            int max = Integer.MIN_VALUE;
            int index = -1;
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i) > max) {
                    max = list.get(i);
                    index = i;
                }
            }
            return list.get(index);
        }

        List<PosicionMarcador> marcadorHijo;

        public void callMinimax(int profundidad, int turno){
            marcadorHijo = new ArrayList<>();
            minimax(profundidad, turno);
        }

        public int minimax(int profundidad, int turno) {

            if (xGana()) return +1;
            if (oGana()) return -1;

            List<Posicion> posicionesDisponibles = getEstadosDisponibles();
            if (posicionesDisponibles.isEmpty()) return 0;

            List<Integer> marcadores = new ArrayList<>();

            for (int i = 0; i < posicionesDisponibles.size(); ++i) {
                Posicion posicion = posicionesDisponibles.get(i);

                if (turno == 1) { //X's selecciona el valor máximo del minimax
                    setJugada(posicion, 1);
                    int marcadorActual = minimax(profundidad + 1, 2);
                    marcadores.add(marcadorActual);

                    if (profundidad == 0)
                        marcadorHijo.add(new PosicionMarcador(marcadorActual, posicion));

                } else if (turno == 2) {//O's selecciona el menor del minimax
                    setJugada(posicion, 2);
                    marcadores.add(minimax(profundidad + 1, 1));
                }
                tablero[posicion.x][posicion.y] = 0; //Reinicia esta posición
            }
            return turno == 1 ? returnMax(marcadores) : returnMin(marcadores);
        }
    }
}
