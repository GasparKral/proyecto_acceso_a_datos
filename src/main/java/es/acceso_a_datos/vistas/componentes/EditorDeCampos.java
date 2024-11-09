package es.acceso_a_datos.vistas.componentes;

import es.acceso_a_datos.modelos.Departamento;
import es.acceso_a_datos.modelos.Empleado;
import javafx.scene.layout.HBox;

public class EditorDeCampos extends HBox {

    public Boolean estaAbierto = false;

    public EditorDeCampos(Object elemento) {

        super();

        this.styleProperty().set("-fx-background-color: #dadada;");

        if (elemento instanceof Empleado) {

        } else if (elemento instanceof Departamento) {

        }

    }

}
