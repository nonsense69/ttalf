package com.davidrando.testtalf;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final int PICKFILE_RESULT_CODE = 1;

    private String pathfichero;

    public TextView titfich = (TextView) findViewById(R.id.tit_pregunta);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void sel_fichero(){

        Intent ficheroIntent = new Intent(Intent.ACTION_GET_CONTENT);

        ficheroIntent.setType("file/*");

        try{
            startActivityForResult(ficheroIntent,PICKFILE_RESULT_CODE);
        }
        catch(ActivityNotFoundException e){
            Log.e("tag", "No hay activities para atrapar la peticion de fichero");
        }

    }

    protected void onActivityResult(int requestcode, int resultcode, Intent data){

        switch(requestcode){
            case PICKFILE_RESULT_CODE:
                if (resultcode==RESULT_OK) {
                    pathfichero = data.getData().getPath();
                }
            break;
        }

        titfich.setText(pathfichero);

    }

    
}
