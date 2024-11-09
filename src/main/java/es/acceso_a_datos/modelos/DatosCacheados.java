package es.acceso_a_datos.modelos;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Clase que representa los datos que se van a cacheear en memoria.
 * 
 * @author Gaspar Kral, Juan Colilla, David Rami?rez, Julian Martinez
 * @licencia Creative Commons Attribution-NonCommercial-ShareAlike 4.0
 *           International License
 * @desde 30/10/2024
 */
public class DatosCacheados implements Serializable {

    /**
     * Instancia unica de esta clase.
     */
    private static DatosCacheados instance;

    /**
     * Constructor privado que impide que se cree una instancia de esta clase desde
     * fuera.
     */
    private DatosCacheados() {
    }

    /**
     * Metodo que devuelve la instancia unica de esta clase.
     * 
     * @return la instancia unica de esta clase.
     */
    public static DatosCacheados getInstance() {
        if (instance == null) {
            instance = new DatosCacheados();
        }
        return instance;
    }

    /**
     * Mapa que almacena los datos cacheados.
     */
    private HashMap<String, Object> datosCacheados = new HashMap<String, Object>();

    /**
     * Metodo que devuelve un dato cacheado.
     * 
     * @param key la clave del dato que se quiere obtener.
     * @return el dato cacheado.
     */
    @SuppressWarnings("unchecked")
    public <T> T getDato(String key) {
        return (T) datosCacheados.get(key);
    }

    /**
     * Metodo que agrega un nuevo dato al mapa de datos cacheados.
     * 
     * @param key   la clave del dato.
     * @param value el valor del dato.
     */
    public void addDato(String key, Object value) {
        datosCacheados.put(key, value);
    }

    /**
     * Metodo que indica si el mapa contiene una clave determinada.
     * 
     * @param key la clave que se busca.
     * @return true si la clave existe, false en caso contrario.
     */
    public boolean containsKey(String key) {
        return datosCacheados.containsKey(key);
    }

    /**
     * Metodo que elimina un dato del mapa de datos cacheados.
     * 
     * @param key la clave del dato que se quiere eliminar.
     */
    public void removeDato(String key) {
        datosCacheados.remove(key);
    }

    /**
     * Metodo que devuelve el mapa de datos cacheados.
     * 
     * @return el mapa de datos cacheados.
     */
    public HashMap<String, Object> getDatosCacheados() {
        return datosCacheados;
    }

}
