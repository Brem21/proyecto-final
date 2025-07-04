import java.util.*;

public class GestionApoyos {
    private Queue<Estudiante> colaSolicitudes = new LinkedList<>();
    private List<Profesor> profesoresDisponibles = new ArrayList<>();
    private List<Tutoria> historialTutorias = new ArrayList<>();

    public void agregarSolicitud(Estudiante e) {
        colaSolicitudes.add(e);
    }

    public Queue<Estudiante> getColaSolicitudes() {
        return colaSolicitudes;
    }

    public void agregarProfesor(Profesor p) {
        profesoresDisponibles.add(p);
    }

    public List<Profesor> getProfesoresDisponibles() {
        return profesoresDisponibles;
    }

    public void asignarTutor(Estudiante e, Profesor p) throws AsignacionTutoriaException {
        if (!e.getRegion().equalsIgnoreCase(p.getRegion())) {
            throw new AsignacionTutoriaException("El profesor no pertenece a la misma región que el estudiante.");
        }

        boolean ocupado = historialTutorias.stream()
                .anyMatch(t -> t.getProfesor().getCodigo().equalsIgnoreCase(p.getCodigo()));

        if (ocupado) {
            throw new AsignacionTutoriaException("El profesor ya tiene una tutoría asignada.");
        }

        historialTutorias.add(new Tutoria(e, p, e.getMateria()));
        colaSolicitudes.remove(e);
    }

    public List<Tutoria> getHistorialTutorias() {
        return historialTutorias;
    }

    public boolean eliminarEstudiantePorCodigo(String codigo) {
        return colaSolicitudes.removeIf(e -> e.getCodigo().equalsIgnoreCase(codigo));
    }

    public boolean eliminarProfesorPorCodigo(String codigo) {
        return profesoresDisponibles.removeIf(p -> p.getCodigo().equalsIgnoreCase(codigo));
    }

    public Optional<Tutoria> getUltimaTutoriaEstudiante(String codigo) {
        for (int i = historialTutorias.size() - 1; i >= 0; i--) {
            if (historialTutorias.get(i).getEstudiante().getCodigo().equalsIgnoreCase(codigo))
                return Optional.of(historialTutorias.get(i));
        }
        return Optional.empty();
    }

    public Optional<Tutoria> getUltimaTutoriaProfesor(String codigo) {
        for (int i = historialTutorias.size() - 1; i >= 0; i--) {
            if (historialTutorias.get(i).getProfesor().getCodigo().equalsIgnoreCase(codigo))
                return Optional.of(historialTutorias.get(i));
        }
        return Optional.empty();
    }

    // ✅ Clase interna para lanzar excepciones de asignación
    public static class AsignacionTutoriaException extends Exception {
        public AsignacionTutoriaException(String mensaje) {
            super(mensaje);
        }
    }
}

