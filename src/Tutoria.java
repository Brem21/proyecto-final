public class Tutoria {
    private Estudiante estudiante;
    private Profesor profesor;
    private String fecha;

    public Tutoria(Estudiante e, Profesor p, String fecha) {
        this.estudiante = e;
        this.profesor = p;
        this.fecha = fecha;
    }

    public String toString() {
        return "Tutoria: " + estudiante + " con " + profesor + " en " + fecha;
    }
}
