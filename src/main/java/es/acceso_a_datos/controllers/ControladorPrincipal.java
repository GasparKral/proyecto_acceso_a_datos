package es.acceso_a_datos.controllers;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;

import es.acceso_a_datos.models.Departamento;
import es.acceso_a_datos.models.Empleado;

/**
 * Clase que representa el controlador principal de la aplicacion.
 * 
 * @author Gaspar Kral, Juan Colilla, David Rami?rez, Julian Martinez
 * @licencia Creative Commons Attribution-NonCommercial-ShareAlike 4.0
 *           International License
 * @desde 30/10/2024
 */
public class ControladorPrincipal {

    // #region Singleton

    private static ControladorPrincipal instance;

    private ControladorPrincipal() {
    }

    /**
     * Metodo que devuelve la instancia del controlador principal.
     * 
     * @return la instancia del controlador principal.
     */
    public static ControladorPrincipal getInstance() {
        if (instance == null) {
            instance = new ControladorPrincipal();
        }
        return instance;
    }

    // #endregion

    // #region Atributos

    /**
     * Controlador de departamentos.
     */
    public ControladorDepartamentos controladorDepartamentos = new ControladorDepartamentos();
    /**
     * Controlador de empleados.
     */
    public ControladorEmpleados controladorEmpleados = new ControladorEmpleados();

    // #endregion

    // #region Metodos

    /**
     * Metodo que se encarga de inicializar los datos de los departamentos y los
     * empleados a partir de los archivos XML.
     * 
     * @param departamentosStream stream del archivo de departamentos.
     * @param empleadosStream     stream del archivo de empleados.
     */
    public void inicializar(InputStream departamentosStream, InputStream empleadosStream) {

        try {
            XStream xstreamObject = new XStream();
            xstreamObject.allowTypes(new Class[] { Empleado.class, Departamento.class, ControladorDepartamentos.class,
                    ControladorEmpleados.class });

            // Convertidores de tipo
            xstreamObject.registerConverter(new SingleValueConverter() {
                @Override
                public boolean canConvert(Class type) {
                    return type.equals(Integer.class);
                }

                @Override
                public Object fromString(String str) {
                    if (str == null || str.isEmpty()) {
                        return null;
                    }
                    return Integer.parseInt(str);
                }

                @Override
                public String toString(Object obj) {
                    return obj.toString();
                }
            });
            xstreamObject.registerConverter(new SingleValueConverter() {

                @Override
                public boolean canConvert(Class type) {
                    return type.equals(Double.class);
                }

                @Override
                public Object fromString(String str) {
                    if (str == null || str.isEmpty()) {
                        return null;
                    }
                    return Double.parseDouble(str);
                }

                @Override
                public String toString(Object obj) {
                    return obj.toString();
                }
            });
            xstreamObject.registerConverter(new DateConverter() {

                @Override
                public boolean canConvert(Class type) {
                    return type.equals(Date.class);
                }

                @Override
                public Date fromString(String str) {
                    if (str == null || str.isEmpty()) {
                        return null;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        return sdf.parse(str);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });

            // Alias para los nombres de las clases
            xstreamObject.alias("Departamentos", ControladorDepartamentos.class);
            xstreamObject.alias("Empleados", ControladorEmpleados.class);

            // Alias para los nombres de las listas
            xstreamObject.addImplicitCollection(ControladorDepartamentos.class, "departamentos");
            xstreamObject.addImplicitCollection(ControladorEmpleados.class, "empleados");

            // Alias para los nombres de los campos
            xstreamObject.alias("Departamento", Departamento.class);
            xstreamObject.alias("Empleado", Empleado.class);

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

            // Parsea los archivos XML y los convierte a objetos
            this.controladorDepartamentos = (ControladorDepartamentos) xstreamObject.fromXML(departamentosStream);
            this.controladorEmpleados = (ControladorEmpleados) xstreamObject.fromXML(empleadosStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // #endregion

}
