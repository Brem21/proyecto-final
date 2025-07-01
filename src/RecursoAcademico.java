public class RecursoAcademico {
    private String nombre;
    private String link;
    private String materia;

    public RecursoAcademico(String nombre, String link, String materia) {
        this.nombre = nombre;
        this.link = link;
        this.materia = materia;
    }

    public String getNombre() { return nombre; }
    public String getLink() { return link; }
    public String getMateria() { return materia; }
}
