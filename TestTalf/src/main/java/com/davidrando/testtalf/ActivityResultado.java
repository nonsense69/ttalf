package com.davidrando.testtalf;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ActivityResultado extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resultado);

        TextView ponerPuntos = (TextView) findViewById(R.id.mostrarPuntos);
        double puntos = this.getIntent().getDoubleExtra("puntuacion",0.0);
        ponerPuntos.setText(String.valueOf(puntos));

        ponerPuntos = (TextView) findViewById(R.id.aprSus);
        if (puntos>=5.0){
            ponerPuntos.setText("APROBADO");
        }
        else{
            ponerPuntos.setText("SUSPENSO");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_resultado, menu);
        return true;
    }
    
}
