package es.acceso_a_datos.vistas.componentes;

import java.util.List;

import es.acceso_a_datos.PuntoEntrada;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResultadosBusqueda extends HBox {

    public ResultadosBusqueda(List<String> campos) {

        super();

        for (String string : campos) {
            this.getChildren().add(crearColumna(string));
        }

        this.setWidth(Double.MAX_VALUE);
        this.setAlignment(javafx.geometry.Pos.CENTER);

        this.setWidth(Double.MAX_VALUE);
        this.setAlignment(javafx.geometry.Pos.CENTER);
        this.setSpacing(0);

        PuntoEntrada.escenario.widthProperty().addListener((obs, oldVal, newVal) -> {
            for (Node columna : this.getChildren()) {
                if (columna instanceof VBox) {
                    ((VBox) columna).setAlignment(javafx.geometry.Pos.CENTER);
                    ((VBox) columna).setPadding(new Insets(5, 0, 5, 0));
                    ((VBox) columna).setStyle("-fx-border-color: black; -fx-border-width: 1 0 1 0; ");
                    ((VBox) columna).setPrefWidth(
                            (newVal.doubleValue() - this.widthProperty().get() + 750) / (this.getChildren().size()));
                }
            }
        });

    }

    private VBox crearColumna(String texto) {
        VBox column = new VBox();
        column.getChildren().add(new Label(texto));
        return column;
    }

}
