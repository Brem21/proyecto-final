import java.util.*;
public class GestionApoyos {
    private Queue<Estudiante> colaSolicitudes;
    private ArrayList<Profesor> profesoresDisponibles;
    private ArrayList<Tutoria> historialTutorias;
    private ArrayList<RecursoAcademico> recursosAcademicos; // Agregado

    public GestionApoyos() {
        colaSolicitudes = new LinkedList<>();
        profesoresDisponibles = new ArrayList<>();
        historialTutorias = new ArrayList<>();
        recursosAcademicos = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiante e) {
        colaSolicitudes.offer(e);
    }

    public void agregarProfesor(Profesor p) {
        profesoresDisponibles.add(p);
    }

    public void agregarRecursoAcademico(RecursoAcademico r) {
        recursosAcademicos.add(r);
    }

    public String listarSolicitudes() {
        if (colaSolicitudes.isEmpty()) return "No hay solicitudes pendientes.";
        StringBuilder sb = new StringBuilder();
        for (Estudiante e : colaSolicitudes) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    public String listarProfesoresRegistrados() {
        if (profesoresDisponibles.isEmpty()) return "No hay profesores registrados.";
        StringBuilder sb = new StringBuilder();
        for (Profesor p : profesoresDisponibles) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }

    public String listarHistorialTutorias() {
        if (historialTutorias.isEmpty()) return "No hay tutorías en el historial.";
        StringBuilder sb = new StringBuilder();
        for (Tutoria t : historialTutorias) {
            sb.append(t.toString()).append("\n");
        }
        return sb.toString();
    }

    public String listarRecursosAcademicos() {
        if (recursosAcademicos.isEmpty()) return "No hay recursos académicos.";
        StringBuilder sb = new StringBuilder();
        for (RecursoAcademico r : recursosAcademicos) {
            sb.append(r.toString()).append("\n");
        }
        return sb.toString();
    }
    // ...
    public boolean eliminarEstudiantePorCodigo(String codigo) {
        return colaSolicitudes.removeIf(e -> e.getCodigo().equals(codigo));
    }

    public boolean eliminarProfesorPorCodigo(String codigo) {
        return profesoresDisponibles.removeIf(p -> p.getCodigo().equals(codigo));
    }

}
