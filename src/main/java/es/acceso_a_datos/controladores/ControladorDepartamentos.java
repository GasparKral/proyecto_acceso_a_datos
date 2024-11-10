package es.acceso_a_datos.controladores;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import es.acceso_a_datos.modelos.Departamento;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;

public class ControladorDepartamentos {

    public HashSet<Departamento> departamentos = new HashSet<>();

    // Definición de un enum para los campos de búsqueda, que utiliza métodos de
    // referencia para obtener valores de campos de Departamento.
    public enum CampoBusqueda {
        ID(Departamento::getId), NOMBRE(Departamento::getNombre), LOCALIZACION(Departamento::getLocalizacion);

        private final Function<Departamento, ?> getMethod;

        // Constructor del enum que asigna la función de obtención de valor a cada
        // campo.
        CampoBusqueda(Function<Departamento, ?> getMethod) {
            this.getMethod = getMethod;
        }

        // Método que aplica la función de obtención de valor al departamento dado.
        public Object getCampo(Departamento departamento) {
            return getMethod.apply(departamento);
        }
    }

    public SimpleMapProperty<CampoBusqueda, String> camposBuscados = null;

    // Método que inicializa los campos de búsqueda con valores nulos.
    public void cargarEnums() {
        this.camposBuscados = new SimpleMapProperty<>(FXCollections.observableHashMap());
        for (CampoBusqueda campo : CampoBusqueda.values()) {
            this.camposBuscados.put(campo, null);
        }
    }

    // Método para establecer el valor de un campo de búsqueda específico.
    public void setValorCampoBuscado(CampoBusqueda campo, String valor) {
        this.camposBuscados.put(campo, valor);
    }

    // Método para modificar un departamento existente, lanzando una excepción si no
    // se encuentra.
    public void modificarDepartamento(int id, String nombre, String localizacion) {
        Departamento departamentoInicial = null; // Almacena el departamento a modificar si se encuentra.
        Departamento departamentoRemplazo = new Departamento(id, nombre, localizacion); // Nuevo departamento con datos
                                                                                        // actualizados.

        // Bucle para buscar el departamento a modificar.
        for (Departamento dep : departamentos) {
            if (dep.getId() == id) {
                departamentoInicial = dep;
            }
        }

        if (departamentoInicial != null) {
            departamentos.remove(departamentoInicial);
            departamentos.add(departamentoRemplazo);
        }
    }

    // Método para crear un nuevo departamento, lanzando una excepción si falla.
    public void crearDepartamento(String nombre, String localizacion) throws Exception {
        int id = departamentos.size() + 1; // Genera un ID nuevo basado en el tamaño del conjunto actual.
        Departamento departamento = new Departamento(id, nombre, localizacion); // Crea un nuevo departamento.
        Boolean agregado = departamentos.add(departamento); // Añade el nuevo departamento al conjunto.

        // Si no se agrega correctamente, lanza una excepción.
        if (!agregado) {
            throw new Exception("No se ha podido agregar el departamento");
        }
    }

    // Método para buscar departamentos que coincidan con los campos de búsqueda
    // establecidos.
    public HashSet<Departamento> buscDepartamentos() {
        // Crea un mapa de los campos de búsqueda que tienen valores no nulos.
        HashMap<CampoBusqueda, String> campos = this.camposBuscados.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), HashMap::putAll);

        // Filtra y devuelve los departamentos que coinciden con los valores de
        // búsqueda.
        return new HashSet<Departamento>(this.departamentos.stream()
                .filter(departamento -> campos.entrySet().stream()
                        .allMatch(campo -> {
                            Object valorCampo = campo.getKey().getCampo(departamento);
                            return valorCampo != null && valorCampo.toString().toLowerCase()
                                    .contains(campo.getValue().toLowerCase());
                        }))
                .collect(Collectors.toSet()));
    }

    // Método para eliminar un departamento por su ID
    public void eliminarDepartamento(int id) throws Exception {
        Departamento departamentoAEliminar = departamentos.stream()
                .filter(dep -> dep.getId() == id)
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró el departamento con ID: " + id));

        if (!departamentos.remove(departamentoAEliminar)) {
            throw new Exception("No se pudo eliminar el departamento");
        }
    }

    /**
     * Comprueba si hay algún campo de búsqueda con un valor no nulo.
     * 
     * @return True si hay algún campo con valor no nulo, false en caso contrario.
     */
    public boolean algunCampoBuscado() {
        return this.camposBuscados.entrySet().stream()
                .anyMatch(entry -> entry.getValue() != null);
    }

    /**
     * Devuelve un conjunto de departamentos que coinciden con los campos
     * establecidos en la búsqueda.
     * Si no hay campos buscados se devuelve el conjunto de todos los
     * departamentos.
     * 
     * @return Un conjunto de departamentos que coinciden con los campos
     *         establecidos.
     */
    public HashSet<Departamento> listarDepartamentos() {

        if (algunCampoBuscado()) {
            return this.buscDepartamentos();
        }

        return this.departamentos;
    }
}
