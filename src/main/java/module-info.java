module es.acceso_a_datos {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;

    opens es.acceso_a_datos to javafx.fxml;
    opens es.acceso_a_datos.controllers to javafx.fxml;
    opens es.acceso_a_datos.views to javafx.fxml;

    exports es.acceso_a_datos.controllers;
    exports es.acceso_a_datos.views;
    exports es.acceso_a_datos.models;
    exports es.acceso_a_datos.models.records;
    exports es.acceso_a_datos;
}
