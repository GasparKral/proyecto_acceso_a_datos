package es.acceso_a_datos.controllers;

import java.io.FileInputStream;
import java.io.InputStream;

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

    public void inicializar(InputStream departamentosStream, InputStream empleadosStream) {

        try {
            XStream xstreamObject = new XStream();
            xstreamObject.allowTypes(new Class[] { Empleado.class, Departamento.class, ControladorDepartamentos.class,
                    ControladorEmpleados.class });

            xstreamObject.alias("Departamentos", ControladorDepartamentos.class);
            xstreamObject.alias("Empleados", ControladorEmpleados.class);
            xstreamObject.addImplicitCollection(ControladorDepartamentos.class, "departamentos");
            xstreamObject.addImplicitCollection(ControladorEmpleados.class, "empleados");

            xstreamObject.alias("Departamento", Departamento.class);
            xstreamObject.alias("Empleado", Empleado.class);

            // Sobre escribir los nombres de los campos
            xstreamObject.aliasField("emp_no", Empleado.class, "id");
            xstreamObject.aliasField("apellido", Empleado.class, "apellido");
            xstreamObject.aliasField("dir", Empleado.class, "director");
            xstreamObject.aliasField("salario", Empleado.class, "salario");
            xstreamObject.aliasField("oficio", Empleado.class, "oficio");
            xstreamObject.aliasField("fecha_alt", Empleado.class, "fecha_alta");
            xstreamObject.aliasField("comision", Empleado.class, "comision");
            xstreamObject.aliasField("dept_no", Empleado.class, "departamento");

            xstreamObject.aliasField("dept_no", Departamento.class, "id");
            xstreamObject.aliasField("dnombre", Departamento.class, "nombre");
            xstreamObject.aliasField("loc", Departamento.class, "localizacion");

            this.controladorDepartamentos = (ControladorDepartamentos) xstreamObject.fromXML(departamentosStream);
            this.controladorEmpleados = (ControladorEmpleados) xstreamObject.fromXML(empleadosStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // #endregion

}
