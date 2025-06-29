public class Profesor {
<<<<<<< HEAD
    private static int contador = 1; // Agrega este campo
    private String nombre;
    private String codigo;
=======
    private String nombre;
>>>>>>> origin/main
    private String experiencia;
    private String materia;
    private String horas;

    public Profesor(String nombre, String experiencia, String materia, String horas) {
        this.nombre = nombre;
<<<<<<< HEAD
        this.codigo = String.format("PROF-%03d", contador++);
=======
>>>>>>> origin/main
        this.experiencia = experiencia;
        this.materia = materia;
        this.horas = horas;
    }

<<<<<<< HEAD
    public String getCodigo() { return codigo; }
=======
    // Getters aquí...

>>>>>>> origin/main
    public String getNombre() { return nombre; }
    public String getExperiencia() { return experiencia; }
    public String getMateria() { return materia; }
    public String getHoras() { return horas; }

    public String toString() {
<<<<<<< HEAD
        return codigo + " - " + nombre + " (" + materia + ")";
=======
        return nombre + " (" + materia + ")";
>>>>>>> origin/main
    }
}
