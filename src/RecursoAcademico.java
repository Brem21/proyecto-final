public class RecursoAcademico {
    private String titulo;
    private String descripcion;
    private String enlace;

    public RecursoAcademico(String titulo, String descripcion, String enlace) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.enlace = enlace;
    }

    public String toString() {
        return "Título: " + titulo + ", Descripción: " + descripcion + ", Enlace: " + enlace;
    }

    // Getters y setters si los necesitas...
}
