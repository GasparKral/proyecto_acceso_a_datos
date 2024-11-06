package es.acceso_a_datos.controllers;

import java.util.HashSet;

import es.acceso_a_datos.models.Departamento;

public class ControladorDepartamentos {

    private HashSet<Departamento> departamentos = new HashSet<>();

    public void modificarDepartamento(int id, String nombre, String localizacion) {

        Departamento departamentoInicial = null; // Declaramos un objeto de la clase departamento y lo inicializamos a
                                                 // null para almacenar mas tarde el departamento a modificar
        Departamento departamentoRemplazo = new Departamento(id, nombre, localizacion); // Declaramos otro

        for (Departamento dep : this.departamentos) {
            if (dep.getId() == id) {
                departamentoInicial = dep;
            }
        }
        if (departamentoInicial != null) {
            this.departamentos.remove(departamentoInicial);
            this.departamentos.add(departamentoRemplazo);
        }

    }

}
