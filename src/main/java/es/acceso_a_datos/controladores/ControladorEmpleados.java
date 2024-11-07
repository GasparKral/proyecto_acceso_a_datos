package es.acceso_a_datos.controladores;

import java.util.HashSet;
import java.util.Date;

import es.acceso_a_datos.modelos.Empleado;

public class ControladorEmpleados {

    public HashSet<Empleado> empleados = new HashSet<>();
    
    public enum CampoBusqueda { //Utilizo un enum para facilitar la busqueda
        ID, APELLIDO, DIRECTOR, SALARIO, OFICIO, FECHA_ALTA, COMISION, DEPARTAMENTO
    }

    public HashSet<Empleado> buscarEmpleado(String valor, CampoBusqueda campo) { //Metodo que sirve para buscar un empleado, el cual devuelve un HashSet de empleados

        HashSet<Empleado> empleadosARetornar = new HashSet<>(); // Creamos el HashSet que se retornará
        
        for (Empleado e : this.empleados) { // Recorremos todos los empleados existentes
            switch(campo) { // Hacemos un switch para separar segun el campo por el que se busca y segun el que sea comparamos con valor dado
                case ID:
                    if (String.valueOf(e.getId()).equals(valor)) {
                        empleadosARetornar.add(e);
                    }
                    break;
                case APELLIDO:
                    if (e.getApellido().equals(valor)) {
                        empleadosARetornar.add(e);
                    }
                    break;
                case DIRECTOR:
                    if (String.valueOf(e.getDirector()).equals(valor)) {
                        empleadosARetornar.add(e);
                    }
                    break;
                case SALARIO:
                    if (String.valueOf(e.getSalario()).equals(valor)) {
                        empleadosARetornar.add(e);
                    }
                    break;
                case OFICIO:
                    if (e.getOficio().equals(valor)) {
                        empleadosARetornar.add(e);
                    }
                    break;
                case FECHA_ALTA:
                    if (e.getFecha_alta().toString().equals(valor)) {
                        empleadosARetornar.add(e);
                    }
                    break;
                case COMISION:
                    if (String.valueOf(e.getComision()).equals(valor)) {
                        empleadosARetornar.add(e);
                    }
                    break;
                case DEPARTAMENTO:
                    if (String.valueOf(e.getDepartamento()).equals(valor)) {
                        empleadosARetornar.add(e);
                    }
                    break;
            }
        }
        
        return empleadosARetornar; // Retornamos el HashSet
    }


    public void modificarEmpleado(int idOriginal, String apellido, int director, double salario, String oficio, Date fecha_alta, Double comision, Integer departamento) {
        Empleado empleadoOriginal = null; // Creamos una objeto empleado para buscar en la coleccion
        Empleado empleadoReemplazo = new Empleado(idOriginal, apellido, director, salario, oficio, fecha_alta, comision,
                departamento); // Creamos al empleado que reemplazará al empleado original
        for (Empleado e : this.empleados) { // Recorremos todas la colleccion
            if (e.getId() == idOriginal) { // Comprobamos que el id que nos han pasado coincida con el de algun empleado
                empleadoOriginal = e; // En caso de que asi sea asignamos el empleado a nuestro objeto creado
                                      // anteriormente
                break;
            }
        }

        if (empleadoOriginal != null) {
            this.empleados.remove(empleadoOriginal); // Eliminamos el empleado original
            this.empleados.add(empleadoReemplazo); // Añadimos su reemplazo modificado
        }
    }

    public void crearEmpleado(String apellido, int director, double salario, String oficio, Date fecha_alta, Double comision, Integer departamento) {
        int id = this.empleados.size() + 1; //
        Empleado empleado = new Empleado(id, apellido, director, salario, oficio, fecha_alta, comision, departamento);
        // Creamos el nuevo objeto empleado que se va añadir a la coleccion
        this.empleados.add(empleado); // Añadimos a nuestro HashSet el nuevo empleado
    }

    public void eliminarEmpleado(int id) {

        Empleado empleadoAEliminar = null; // Creamos una objeto para el empleado a eliminar

        for (Empleado e : this.empleados) { // Recorremos todas la colleccion
            if (e.getId() == id) { // Comprobamos que el id que nos han pasado coincida con el de algun empleado
                empleadoAEliminar = e; // En caso de que asi sea asignamos el empleado a nuestro objeto creado
                                       // anteriormente
                break;
            }
        }
        if (empleadoAEliminar != null) {
            this.empleados.remove(empleadoAEliminar); // Eliminamos el empleado
        }
    }
}
