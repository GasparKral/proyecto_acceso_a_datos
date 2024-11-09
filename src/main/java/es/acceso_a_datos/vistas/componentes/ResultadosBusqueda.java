package es.acceso_a_datos.vistas.componentes;

import java.util.List;

import es.acceso_a_datos.PuntoEntrada;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResultadosBusqueda extends HBox {

    /**
     * Constructor que recibe una lista de campos y los muestra en columnas.
     * 
     * @param campos
     */
    public ResultadosBusqueda(List<String> campos) {

        super();

        for (String string : campos) {
            // Crea una columna para cada campo y se la añade
            this.getChildren().add(crearColumna(string));
        }

        // Se ajusta el ancho para que ocupe todo el espacio disponible
        this.setWidth(Double.MAX_VALUE);
        this.setAlignment(javafx.geometry.Pos.CENTER);
        this.setSpacing(0);

        aplicarEstilos(PuntoEntrada.escenario.getWidth());

        // Se define el estilo para cuando se pasa el ratón por encima
        this.hoverProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                this.setStyle("-fx-background-color: #bbdefb;");
            } else {
                this.setStyle("");
            }
        });

        // Se ajusta el ancho de las columnas según el ancho de la escena
        PuntoEntrada.escenario.widthProperty().addListener((obs, oldVal, newVal) -> {
            aplicarEstilos(newVal.doubleValue());
        });

    };

    private void aplicarEstilos(Double ancho) {
        for (Node columna : this.getChildren()) {
            // Se ajusta el ancho y el padding de las columnas
            ((VBox) columna).setAlignment(javafx.geometry.Pos.CENTER);
            ((VBox) columna).setPadding(new Insets(5, 0, 5, 0));
            ((VBox) columna).setStyle(
                    "-fx-border-color: black; -fx-border-width: 0 0 1 0; "
                            + "-fx-background-color: transparent; ");
            ((VBox) columna).setPrefWidth(
                    ((ancho - 100) / this.getChildren().size()));
        }
    }

    /**
     * Crea una columna con un label que muestra el texto dado.
     * 
     * @param texto
     * @return
     */
    private VBox crearColumna(String texto) {
        VBox column = new VBox();
        column.getChildren().add(new Label(texto));
        ((Label) column.getChildren().get(0)).setAlignment(javafx.geometry.Pos.CENTER);
        return column;
    }

}
