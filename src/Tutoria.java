public class Tutoria {
    private Estudiante estudiante;
    private Profesor profesor;
    private String materia;

    public Tutoria(Estudiante estudiante, Profesor profesor, String materia) {
        this.estudiante = estudiante;
        this.profesor = profesor;
        this.materia = materia;
    }

    public Estudiante getEstudiante() { return estudiante; }
    public Profesor getProfesor() { return profesor; }
    public String getMateria() { return materia; }
}
