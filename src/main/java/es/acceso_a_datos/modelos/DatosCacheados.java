package es.acceso_a_datos.modelos;

import java.io.Serializable;
import java.util.HashMap;

public class DatosCacheados implements Serializable {

    private static DatosCacheados instance;

    private DatosCacheados() {
    }

    public static DatosCacheados getInstance() {
        if (instance == null) {
            instance = new DatosCacheados();
        }
        return instance;
    }

    private HashMap<String, Object> datosCacheados = new HashMap<String, Object>();

    @SuppressWarnings("unchecked")
    public <T> T getDato(String key) {
        return (T) datosCacheados.get(key);
    }

    public void addDato(String key, Object value) {
        datosCacheados.put(key, value);
    }

    public boolean containsKey(String key) {
        return datosCacheados.containsKey(key);
    }

    public void removeDato(String key) {
        datosCacheados.remove(key);
    }

    public HashMap<String, Object> getDatosCacheados() {
        return datosCacheados;
    }

}
