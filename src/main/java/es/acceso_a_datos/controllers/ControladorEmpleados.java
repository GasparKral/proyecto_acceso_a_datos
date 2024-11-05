package es.acceso_a_datos.controllers;

import java.util.HashSet;
import java.util.Date;
import java.io.File;
import java.io.IOException;

import es.acceso_a_datos.models.Empleado;

public class ControladorEmpleados extends HashSet<Empleado> {

    public void leerEmpleados(File fichero) throws ClassNotFoundException, IOException {
        // Implementación futura
    }

    public void modificarEmpleado(int idOriginal, String apellido, int director, double salario, String oficio, Date fecha_alta, Double comision, Integer departamento) {
        Empleado empleadoOriginal = null; // Creamos una objeto empleado para buscar en la coleccion
        Empleado empleadoReemplazo = new Empleado(idOriginal,apellido,director,salario,oficio,fecha_alta,comision,departamento); // Creamos al empleado que reemplazará al empleado original
        for (Empleado e : this) { // Recorremos todas la colleccion 
            if (e.getId() == idOriginal) { // Comprobamos que el id que nos han pasado coincida con el de algun empleado
                empleadoOriginal = e; // En caso de que asi sea asignamos el empleado a nuestro objeto creado anteriormente
                break;
            }
        }
        
        if (empleadoOriginal != null) { 
            this.remove(empleadoOriginal); // Eliminamos el empleado original
            this.add(empleadoReemplazo); // Añadimos su reemplazo modificado
        }
    }

    public void crearEmpleado(String apellido, int director, double salario, String oficio, Date fecha_alta, Double comision, Integer departamento) {
        int id = this.size()+1; // 
        Empleado empleado = new Empleado(id,apellido,director,salario,oficio,fecha_alta,comision,departamento); // Creamos el nuevo objeto empleado que se va añadir a la coleccion
        this.add(empleado); //Añadimos a nuestro HashSet el nuevo empleado
    }

    public void eliminar(int id) {

        Empleado empleadoAEliminar = null; // Creamos una objeto para el empleado a eliminar

        for (Empleado e : this) { // Recorremos todas la colleccion 
            if (e.getId() == id) { // Comprobamos que el id que nos han pasado coincida con el de algun empleado
                empleadoAEliminar = e; // En caso de que asi sea asignamos el empleado a nuestro objeto creado anteriormente
                break;
            }
        }
        if (empleadoAEliminar != null) { 
            this.remove(empleadoAEliminar); // Eliminamos el empleado 
        }
    }
}
