import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private GestionApoyos sistema;

    public MainFrame() {
        sistema = new GestionApoyos();
        setTitle("Gestión de Tutorías - Proyecto Final");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panelMenu = new JPanel(new GridLayout(4, 1, 10, 10));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        JButton btnAgregarEstudiante = new JButton("Agregar Estudiante");
        JButton btnAgregarProfesor = new JButton("Agregar Profesor");
        JButton btnAsignarTutoría = new JButton("Asignar Tutoría");
        JButton btnHistorial = new JButton("Ver Historial de Tutorías");

        panelMenu.add(btnAgregarEstudiante);
        panelMenu.add(btnAgregarProfesor);
        panelMenu.add(btnAsignarTutoría);
        panelMenu.add(btnHistorial);

        add(panelMenu);

        // Acciones de los botones
        btnAgregarEstudiante.addActionListener(e -> agregarEstudiante());
        btnAgregarProfesor.addActionListener(e -> agregarProfesor());
        btnAsignarTutoría.addActionListener(e -> asignarTutoria());
        btnHistorial.addActionListener(e -> verHistorial());

        setVisible(true);
    }

    // Ventana para agregar estudiante
    private void agregarEstudiante() {
        JTextField nombre = new JTextField();
        JTextField codigo = new JTextField();
        JTextField edad = new JTextField();
        JTextField materia = new JTextField();
        JTextField horas = new JTextField();

        Object[] message = {
                "Nombre:", nombre,
                "Código:", codigo,
                "Edad:", edad,
                "Materia:", materia,
                "Horas:", horas
        };

        int option = JOptionPane.showConfirmDialog(
                this, message, "Agregar Estudiante", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                Estudiante est = new Estudiante(
                        nombre.getText(),
                        codigo.getText(),
                        Integer.parseInt(edad.getText()),
                        materia.getText(),
                        horas.getText()
                );
                sistema.agregarSolicitud(est);
                JOptionPane.showMessageDialog(this, "Estudiante agregado a la cola correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos. Intenta de nuevo.");
            }
        }
    }

    // Ventana para agregar profesor
    private void agregarProfesor() {
        JTextField nombre = new JTextField();
        JTextField experiencia = new JTextField();
        JTextField materia = new JTextField();
        JTextField horas = new JTextField();

        Object[] message = {
                "Nombre:", nombre,
                "Experiencia:", experiencia,
                "Materia a dar:", materia,
                "Horas:", horas
        };

        int option = JOptionPane.showConfirmDialog(
                this, message, "Agregar Profesor", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            Profesor prof = new Profesor(
                    nombre.getText(),
                    experiencia.getText(),
                    materia.getText(),
                    horas.getText()
            );
            sistema.agregarProfesor(prof);
            JOptionPane.showMessageDialog(this, "Profesor registrado correctamente.");
        }
    }

    // Asignar tutoría automáticamente (FIFO)
    private void asignarTutoria() {
        Tutoria t = sistema.asignarTutor();
        if (t != null) {
            JOptionPane.showMessageDialog(this,
                    "Tutoría asignada:\nEstudiante: " + t.getEstudiante().getNombre() +
                            "\nProfesor: " + t.getProfesor().getNombre() +
                            "\nMateria: " + t.getEstudiante().getMateria() +
                            "\nHoras: " + t.getEstudiante().getHoras());
        } else {
            JOptionPane.showMessageDialog(this,
                    "No hay estudiantes o profesores disponibles para asignar.");
        }
    }

    // Ver historial de tutorías
    private void verHistorial() {
        StringBuilder sb = new StringBuilder();
        if (sistema.getHistorialTutorias().isEmpty()) {
            sb.append("Sin tutorías asignadas aún.");
        } else {
            for (Tutoria t : sistema.getHistorialTutorias()) {
                sb.append("Estudiante: ").append(t.getEstudiante().getNombre())
                        .append(" | Profesor: ").append(t.getProfesor().getNombre())
                        .append(" | Materia: ").append(t.getEstudiante().getMateria())
                        .append(" | Horas: ").append(t.getEstudiante().getHoras())
                        .append("\n");
            }
        }
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 200));
        JOptionPane.showMessageDialog(this, scroll, "Historial de Tutorías", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
