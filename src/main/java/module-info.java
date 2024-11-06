module es.acceso_a_datos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires xstream;
    requires junit;

    opens acceso_a_datos to javafx.fxml, xstream;
    opens es.acceso_a_datos.models to javafx.fxml, xstream;
    opens es.acceso_a_datos.models.records to javafx.fxml, xstream;
    opens es.acceso_a_datos.controllers to javafx.fxml, xstream;
    opens es.acceso_a_datos.views to javafx.fxml, xstream;

    exports es.acceso_a_datos.controllers;
    exports es.acceso_a_datos.views;
    exports es.acceso_a_datos.models;
    exports es.acceso_a_datos.models.records;

}
