package es.acceso_a_datos.controllers;
import java.util.Date;
import java.util.HashSet;

import es.acceso_a_datos.models.Departamento;
import es.acceso_a_datos.models.Empleado;

public class ControladorDepartamentos {
    
    private HashSet<Departamento> departamentos = new HashSet<>();
    
    public void modificarDepartamento(int id, String nombre ,String localizacion){

        
        Departamento departamentoInicial = null; // Declaramos un objeto de la clase departamento y lo inicializamos a null para almacenar mas tarde el departamento a modificar
        Departamento departamentoRemplazo = new Departamento(id,nombre,localizacion); //Declaramos otro 

        for (Departamento dep : departamentos){
            if (dep.getId()==id) {
                departamentoInicial=dep;
            }
        }
        if (departamentoInicial!=null) {
            departamentos.remove(departamentoInicial);
            departamentos.add(departamentoRemplazo);
        }

    }

    public void crearDepartamento(String apellido, int director, double salario, String oficio, Date fecha_alta,
            Double comision, Integer departamento) {
        int id = this.departamentos.size() + 1; //
        Empleado empleado = new Empleado(id, apellido, director, salario, oficio, fecha_alta, comision, departamento); // Creamos el nuevo objeto empleado que se va añadir a la coleccion
        this.depar.add(empleado); // Añadimos a nuestro HashSet el nuevo empleado
    }

}
