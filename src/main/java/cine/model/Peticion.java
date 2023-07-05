package cine.model;

import java.util.List;

public class Peticion {

    private String armarColumnas(List<String> columnas) {
        String peticion = "(";
        for(String columna :columnas) {
            peticion += ", " + columna;
        }
        return peticion.replaceFirst(", ", "") + ")";
    }

    private String armarPosibleValores(List<String> columnas) {
        String peticion = "(";
        for (int cantidad = 0; cantidad < columnas.size() ; cantidad++) {
            peticion += ", " + "?";
        }
        return peticion.replaceFirst(", ", "") + ")";
    }

    public String insert(String tabla, List<String> columnas) {
        return "INSERT INTO " + tabla + " " + armarColumnas(columnas) + " VALUES " + armarPosibleValores(columnas);
    }

    public String select(String tabla, String columna, String columCondicion) {
        return "SELECT " + columna + " FROM " + tabla + " WHERE " + columCondicion + " = ?";
    }
}
