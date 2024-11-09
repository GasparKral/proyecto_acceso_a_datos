package es.acceso_a_datos.vistas.controladoresUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaginaEmpleadoControlador {
    @FXML
    TextField tFId;
    @FXML
    Label mensajeCamposVacios;
    public void initialize() {
        mensajeCamposVacios.setVisible(false);
    }

    public void bComprobarOnAction(ActionEvent event) {

    }
}
