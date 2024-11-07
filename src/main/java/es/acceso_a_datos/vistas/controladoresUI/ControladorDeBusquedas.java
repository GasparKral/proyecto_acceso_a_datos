package es.acceso_a_datos.vistas.controladoresUI;

import es.acceso_a_datos.PuntoEntrada;
import es.acceso_a_datos.controladores.ControladorPrincipal;
import es.acceso_a_datos.modelos.Departamento;
import es.acceso_a_datos.modelos.Empleado;
import es.acceso_a_datos.vistas.componentes.ResultadosBusqueda;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class ControladorDeBusquedas {

    private ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstance();

    @FXML
    VBox padre;

    @FXML
    TilePane cabecera;

    @FXML
    public void initialize() {

        if (controladorPrincipal.editandoEmpeados) {
            cabecera.getChildren().add(new Label("Id"));
            cabecera.getChildren().add(new Label("Apellido"));
            cabecera.getChildren().add(new Label("Director"));
            cabecera.getChildren().add(new Label("Salario"));
            cabecera.getChildren().add(new Label("Oficio"));
            cabecera.getChildren().add(new Label("Fecha alta"));
            cabecera.getChildren().add(new Label("Comision"));
            cabecera.getChildren().add(new Label("Departamento"));

            for (Empleado empleado : controladorPrincipal.controladorEmpleados.empleados) {
                padre.getChildren().add(new ResultadosBusqueda(empleado.getCamposComoStrings()));
            }

        } else {
            cabecera.getChildren().add(new Label("Id"));
            cabecera.getChildren().add(new Label("Nombre"));
            cabecera.getChildren().add(new Label("Localización"));

            for (Departamento departamento : controladorPrincipal.controladorDepartamentos.departamentos) {
                padre.getChildren().add(new ResultadosBusqueda(departamento.getCamposComoStrings()));
            }
        }

        PuntoEntrada.escenario.widthProperty().addListener((obs, oldVal, newVal) -> {
            cabecera.setHgap((newVal.doubleValue() - 750) /
                    cabecera.getChildren().size());
        });

    }

}
