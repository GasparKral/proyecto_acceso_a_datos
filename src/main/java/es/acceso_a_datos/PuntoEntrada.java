package es.acceso_a_datos;

import java.io.IOException;

import es.acceso_a_datos.modelos.records.OpcionesDeEscena;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal del programa que extiende Application de JavaFX.
 * Maneja la inicialización y gestión de las escenas.
 * 
 * @autor Gaspar Kral, Juan Colilla, David Ramiérez, Julian Martinez
 * @licencia Creative Commons Attribution-NonCommercial-ShareAlike 4.0
 *           International License
 * @desde 30/10/2024
 */
public class PuntoEntrada extends Application {

    public static Stage escenario;
    private static Scene escena;

    @Override
    public void start(Stage escenarioInicial) throws IOException {
        escenario = escenarioInicial;
        escena = new Scene(cargarFXML("pantallaPrincipal"), 750, 480);
        escenario.setScene(escena);
        escenario.show();
    }

    /**
     * Establece la raíz de la escena actual.
     * 
     * @param fxml el archivo FXML a cargar.
     * @throws IOException si ocurre un error al cargar el archivo FXML.
     */
    static void establecerRaiz(String fxml) throws IOException {
        escena.setRoot(cargarFXML(fxml));
    }

    /**
     * Carga el archivo FXML especificado.
     * 
     * @param fxml el nombre del archivo FXML sin la extensión.
     * @return el nodo raíz cargado.
     * @throws IOException si ocurre un error al cargar el archivo FXML.
     */
    private static Parent cargarFXML(String fxml) throws IOException {

        FXMLLoader cargadorFXML = new FXMLLoader(PuntoEntrada.class.getResource("frames/" + fxml + ".fxml"));
        return cargadorFXML.load();
    }

    // #region Gestión de Escenas

    /**
     * Cambia a una nueva escena especificada por el archivo FXML.
     * 
     * @param fxml el archivo FXML a cargar.
     * @throws IOException si ocurre un error al cargar el archivo FXML.
     */
    public static void cambiarEscenaA(String fxml) throws IOException {
        FXMLLoader cargador = new FXMLLoader(PuntoEntrada.class.getResource("frames/" + fxml + ".fxml"));
        Parent raiz = cargador.load();
        escena.setRoot(raiz);
        escenario.setScene(escena);
        escenario.show();
    }

    /**
     * Cambia a una nueva escena con opciones de escena.
     * 
     * @param fxml           el archivo FXML a cargar.
     * @param opcionesEscena las opciones de tamaño y posición de la escena.
     * @throws IOException si ocurre un error al cargar el archivo FXML.
     */
    public static void cambiarEscenaA(String fxml, OpcionesDeEscena opcionesEscena) throws IOException {
        FXMLLoader cargador = new FXMLLoader(PuntoEntrada.class.getResource("frames/" + fxml + ".fxml"));
        Parent raiz = cargador.load();

        int ancho = opcionesEscena.tamaño() != null ? opcionesEscena.tamaño().first() : 640;
        int alto = opcionesEscena.tamaño() != null ? opcionesEscena.tamaño().second() : 480;

        Scene nuevaEscena = new Scene(raiz, ancho, alto);
        escenario.setScene(nuevaEscena);

        if (opcionesEscena.posicion() != null) {
            escenario.setX(opcionesEscena.posicion().first());
            escenario.setY(opcionesEscena.posicion().second());
        }

        escenario.setMaximized(opcionesEscena.maximizado());
        escenario.setResizable(opcionesEscena.redimensionable());

        escenario.show();
    }

    /**
     * Cambia a una nueva escena con un controlador específico.
     * 
     * @param fxml        el archivo FXML a cargar.
     * @param controlador el controlador a usar con la escena.
     * @throws IOException si ocurre un error al cargar el archivo FXML.
     */
    public static void cambiarEscenaA(String fxml, Object controlador) throws IOException {
        FXMLLoader cargador = new FXMLLoader(PuntoEntrada.class.getResource("frames/" + fxml + ".fxml"));
        cargador.setController(controlador);
        Parent raiz = cargador.load();
        escena.setRoot(raiz);
        escenario.setScene(escena);
        escenario.show();
    }

    /**
     * Cambia a una nueva escena con un controlador específico y opciones de escena.
     * 
     * @param fxml           el archivo FXML a cargar.
     * @param controlador    el controlador a usar con la escena.
     * @param opcionesEscena las opciones de tamaño y posición de la escena.
     * @throws IOException si ocurre un error al cargar el archivo FXML.
     */
    public static void cambiarEscenaA(String fxml, Object controlador, OpcionesDeEscena opcionesEscena)
            throws IOException {
        FXMLLoader cargador = new FXMLLoader(PuntoEntrada.class.getResource("frames/" + fxml + ".fxml"));
        cargador.setController(controlador);
        Parent raiz = cargador.load();

        int ancho = opcionesEscena.tamaño() != null ? opcionesEscena.tamaño().first() : 640;
        int alto = opcionesEscena.tamaño() != null ? opcionesEscena.tamaño().second() : 480;

        Scene nuevaEscena = new Scene(raiz, ancho, alto);
        escenario.setScene(nuevaEscena);

        if (opcionesEscena.posicion() != null) {
            escenario.setX(opcionesEscena.posicion().first());
            escenario.setY(opcionesEscena.posicion().second());
        }

        escenario.setMaximized(opcionesEscena.maximizado());
        escenario.setResizable(opcionesEscena.redimensionable());

        escenario.show();
    }

    /**
     * Método principal que lanza la aplicación.
     * 
     * @param args argumentos de la línea de comandos.
     */
    public static void main(String[] args) {

        launch();
    }

}
