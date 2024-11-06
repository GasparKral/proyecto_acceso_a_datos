package es.acceso_a_datos.models;

import java.util.Date;

public class Empleado {

    private int id;
    private String apellido;
    private Integer director;
    private double salario;
    private String oficio;
    private Date fecha_alta;
    private Double comision;
    private Integer departamento;

    public Empleado() {
    }

    public Empleado(int id, String apellido, double salario, String oficio, Date fecha_alta,
            Double comision, Integer departamento) {
        this.id = id;
        this.apellido = apellido;
        this.director = null;
        this.salario = salario;
        this.oficio = oficio;
        this.fecha_alta = fecha_alta;
        this.comision = comision;
        this.departamento = departamento;
    }

    public Empleado(int id, String apellido, int director, double salario, String oficio, Date fecha_alta,
            Double comision, Integer departamento) {
        this.id = id;
        this.apellido = apellido;
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

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public Integer getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Empleado:\n" +
                "id: " + id +
                "\nApellido: " + apellido +
                "\ndirector: " + director +
                "\nsalario: " + salario +
                "\noficio: " + oficio +
                "\nfecha_alta: " + fecha_alta +
                "\ncomision: " + comision +
                "\ndepartamento: " + departamento;
    }
}
