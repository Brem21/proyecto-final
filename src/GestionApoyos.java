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

    // Agrega estudiante a la cola
    public void agregarSolicitud(Estudiante e) {
        colaSolicitudes.offer(e);
    }

    // Agrega profesor a la lista
    public void agregarProfesor(Profesor p) {
        profesoresDisponibles.add(p);
    }

    // Asigna tutoría (FIFO: primer estudiante, primer profesor disponible)
    public Tutoria asignarTutor() {
        if (!colaSolicitudes.isEmpty() && !profesoresDisponibles.isEmpty()) {
            Estudiante e = colaSolicitudes.poll();
            Profesor p = profesoresDisponibles.get(0); // asigna el primero, puedes cambiar la lógica si quieres
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

    // Eliminar estudiante por código (de cola y del historial)
    public boolean eliminarEstudiantePorCodigo(String codigo) {
        boolean eliminado = false;
        colaSolicitudes.removeIf(e -> e.getCodigo().equals(codigo));
        eliminado = historialTutorias.removeIf(t -> t.getEstudiante().getCodigo().equals(codigo));
        return eliminado;
    }

    // Eliminar profesor por código (de lista y del historial)
    public boolean eliminarProfesorPorCodigo(String codigo) {
        boolean eliminado = false;
        profesoresDisponibles.removeIf(p -> p.getCodigo().equals(codigo));
        eliminado = historialTutorias.removeIf(t -> t.getProfesor().getCodigo().equals(codigo));
        return eliminado;
    }
}
