package es.acceso_a_datos.vistas.componentes;

import java.util.ArrayList;
import java.util.List;

import es.acceso_a_datos.modelos.Departamento;
import es.acceso_a_datos.modelos.Empleado;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditorDeCampos extends HBox {

    public SimpleBooleanProperty estaAbierto = new SimpleBooleanProperty(false);

    public EditorDeCampos(Object elemento) {

        super();

        this.styleProperty().set("-fx-background-color: #dadada;");

        VBox area = new VBox();
        HBox areaBotones = new HBox();
        HBox areaCampos = new HBox();

        area.setPadding(new Insets(10, 10, 10, 10));
        areaCampos.setPadding(new Insets(10, 10, 10, 10));

        Button guardar = new Button("Guardar");
        Button eliminar = new Button("Eliminar");

        eliminar.getStyleClass().add("button-danger");

        this.getChildren().add(area);
        area.getChildren().add(areaBotones);
        area.getChildren().add(areaCampos);

        areaBotones.getChildren().add(guardar);
        areaBotones.getChildren().add(eliminar);

        areaBotones.setSpacing(20);
        areaCampos.setSpacing(20);

        this.getChildren().forEach(c -> {
            c.setManaged(false);
            c.setVisible(false);
        });

        estaAbierto.addListener((obs, oldVal, newVal) -> {
            this.getChildren().forEach(c -> {
                c.setManaged(newVal);
                c.setVisible(newVal);
            });
        });

        if (elemento instanceof Empleado) {

            Empleado empleado = (Empleado) elemento;

            List<Node> campos = new ArrayList<>();
            campos.add(new VBox(new Label("Apellido"), new TextField(empleado.getApellido())));
            campos.add(new VBox(new Label("Oficio"), new TextField(empleado.getOficio())));
            campos.add(new VBox(new Label("Salario"), new TextField(String.valueOf(empleado.getSalario()))));
            campos.add(new VBox(new Label("Comision"), new TextField(String.valueOf(empleado.getComision()))));
            campos.add(new VBox(new Label("Departamento"), new TextField(String.valueOf(empleado.getDepartamento()))));
            campos.add(new VBox(new Label("Fecha de alta"),
                    new DatePicker(empleado.getFecha_alta())));

            if (empleado.getDirector() != null) {
                campos.add(new VBox(new Label("Director"), new TextField(String.valueOf(empleado.getDirector()))));
            }

            areaCampos.getChildren().addAll(campos);

        } else if (elemento instanceof Departamento) {

            Departamento departamento = (Departamento) elemento;

            List<Node> campos = new ArrayList<>();
            campos.add(new HBox(new Label("Nombre"), new TextField(departamento.getNombre())));
            campos.add(new HBox(new Label("Localizacion"), new TextField(departamento.getLocalizacion())));

            areaCampos.getChildren().addAll(campos);

        }

    }

}
