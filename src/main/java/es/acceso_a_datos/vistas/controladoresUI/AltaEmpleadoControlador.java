package es.acceso_a_datos.vistas.controladoresUI;

import java.io.IOException;
import java.time.ZoneId;

import es.acceso_a_datos.PuntoEntrada;
import es.acceso_a_datos.controladores.ControladorPrincipal;
import es.acceso_a_datos.modelos.records.OpcionesDeEscena;
import es.acceso_a_datos.modelos.records.Par;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AltaEmpleadoControlador {
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

                ControladorPrincipal.getInstance().controladorEmpleados.crearEmpleado(tFApellido.getText(),
                        Integer.parseInt(tFDirector.getText()), Double.parseDouble(tFSalario.getText()),
                        tFOficio.getText(), dPFecha.getValue(), Double.parseDouble(tFComision.getText()),
                        Integer.parseInt(tFDepartamento.getText()));
                mensajeCamposVacios.setVisible(false);
            } catch (Exception e) {
                e = new Exception("Ocurrio un error al cargar guardar el departamento.");
            }

        }
    }

    @FXML
    public void volver() {
        try {
            PuntoEntrada.cambiarEscenaA("busquedaPorCampos", BusquedasControlador.getInstance(),
                    new OpcionesDeEscena(null,
                            new Par<Integer, Integer>((int) PuntoEntrada.escenario.getX(),
                                    (int) PuntoEntrada.escenario.getY()),
                            true, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
