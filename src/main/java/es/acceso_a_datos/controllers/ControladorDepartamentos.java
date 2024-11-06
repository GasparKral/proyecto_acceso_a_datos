package es.acceso_a_datos.controllers;
import java.io.IOException;
import java.util.HashSet;
import es.acceso_a_datos.models.Departamento;

public class ControladorDepartamentos {
    
    public HashSet<Departamento> departamentos = new HashSet<>();
    
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

    public void crearDepartamento(String nombre, String localizacion) throws Exception {
        int id = departamentos.size() + 1; // generamos el id para el nuevo departamento a partir del tamaño de la colección
        Departamento departamento = new Departamento(id, nombre, localizacion); // Creamos el nuevo objeto departamento que se va añadir a la coleccion
        Boolean agregado = departamentos.add(departamento); // Añadimos a nuestro HashSet el nuevo departamento
        
        if (!agregado) {
            throw new Exception("No se ha podido agregar el departamento");
        }
    }

    public void borrarDepartamento(int id) throws Exception{

        for (Departamento dep : departamentos) {
            if (dep.getId()==id) {
                
                Boolean eliminado = departamentos.remove(dep);
                if (!eliminado) {
                    throw new Exception("No se ha podido realizar el borrado");
                }
            }
        }
    }

    public Departamento buscarDepartamentoPorId(int id){

        for (Departamento dep : departamentos) {

            if (dep.getId()==id) {
                return dep;
            }
        }

        return null;

    }

    public Departamento buscarDepartamentoPorNombre(String nombre){

        for (Departamento dep : departamentos) {
            if(dep.getNombre().equals(nombre)){
                return dep;
            }
        }

        return null;

    }

    public Departamento buscarDepartamentoPorLocalizacion(String localizacion){

        for (Departamento dep : departamentos) {
            if (dep.getLocalizacion().equals(localizacion)) {
                return dep;
            }
            
        }

        return null;
    }

}
