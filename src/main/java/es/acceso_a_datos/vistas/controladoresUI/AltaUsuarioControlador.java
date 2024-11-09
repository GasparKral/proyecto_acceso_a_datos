package es.acceso_a_datos.vistas.controladoresUI;

import java.time.ZoneId;
import java.util.Date;

import es.acceso_a_datos.PuntoEntrada;
import es.acceso_a_datos.controladores.ControladorPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AltaUsuarioControlador {
	ZoneId defaultZoneId = ZoneId.systemDefault();


    @FXML
    TextField tFApellido, tFDirector, tFSalario, tFOficio, tFComision, tFDepartamento;
    @FXML
    DatePicker dPFecha;
    @FXML
    Label mensajeCamposVacios;

    public void initialize() {
        mensajeCamposVacios.setVisible(false);
    }

    public void nuevoUsuarioOnAction(ActionEvent actionEvent) {
        if (tFApellido.getText().isEmpty() || tFDirector.getText().isEmpty() || tFSalario.getText().isEmpty()
                || tFOficio.getText().isEmpty() || tFComision.getText().isEmpty()
                || tFDepartamento.getText().isEmpty()) {
            mensajeCamposVacios.setVisible(true);
        } else {
            try {
                Date date = Date.from(dPFecha.getValue().atStartOfDay(defaultZoneId).toInstant());
                ControladorPrincipal.getInstance().controladorEmpleados.crearEmpleado(tFApellido.getText(),
                        Integer.parseInt(tFDirector.getText()), Double.parseDouble(tFSalario.getText()),
                        tFOficio.getText(), date, Double.parseDouble(tFComision.getText()),
                        Integer.parseInt(tFDepartamento.getText()));
                mensajeCamposVacios.setVisible(false);
            } catch (Exception e) {
                e = new Exception("Ocurrio un error al cargar guardar el departamento.");
            }

            try {
                PuntoEntrada.cambiarEscenaA("menu");
            } catch (Exception e) {
                e = new Exception("Ocurrio un error al cargar la ventana de menu de departamento.");
            }

        }
    }
}
