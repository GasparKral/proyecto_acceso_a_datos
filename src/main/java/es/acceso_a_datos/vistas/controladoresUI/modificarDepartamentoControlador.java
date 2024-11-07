package es.acceso_a_datos.vistas.controladoresUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class modificarDepartamentoControlador {

    @FXML
    TextField tFNombre,tFLocalizacion;
    @FXML
    Label mensajeCamposVacios;

    public void initialize(){
        mensajeCamposVacios.setVisible(false);
    }
    public void modificarDepartamentoOnAction(ActionEvent event) {

    }
}
