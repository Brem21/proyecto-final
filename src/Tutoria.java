public class Tutoria {
    private Estudiante estudiante;
    private Profesor profesor;

    public Tutoria(Estudiante estudiante, Profesor profesor) {
        this.estudiante = estudiante;
        this.profesor = profesor;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    @Override
    public String toString() {
        return "Estudiante: " + estudiante.getNombre() + " | Profesor: " + profesor.getNombre();
    }
}
