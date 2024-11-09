package es.acceso_a_datos.vistas.controladoresUI;

import java.io.IOException;

import es.acceso_a_datos.PuntoEntrada;
import es.acceso_a_datos.controladores.ControladorPrincipal;
import es.acceso_a_datos.modelos.Departamento;
import es.acceso_a_datos.modelos.Empleado;
import es.acceso_a_datos.vistas.componentes.ResultadosBusqueda;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * Clase que controla la vista de búsqueda de empleados o departamentos.
 * 
 */
public class BusquedasControlador {

    private ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstance();

    @FXML
    VBox padre;

    @FXML
    TilePane cabecera;

    @FXML
    Pane formulario;

    @FXML
    public void initialize() {

        if (controladorPrincipal.isEditandoEmpeados()) {

            // Carga los campos para búsqueda de empleados
            try {
                formulario.getChildren()
                        .add(new FXMLLoader(PuntoEntrada.class.getResource("frames/empleadosCampos.fxml")).load());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Carga las etiquetas de los campos de los empleados
            cabecera.getChildren().add(new Label("Id"));
            cabecera.getChildren().add(new Label("Apellido"));
            cabecera.getChildren().add(new Label("Director"));
            cabecera.getChildren().add(new Label("Salario"));
            cabecera.getChildren().add(new Label("Oficio"));
            cabecera.getChildren().add(new Label("Fecha alta"));
            cabecera.getChildren().add(new Label("Comision"));
            cabecera.getChildren().add(new Label("Departamento"));

            // Carga los todos los empleados por primera vez
            for (Empleado empleado : controladorPrincipal.controladorEmpleados.listarEmpleados()) {
                padre.getChildren().add(new ResultadosBusqueda(empleado.getCamposComoStrings(), empleado));
            }

            controladorPrincipal.controladorEmpleados.camposBuscados.addListener(
                    (obs, oldVal, newVal) -> {

                        this.padre.getChildren().clear();

                        // Carga los resultados de la búsqueda de empleados
                        for (Empleado empleado : controladorPrincipal.controladorEmpleados.buscarEmpleados()) {
                            padre.getChildren().add(new ResultadosBusqueda(empleado.getCamposComoStrings(), empleado));
                        }
                    });

        } else {

            // Carga los campos para búsqueda de departamentos
            try {
                formulario.getChildren()
                        .add(new FXMLLoader(PuntoEntrada.class.getResource("frames/departamentosCampos.fxml"))
                                .load());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Carga las etiquetas de los campos de los departamentos
            cabecera.getChildren().add(new Label("Id"));
            cabecera.getChildren().add(new Label("Nombre"));
            cabecera.getChildren().add(new Label("Localización"));

            // Carga los departamentos por primera vez
            for (Departamento departamento : controladorPrincipal.controladorDepartamentos.departamentos) {
                padre.getChildren().add(new ResultadosBusqueda(departamento.getCamposComoStrings(), departamento));
            }

            controladorPrincipal.controladorDepartamentos.camposBuscados.addListener(
                    (obs, oldVal, newVal) -> {

                        this.padre.getChildren().clear();

                        // Carga los resultados de la búsqueda de departamentos
                        for (Departamento departamento : controladorPrincipal.controladorDepartamentos
                                .buscDepartamentos()) {
                            padre.getChildren()
                                    .add(new ResultadosBusqueda(departamento.getCamposComoStrings(), departamento));
                        }
                    });

        }

        // Estiliza las etiquetas de los campos
        for (Node label : cabecera.getChildren()) {
            ((Label) label).setStyle("-fx-font-weight: bold;");
            ((Label) label).setTextFill(javafx.scene.paint.Color.valueOf("#fafafa"));
            ((Label) label).alignmentProperty().setValue(javafx.geometry.Pos.CENTER);
            ((Label) label).setMaxWidth(500);
            ((Label) label).setMinWidth(50);

        }

        // Configura el ancho de cada casilla según el ancho de la escena
        cabecera.prefColumnsProperty().setValue(cabecera.getChildren().size());

        PuntoEntrada.escenario.widthProperty().addListener((obs, oldVal, newVal) -> {
            cabecera.prefTileWidthProperty().setValue((newVal.doubleValue() - 100)
                    / cabecera.getChildren().size());

        });

    }

}
