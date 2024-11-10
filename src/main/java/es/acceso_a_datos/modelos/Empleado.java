package es.acceso_a_datos.modelos;

import java.time.LocalDate;
import java.util.List;

public class Empleado {

    /**
     * Identificador del empleado.
     */
    private int id;
    /**
     * Apellido del empleado.
     */
    private String apellido;
    /**
     * Identificador del director del empleado, o null si no tiene.
     */
    private Integer director;
    /**
     * Salario del empleado.
     */
    private double salario;
    /**
     * Oficio del empleado.
     */
    private String oficio;
    /**
     * Fecha de alta del empleado.
     */
    private LocalDate fecha_alta;
    /**
     * comisión del empleado, o null si no tiene.
     */
    private Double comision;
    /**
     * Identificador del departamento del empleado.
     */
    private Integer departamento;

    /**
     * Constructor vacío.
     */
    public Empleado() {
    }

    /**
     * Constructor con todos los campos.
     * 
     * @param id           Identificador del empleado.
     * @param apellido     Apellido del empleado.
     * @param director     Identificador del director del empleado, o null si no
     *                     tiene.
     * @param salario      Salario del empleado.
     * @param oficio       Oficio del empleado.
     * @param fecha_alta   Fecha de alta del empleado.
     * @param comision     Comisión del empleado, o null si no tiene.
     * @param departamento Identificador del departamento del empleado.
     */
    public Empleado(int id, String apellido, double salario, String oficio, LocalDate fecha_alta,
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

    /**
     * Constructor con todos los campos, excepto el director.
     * 
     * @param id           Identificador del empleado.
     * @param apellido     Apellido del empleado.
     * @param salario      Salario del empleado.
     * @param oficio       Oficio del empleado.
     * @param fecha_alta   Fecha de alta del empleado.
     * @param comision     Comisión del empleado, o null si no tiene.
     * @param departamento Identificador del departamento del empleado.
     */
    public Empleado(int id, String apellido, Integer director, double salario, String oficio, LocalDate fecha_alta,
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

    /**
     * Getter del identificador.
     * 
     * @return Identificador del empleado.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del identificador.
     * 
     * @param id Identificador del empleado.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter del apellido.
     * 
     * @return Apellido del empleado.
     */
    public String getApellido() {
        return this.apellido;
    }

    /**
     * Setter del apellido.
     * 
     * @param apellido Apellido del empleado.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Getter del director.
     * 
     * @return Identificador del director del empleado, o null si no tiene.
     */
    public Integer getDirector() {
        return director;
    }

    /**
     * Setter del director.
     * 
     * @param director Identificador del director del empleado, o null si no
     *                 tiene.
     */
    public void setDirector(int director) {
        this.director = director;
    }

    /**
     * Getter del salario.
     * 
     * @return Salario del empleado.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Setter del salario.
     * 
     * @param salario Salario del empleado.
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Getter del oficio.
     * 
     * @return Oficio del empleado.
     */
    public String getOficio() {
        return oficio;
    }

    /**
     * Setter del oficio.
     * 
     * @param oficio Oficio del empleado.
     */
    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    /**
     * Getter de la fecha de alta.
     * 
     * @return Fecha de alta del empleado.
     */
    public LocalDate getFecha_alta() {
        return fecha_alta;
    }

    /**
     * Setter de la fecha de alta.
     * 
     * @param fecha_alta Fecha de alta del empleado.
     */
    public void setFecha_alta(LocalDate fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    /**
     * Getter de la comisión.
     * 
     * @return comisión del empleado, o null si no tiene.
     */
    public Double getComision() {
        return comision;
    }

    /**
     * Setter de la comisión.
     * 
     * @param comision comisión del empleado, o null si no tiene.
     */
    public void setComision(Double comision) {
        this.comision = comision;
    }

    /**
     * Getter del departamento.
     * 
     * @return Identificador del departamento del empleado.
     */
    public Integer getDepartamento() {
        return departamento;
    }

    /**
     * Setter del departamento.
     * 
     * @param departamento Identificador del departamento del empleado.
     */
    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    /**
     * Convierte los campos del empleado en un String.
     * 
     * @return Un arreglo de Strings con los campos del empleado.
     */
    public List<String> getCamposComoStrings() {
        return List.of(
                String.valueOf(id),
                apellido,
                director == null ? "-" : String.valueOf(director),
                String.format("%.2f€",
                        salario),
                oficio,
                String.valueOf(fecha_alta),
                comision == null ? "0.00€"
                        : String.format("%.2f€",
                                comision),
                String.valueOf(departamento));
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
