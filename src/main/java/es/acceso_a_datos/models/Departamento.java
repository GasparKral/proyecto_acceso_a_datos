package es.acceso_a_datos.models;

public class Departamento {

    //Esta es la clase Departamento en la que declaramos tres variables, 2 constructores, los getter y los setter y un toString

    private int id;
    private String nombre;
    private String localizacion;

    public Departamento() {
    }

    public Departamento(int id, String nombre, String localizacion) {
        this.id = id;
        this.nombre = nombre;
        this.localizacion = localizacion;
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


    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    @Override
    public String toString() {
        return "Departamento:\n" +
                "id: " + id +
                "\nnombre='" + nombre +
                "\nLocalizacion='" + localizacion;
    }
}
