package com.davidrando.testtalf;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

import java.io.File;
import java.util.StringTokenizer;

public class MainActivity extends Activity {

    private static final int PICKFILE_RESULT_CODE = 1;

    private String pathfichero=null;
    private File fichero;
    private Bundle datos_examen;

    public TextView titfich;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void sel_fichero(View v){

        Intent ficheroIntent = new Intent(Intent.ACTION_GET_CONTENT);

        ficheroIntent.setType("file/*");

        try{
            startActivityForResult(ficheroIntent,PICKFILE_RESULT_CODE);
        }
        catch(ActivityNotFoundException e){
            Log.e("errores", "No hay activities para atrapar la peticion de fichero");
        }
    }

    public void selNumPreg(View v){
        RadioButton bRadio;
        switch(v.getId()){
            case R.id.radio_preg_40:
                bRadio = (RadioButton) findViewById(R.id.radio_preg_20);
                bRadio.setChecked(false);
                break;
            case R.id.radio_preg_20:
                bRadio = (RadioButton) findViewById(R.id.radio_preg_40);
                bRadio.setChecked(false);
                break;
        }
    }


    public void comenzar(View v){
        RadioButton boton;

        if (pathfichero==null){
            Toast.makeText(getApplicationContext(),"No has elegido fichero",Toast.LENGTH_SHORT).show();
        }
        else{
            datos_examen=new Bundle();
            datos_examen.putString("fichero",pathfichero);
            boton = (RadioButton) findViewById(R.id.radio_preg_40);
            if (boton.isChecked()){
                datos_examen.putInt("preguntas",40);
            }
            else{
                datos_examen.putInt("preguntas",20);
            }
            Intent examen = new Intent(this,ActivityExamen.class);
            examen.putExtras(datos_examen);
            startActivity(examen);

        }


    }


    protected void onActivityResult(int requestcode, int resultcode, Intent data){

        switch(requestcode){
            case PICKFILE_RESULT_CODE:
                if (resultcode==RESULT_OK) {
                    pathfichero = data.getData().getPath();
                    titfich = (TextView) findViewById(R.id.tit_pregunta);
                    titfich.setText(pathfichero);

                    StringTokenizer token = new StringTokenizer(pathfichero,"/");
                    String temp = null;
                    while(token.hasMoreTokens()){
                        temp=token.nextToken();
                    }
                    titfich.setText(temp);

                }
            break;
        }

    }

}
