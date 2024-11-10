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

    /**
     * Propiedad que indica si el editor est abierto o no.
     */
    public SimpleBooleanProperty estaAbierto = new SimpleBooleanProperty(false);

    /**
     * Instancia del controlador principal de la aplicaci n.
     */
    private ControladorPrincipal instancia = ControladorPrincipal.getInstance();

    /**
     * Constructor de la clase que crea un nuevo editor de campos para el objeto
     * determinado.
     * 
     * @param elemento el objeto que se va a editar.
     */

    public EditorDeCampos(Object elemento) {

        super();

        // Establece el estilo de fondo del editor
        this.styleProperty().set("-fx-background-color: #dadada;");

        // Crea un contenedor vertical (VBox) para los campos y botones
        VBox area = new VBox();
        // Crea un contenedor horizontal (HBox) para los botones
        HBox areaBotones = new HBox();
        // Crea un contenedor horizontal (HBox) para los campos
        HBox areaCampos = new HBox();

        // Establece el relleno para los contenedores
        area.setPadding(new Insets(10, 10, 10, 10));
        areaCampos.setPadding(new Insets(10, 10, 10, 10));

        // Crea los botones de guardar y eliminar
        Button guardar = new Button("Guardar");
        Button eliminar = new Button("Eliminar");

        // Establece el estilo de peligro para el botón de eliminar
        eliminar.getStyleClass().add("button-danger");

        // Agrega los contenedores y botones al editor
        this.getChildren().add(area);
        area.getChildren().add(areaBotones);
        area.getChildren().add(areaCampos);

        // Agrega los botones al contenedor de botones
        areaBotones.getChildren().add(guardar);
        areaBotones.getChildren().add(eliminar);

        // Establece el espacio entre los botones
        areaBotones.setSpacing(20);
        areaCampos.setSpacing(20);

        // Oculta los elementos del editor por defecto
        this.getChildren().forEach(c -> {
            c.setManaged(false);
            c.setVisible(false);
        });

        // Agrega un listener para mostrar u ocultar los elementos del editor
        estaAbierto.addListener((obs, oldVal, newVal) -> {
            this.getChildren().forEach(c -> {
                c.setManaged(newVal);
                c.setVisible(newVal);
            });
        });

        // Verifica si el elemento es un Empleado
        if (elemento instanceof Empleado) {
            // Crea un empleado a partir del elemento
            Empleado empleado = (Empleado) elemento;

            // Crea una lista de nodos para los campos del empleado
            List<Node> campos = new ArrayList<>();

            // Agrega los campos del empleado a la lista
            campos.add(new VBox(new Label("Apellido"), new TextField(empleado.getApellido())));
            campos.add(new VBox(new Label("Oficio"), new TextField(empleado.getOficio())));
            campos.add(new VBox(new Label("Salario"), new Spinner<>(0, 100000, empleado.getSalario())));
            campos.add(new VBox(new Label("Comision"),
                    new Spinner<>(0, 10000, empleado.getComision() == null ? 0 : empleado.getComision())));
            campos.add(new VBox(new Label("Departamento"),
                    new Spinner<>(0, 100, empleado.getDepartamento() == null ? -1 : empleado.getDepartamento())));
            campos.add(new VBox(new Label("Fecha de alta"), new DatePicker(empleado.getFecha_alta())));

            // Verifica si el empleado tiene un director
            if (empleado.getDirector() != null) {
                // Agrega el campo del director a la lista
                campos.add(new VBox(new Label("Director"), new TextField(String.valueOf(empleado.getDirector()))));
            }

            // Agrega los campos a el contenedor de campos
            areaCampos.getChildren().addAll(campos);

            // Agrega un listener para el botón de guardar
            guardar.setOnAction(e -> {
                // Recorre los campos y actualiza los valores del empleado
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
                            // Actualiza los valores del empleado según el campo
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
                // Llama al método para modificar el empleado
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
            // Agrega un listener para el botón de eliminar
            eliminar.setOnAction(e -> {
                // Crea un diálogo de confirmación para eliminar el empleado
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar el empleado?");
                alert.showAndWait().ifPresent(
                        buttonType -> {
                            if (buttonType == ButtonType.OK) {
                                // Llama al método para eliminar el empleado
                                instancia.controladorEmpleados.eliminarEmpleado(empleado.getId());

                                // Actualiza la lista de empleados
                                BusquedasControlador.getInstance().actualizarListaEmpleados();
                            }
                        });
            });

            // Verifica si el elemento es un Departamento
        } else if (elemento instanceof Departamento) {
            // Crea un departamento a partir del elemento
            Departamento departamento = (Departamento) elemento;

            // Crea una lista de nodos para los campos del departamento
            List<Node> campos = new ArrayList<>();

            // Agrega los campos del departamento a la lista
            campos.add(new HBox(new Label("Nombre"), new TextField(departamento.getNombre())));
            campos.add(new HBox(new Label("Localizacion"), new TextField(departamento.getLocalizacion())));

            areaCampos.getChildren().addAll(campos);

            // Agrega un listener para el botón de guardar

            guardar.setOnAction(e -> {
                // Recorre los campos y actualiza los valores del departamento

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
                            // Actualiza los valores del departamento según el campo

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
                // Llama al método para modificar el departamento

                instancia.controladorDepartamentos.modificarDepartamento(
                        departamento.getId(),
                        departamento.getNombre(),
                        departamento.getLocalizacion());

            });
            // Agrega un listener para el botón de eliminar

            eliminar.setOnAction(e -> {
                // Crea un diálogo de confirmación para eliminar el departamento

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar el departamento?");
                alert.showAndWait().ifPresent(
                        buttonType -> {
                            if (buttonType == ButtonType.OK) {
                                try {
                                    // Llama al método para eliminar el departamento

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
                                    // Actualiza la lista de departamentos

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
