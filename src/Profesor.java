public class Profesor {
    private static int contador = 1;
    private String nombre, experiencia, materia, horas, region, codigo;

    public Profesor(String nombre, String experiencia, String materia, String horas, String region) {
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.materia = materia;
        this.horas = horas;
        this.region = region;
        this.codigo = String.format("PROF-%03d", contador++);
    }

    public String getCodigo() { return codigo; }

    public String toString() {
        return codigo + " - " + nombre + " (" + materia + ", región: " + region + ")";
    }
}
