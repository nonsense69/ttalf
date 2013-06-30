package com.davidrando.testtalf;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
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
    private int preguntas;
    private Bundle paquete;
    private LinkedList<String> listaPreguntas;


    // Aquí estamos abriendo el fichero ya
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_examen);

        // ---- PAQUETE SACAR DATOS
        paquete=this.getIntent().getExtras();
        nombreFichero=paquete.getString("fichero");
        preguntas = paquete.getInt("preguntas");


        listaPreguntas = new LinkedList<String>();
        String linea = null;

        try {
            fichero = new FileReader(nombreFichero);
            bufferLeer = new BufferedReader(fichero);
            linea = bufferLeer.readLine();
            while(linea != null){
                listaPreguntas.add(linea);
                linea = bufferLeer.readLine();
            }
            //Lo desordenamos un poco para que sea aleatorio
             Collections.shuffle(listaPreguntas);
            TextView pregunta = (TextView) findViewById(R.id.textoPregunta);
            pregunta.setText(listaPreguntas.getFirst());

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

}