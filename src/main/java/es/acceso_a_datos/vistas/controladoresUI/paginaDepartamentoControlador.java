package es.acceso_a_datos.vistas.controladoresUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaginaDepartamentoControlador {
    @FXML
    TextField tFId;
    @FXML
    Label mensajerCamposVacios;

    public void initialize(){
        mensajerCamposVacios.setVisible(false);
    }
    public void bComprobarOnAction(ActionEvent event) {

    }
}
