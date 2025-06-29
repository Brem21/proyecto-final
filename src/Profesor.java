// Profesor.java
public class Profesor {
    private static int contador = 1;
    private String nombre;
    private String codigo;
    private String experiencia;
    private String materia;
    private String horas;

    public Profesor(String nombre, String experiencia, String materia, String horas) {
        this.nombre = nombre;
        this.codigo = String.format("PROF-%03d", contador++);
        this.experiencia = experiencia;
        this.materia = materia;
        this.horas = horas;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getExperiencia() { return experiencia; }
    public String getMateria() { return materia; }
    public String getHoras() { return horas; }

    public String toString() {
        return codigo + " - " + nombre + " (" + materia + ")";
    }
}

