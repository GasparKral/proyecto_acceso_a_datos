package es.acceso_a_datos.vistas.controladoresUI;


import es.acceso_a_datos.PuntoEntrada;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class menuControlador {

    @FXML
    private Button bAlta;

    @FXML
    private Button bBaja;

    @FXML
    private Button bBuscar;

    @FXML
    private Button bListar;

    @FXML
    private Button bModificar;

    @FXML
    private Button bSalir;

    @FXML
    void altaUsuario(MouseEvent event) {
        try {
            if (inicioControlador.esEmpleado) {
                PuntoEntrada.cambiarEscenaA("altaEmpleado");
            } else {
                PuntoEntrada.cambiarEscenaA("altaDepartamento");
            }
        } catch (Exception e) {
            e = new Exception("Ocurrio un error al cargar la ventana de alta de usuario.");
        }
    }

    @FXML
    void bajaUsuario(MouseEvent event) {
        try {
            if (inicioControlador.esEmpleado) {
                PuntoEntrada.cambiarEscenaA("bajaEmpleado");
            } else {
                PuntoEntrada.cambiarEscenaA("bajaDepartamento");
            }
        } catch (Exception e) {
            e = new Exception("Ocurrio un error al cargar la ventana de baja de usuario.");
        }

    }

    @FXML
    void buscarUnUsuario(MouseEvent event) {
        try {
            if (inicioControlador.esEmpleado) {
                PuntoEntrada.cambiarEscenaA("buscarEmpleado");
            } else {
                PuntoEntrada.cambiarEscenaA("buscarDepartamento");
            }
        } catch (Exception e) {
            e = new Exception("Ocurrio un error al cargar la ventana de busqueda de usuario.");
        }
    }

    @FXML
    void listarUsuarios(MouseEvent event) {
        try {
            if (inicioControlador.esEmpleado) {
                PuntoEntrada.cambiarEscenaA("listadoEmpleado");
            } else {
                PuntoEntrada.cambiarEscenaA("listadoDepartamento");
            }
        } catch (Exception e) {
            e = new Exception("Ocurrio un error al cargar el listado de usuarios.");
        }
    }

    @FXML
    void modificarUsuario(MouseEvent event) {
        try {
            if (inicioControlador.esEmpleado) {
                PuntoEntrada.cambiarEscenaA("modificarEmpleado");
            } else {
                PuntoEntrada.cambiarEscenaA("modificarDepartamento");
            }
        } catch (Exception e) {
            e = new Exception("Ocurrio un error al cargar la ventana de modificacion de usuario.");
        }
    }

    @FXML
    void salirAlInicio(MouseEvent event) {
        try {
            PuntoEntrada.cambiarEscenaA("Inicio");
        } catch (Exception e) {
            e = new Exception("Ocurrio un error al cargar la ventana de inicio.");
        }
    }

}
