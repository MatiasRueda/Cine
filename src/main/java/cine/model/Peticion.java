package cine.model;

import java.util.List;

public class Peticion {

    private String armarColumnas(List<String> columnas) {
        String peticion = "";
        for(String columna :columnas) {
            peticion += ", " + columna;
        }
        return peticion.replaceFirst(", ", "");
    }

    private String columnasInsert(List<String> columnas) {
        return "(" + armarColumnas(columnas) + ")";
    }

    private String armarPosibleValores(List<String> columnas) {
        String peticion = "(";
        for (int cantidad = 0; cantidad < columnas.size() ; cantidad++) {
            peticion += ", " + "?";
        }
        return peticion.replaceFirst(", ", "") + ")";
    }

    public String insert(String tabla, List<String> columnas) {
        return "INSERT INTO " + tabla + " " + columnasInsert(columnas) + " VALUES " + armarPosibleValores(columnas);
    }

    public String select(String tabla, String columna, String columCondicion) {
        return "SELECT " + columna + " FROM " + tabla + " WHERE " + columCondicion + " = ?";
    }

    public String select(String tabla, String columna) {
        return "SELECT " + columna + " FROM " + tabla ;
    }

    public String select(String tabla, List<String> columnas, String columCondicion) {
        return "SELECT " + armarColumnas(columnas) + " FROM " + tabla + " WHERE " + columCondicion + " = ?";
    }

    public String select(String tabla, String columna, List<String> columCondicion) {
        String condiciones = "";
        for (String columnas : columCondicion) {
            condiciones += columnas + " " + "= ? AND " ;
        }
        condiciones = condiciones.substring(0, condiciones.length() - 5);
        return "SELECT " + columna + " FROM " + tabla + " WHERE " + condiciones;
    }

    public String select(String tabla, List<String> columnas) {
        return "SELECT " + armarColumnas(columnas) + " FROM " + tabla ;
    }

    public String select(String tabla, List<String> columnas, String limit, String offset) {
        return "SELECT " + armarColumnas(columnas) + " FROM " + tabla + " LIMIT " + limit + " OFFSET " + offset;
    }


    public String delete(String tabla, String columCondicion ) {
        return  "DELETE FROM " + tabla + " WHERE " + columCondicion + " = ?" ;
    }
}
