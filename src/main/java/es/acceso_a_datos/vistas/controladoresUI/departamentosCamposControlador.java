package es.acceso_a_datos.vistas.controladoresUI;

import es.acceso_a_datos.controladores.ControladorDepartamentos.CampoBusqueda;
import es.acceso_a_datos.controladores.ControladorPrincipal;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class DepartamentosCamposControlador {

    ControladorPrincipal instancia = ControladorPrincipal.getInstance();

    @FXML
    TextField tFId, tFNombre, tFLocalizacion;

    /**
     * Método que se ejecuta cuando se produce un cambio en algún campo de texto.
     * 
     * @param evento el evento de teclado que se produjo
     */
    @FXML
    public void campoCambiado(KeyEvent evento) {

        TextField campo = (TextField) evento.getSource();
        String id = campo.getId();

        if (id.equals("tFId")) {
            // Para el campo de Identificación
            instancia.controladorDepartamentos.setValorCampoBuscado(CampoBusqueda.ID,
                    (campo.getText().isEmpty()) ? null : campo.getText());
        } else if (id.equals("tFNombre")) {
            // Para el campo de Nombre
            instancia.controladorDepartamentos.setValorCampoBuscado(CampoBusqueda.NOMBRE,
                    (campo.getText().isEmpty()) ? null : campo.getText());
        } else if (id.equals("tFLocalizacion")) {
            // Para el campo de Localizacion
            instancia.controladorDepartamentos.setValorCampoBuscado(CampoBusqueda.LOCALIZACION,
                    (campo.getText().isEmpty()) ? null : campo.getText());
        }

    }

}
