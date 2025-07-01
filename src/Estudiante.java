public class Estudiante {
    private static int contador = 1;
    private String nombre;
    private int edad;
    private String materia;
    private String horas;
    private String region;
    private String codigo;

    public Estudiante(String nombre, int edad, String materia, String horas, String region) {
        this.nombre = nombre;
        this.edad = edad;
        this.materia = materia;
        this.horas = horas;
        this.region = region;
        this.codigo = String.format("EST-%03d", contador++);
    }

    public String getCodigo() { return codigo; }

    public String toString() {
        return codigo + " - " + nombre + " (" + materia + ", región: " + region + ")";
    }
}
