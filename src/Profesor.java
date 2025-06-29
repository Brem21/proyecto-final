public class Profesor {
    private String nombre;
    private String experiencia;
    private String materia;
    private String horas;

    public Profesor(String nombre, String experiencia, String materia, String horas) {
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.materia = materia;
        this.horas = horas;
    }

    // Getters aquí...

    public String getNombre() { return nombre; }
    public String getExperiencia() { return experiencia; }
    public String getMateria() { return materia; }
    public String getHoras() { return horas; }

    public String toString() {
        return nombre + " (" + materia + ")";
    }
}
