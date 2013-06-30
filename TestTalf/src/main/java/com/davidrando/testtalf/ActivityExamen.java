package com.davidrando.testtalf;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;


public class ActivityExamen extends Activity {

    private FileReader fichero;
    private BufferedReader bufferLeer;
    private int numPreguntas;
    private double puntacionPorPregunta=0.25;
    private String nombreFichero;
    private double puntosExamen;
    private int preguntas;
    private Bundle paquete;
    private LinkedList<pregunta> listaPreguntas;


    private int puntuacion;
    private pregunta preguntaActual;


    // Aquí estamos abriendo el fichero ya
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_examen);

        // ---- PAQUETE SACAR DATOS
        paquete=this.getIntent().getExtras();
        nombreFichero=paquete.getString("fichero");
        preguntas = paquete.getInt("preguntas");


        TextView tExamen = (TextView) findViewById(R.id.examenTitulo);
        tExamen.setText(paquete.getString("tituloExamen"));

        listaPreguntas = new LinkedList<pregunta>();
        String linea = null;

        try {
            fichero = new FileReader(nombreFichero);
            bufferLeer = new BufferedReader(fichero);
            linea = bufferLeer.readLine();
            while(linea != null){
                pregunta Nodo = new pregunta();
                Nodo.setTexto(linea);
                listaPreguntas.add(Nodo);
                linea = bufferLeer.readLine();
            }
            //Lo desordenamos un poco para que sea aleatorio
             Collections.shuffle(listaPreguntas);
            TextView pregunta = (TextView) findViewById(R.id.textoPregunta);
            pregunta.setText(listaPreguntas.getFirst().getTexto());
            preguntaActual = listaPreguntas.getFirst();

            fichero.close();;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_examen, menu);
        return true;
    }


    public void sigPregunta(){
        listaPreguntas.removeFirst();
        preguntaActual = listaPreguntas.getFirst();
        TextView preg = (TextView) findViewById(R.id.textoPregunta);
        preg.setText(preguntaActual.getTexto());
    }


    public void responder(View v){
        // Vamos a ver que ha respondido el usuario

        String respActual=null;

        switch (v.getId()){
            case R.id.boton_si:
                respActual="V";
                break;
            case R.id.boton_no:
                respActual="F";
                break;
        }

        if (preguntaActual.esCorrecta(respActual)){
            puntosExamen = puntosExamen + puntacionPorPregunta;
        }
        else{
            puntosExamen = puntosExamen - puntacionPorPregunta;
        }

        TextView pTemp = (TextView) findViewById(R.id.puntuacion);
        pTemp.setText("Puntuacion"+String.valueOf(puntosExamen));

        this.sigPregunta();
    }

}