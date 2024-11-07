package es.acceso_a_datos.vistas.controladoresUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class modificarUsuarioControlador {

    @FXML
    TextField tFApellido,tFDirector,tFSalario,tFOficio,tFComision,tFDepartamento;
    @FXML
    DatePicker dPFecha;
    @FXML
    Label mensajeCamposVacios;

    public void initialize(){
        mensajeCamposVacios.setVisible(false);
    }
    public void modificarUsuarioOnAction(ActionEvent event) {

    }
}
