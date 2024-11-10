package es.acceso_a_datos.controladores;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import es.acceso_a_datos.modelos.Empleado;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;

/**
 * Clase que gestiona la lógica de negocio de los empleados.
 */
public class ControladorEmpleados {

    /**
     * Conjunto de empleados.
     */
    public HashSet<Empleado> empleados = new HashSet<>();

    /**
     * Enum que define los campos que se pueden buscar.
     */
    public enum CampoBusqueda {
        /**
         * Campo que se puede buscar por id.
         */
        ID(Empleado::getId),
        /**
         * Campo que se puede buscar por apellido.
         */
        APELLIDO(Empleado::getApellido),
        /**
         * Campo que se puede buscar por director.
         */
        DIRECTOR(Empleado::getDirector),
        /**
         * Campo que se puede buscar por salario.
         */
        SALARIO(Empleado::getSalario),
        /**
         * Campo que se puede buscar por oficio.
         */
        OFICIO(Empleado::getOficio),
        /**
         * Campo que se puede buscar por fecha de alta.
         */
        FECHA_ALTA(Empleado::getFecha_alta),
        /**
         * Campo que se puede buscar por comision.
         */
        COMISION(Empleado::getComision),
        /**
         * Campo que se puede buscar por departamento.
         */
        DEPARTAMENTO(Empleado::getDepartamento);

        /**
         * Función que obtiene el valor del campo de un empleado.
         */
        private final Function<Empleado, ?> getMethod;

        CampoBusqueda(Function<Empleado, ?> getMethod) {
            this.getMethod = getMethod;
        }

        /**
         * Devuelve el valor del campo de un empleado.
         * 
         * @param empleado El empleado del que se quiere obtener el valor del campo.
         * @return El valor del campo del empleado.
         */
        public Object getCampo(Empleado empleado) {
            return getMethod.apply(empleado);
        }
    }

    /**
     * Mapa que contiene los campos que se están buscando con sus valores.
     */
    public SimpleMapProperty<CampoBusqueda, String> camposBuscados = null;

    /**
     * Inicializa el mapa de campos buscados.
     */
    public void cargarEnums() {

        this.camposBuscados = new SimpleMapProperty<>(FXCollections.observableHashMap());
        for (CampoBusqueda campo : CampoBusqueda.values()) {
            this.camposBuscados.put(campo, null);
        }

    }

    /**
     * Establece el valor de un campo buscado.
     * 
     * @param campo El campo que se quiere establecer.
     * @param valor El valor que se quiere establecer.
     */
    public void setValorCampoBuscado(CampoBusqueda campo, String valor) {
        this.camposBuscados.put(campo, valor);
    }

    /**
     * Busca empleados que coinciden con los campos establecidos en la búsqueda.
     * 
     * @return Un conjunto de empleados que coinciden con los campos establecidos.
     */
    public HashSet<Empleado> buscarEmpleados() {
        // Creamos un mapa con los campos que se están buscando con sus valores
        HashMap<CampoBusqueda, String> campos = this.camposBuscados.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        HashMap::putAll);

        // Filtra los empleados por aquellos que contengan los parcialmente los valores
        // buscados
        return new HashSet<Empleado>(this.empleados.stream()
                .filter(empleado -> campos.entrySet().stream()
                        .allMatch(campo -> {
                            Object valorCampo = campo.getKey().getCampo(empleado);
                            return valorCampo != null && valorCampo.toString().toLowerCase()
                                    .contains(campo.getValue().toLowerCase());
                        }))
                .collect(Collectors.toSet()));
    }

    /**
     * Modifica un empleado existente.
     * 
     * @param idOriginal   El id del empleado que se quiere modificar.
     * @param apellido     El apellido del empleado.
     * @param director     El id del director del empleado.
     * @param salario      El salario del empleado.
     * @param oficio       El oficio del empleado.
     * @param fecha_alta   La fecha de alta del empleado.
     * @param comision     La comision del empleado.
     * @param departamento El id del departamento del empleado.
     */
    public void modificarEmpleado(int idOriginal, String apellido, int director, double salario, String oficio,
            LocalDate fecha_alta, Double comision, Integer departamento) {
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

    /**
     * Crea un nuevo empleado.
     * 
     * @param apellido     El apellido del empleado.
     * @param director     El id del director del empleado.
     * @param salario      El salario del empleado.
     * @param oficio       El oficio del empleado.
     * @param fecha_alta   La fecha de alta del empleado.
     * @param comision     La comision del empleado.
     * @param departamento El id del departamento del empleado.
     */
    public void crearEmpleado(String apellido, int director, double salario, String oficio, LocalDate fecha_alta,
            Double comision, Integer departamento) {
        int id = this.empleados.size() + 1; //
        Empleado empleado = new Empleado(id, apellido, director, salario, oficio, fecha_alta, comision, departamento);
        // Creamos el nuevo objeto empleado que se va añadir a la coleccion
        this.empleados.add(empleado); // Añadimos a nuestro HashSet el nuevo empleado
    }

    /**
     * Elimina un empleado existente.
     * 
     * @param id El id del empleado que se quiere eliminar.
     */
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

    /**
     * Comprueba si hay algún campo buscado.
     * 
     * @return True si hay algún campo buscado, false en caso contrario.
     */
    private boolean algunCampoBuscado() {
        return this.camposBuscados.entrySet().stream().anyMatch(entry -> entry.getValue() != null);
    }

    /**
     * Devuelve un conjunto de empleados que coinciden con los campos establecidos
     * en la búsqueda.
     * Si no hay campos buscados se devuelve el conjunto de todos los empleados.
     * 
     * @return Un conjunto de empleados que coinciden con los campos establecidos.
     */
    public HashSet<Empleado> listarEmpleados() {

        if (algunCampoBuscado()) {
            return this.buscarEmpleados();
        }

        return this.empleados;
    }

}
