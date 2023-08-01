package cine.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Errores {

    static public enum ErrorUsuario { 
        NOMBRE_CAMPO,
        DNI_CAMPO,
        EMAIL_CAMPO,
        CONTRASENIA_CAMPO,
        CONTRASENIA_CONFIRMAR_CAMPO,
        USUARIO_REGISTRADO,
        USUARIO_NO_REGISTRADO,
        CONTRASENIA_INCORRECTA,
        CONTRASENIAS_DISTINTAS};

    private List<String> mensajes = Arrays.asList(new String[]{
        "El nombre esta en blanco",
        "El DNI esta en blanco",
        "El Email esta en blanco",
        "La contrasenia esta en blanco",
        "Confirme la contrasenia",
        "El usuario ya esta registrado",
        "El usuario no esta registrado",
        "Contrasenia incorrecta",
        "Las contrasenias no son iguales"
    });

    private HashMap<ErrorUsuario, String> errorMsj = new HashMap<>();


    public Errores() {
        this.armarErrorMsj();
    }

    private void armarErrorMsj() {
        int indice = 0;
        for (ErrorUsuario error: ErrorUsuario.values()) {
            errorMsj.put(error, mensajes.get(indice));
            indice++;
        }
    }

    public String getMensaje(ErrorUsuario error) {
        return this.errorMsj.get(error);
    }
}