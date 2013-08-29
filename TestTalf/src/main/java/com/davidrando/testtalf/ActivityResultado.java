package com.davidrando.testtalf;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityResultado extends Activity {

    private ArrayList<String> listaFalladas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resultado);

        TextView ponerPuntos = (TextView) findViewById(R.id.mostrarPuntos);
        double puntos = this.getIntent().getDoubleExtra("puntuacion",0.0);
        listaFalladas = (ArrayList<String>) this.getIntent().getSerializableExtra("fallos");


        ponerPuntos.setText(String.valueOf(puntos));

        ponerPuntos = (TextView) findViewById(R.id.aprSus);
        if (puntos>=5.0){
            ponerPuntos.setText("APROBADO");
        }
        else{
            ponerPuntos.setText("SUSPENSO");
        }

        for (int i=0;i<listaFalladas.size();i++){
            ((TextView) findViewById(R.id.idListaFalladas)).append(listaFalladas.get(i)+"\n");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_resultado, menu);
        return true;
    }
    
}
