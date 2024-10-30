package es.acceso_a_datos.controllers;

import java.io.File;

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

    private ControladorDepartamentos controladorDepartamentos;
    private ControladorEmpleados controladorEmpleados;

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

        controladorDepartamentos.leerDepartamentos(ficheroDepartamentos);
        controladorEmpleados.leerEmpleados(ficheroEmpleados);

    }

    // #endregion

}
