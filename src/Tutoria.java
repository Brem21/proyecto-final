import java.time.LocalDate;

public class Tutoria {
    private Estudiante estudiante;
    private Profesor profesor;
    private String fecha;

    public Tutoria(Estudiante e, Profesor p) {
        this.estudiante = e;
        this.profesor = p;
        this.fecha = LocalDate.now().toString();
    }

    public Estudiante getEstudiante() { return estudiante; }
    public Profesor getProfesor() { return profesor; }
    public String getFecha() { return fecha; }

    @Override
    public String toString() {
        return "Tutoría: " + estudiante + " con " + profesor + " en " + fecha;
    }
}
