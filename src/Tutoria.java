public class Tutoria {
    private Estudiante estudiante;
    private Profesor profesor;

    public Tutoria(Estudiante estudiante, Profesor profesor) {
        this.estudiante = estudiante;
        this.profesor = profesor;
    }

    public Estudiante getEstudiante() { return estudiante; }
    public Profesor getProfesor() { return profesor; }

    public String toString() {
        return estudiante.toString() + " ASIGNADO A " + profesor.toString();
    }
}