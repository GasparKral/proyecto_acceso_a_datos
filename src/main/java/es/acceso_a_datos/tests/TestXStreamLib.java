package es.acceso_a_datos.tests;

import es.acceso_a_datos.controllers.ControladorPrincipal;

public class TestXStreamLib {

        public static void main(String[] args) {
                ControladorPrincipal instance = ControladorPrincipal.getInstance();

                System.out.println();

                instance.inicializar(
                                TestXStreamLib.class.getResourceAsStream(
                                                "/es/acceso_a_datos/data/tests/Departamentos.xml"),
                                TestXStreamLib.class.getResourceAsStream(
                                                "/es/acceso_a_datos/data/tests/Empleados.xml"));
        }

}
