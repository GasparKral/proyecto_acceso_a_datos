package es.acceso_a_datos.vistas.controladoresUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class altaDepartamentoControlador {
    @FXML
    Label mensajeCamposVacios;
    @FXML
    TextField tFLocalizacion, tFNombre;

    public void initialize(){
        mensajeCamposVacios.setVisible(false);
    }


    public void nuevoDepartamentoOnAction(ActionEvent event) {

    }
}
