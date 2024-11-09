package es.acceso_a_datos.vistas.controladoresUI;

import es.acceso_a_datos.PuntoEntrada;
import es.acceso_a_datos.controladores.ControladorPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BajaUsuarioControlador {

    @FXML
    TextField tfId;
    @FXML
    Label mensajeCamposVacios;
    public void initialize() {
        mensajeCamposVacios.setVisible(false);
    }

    public void enviarBajaOnAction(ActionEvent event) {
        if (tfId.getText().isEmpty()) {
            mensajeCamposVacios.setVisible(true);
        } else {
            try {
                ControladorPrincipal.getInstance().controladorEmpleados.eliminarEmpleado(Integer.parseInt(tfId.getText()));
                mensajeCamposVacios.setVisible(false);
            } catch (Exception e) {
                e = new Exception("Ocurrió un error al eliminar el empleado.");
            }

            try {
                PuntoEntrada.cambiarEscenaA("menu");
            } catch (Exception e) {
                e = new Exception("Ocurrió un error al cargar la ventana de menú de empleado.");
            }
        }
    }
}
