import java.util.LinkedList;
import java.util.Queue;

public class ColaEstudiantes {
    private Queue<String> colaEstudiantes;

    public ColaEstudiantes() {
        colaEstudiantes = new LinkedList<>();
    }

    public void agregarEstudiante(String nombre) {
        colaEstudiantes.offer(nombre);
        System.out.println("Estudiante agregado a la cola: " + nombre);
    }

    public String atenderEstudiante() {
        String estudiante = colaEstudiantes.poll();
        if (estudiante != null) {
            System.out.println("Atendiendo a: " + estudiante);
        } else {
            System.out.println("No hay estudiantes en la cola");
        }
        return estudiante;
    }

    public void mostrarCola() {
        System.out.println("Cola de estudiantes: " + colaEstudiantes);
    }
}