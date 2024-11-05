package es.acceso_a_datos.controllers;

import java.io.File;
import java.io.FileInputStream;

import com.thoughtworks.xstream.XStream;

import es.acceso_a_datos.models.Departamento;
import es.acceso_a_datos.models.Empleado;

public class ControladorPrincipal {

    // #region Singleton

    private static ControladorPrincipal instance;

    private ControladorPrincipal() {
    }

    public static ControladorPrincipal getInstance() {
        if (instance == null) {
            instance = new ControladorPrincipal();
        }
        return instance;
    }

    // #endregion

    // #region Atributos

    private ControladorDepartamentos controladorDepartamentos = new ControladorDepartamentos();
    private ControladorEmpleados controladorEmpleados = new ControladorEmpleados();

    // #endregion

    // #region Metodos

    public void leerFicheros(String departamentos, String empleados) {

        File ficheroDepartamentos = new File(departamentos);
        File ficheroEmpleados = new File(empleados);

        if (!ficheroDepartamentos.exists() || !ficheroEmpleados.exists()) {
            throw new IllegalArgumentException("Ficheros no encontrados");
        }

        if (!ficheroDepartamentos.isFile() || !ficheroEmpleados.isFile()) {
            throw new IllegalArgumentException("Ruta introducida no es un fichero");
        }

    }

    // #endregion

    public void inicializar(
            String departamentosXML,
            String empleadosXML) {

        try {

            XStream xstreamObject = new XStream();
            xstreamObject.alias("Departamentos", Departamento.class);
            xstreamObject.alias("Empleados", Empleado.class);

            // Sobre escribir los nombres de los campos
            xstreamObject.aliasField("Id", Empleado.class, "id");
            xstreamObject.aliasField("Apellido", Empleado.class, "apellido");
            xstreamObject.aliasField("Director", Empleado.class, "director");
            xstreamObject.aliasField("Salario", Empleado.class, "salario");
            xstreamObject.aliasField("Oficio", Empleado.class, "oficio");
            xstreamObject.aliasField("Fecha_alta", Empleado.class, "fecha_alta");
            xstreamObject.aliasField("Departamento", Empleado.class, "departamento");

            xstreamObject.aliasField("Id", Departamento.class, "id");
            xstreamObject.aliasField("Nombre", Departamento.class, "nombre");
            xstreamObject.aliasField("Poblacion", Departamento.class, "poblacion");
            xstreamObject.aliasField("Localizacion", Departamento.class, "localizacion");

            controladorDepartamentos = (ControladorDepartamentos) xstreamObject
                    .fromXML(new FileInputStream(departamentosXML));

            controladorEmpleados = (ControladorEmpleados) xstreamObject
                    .fromXML(new FileInputStream(empleadosXML));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
