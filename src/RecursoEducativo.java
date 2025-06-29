public class RecursoEducativo {
    private int id;
    private String titulo;
    private String tipo; // PDF, Video, etc.
    private String materia;
    private String nivel;
    private String contenido;

    public RecursoEducativo(int id, String titulo, String tipo, String materia, String nivel, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.materia = materia;
        this.nivel = nivel;
        this.contenido = contenido;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMateria() {
        return materia;
    }

    public String getNivel() {
        return nivel;
    }

    public String getContenido() {
        return contenido;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return id + ". " + titulo + " (" + tipo + ") [" + materia + " - " + nivel + "]";
    }
}
