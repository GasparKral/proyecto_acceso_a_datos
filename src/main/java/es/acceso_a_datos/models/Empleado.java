package es.acceso_a_datos.models;

import java.util.Date;

public class Empleado {

    private int id;
    private String Nombre;
    private String Apellido;
    private int director;
    private double salario;
    private String oficio;
    private Date fecha_alta;
    private int comision;
    private Departamento departamento;

    public Empleado() {
    }

    public Empleado(int id, String nombre, String apellido, int director, double salario, String oficio, Date fecha_alta, int comision, Departamento departamento) {
        this.id = id;
        Nombre = nombre;
        Apellido = apellido;
        this.director = director;
        this.salario = salario;
        this.oficio = oficio;
        this.fecha_alta = fecha_alta;
        this.comision = comision;
        this.departamento = departamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getDirector() {
        return director;
    }

    public void setDirector(int director) {
        this.director = director;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Empleado:\n" +
                "id: " + id +
                "\nNombre: " + Nombre  +
                "\nApellido: " + Apellido  +
                "\ndirector: " + director +
                "\nsalario: " + salario +
                "\noficio: " + oficio  +
                "\nfecha_alta: " + fecha_alta +
                "\ncomision: " + comision +
                "\ndepartamento: " + departamento;
    }
}
