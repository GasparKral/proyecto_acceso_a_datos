package es.acceso_a_datos.controladores;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;

import es.acceso_a_datos.modelos.DatosCacheados;
import es.acceso_a_datos.modelos.Departamento;
import es.acceso_a_datos.modelos.Empleado;

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
     * Bandera que indica si se estan editando los empleados o no.
     */
    private boolean editandoEmpeados = false;

    /**
     * Referencia del objeto XStream para la serializacion.
     */
    private XStream objetoXStream = new XStream();

    /**
     * Controlador de departamentos.
     */
    public ControladorDepartamentos controladorDepartamentos = new ControladorDepartamentos();
    /**
     * Controlador de empleados.
     */
    public ControladorEmpleados controladorEmpleados = new ControladorEmpleados();

    /**
     * Estructura para almacenar los datos cacheados de la aplicacion.
     */
    public ControladorDatos controladorDatos = new ControladorDatos();

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
            objetoXStream.allowTypes(new Class[] { Empleado.class, Departamento.class, ControladorDepartamentos.class,
                    ControladorEmpleados.class });

            // Convertidores de tipo
            objetoXStream.registerConverter(new SingleValueConverter() {
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
            objetoXStream.registerConverter(new SingleValueConverter() {

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
            objetoXStream.registerConverter(new DateConverter() {

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
            objetoXStream.alias("Departamentos", ControladorDepartamentos.class);
            objetoXStream.alias("Empleados", ControladorEmpleados.class);

            // Alias para los nombres de las listas
            objetoXStream.addImplicitCollection(ControladorDepartamentos.class, "departamentos");
            objetoXStream.addImplicitCollection(ControladorEmpleados.class, "empleados");

            // Alias para los nombres de los campos
            objetoXStream.alias("Departamento", Departamento.class);
            objetoXStream.alias("Empleado", Empleado.class);

            objetoXStream.aliasField("emp_no", Empleado.class, "id");
            objetoXStream.aliasField("apellido", Empleado.class, "apellido");
            objetoXStream.aliasField("dir", Empleado.class, "director");
            objetoXStream.aliasField("salario", Empleado.class, "salario");
            objetoXStream.aliasField("oficio", Empleado.class, "oficio");
            objetoXStream.aliasField("fecha_alt", Empleado.class, "fecha_alta");
            objetoXStream.aliasField("comision", Empleado.class, "comision");
            objetoXStream.aliasField("dept_no", Empleado.class, "departamento");

            objetoXStream.aliasField("dept_no", Departamento.class, "id");
            objetoXStream.aliasField("dnombre", Departamento.class, "nombre");
            objetoXStream.aliasField("loc", Departamento.class, "localizacion");

            // Parsea los archivos XML y los convierte a objetos
            this.controladorDepartamentos = (ControladorDepartamentos) objetoXStream.fromXML(departamentosStream);
            this.controladorEmpleados = (ControladorEmpleados) objetoXStream.fromXML(empleadosStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isEditandoEmpeados() {
        return editandoEmpeados;
    }

    public void editarEmpleados() {
        this.editandoEmpeados = true;
    }

    public void editarDepartamentos() {
        this.editandoEmpeados = false;
    }

    /**
     * Guarda los datos de los departamentos y empleados en archivos XML.
     *
     * @param rutaDepartamentos Ruta del archivo donde se guardarán los departamentos.
     * @param rutaEmpleados     Ruta del archivo donde se guardarán los empleados.
     */
    public void guardarXML(String rutaDepartamentos, String rutaEmpleados) {
        try {
            // Omite el campo 'camposBuscados' de la serialización
            objetoXStream.omitField(ControladorEmpleados.class, "camposBuscados");
            objetoXStream.omitField(ControladorDepartamentos.class, "camposBuscados");

            // Serializa los departamentos y guarda en el archivo especificado
            objetoXStream.toXML(this.controladorDepartamentos, new FileOutputStream(rutaDepartamentos));
            
            // Serializa los empleados y guarda en el archivo especificado
            objetoXStream.toXML(this.controladorEmpleados, new FileOutputStream(rutaEmpleados));

        } catch (Exception e) {
            // Imprime la traza de la excepción en caso de error
            e.printStackTrace();
        }
    }

    // #endregion

}
