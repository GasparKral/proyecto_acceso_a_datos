package es.acceso_a_datos.modelos;

import java.util.List;

/**
 * Representa un Departamento en la empresa, con sus correspondientes
 * campos y comportamientos.
 */
public class Departamento {

    // Identificador número del Departamento
    private int id;

    // Nombre del Departamento
    private String nombre;

    // Localización del Departamento
    private String localizacion;

    public Departamento() {
    }

    /**
     * Constructor que inicializa todos los campos.
     *
     * @param id           Identificador número del Departamento
     * @param nombre       Nombre del Departamento
     * @param localizacion Localización del Departamento
     */
    public Departamento(int id, String nombre, String localizacion) {
        this.id = id;
        this.nombre = nombre;
        this.localizacion = localizacion;
    }

    /**
     * Devuelve el identificador número del Departamento.
     *
     * @return Identificador número del Departamento
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador número del Departamento.
     *
     * @param id Identificador número del Departamento
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del Departamento.
     *
     * @return Nombre del Departamento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del Departamento.
     *
     * @param nombre Nombre del Departamento
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la localización del Departamento.
     *
     * @return localización del Departamento
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * Establece la localización del Departamento.
     *
     * @param localizacion localización del Departamento
     */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * Devuelve una lista que contiene todos los campos del Departamento
     * como cadenas de texto.
     *
     * @return Lista con todos los campos como cadenas de texto.
     */
    public List<String> getCamposComoStrings() {
        return List.of(
                String.valueOf(id),
                nombre,
                localizacion);
    }

    /**
     * Devuelve una representación en forma de cadena de texto del
     * objeto Departamento.
     *
     * @return Representación en forma de cadena de texto del objeto
     *         Departamento.
     */
    @Override
    public String toString() {
        return "Departamento:\n" +
                "id: " + id +
                "\nnombre='" + nombre +
                "\nLocalizacion='" + localizacion;
    }
}
