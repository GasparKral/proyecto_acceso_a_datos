package es.acceso_a_datos.vistas.controladoresUI;


import es.acceso_a_datos.PuntoEntrada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuControlador {

    @FXML
    Button bAlta,bBaja,bBuscar,bListar,bModificar,bSalir;

    public void bBuscarOnAction(ActionEvent actionEvent) {
        try {
            if (InicioControlador.esEmpleado) {
                PuntoEntrada.cambiarEscenaA("buscarEmpleado");
            } else {
                PuntoEntrada.cambiarEscenaA("buscarDepartamento");
            }
        } catch (Exception e) {
            e = new Exception("Ocurrio un error al cargar la ventana de busqueda de usuario.");
        }
    }

    public void bModificarOnAction(ActionEvent actionEvent) {
        try {
            if (InicioControlador.esEmpleado) {
                PuntoEntrada.cambiarEscenaA("modificarEmpleado");
            } else {
                PuntoEntrada.cambiarEscenaA("modificarDepartamento");
            }
        } catch (Exception e) {
            e = new Exception("Ocurrio un error al cargar la ventana de modificacion de usuario.");
        }
    }

    public void bAltaOnAction(ActionEvent actionEvent) {
        try {
            if (InicioControlador.esEmpleado) {
                PuntoEntrada.cambiarEscenaA("altaEmpleado");
            } else {
                PuntoEntrada.cambiarEscenaA("altaDepartamento");
            }
        } catch (Exception e) {
            e = new Exception("Ocurrio un error al cargar la ventana de alta de usuario.");
        }
    }

    public void bBajaOnAction(ActionEvent actionEvent) {
        try {
            if (InicioControlador.esEmpleado) {
                PuntoEntrada.cambiarEscenaA("bajaEmpleado");
            } else {
                PuntoEntrada.cambiarEscenaA("bajaDepartamento");
            }
        } catch (Exception e) {
            e = new Exception("Ocurrio un error al cargar la ventana de baja de usuario.");
        }
    }

    public void bListarOnAction(ActionEvent actionEvent) {
        try {
            if (InicioControlador.esEmpleado) {
                PuntoEntrada.cambiarEscenaA("listadoEmpleado");
            } else {
                PuntoEntrada.cambiarEscenaA("listadoDepartamento");
            }
        } catch (Exception e) {
            e = new Exception("Ocurrio un error al cargar el listado de usuarios.");
        }
    }

    public void bSalirOnAction(ActionEvent actionEvent) {
        try {
            PuntoEntrada.cambiarEscenaA("Inicio");
        } catch (Exception e) {
            e = new Exception("Ocurrio un error al cargar la ventana de inicio.");
        }
    }
}
