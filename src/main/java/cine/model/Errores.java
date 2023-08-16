package cine.model;

public enum Errores {
    NOMBRE_CAMPO("El nombre esta en blanco"),
    DNI_CAMPO("El DNI esta en blanco"),
    EMAIL_CAMPO("El Email esta en blanco"),
    CONTRASENIA_CAMPO("La contrasenia esta en blanco"),
    CONTRASENIA_CONFIRMAR_CAMPO("Confirme la contrasenia"),
    USUARIO_REGISTRADO("El usuario ya esta registrado"),
    USUARIO_NO_REGISTRADO("El usuario no esta registrado"),
    CONTRASENIA_INCORRECTA("Contrasenia incorrecta"),
    CONTRASENIAS_DISTINTAS("Las contrasenias no son iguales");

    public String mensaje;

    Errores(String mensaje) {
        this.mensaje = mensaje;
    }
};