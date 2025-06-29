public class Estudiante {
    private static int contador = 1;
    private String nombre;
    private String codigo;
    private int edad;
    private String materia;
    private String horas;

    public Estudiante(String nombre, int edad, String materia, String horas) {
        this.nombre = nombre;
        this.codigo = String.format("EST-%03d", contador++);
        this.edad = edad;
        this.materia = materia;
        this.horas = horas;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getMateria() { return materia; }
    public String getHoras() { return horas; }

    public String toString() {
        return codigo + " - " + nombre + " (" + materia + ")";
    }
}
