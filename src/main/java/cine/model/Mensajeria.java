package cine.model;


public class Mensajeria {
    private String mensaje;

    public boolean setMensaje(boolean resultado, Errores errorUsuario) {
        if (resultado) this.mensaje = errorUsuario.mensaje;
        return resultado;
    }

    public boolean setMensaje(boolean resultado, Errores errorUsuario , Errores errorUsuario2) {
        this.mensaje = resultado ?  errorUsuario.mensaje: errorUsuario2.mensaje;
        return resultado;
    }

    public String getMensaje() {
        return this.mensaje;
    }
}
