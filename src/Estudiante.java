public class Estudiante {
<<<<<<< HEAD
    private static int contador = 1; // Agrega este campo
=======
>>>>>>> origin/main
    private String nombre;
    private String codigo;
    private int edad;
    private String materia;
    private String horas;

<<<<<<< HEAD
    public Estudiante(String nombre, int edad, String materia, String horas) {
        this.nombre = nombre;
        this.codigo = String.format("EST-%03d", contador++);
=======
    public Estudiante(String nombre, String codigo, int edad, String materia, String horas) {
        this.nombre = nombre;
        this.codigo = codigo;
>>>>>>> origin/main
        this.edad = edad;
        this.materia = materia;
        this.horas = horas;
    }

<<<<<<< HEAD
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
=======
    // Getters aquí...

    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
>>>>>>> origin/main
    public int getEdad() { return edad; }
    public String getMateria() { return materia; }
    public String getHoras() { return horas; }

    public String toString() {
        return codigo + " - " + nombre + " (" + materia + ")";
    }
}
