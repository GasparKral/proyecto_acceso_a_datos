package es.acceso_a_datos.vistas.controladoresUI;

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

    }
}
