public class Profesor {
    private static int contador = 1;
    private String nombre;
    private String experiencia;
    private String materia;
    private String horas;
    private String region;
    private String codigo;
    private boolean ocupado = false;

    public Profesor(String nombre, String experiencia, String materia, String horas, String region) {
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.materia = materia;
        this.horas = horas;
        this.region = region;
        this.codigo = String.format("PROF-%03d", contador++);
    }

    public String getCodigo() { return codigo; }
    public String getRegion() { return region; }
    public boolean isOcupado() { return ocupado; }
    public void setOcupado(boolean ocupado) { this.ocupado = ocupado; }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + materia + ", región: " + region + ")" + (ocupado ? " [Ocupado]" : "");
    }
}
