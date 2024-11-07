package es.acceso_a_datos.controladores;

import java.util.HashSet;
import es.acceso_a_datos.modelos.Departamento;

public class ControladorDepartamentos {

    public HashSet<Departamento> departamentos = new HashSet<>();

    public void modificarDepartamento(int id, String nombre, String localizacion) throws Exception {

        Departamento departamentoInicial = null; // Declaramos un objeto de la clase departamento y lo inicializamos a
                                                 // null para almacenar mas tarde el departamento a modificar
        Departamento departamentoRemplazo = new Departamento(id, nombre, localizacion); // Declaramos otro objeto
                                                                                        // departamento en el que
                                                                                        // almacenamos los datos
                                                                                        // actualizados

        // creamos un bucle foreach para buscar el departamento que queremos modificar
        for (Departamento dep : departamentos) {
            // si el id del departamento coincide con el id pasado por parámetro, se volcará
            // dicho departamento
            // en el primer objeto declarado para eliminarlo de la colección
            if (dep.getId() == id) {
                departamentoInicial = dep;
            }
        }
        // Si se ha encontrado el departamento, se elimina y se añade el departamento
        // con los atributos modificados, si no, mostrará una excepción avisando del
        // error
        if (departamentoInicial != null) {
            departamentos.remove(departamentoInicial);
            departamentos.add(departamentoRemplazo);
        } else {
            throw new Exception("No se ha podido modificar el departamento");
        }

    }

    public void crearDepartamento(String nombre, String localizacion) throws Exception {
        int id = departamentos.size() + 1; // generamos el id para el nuevo departamento a partir del tamaño de la
                                           // colección
        Departamento departamento = new Departamento(id, nombre, localizacion); // Creamos el nuevo objeto departamento
                                                                                // que se va añadir a la coleccion
        Boolean agregado = departamentos.add(departamento); // Añadimos a nuestro HashSet el nuevo departamento

        // Si no se ha agregado correctamente el nuevo departamento se mostrará una
        // excepción informando del fallo
        if (!agregado) {
            throw new Exception("No se ha podido agregar el departamento");
        }
    }

    public void borrarDepartamento(int id) throws Exception {
        // Mediante un foreach, recorremos la colección buscando el departamento a
        // borrar, si es encontrado se elimina, si no se lanzará una excepción
        for (Departamento dep : departamentos) {
            if (dep.getId() == id) {

                Boolean eliminado = departamentos.remove(dep);
                if (!eliminado) {
                    throw new Exception("No se ha podido realizar el borrado");
                }
            }
        }
    }

    public Departamento buscarDepartamentoPorId(int id) {

        // Recorremos la colección buscando el departamento por el id pasado por
        // parámetro, si lo encontamos, se retornará el departamento, sino se retornará
        // null
        for (Departamento dep : departamentos) {

            if (dep.getId() == id) {
                return dep;
            }
        }

        return null;

    }

    public Departamento buscarDepartamentoPorNombre(String nombre) {

        // Recorremos la colección buscando el departamento por el nombre pasado por
        // parámetro, si lo encontamos, se retornará el departamento, sino se retornará
        // null
        for (Departamento dep : departamentos) {
            if (dep.getNombre().equals(nombre)) {
                return dep;
            }
        }

        return null;

    }

    public Departamento buscarDepartamentoPorLocalizacion(String localizacion) {

        // Recorremos la colección buscando el departamento por la localización pasada
        // por parámetro, si lo encontamos, se retornará el departamento, sino se
        // retornará null
        for (Departamento dep : departamentos) {
            if (dep.getLocalizacion().equals(localizacion)) {
                return dep;
            }

        }

        return null;
    }

}
