package es.acceso_a_datos.vistas.controladoresUI;

import es.acceso_a_datos.PuntoEntrada;
import es.acceso_a_datos.controladores.ControladorPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class modificarDepartamentoControlador {

    private ControladorPrincipal controlador = ControladorPrincipal.getInstance();

    @FXML
    TextField tFNombre,tFLocalizacion;
    @FXML 
    Label mensajeCamposVacios;

    public void initialize(){
        mensajeCamposVacios.setVisible(false);
    }

    public void modificarDepartamentoOnAction(ActionEvent event) {
        if(tFNombre.getText().isEmpty() || tFLocalizacion.getText().isEmpty()) {
            mensajeCamposVacios.setVisible(true);
            return;
        }

        try {
            // Necesitamos el ID del departamento seleccionado actualmente
            int idDepartamento = controlador.controladorDepartamentos.buscDepartamentos().iterator().next().getId();
            
            controlador.controladorDepartamentos.modificarDepartamento(
                idDepartamento,
                tFNombre.getText(),
                tFLocalizacion.getText()
            );
            PuntoEntrada.cambiarEscenaA("menu");
        } catch(Exception e) {
            mensajeCamposVacios.setText("Error al modificar el departamento");
            mensajeCamposVacios.setVisible(true);
        }
    }
}
