package es.acceso_a_datos.vistas.controladoresUI;

import java.io.IOException;

import es.acceso_a_datos.PuntoEntrada;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class InicioControlador {

    static boolean esEmpleado = false;

    @FXML
    private Button bDepartamento;

    @FXML
    private Button bEmpleado;

    @FXML
    void cargarMenuDepartamento(MouseEvent event) {
        try {
            esEmpleado = false;
            PuntoEntrada.cambiarEscenaA("menu");
        } catch (IOException e) {
            e = new IOException("Ocurrio un error al cargar la ventana de menu de departamento.");
        }
    }

    @FXML
    void cargarMenuEmpleado(MouseEvent event) {
        try {
            esEmpleado = true;
            PuntoEntrada.cambiarEscenaA("menu");
        } catch (Exception e) {
            e = new IOException("Ocurrio un error al cargar la ventana de menu de departamento.");
        }
    }

}
