public class MaterialDidactico {
    private String titulo;
    private String descripcion;
    private String enlace; // o ruta del archivo

    public MaterialDidactico(String titulo, String descripcion, String enlace) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.enlace = enlace;
    }

    // Getters y toString...
    @Override
    public String toString() {
        return "Título: " + titulo + "\nDescripción: " + descripcion + "\nEnlace: " + enlace;
    }
}
