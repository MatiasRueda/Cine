# Cine
![Static Badge](https://img.shields.io/badge/Estado%20-%20Terminado%20-%20green)

## Introducción
  Proyecto personal terminado, utilizado para poder demostrar mis conocimientos en Java.
  La aplicación ofrece simular la compra de productos de un cine (entradas o alimentos) a través de una interfaz   grafica y guardar los datos de estos usuarios con sus compras realizadas.
  Por ultimo, el proyecto cuanta con algunos test automáticos.

## Tipo de proyecto
Proyecto individual.

## Tecnologías utilizadas
  - Java
  - JavaFX
  - JUnit
  - MySQL

## Estructura
```
Cine
├─ .gitignore
├─ Cine.jar
├─ pom.xml
├─ README.md
└─ src
   ├─ main
   │  ├─ java
   │  │  ├─ cine
   │  │  │  ├─ App.java
   │  │  │  ├─ controller
   │  │  │  │  ├─ Candy.java
   │  │  │  │  ├─ Candys.java
   │  │  │  │  ├─ Cartelera.java
   │  │  │  │  ├─ Comprado.java
   │  │  │  │  ├─ Comprar.java
   │  │  │  │  ├─ Confirmar.java
   │  │  │  │  ├─ Controlador.java
   │  │  │  │  ├─ ESCENA.java
   │  │  │  │  ├─ Escenas.java
   │  │  │  │  ├─ Fecha.java
   │  │  │  │  ├─ Horario.java
   │  │  │  │  ├─ Ingresar.java
   │  │  │  │  ├─ Inicio.java
   │  │  │  │  ├─ Mensaje.java
   │  │  │  │  ├─ NavSinUsuario.java
   │  │  │  │  ├─ NavUsuario.java
   │  │  │  │  ├─ Opcion.java
   │  │  │  │  ├─ Peliculas.java
   │  │  │  │  ├─ Registrar.java
   │  │  │  │  └─ Sala.java
   │  │  │  ├─ Main.java
   │  │  │  ├─ model
   │  │  │  │  ├─ Cine.java
   │  │  │  │  ├─ Conversor.java
   │  │  │  │  ├─ Encryptor.java
   │  │  │  │  ├─ Errores.java
   │  │  │  │  ├─ Mensajeria.java
   │  │  │  │  ├─ MySQL.java
   │  │  │  │  ├─ Peticion.java
   │  │  │  │  ├─ Usuario.java
   │  │  │  │  └─ Verificador.java
   │  │  │  └─ view
   │  │  │     ├─ Butacas.java
   │  │  │     ├─ Candys.java
   │  │  │     ├─ Item.java
   │  │  │     ├─ Nav.java
   │  │  │     ├─ Opcion.java
   │  │  │     └─ Pelicula.java
   │  │  └─ module-info.java
   │  └─ resources
   │     ├─ cine
   │     │  ├─ candy.fxml
   │     │  ├─ candys.fxml
   │     │  ├─ carga.fxml
   │     │  ├─ cargando.fxml
   │     │  ├─ cartelera.fxml
   │     │  ├─ comprado.fxml
   │     │  ├─ comprar.fxml
   │     │  ├─ confirmar.fxml
   │     │  ├─ controlador.fxml
   │     │  ├─ fecha.fxml
   │     │  ├─ horario.fxml
   │     │  ├─ ingresar.fxml
   │     │  ├─ inicio.fxml
   │     │  ├─ mensaje.fxml
   │     │  ├─ navsinusuario.fxml
   │     │  ├─ navusuario.fxml
   │     │  ├─ opcion.fxml
   │     │  ├─ peliculas.fxml
   │     │  ├─ registrar.fxml
   │     │  └─ sala.fxml
   │     ├─ estilos
   │     │  ├─ candy.css
   │     │  ├─ candys.css
   │     │  ├─ carga.css
   │     │  ├─ cartelera.css
   │     │  ├─ compraExito.css
   │     │  ├─ comprar.css
   │     │  ├─ confirmar.css
   │     │  ├─ fecha.css
   │     │  ├─ horario.css
   │     │  ├─ ingresar.css
   │     │  ├─ mensaje.css
   │     │  ├─ nav.css
   │     │  ├─ opcion.css
   │     │  ├─ peliculas.css
   │     │  ├─ registrar.css
   │     │  └─ sala.css
   │     └─ imagenes
   │        ├─ pelicula.png
   │        └─ pochoclos.png
   └─ test
      └─ java
         └─ cine
            ├─ CineTest.java
            ├─ ConversorTest.java
            ├─ EncryptorTest.java
            ├─ ErroresTest.java
            ├─ MensajeriaTest.java
            ├─ MySQLTest.java
            └─ PeticionTest.java

```

## Uso
Para poder utilizar la aplicación será necesario contar con una base de datos.
Sin embargo en el mismo repositorio se cuenta con un archivo .jar que le podrá ser de utilidad
para usar esta aplicación.
Para poder utilizarlo:
1. Es necesario tener instalado Java.
2. Abra la terminal en la ruta donde se encuentra el archivo .jar.
3. Escriba el siguiente comando:
    ```
	java -jar Cine.jar
    ```
> [!NOTE]
> La base de datos a la que conectada el archivo. jar es lenta.  Así que por favor tenga paciencia.
