package cine.model;

import java.util.HashMap;

public class Mensajes {
    private HashMap<String, String> errores = new HashMap<>();
    private String mensaje;

    public Mensajes() {
        this.armarErrores();
    }

    private void armarErrores() {
        errores.put("nombre-campo", "El nombre esta en blanco");
        errores.put("contrasenia-campo", "La contrasenia esta en blanco");
        errores.put("usuario-registrado", "El usuario ya esta registrado");
        errores.put("usuario-no-registrado", "El usuario no esta registrado");
        errores.put("contrasenia-comparacion", "La contrasenia es incorrecta");
    }

    public boolean setMensaje(boolean resultado, String tipoError) {
        if (resultado) this.mensaje = errores.get(tipoError);
        return resultado;
    }

    public boolean setMensaje(boolean resultado, String tipoError1 , String tipoError2) {
        this.mensaje = resultado ?  errores.get(tipoError1): errores.get(tipoError2);
        return resultado;
    }

    public String getMensaje() {
        return this.mensaje;
    }
}
