package tests;

import es.acceso_a_datos.controllers.ControladorPrincipal;

public class TestXStreamLib {

    public static void main(String[] args) {
        ControladorPrincipal instance = ControladorPrincipal.getInstance();
        instance.inicializar("departamentos.xml", "empleados.xml");
    }

}
