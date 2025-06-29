import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class GestionApoyos {
    private Queue<Estudiante> colaSolicitudes;
    private ArrayList<Profesor> profesoresDisponibles;
    private ArrayList<Tutoria> historialTutorias;

    public GestionApoyos() {
        colaSolicitudes = new LinkedList<>();
        profesoresDisponibles = new ArrayList<>();
        historialTutorias = new ArrayList<>();
    }

    public void agregarSolicitud(Estudiante e) {
        colaSolicitudes.offer(e);
    }

    public void agregarProfesor(Profesor p) {
        profesoresDisponibles.add(p);
    }

    public Tutoria asignarTutor() {
        if (!colaSolicitudes.isEmpty() && !profesoresDisponibles.isEmpty()) {
            Estudiante e = colaSolicitudes.poll();
            Profesor p = profesoresDisponibles.get(0); // Asigna siempre el primero (puedes mejorar para asignar según materia)
            Tutoria t = new Tutoria(e, p);
            historialTutorias.add(t);
            return t;
        }
        return null;
    }

    public Queue<Estudiante> getColaSolicitudes() {
        return colaSolicitudes;
    }

    public ArrayList<Tutoria> getHistorialTutorias() {
        return historialTutorias;
    }
    public ArrayList<Profesor> getProfesoresDisponibles() {
        return profesoresDisponibles;
    }

}
