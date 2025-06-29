public class Estudiante {
    private String nombre;
    private String codigo;
    private int edad;
    private String materia;
    private String horas;

    public Estudiante(String nombre, String codigo, int edad, String materia, String horas) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.edad = edad;
        this.materia = materia;
        this.horas = horas;
    }

    // Getters aquí...

    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
    public int getEdad() { return edad; }
    public String getMateria() { return materia; }
    public String getHoras() { return horas; }

    public String toString() {
        return codigo + " - " + nombre + " (" + materia + ")";
    }
}
