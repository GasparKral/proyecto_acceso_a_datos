package es.acceso_a_datos.vistas.controladoresUI;

import java.util.regex.Pattern;

import es.acceso_a_datos.controladores.ControladorEmpleados.CampoBusqueda;
import es.acceso_a_datos.controladores.ControladorPrincipal;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class empleadosCamposControlador {

        ControladorPrincipal instancia = ControladorPrincipal.getInstance();

        @FXML
        TextField tFId, tFApellido, tFDirector, tFSalario, tFOficio, tFComision, tFDepartamento;
        @FXML
        DatePicker dPFecha;

        /**
         * Maneja el evento cuando se cambia el contenido de un campo de texto.
         * Establece el valor de campo de b squeda correspondiente seg n el ID del campo
         * de texto.
         * 
         * @param evento KeyEvent que desencadena el m todo
         */
        @FXML
        public void campoCambiado(KeyEvent evento) {

                TextField campo = (TextField) evento.getSource();
                String id = campo.getId();

                // Verifica qu campo de texto desencaden el evento y actualiza el valor del
                // campo de búsqueda
                if (id.equals("tFId")) {
                        // Para el campo de Identificación
                        instancia.controladorEmpleados.setValorCampoBuscado(CampoBusqueda.ID,
                                        (campo.getText().isEmpty()) ? null : campo.getText());
                } else if (id.equals("tFApellido")) {
                        // Para el campo de Apellido
                        instancia.controladorEmpleados.setValorCampoBuscado(CampoBusqueda.APELLIDO,
                                        (campo.getText().isEmpty()) ? null : campo.getText());
                } else if (id.equals("tFDirector")) {
                        // Para el campo de Director, aseg ra que sea un n mero
                        instancia.controladorEmpleados.setValorCampoBuscado(CampoBusqueda.DIRECTOR,
                                        (!Pattern.matches("[0-9]+", campo.getText())) ? null : campo.getText());
                } else if (id.equals("tFSalario")) {
                        // Para el campo de Salario
                        instancia.controladorEmpleados.setValorCampoBuscado(CampoBusqueda.SALARIO,
                                        (campo.getText().isEmpty()) ? null : campo.getText());
                } else if (id.equals("tFOficio")) {
                        // Para el campo de Oficio
                        instancia.controladorEmpleados.setValorCampoBuscado(CampoBusqueda.OFICIO,
                                        (campo.getText().isEmpty()) ? null : campo.getText());
                } else if (id.equals("tFComision")) {
                        // Para el campo de Comisión
                        instancia.controladorEmpleados.setValorCampoBuscado(CampoBusqueda.COMISION,
                                        (campo.getText().isEmpty()) ? null : campo.getText());
                } else if (id.equals("tFDepartamento")) {
                        // Para el campo de Departamento
                        instancia.controladorEmpleados.setValorCampoBuscado(CampoBusqueda.DEPARTAMENTO,
                                        (campo.getText().isEmpty()) ? null : campo.getText());
                }
        }

        @FXML
        public void dPCambiado() {
                String valor = (dPFecha.getValue() != null) ? dPFecha.getValue().toString() : null;
                instancia.controladorEmpleados.setValorCampoBuscado(CampoBusqueda.FECHA_ALTA, valor);
        }

}
