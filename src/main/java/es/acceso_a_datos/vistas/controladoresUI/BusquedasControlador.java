package es.acceso_a_datos.vistas.controladoresUI;

import java.io.IOException;

import es.acceso_a_datos.PuntoEntrada;
import es.acceso_a_datos.controladores.ControladorPrincipal;
import es.acceso_a_datos.modelos.Departamento;
import es.acceso_a_datos.modelos.Empleado;
import es.acceso_a_datos.vistas.componentes.EditorDeCampos;
import es.acceso_a_datos.vistas.componentes.ResultadosBusqueda;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Clase que controla la vista de búsqueda de empleados o departamentos.
 * 
 */
public class BusquedasControlador {

    private final double ALTURA_MAXIMA_EDITOR_DE_CAMPOS = 100.0;

    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstance();

    @FXML
    VBox padre;

    @FXML
    TilePane cabecera;

    @FXML
    Pane formulario;

    @FXML
    private void initialize() {

        if (controladorPrincipal.isEditandoEmpeados()) {

            // Carga los campos para búsqueda de empleados
            try {
                formulario.getChildren().add(
                        new FXMLLoader(PuntoEntrada.class.getResource("frames/empleadosCampos.fxml")).load());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Carga las etiquetas de los campos de los empleados
            cabecera.getChildren().addAll(
                    new Label("Id"),
                    new Label("Apellido"),
                    new Label("Director"),
                    new Label("Salario"),
                    new Label("Oficio"),
                    new Label("Fecha alta"),
                    new Label("Comision"),
                    new Label("Departamento"));

            // Carga los todos los empleados por primera vez
            for (Empleado empleado : controladorPrincipal.controladorEmpleados.listarEmpleados()) {
                padre.getChildren().add(crearFilaEmpleado(empleado));
            }

            controladorPrincipal.controladorEmpleados.camposBuscados.addListener(
                    (obs, oldVal, newVal) -> {

                        padre.getChildren().clear();

                        // Carga los resultados de la búsqueda de empleados
                        for (Empleado empleado : controladorPrincipal.controladorEmpleados.buscarEmpleados()) {
                            padre.getChildren().add(crearFilaEmpleado(empleado));
                        }
                    });

        } else {

            // Carga los campos para búsqueda de departamentos
            try {
                formulario.getChildren().add(
                        new FXMLLoader(PuntoEntrada.class.getResource("frames/departamentosCampos.fxml"))
                                .load());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Carga las etiquetas de los campos de los departamentos
            cabecera.getChildren().addAll(
                    new Label("Id"),
                    new Label("Nombre"),
                    new Label("Localización"));

            // Carga los departamentos por primera vez
            for (Departamento departamento : controladorPrincipal.controladorDepartamentos.departamentos) {
                padre.getChildren().add(crearFilaDepartamento(departamento));
            }

            controladorPrincipal.controladorDepartamentos.camposBuscados.addListener(
                    (obs, oldVal, newVal) -> {

                        padre.getChildren().clear();

                        // Carga los resultados de la búsqueda de departamentos
                        for (Departamento departamento : controladorPrincipal.controladorDepartamentos
                                .buscDepartamentos()) {
                            padre.getChildren().add(crearFilaDepartamento(departamento));
                        }
                    });

        }

        // Estiliza las etiquetas de los campos
        for (Node label : cabecera.getChildren()) {
            ((Label) label).setStyle("-fx-font-weight: bold;");
            ((Label) label).setTextFill(javafx.scene.paint.Color.valueOf("#fafafa"));
            ((Label) label).setAlignment(javafx.geometry.Pos.CENTER);
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

    private VBox crearFilaDepartamento(Departamento departamento) {

        EditorDeCampos editorDeCampos = new EditorDeCampos(departamento);

        VBox fila = new VBox(
                new ResultadosBusqueda(departamento.getCamposComoStrings()), editorDeCampos);

        fila.setOnMouseClicked(event -> {
            Timeline timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);

            // Creamos un KeyFrame para la animación
            Timeline animacion = new Timeline();
            animacion.setOnFinished((a) -> {
                // Cambia el estado del editor cuando la animación termina
                editorDeCampos.estaAbierto.set(!editorDeCampos.estaAbierto.get());
            });

            // KeyFrame que define la duración, propiedades y valores finales de la
            // animación
            KeyFrame keyFrame = new KeyFrame(Duration.millis(300),
                    new KeyValue(editorDeCampos.opacityProperty(), editorDeCampos.estaAbierto.get() ? 0.0 : 1.0,
                            Interpolator.EASE_BOTH),
                    new KeyValue(editorDeCampos.minHeightProperty(),
                            editorDeCampos.estaAbierto.get() ? 0.0 : ALTURA_MAXIMA_EDITOR_DE_CAMPOS,
                            Interpolator.EASE_BOTH));

            // Añade el KeyFrame a la animación y comienza la animación
            animacion.getKeyFrames().add(keyFrame);
            animacion.play();

        });

        return fila;
    }

    private VBox crearFilaEmpleado(Empleado empleado) {

        EditorDeCampos editorDeCampos = new EditorDeCampos(empleado);

        VBox fila = new VBox(
                new ResultadosBusqueda(empleado.getCamposComoStrings()), editorDeCampos);

        fila.setOnMouseClicked(event -> {
            Timeline timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);

            // Creamos un KeyFrame para la animación
            Timeline animacion = new Timeline();
            animacion.setOnFinished((a) -> {
                // Cambia el estado del editor cuando la animación termina
                editorDeCampos.estaAbierto.set(!editorDeCampos.estaAbierto.get());
            });

            // KeyFrame que define la duración, propiedades y valores finales de la
            // animación
            KeyFrame keyFrame = new KeyFrame(Duration.millis(300),
                    new KeyValue(editorDeCampos.opacityProperty(), editorDeCampos.estaAbierto.get() ? 0.0 : 1.0,
                            Interpolator.EASE_BOTH),
                    new KeyValue(editorDeCampos.minHeightProperty(),
                            editorDeCampos.estaAbierto.get() ? 0.0 : ALTURA_MAXIMA_EDITOR_DE_CAMPOS,
                            Interpolator.EASE_BOTH));

            // Añade el KeyFrame a la animación y comienza la animación
            animacion.getKeyFrames().add(keyFrame);
            animacion.play();

        });

        return fila;
    }
}
