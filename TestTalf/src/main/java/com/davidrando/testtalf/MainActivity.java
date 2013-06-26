package com.davidrando.testtalf;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void sel_fichero(){

        Intent ficheroIntent = new Intent(Intent.ACTION_GET_CONTENT);

        ficheroIntent.setType("gagt/sdf");
        try{
            startActivityForResult(ficheroIntent,PICK_FILE_FOR_RESULT);
        }
        catch(ActivityNotFoundException e){
            Log.e("tag", "No hay activities para atrapar la peticion de fichero");
        }

        

    }

    
}
