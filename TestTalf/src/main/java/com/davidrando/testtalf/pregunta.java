package com.davidrando.testtalf;

import java.util.StringTokenizer;

/**
 * Created by david on 30/06/13.
 */
public class pregunta {

    private String texto;
    private String respCorrecta;


    public pregunta(){
        this.texto=null;
        this.respCorrecta=null;
    }

    public void setTexto(String textoP){
        // Aqui le pasamos el texto y lo tokenizaremos

        StringTokenizer token = new StringTokenizer(textoP,"__");
        this.respCorrecta = token.nextToken();
        this.texto = token.nextToken();

    }

    public String getTexto(){
        return this.texto;
    }

    public String getRespuesta(){
        return this.respCorrecta;
    }

    public boolean esCorrecta(String respuestaAlumno){
        return respuestaAlumno.equals(this.respCorrecta);
    }

}
