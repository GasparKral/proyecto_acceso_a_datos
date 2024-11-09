package es.acceso_a_datos.vistas.controladoresUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import es.acceso_a_datos.PuntoEntrada;
import es.acceso_a_datos.controladores.ControladorPrincipal;
import es.acceso_a_datos.modelos.records.SceneOptions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * Clase que controla la vista principal de la aplicación.
 * 
 * @author Gaspar Kral, Juan Colilla, David Ramírez, Julian Martinez
 * @licencia Creative Commons Attribution-NonCommercial-ShareAlike 4.0
 *           International License
 * @desde 30/10/2024
 */
public class PantallaPrincipalControlador {

    /**
     * Instancia del controlador principal.
     * 
     * @see es.acceso_a_datos.controladores.ControladorPrincipal
     */
    ControladorPrincipal instancia = ControladorPrincipal.getInstance();

    /**
     * Campo de texto donde se introduce el nombre del archivo de departamentos.
     * 
     * @see es.acceso_a_datos.controladores.ControladorPrincipal#inicializar(InputStream,
     *      InputStream)
     */
    @FXML
    TextField departamentosCampo;
    /**
     * Campo de texto donde se introduce el nombre del archivo de empleados.
     * 
     * @see es.acceso_a_datos.controladores.ControladorPrincipal#inicializar(InputStream,
     *      InputStream)
     */
    @FXML
    TextField empleadoCampo;
    /**
     * Bóton para seleccionar el archivo de departamentos.
     * 
     * @see es.acceso_a_datos.controladores.ControladorPrincipal#inicializar(InputStream,
     *      InputStream)
     */
    @FXML
    Button departamentoXML;
    /**
     * Bóton para seleccionar el archivo de empleados.
     * 
     * @see es.acceso_a_datos.controladores.ControladorPrincipal#inicializar(InputStream,
     *      InputStream)
     */
    @FXML
    Button empleadoXML;
    /**
     * Etiqueta para mostrar el error, si es que hay.
     * 
     * @see es.acceso_a_datos.controladores.ControladorPrincipal#inicializar(InputStream,
     *      InputStream)
     */
    @FXML
    Label errorLabel;

    /**
     * Método que se encarga de inicializar el controlador principal.
     * 
     * Establece los valores iniciales de los campos de texto con los valores
     * guardados en la cache de datos.
     * 
     * @see es.acceso_a_datos.modelos.DatosCacheados#addDato(String, String)
     * @see es.acceso_a_datos.modelos.DatosCacheados#getDato(String)
     */
    @FXML
    public void initialize() {

        // Crear un selector de archivos para los departamentos
        FileChooser selectorDepartamentosXML = new FileChooser();

        // Si hay un archivo guardado en la cache, establecerlo como el valor
        // predeterminado
        if (instancia.controladorDatos.datosCacheados.containsKey("departamentosXML")) {
            departamentosCampo.setText(instancia.controladorDatos.datosCacheados.getDato("departamentosXML"));
            selectorDepartamentosXML.setInitialDirectory(new File(departamentosCampo.getText()));
        }

        // Establecer el filtro de extensiones para que solo se muestren archivos xml
        selectorDepartamentosXML.selectedExtensionFilterProperty().setValue(
                new FileChooser.ExtensionFilter("XML", "*.xml"));

        // Crear un selector de archivos para los empleados
        FileChooser selectorEmpleadosXML = new FileChooser();

        // Si hay un archivo guardado en la cache, establecerlo como el valor
        // predeterminado
        if (instancia.controladorDatos.datosCacheados.containsKey("empleadosXML")) {
            empleadoCampo.setText(instancia.controladorDatos.datosCacheados.getDato("empleadosXML"));
            selectorEmpleadosXML.setInitialDirectory(new File(empleadoCampo.getText()));
        }

        // Establecer el filtro de extensiones para que solo se muestren archivos xml
        selectorEmpleadosXML.selectedExtensionFilterProperty().setValue(
                new FileChooser.ExtensionFilter("XML", "*.xml"));

        // Establecer el comportamiento del botón de seleccionar archivo de
        // departamentos
        departamentoXML.setOnAction(event -> {
            File seleccionado = selectorDepartamentosXML.showOpenDialog(PuntoEntrada.escenario.getScene().getWindow());
            if (seleccionado != null) {
                departamentosCampo.setText(seleccionado.getAbsolutePath());
                instancia.controladorDatos.datosCacheados.addDato("departamentosXML", seleccionado.getAbsolutePath());
            }
        });

        // Establecer el comportamiento del botón de seleccionar archivo de empleados
        empleadoXML.setOnAction(event -> {
            File seleccionado = selectorEmpleadosXML.showOpenDialog(PuntoEntrada.escenario.getScene().getWindow());
            if (seleccionado != null) {
                empleadoCampo.setText(seleccionado.getAbsolutePath());
                instancia.controladorDatos.datosCacheados.addDato("empleadosXML", seleccionado.getAbsolutePath());
            }
        });

    }

    /**
     * Método que se encarga de inicializar el controlador principal con los
     * archivos de departamentos y empleados.
     * 
     * @param departamentosStream
     *                            Stream del archivo de departamentos
     * @param empleadosStream
     *                            Stream del archivo de empleados
     */
    @FXML
    public void bEmpleadoOnAction() {

        if (departamentosCampo.getText().isEmpty() || empleadoCampo.getText().isEmpty()) {
            errorLabel.setText("Los campos no pueden estar vacíos");
            errorLabel.setVisible(true);
            return;
        }

        String departamentosXML = departamentosCampo.getText();
        String empleadosXML = empleadoCampo.getText();

        // Crear streams para los archivos de departamentos y empleados
        InputStream departamentosStream = null;
        InputStream empleadosStream = null;
        try {
            // Abrir los archivos y crear los streams
            departamentosStream = new FileInputStream(departamentosXML);
            empleadosStream = new FileInputStream(empleadosXML);
        } catch (Exception e) {
            // Mostrar el error
            e.printStackTrace();
        }

        // Inicializar el controlador principal con los streams
        instancia.inicializar(departamentosStream, empleadosStream);
        // Establecer el modo de edición de empleados
        instancia.editarEmpleados();
        // Cargar los campos de búsqueda en el controlador de empleados
        instancia.controladorEmpleados.cargarEnums();
        // Ocultar el mensaje de error
        if (errorLabel.isVisible())
            errorLabel.setVisible(false);

        try {
            // Cambiar a la escena de búsqueda por campos
            PuntoEntrada.cambiarEscenaA("busquedaPorCampos", new BusquedasControlador(),
                    new SceneOptions(null, null, true, false));
        } catch (Exception e) {
            // Mostrar el error
            e.printStackTrace();
        }
    }

    /**
     * Método que maneja la acción al presionar el botón de departamento.
     * 
     * Verifica que los campos no estén vacíos, inicializa el controlador
     * principal con los archivos especificados y cambia a la escena de búsqueda.
     */
    @FXML
    public void bDepartamentoOnAction() {

        // Verificar que los campos no estén vacíos
        if (departamentosCampo.getText().isEmpty() || empleadoCampo.getText().isEmpty()) {
            errorLabel.setText("Los campos no pueden estar vacíos");
            errorLabel.setVisible(true);
            return;
        }

        // Obtener las rutas de los archivos XML desde los campos de texto
        String departamentosXML = departamentosCampo.getText();
        String empleadosXML = empleadoCampo.getText();

        InputStream departamentosStream = null;
        InputStream empleadosStream = null;
        try {
            // Crear streams para los archivos de departamentos y empleados
            departamentosStream = new FileInputStream(departamentosXML);
            empleadosStream = new FileInputStream(empleadosXML);
        } catch (Exception e) {
            // Mostrar el error en caso de que ocurra una excepción al abrir los archivos
            e.printStackTrace();
        }

        // Inicializar el controlador principal con los streams
        instancia.inicializar(departamentosStream, empleadosStream);
        // Establecer el modo de edición de departamentos
        instancia.editarDepartamentos();
        // Cargar los campos de búsqueda en el controlador de empleados
        instancia.controladorDepartamentos.cargarEnums();
        // Ocultar el mensaje de error
        if (errorLabel.isVisible())
            errorLabel.setVisible(false);

        try {
            // Cambiar a la escena de búsqueda por campos
            PuntoEntrada.cambiarEscenaA("busquedaPorCampos", new BusquedasControlador(),
                    new SceneOptions(null, null, true, false));
        } catch (Exception e) {
            // Mostrar el error si ocurre una excepción al cambiar la escena
            e.printStackTrace();
        }
    }
}
