package com.davidrando.testtalf;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class ActivityExamen extends Activity {

    private FileReader fichero;
    private BufferedReader bufferLeer;
    private int numPreguntas;
    private double puntacionPorPregunta=0.25;
    private String nombreFichero;
    private double puntosExamen = 0.0;
    private int preguntas;
    private Bundle paquete;
    private LinkedList<pregunta> listaPreguntas;


    private int puntuacion;
    private pregunta preguntaActual;
    private int contador=1;


    // Aquí estamos abriendo el fichero ya
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_examen);

        // ---- PAQUETE SACAR DATOS
        paquete=this.getIntent().getExtras();
        nombreFichero=paquete.getString("fichero");
        preguntas = paquete.getInt("preguntas");
        switch (preguntas){
            case 20:
                this.puntacionPorPregunta=0.5;
                break;
            case 30:
                this.puntacionPorPregunta=0.33;
                break;
        }

        TextView tExamen = (TextView) findViewById(R.id.examenTitulo);
        tExamen.setText("Pregunta: "+String.valueOf(this.contador)+" - "+paquete.getString("tituloExamen"));

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
            preguntaActual = listaPreguntas.getFirst();
            pregunta.setText(preguntaActual.getTexto());

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
            this.listaPreguntas.removeFirst();
            this.preguntaActual = this.listaPreguntas.getFirst();
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
            case R.id.boton_nocon:
                respActual="NC";
                break;
        }

        if (respActual!="NC"){
            ImageView imagen = (ImageView) findViewById(R.id.imagenRespuesta);
            if (preguntaActual.esCorrecta(respActual)){
                puntosExamen = puntosExamen + puntacionPorPregunta;
                imagen.setImageResource(R.drawable.yao_si);
            }
            else{
                puntosExamen = puntosExamen - puntacionPorPregunta;
                imagen.setImageResource(R.drawable.ffuu_no);
             }

            TextView pTemp = (TextView) findViewById(R.id.puntosLayout);
            pTemp.setText("Puntuacion: "+String.valueOf(puntosExamen));
        }
            TextView antPregunta = (TextView) findViewById(R.id.pAnterior);
            antPregunta.setText("Pregunta anterior: "+this.preguntaActual.getTexto());

        try{
            this.sigPregunta();
            this.contador++;
            TextView tExamen = (TextView) findViewById(R.id.examenTitulo);
            tExamen.setText("Pregunta: "+String.valueOf(this.contador)+" - "+paquete.getString("tituloExamen"));

        }
        catch(NoSuchElementException e){
            // Vamos a cargar el otro layout para mostrar el resultado
            Intent finalExamen = new Intent(this,ActivityResultado.class);
            finalExamen.putExtra("puntuacion",this.puntosExamen);
            startActivity(finalExamen);
        }

    }

}