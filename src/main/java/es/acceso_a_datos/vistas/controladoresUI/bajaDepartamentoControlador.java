package es.acceso_a_datos.vistas.controladoresUI;

import es.acceso_a_datos.PuntoEntrada;
import es.acceso_a_datos.controladores.ControladorPrincipal;
import es.acceso_a_datos.modelos.Departamento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Date;

public class BajaDepartamentoControlador {
    @FXML
    TextField tFId;
    @FXML
    Label mensajeCamposVacios;

    public void initialize() {
        mensajeCamposVacios.setVisible(false);
    }

    public void enviarBajaOnAction(ActionEvent event) {

    }
}
