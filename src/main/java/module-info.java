module es.acceso_a_datos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires xstream;
    requires junit;
    requires javafx.graphics;

    opens es.acceso_a_datos to javafx.fxml, xstream;
    opens es.acceso_a_datos.modelos to javafx.fxml, xstream;
    opens es.acceso_a_datos.modelos.records to javafx.fxml, xstream;
    opens es.acceso_a_datos.controladores to javafx.fxml, xstream;
    opens es.acceso_a_datos.vistas.componentes to javafx.fxml, xstream;
    opens es.acceso_a_datos.vistas.controladoresUI to javafx.fxml, xstream;

    exports es.acceso_a_datos;
    exports es.acceso_a_datos.controladores;
    exports es.acceso_a_datos.modelos;
    exports es.acceso_a_datos.modelos.records;

}
