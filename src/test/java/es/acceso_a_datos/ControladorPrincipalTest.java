package es.acceso_a_datos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.acceso_a_datos.controladores.ControladorPrincipal;
import es.acceso_a_datos.modelos.Departamento;
import es.acceso_a_datos.modelos.Empleado;

public class ControladorPrincipalTest {

        private ControladorPrincipal controladorPrincipal;

        @Before
        public void setUp() {
                controladorPrincipal = ControladorPrincipal.getInstance();
        }

        @Test
        public void testInicializar() {
                // Create mock streams for departamentos and empleados
                InputStream departamentosStream = getClass().getResourceAsStream("Departamentos.xml");
                InputStream empleadosStream = getClass().getResourceAsStream("Empleados.xml");

                // Call the inicializar method
                controladorPrincipal.inicializar(departamentosStream, empleadosStream);

                // Check that the controladorDepartamentos and controladorEmpleados attributes
                // are not null
                assertNotNull(controladorPrincipal.controladorDepartamentos);
                assertNotNull(controladorPrincipal.controladorEmpleados);

                // Check that the departamentos and empleados sets are not empty
                assertEquals(2, controladorPrincipal.controladorDepartamentos.departamentos.size());
                assertEquals(3, controladorPrincipal.controladorEmpleados.empleados.size());

                // Check that the departamentos and empleados sets contain the expected objects
                Departamento departamento1 = new Departamento(1, "Departamento 1", "Localizacion 1");
                Departamento departamento2 = new Departamento(2, "Departamento 2", "Localizacion 2");
                Empleado empleado1 = new Empleado(1, "Apellido 1", 1, 1000.0, "Oficio 1", LocalDate.now(), null, 1);
                Empleado empleado2 = new Empleado(2, "Apellido 2", 2, 2000.0, "Oficio 2", LocalDate.now(), null, 2);
                Empleado empleado3 = new Empleado(3, "Apellido 3", 3, 3000.0, "Oficio 3", LocalDate.now(), null, 1);

                assertTrue(controladorPrincipal.controladorDepartamentos.departamentos.contains(departamento1));
                assertTrue(controladorPrincipal.controladorDepartamentos.departamentos.contains(departamento2));
                assertTrue(controladorPrincipal.controladorEmpleados.empleados.contains(empleado1));
                assertTrue(controladorPrincipal.controladorEmpleados.empleados.contains(empleado2));
                assertTrue(controladorPrincipal.controladorEmpleados.empleados.contains(empleado3));
        }
}