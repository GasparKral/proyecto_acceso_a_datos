package es.acceso_a_datos.vistas.controladoresUI;

import java.io.IOException;

import es.acceso_a_datos.PuntoEntrada;
import es.acceso_a_datos.controladores.ControladorPrincipal;
import es.acceso_a_datos.modelos.records.OpcionesDeEscena;
import es.acceso_a_datos.modelos.records.Par;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AltaDepartamentoControlador {
    @FXML
    Label mensajeCamposVacios;
    @FXML
    TextField tFLocalizacion, tFNombre;

    public void initialize() {
        mensajeCamposVacios.setVisible(false);
    }

    public void nuevoDepartamentoOnAction(ActionEvent actionEvent) {
        if (tFLocalizacion.getText().isEmpty() || tFNombre.getText().isEmpty()) {
            mensajeCamposVacios.setVisible(true);
        } else {
            try {
                ControladorPrincipal.getInstance().controladorDepartamentos.crearDepartamento(tFNombre.getText(),
                        tFLocalizacion.getText());
                ;
                mensajeCamposVacios.setVisible(false);
            } catch (Exception e) {
                e = new Exception("Ocurrio un error al cargar guardar el departamento.");
            }

        }
    }

    @FXML
    public void volver() {
        try {
            PuntoEntrada.cambiarEscenaA("busquedaPorCampos", BusquedasControlador.getInstance(),new OpcionesDeEscena(null,
                    new Par<Integer, Integer>((int) PuntoEntrada.escenario.getX(), (int) PuntoEntrada.escenario.getY()),
                    true, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
