import javax.swing.*;
import java.awt.*;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
>>>>>>> origin/main

public class MainFrame extends JFrame {

    private GestionApoyos sistema;

    public MainFrame() {
        sistema = new GestionApoyos();
        setTitle("Gestión de Tutorías - Proyecto Final");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
<<<<<<< HEAD
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panelMenu = new JPanel(new GridLayout(6, 1, 10, 10));
=======
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panelMenu = new JPanel(new GridLayout(4, 1, 10, 10));
>>>>>>> origin/main
        panelMenu.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        JButton btnAgregarEstudiante = new JButton("Agregar Estudiante");
        JButton btnAgregarProfesor = new JButton("Agregar Profesor");
<<<<<<< HEAD
        JButton btnAsignarTutoria = new JButton("Asignar Tutoría");
        JButton btnHistorial = new JButton("Ver Historial de Tutorías");
        JButton btnVerSolicitudes = new JButton("Ver Solicitudes Estudiantes");
        JButton btnVerProfesores = new JButton("Ver Profesores Registrados");

        panelMenu.add(btnAgregarEstudiante);
        panelMenu.add(btnAgregarProfesor);
        panelMenu.add(btnAsignarTutoria);
        panelMenu.add(btnHistorial);
        panelMenu.add(btnVerSolicitudes);
        panelMenu.add(btnVerProfesores);
=======
        JButton btnAsignarTutoría = new JButton("Asignar Tutoría");
        JButton btnHistorial = new JButton("Ver Historial de Tutorías");

        panelMenu.add(btnAgregarEstudiante);
        panelMenu.add(btnAgregarProfesor);
        panelMenu.add(btnAsignarTutoría);
        panelMenu.add(btnHistorial);
>>>>>>> origin/main

        add(panelMenu);

        // Acciones de los botones
        btnAgregarEstudiante.addActionListener(e -> agregarEstudiante());
        btnAgregarProfesor.addActionListener(e -> agregarProfesor());
<<<<<<< HEAD
        btnAsignarTutoria.addActionListener(e -> asignarTutoria());
        btnHistorial.addActionListener(e -> verHistorial());
        btnVerSolicitudes.addActionListener(e -> verSolicitudesEstudiantes());
        btnVerProfesores.addActionListener(e -> verProfesoresRegistrados());
=======
        btnAsignarTutoría.addActionListener(e -> asignarTutoria());
        btnHistorial.addActionListener(e -> verHistorial());
>>>>>>> origin/main

        setVisible(true);
    }

<<<<<<< HEAD
    // Ventana para agregar estudiante (código automático)
    private void agregarEstudiante() {
        JTextField nombre = new JTextField();
        JTextField edad = new JTextField();
        JTextField materia = new JTextField();
        String[] opcionesHoras = { "1 hora", "2 horas", "3 horas" };
        JComboBox<String> horas = new JComboBox<>(opcionesHoras);

        Object[] message = {
                "Nombre:", nombre,
=======
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
>>>>>>> origin/main
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
<<<<<<< HEAD
                        Integer.parseInt(edad.getText()),
                        materia.getText(),
                        (String) horas.getSelectedItem()
                );
                sistema.agregarSolicitud(est);
                JOptionPane.showMessageDialog(this, "Estudiante agregado (código: " + est.getCodigo() + ")");
=======
                        codigo.getText(),
                        Integer.parseInt(edad.getText()),
                        materia.getText(),
                        horas.getText()
                );
                sistema.agregarSolicitud(est);
                JOptionPane.showMessageDialog(this, "Estudiante agregado a la cola correctamente.");
>>>>>>> origin/main
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos. Intenta de nuevo.");
            }
        }
    }

<<<<<<< HEAD
    // Ventana para agregar profesor (código automático)
=======
    // Ventana para agregar profesor
>>>>>>> origin/main
    private void agregarProfesor() {
        JTextField nombre = new JTextField();
        JTextField experiencia = new JTextField();
        JTextField materia = new JTextField();
<<<<<<< HEAD
        String[] opcionesHoras = { "1 hora", "2 horas", "3 horas" };
        JComboBox<String> horas = new JComboBox<>(opcionesHoras);
=======
        JTextField horas = new JTextField();
>>>>>>> origin/main

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
<<<<<<< HEAD
                    (String) horas.getSelectedItem()
            );
            sistema.agregarProfesor(prof);
            JOptionPane.showMessageDialog(this, "Profesor registrado (código: " + prof.getCodigo() + ")");
=======
                    horas.getText()
            );
            sistema.agregarProfesor(prof);
            JOptionPane.showMessageDialog(this, "Profesor registrado correctamente.");
>>>>>>> origin/main
        }
    }

    // Asignar tutoría automáticamente (FIFO)
    private void asignarTutoria() {
        Tutoria t = sistema.asignarTutor();
        if (t != null) {
            JOptionPane.showMessageDialog(this,
                    "Tutoría asignada:\nEstudiante: " + t.getEstudiante().getNombre() +
<<<<<<< HEAD
                            "\n(Código: " + t.getEstudiante().getCodigo() + ")" +
                            "\nProfesor: " + t.getProfesor().getNombre() +
                            "\n(Código: " + t.getProfesor().getCodigo() + ")" +
=======
                            "\nProfesor: " + t.getProfesor().getNombre() +
>>>>>>> origin/main
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
<<<<<<< HEAD
                        .append(" (").append(t.getEstudiante().getCodigo()).append(")")
                        .append(" | Profesor: ").append(t.getProfesor().getNombre())
                        .append(" (").append(t.getProfesor().getCodigo()).append(")")
=======
                        .append(" | Profesor: ").append(t.getProfesor().getNombre())
>>>>>>> origin/main
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

<<<<<<< HEAD
    // Ver solicitudes de estudiantes (cola FIFO)
    private void verSolicitudesEstudiantes() {
        StringBuilder sb = new StringBuilder();
        if (sistema.getColaSolicitudes().isEmpty()) {
            sb.append("No hay solicitudes pendientes.");
        } else {
            for (Estudiante e : sistema.getColaSolicitudes()) {
                sb.append(e).append("\n");
            }
        }
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 200));
        JOptionPane.showMessageDialog(this, scroll, "Solicitudes de Estudiantes", JOptionPane.INFORMATION_MESSAGE);
    }

    // Ver profesores registrados
    private void verProfesoresRegistrados() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Profesor> profesores = sistema.getProfesoresDisponibles();
        if (profesores.isEmpty()) {
            sb.append("No hay profesores registrados.");
        } else {
            for (Profesor p : profesores) {
                sb.append(p).append("\n");
            }
        }
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 200));
        JOptionPane.showMessageDialog(this, scroll, "Profesores Registrados", JOptionPane.INFORMATION_MESSAGE);
    }

=======
>>>>>>> origin/main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
