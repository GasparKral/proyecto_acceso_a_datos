package es.acceso_a_datos.models;

public class Departamento {

    private int id;
    private String nombre;
    private String poblacion;
    private String Localizacion;

    public Departamento() {
    }

    public Departamento(int id, String nombre, String poblacion, String localizacion) {
        this.id = id;
        this.nombre = nombre;
        this.poblacion = poblacion;
        Localizacion = localizacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getLocalizacion() {
        return Localizacion;
    }

    public void setLocalizacion(String localizacion) {
        Localizacion = localizacion;
    }

    @Override
    public String toString() {
        return "Departamento:\n" +
                "id: " + id +
                "\nnombre='" + nombre +
                "\npoblacion='" + poblacion+
                "\nLocalizacion='" + Localizacion;
    }
}
