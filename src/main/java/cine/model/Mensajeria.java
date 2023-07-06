package cine.model;

import cine.model.Errores.ErrorUsuario;

public class Mensajeria {
    private Errores errores = new Errores();
    private String mensaje;

    public boolean setMensaje(boolean resultado, ErrorUsuario errorUsuario) {
        if (resultado) this.mensaje = errores.getMensaje(errorUsuario);
        return resultado;
    }

    public boolean setMensaje(boolean resultado, ErrorUsuario errorUsuario , ErrorUsuario errorUsuario2) {
        this.mensaje = resultado ?  errores.getMensaje(errorUsuario): errores.getMensaje(errorUsuario2);
        return resultado;
    }

    public String getMensaje() {
        return this.mensaje;
    }
}
