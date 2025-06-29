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

    // Agregar estudiante a la cola
    public void agregarSolicitud(Estudiante e) {
        colaSolicitudes.offer(e);
    }

    // Agregar profesor a la lista
    public void agregarProfesor(Profesor p) {
        profesoresDisponibles.add(p);
    }

    // Asignar tutoría (FIFO: primer estudiante, primer profesor disponible)
    public Tutoria asignarTutor() {
        if (!colaSolicitudes.isEmpty() && !profesoresDisponibles.isEmpty()) {
            Estudiante e = colaSolicitudes.poll();
            Profesor p = profesoresDisponibles.get(0); // asigna el primero, puedes cambiar esto si quieres buscar por materia
            Tutoria t = new Tutoria(e, p);
            historialTutorias.add(t);
            return t;
        }
        return null;
    }

    // Obtener la cola de solicitudes de estudiantes
    public Queue<Estudiante> getColaSolicitudes() {
        return colaSolicitudes;
    }

    // Obtener la lista de profesores registrados
    public ArrayList<Profesor> getProfesoresDisponibles() {
        return profesoresDisponibles;
    }

    // Obtener el historial de tutorías realizadas
    public ArrayList<Tutoria> getHistorialTutorias() {
        return historialTutorias;
    }
}
