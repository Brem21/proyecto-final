import java.util.ArrayList;
import java.util.List;

public class ModuloRecursos {
    private List<RecursoEducativo> recursos;
    private List<RecursoEducativo> eliminados;

    public ModuloRecursos() {
        recursos = new ArrayList<>();
        eliminados = new ArrayList<>();
    }

    // Crear recurso
    public void crearRecurso(String titulo, String tipo, String materia, String nivel, String contenido) {
        int idNuevo = recursos.size() + 1;
        RecursoEducativo recurso = new RecursoEducativo(idNuevo, titulo, tipo, materia, nivel, contenido);
        recursos.add(recurso);
        System.out.println("Recurso creado: " + recurso);
    }

    // Leer recursos (todos o por filtro)
    public void leerRecursos(String materia, String nivel) {
        System.out.println("Lista de recursos:");
        for (RecursoEducativo r : recursos) {
            if ((materia == null || r.getMateria().equalsIgnoreCase(materia)) &&
                    (nivel == null || r.getNivel().equalsIgnoreCase(nivel))) {
                System.out.println(r);
            }
        }
    }

    // Actualizar recurso
    public void actualizarRecurso(int id, String nuevoTitulo, String nuevoTipo, String nuevaMateria, String nuevoNivel, String nuevoContenido) {
        for (RecursoEducativo r : recursos) {
            if (r.getId() == id) {
                r.setTitulo(nuevoTitulo);
                r.setTipo(nuevoTipo);
                r.setMateria(nuevaMateria);
                r.setNivel(nuevoNivel);
                r.setContenido(nuevoContenido);
                System.out.println("Recurso actualizado: " + r);
                return;
            }
        }
        System.out.println("Recurso no encontrado");
    }

    // Eliminar recurso
    public void eliminarRecurso(int id) {
        for (RecursoEducativo r : recursos) {
            if (r.getId() == id) {
                recursos.remove(r);
                eliminados.add(r);
                System.out.println("Recurso eliminado: " + r);
                return;
            }
        }
        System.out.println("Recurso no encontrado");
    }

    // Consultar recursos eliminados
    public void mostrarEliminados() {
        System.out.println("Recursos eliminados:");
        for (RecursoEducativo r : eliminados) {
            System.out.println(r);
        }
    }

    // Devuelve lista (para conectar a la GUI después)
    public List<RecursoEducativo> getRecursos() {
        return recursos;
    }
}
