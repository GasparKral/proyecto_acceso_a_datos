package es.acceso_a_datos.vistas.componentes;

import java.util.ArrayList;
import java.util.List;

import es.acceso_a_datos.controladores.ControladorPrincipal;
import es.acceso_a_datos.modelos.Departamento;
import es.acceso_a_datos.modelos.Empleado;
import es.acceso_a_datos.vistas.controladoresUI.BusquedasControlador;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditorDeCampos extends HBox {

    public SimpleBooleanProperty estaAbierto = new SimpleBooleanProperty(false);
    private ControladorPrincipal instancia = ControladorPrincipal.getInstance();

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
            campos.add(new VBox(new Label("Salario"), new Spinner<>(0, 100000, empleado.getSalario())));
            campos.add(new VBox(new Label("Comision"),
                    new Spinner<>(0, 10000, empleado.getComision() == null ? 0 : empleado.getComision())));
            campos.add(new VBox(new Label("Departamento"),
                    new Spinner<>(0, 100, empleado.getDepartamento() == null ? -1 : empleado.getDepartamento())));
            campos.add(new VBox(new Label("Fecha de alta"), new DatePicker(empleado.getFecha_alta())));

            if (empleado.getDirector() != null) {
                campos.add(new VBox(new Label("Director"), new TextField(String.valueOf(empleado.getDirector()))));
            }

            areaCampos.getChildren().addAll(campos);

            guardar.setOnAction(e -> {

                areaCampos.getChildren().forEach(c -> {
                    if (c instanceof VBox) {
                        ((VBox) c).getChildren().forEach(n -> {
                            String label;
                            Object valor;
                            if (n instanceof Label) {
                                label = ((Label) n).getText();
                            } else {
                                label = "";
                            }
                            if (n instanceof Spinner) {
                                valor = ((Spinner) n).getValue();
                            } else if (n instanceof TextField) {
                                valor = ((TextField) n).getText();
                            } else {
                                valor = "";
                            }

                            switch (label) {
                                case "Apellido":
                                    empleado.setApellido((String) valor);
                                    break;
                                case "Oficio":
                                    empleado.setOficio((String) valor);
                                    break;
                                case "Salario":
                                    empleado.setSalario(Double.parseDouble(valor.toString()));
                                    break;
                                case "Comision":
                                    empleado.setComision(Double.parseDouble(valor.toString()));
                                    break;
                                case "Departamento":
                                    empleado.setDepartamento(Integer.parseInt(valor.toString()));
                                    break;
                                case "Fecha de alta":
                                    empleado.setFecha_alta(((DatePicker) n).getValue());
                                    break;
                            }
                        });
                    }
                });

                instancia.controladorEmpleados.modificarEmpleado(
                        empleado.getId(),
                        empleado.getApellido(),
                        empleado.getDirector(),
                        empleado.getSalario(),
                        empleado.getOficio(),
                        empleado.getFecha_alta(),
                        empleado.getComision(),
                        empleado.getDepartamento());

            });

            eliminar.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar el empleado?");
                alert.showAndWait().ifPresent(
                        buttonType -> {
                            if (buttonType == ButtonType.OK) {
                                instancia.controladorEmpleados.eliminarEmpleado(empleado.getId());

                                BusquedasControlador.getInstance().actualizarListaEmpleados();
                            }
                        });
            });

        } else if (elemento instanceof Departamento) {

            Departamento departamento = (Departamento) elemento;

            List<Node> campos = new ArrayList<>();
            campos.add(new HBox(new Label("Nombre"), new TextField(departamento.getNombre())));
            campos.add(new HBox(new Label("Localizacion"), new TextField(departamento.getLocalizacion())));

            areaCampos.getChildren().addAll(campos);

            guardar.setOnAction(e -> {
                areaCampos.getChildren().forEach(c -> {
                    if (c instanceof HBox) {
                        ((HBox) c).getChildren().forEach(n -> {
                            String label;
                            String texto;
                            if (n instanceof Label) {
                                label = ((Label) n).getText();
                            } else {
                                label = "";
                            }
                            if (n instanceof TextField) {
                                texto = ((TextField) n).getText();
                            } else {
                                texto = "";
                            }
                            switch (label) {
                                case "Nombre":
                                    departamento.setNombre(texto);
                                    break;
                                case "Localizacion":
                                    departamento.setLocalizacion(texto);
                                    break;
                            }
                        });
                    }
                });

                instancia.controladorDepartamentos.modificarDepartamento(
                        departamento.getId(),
                        departamento.getNombre(),
                        departamento.getLocalizacion());

            });

            eliminar.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar el departamento?");
                alert.showAndWait().ifPresent(
                        buttonType -> {
                            if (buttonType == ButtonType.OK) {
                                try {
                                    instancia.controladorDepartamentos.eliminarDepartamento(departamento.getId());
                                    List<Empleado> empleadosCopia = new ArrayList<>(
                                            instancia.controladorEmpleados.empleados);
                                    empleadosCopia.forEach(empleado -> {
                                        if (empleado.getDepartamento() != null) {
                                            if (empleado.getDepartamento() == departamento.getId()) {
                                                instancia.controladorEmpleados.modificarEmpleado(empleado.getId(),
                                                        empleado.getApellido(), empleado.getDirector(),
                                                        empleado.getSalario(), empleado.getOficio(),
                                                        empleado.getFecha_alta(), empleado.getComision(), null);
                                            }
                                        }
                                    });

                                    BusquedasControlador.getInstance().actualizarListaDepartamentos();

                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
            });

        }

    }

}
