import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class MainFrame extends JFrame {

    private GestionApoyos sistema;
    private JPanel panelCentral;

    public MainFrame() {
        sistema = new GestionApoyos();
        setTitle("Gestión de Tutorías - Proyecto Final");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 540);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Menú lateral
        JPanel panelMenu = new JPanel(new GridLayout(10, 1, 10, 10));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        JButton btnEstudiante = new JButton("Estudiante");
        JButton btnProfesor = new JButton("Profesor");
        JButton btnAsignarTutoria = new JButton("Asignar Tutoría");
        JButton btnSolicitudes = new JButton("Solicitudes");
        JButton btnProfesores = new JButton("Profesores Registrados");
        JButton btnEstudiantesReg = new JButton("Estudiantes Registrados");
        JButton btnHistorialTutorias = new JButton("Historial de Tutorías");
        JButton btnEliminarEstudiante = new JButton("Eliminar Estudiante");
        JButton btnEliminarProfesor = new JButton("Eliminar Profesor");

        panelMenu.add(btnEstudiante);
        panelMenu.add(btnProfesor);
        panelMenu.add(btnAsignarTutoria);
        panelMenu.add(btnSolicitudes);
        panelMenu.add(btnProfesores);
        panelMenu.add(btnEstudiantesReg);
        panelMenu.add(btnHistorialTutorias);
        panelMenu.add(btnEliminarEstudiante);
        panelMenu.add(btnEliminarProfesor);

        add(panelMenu, BorderLayout.WEST);

        // Panel central inicial
        panelCentral = new JPanel();
        panelCentral.add(new JLabel("Selecciona una opción del menú"));
        add(panelCentral, BorderLayout.CENTER);

        // Acciones de menú
        btnEstudiante.addActionListener(e -> mostrarPanelEstudiante());
        btnProfesor.addActionListener(e -> mostrarPanelProfesor());
        btnAsignarTutoria.addActionListener(e -> mostrarPanelAsignarTutoria());
        btnSolicitudes.addActionListener(e -> mostrarPanelSolicitudes());
        btnProfesores.addActionListener(e -> mostrarPanelProfesores());
        btnEstudiantesReg.addActionListener(e -> mostrarPanelEstudiantesRegistrados());
        btnHistorialTutorias.addActionListener(e -> mostrarPanelHistorialTutorias());
        btnEliminarEstudiante.addActionListener(e -> mostrarPanelEliminarEstudiante());
        btnEliminarProfesor.addActionListener(e -> mostrarPanelEliminarProfesor());

        setVisible(true);
    }

    // PANEL: Estudiante
    private void mostrarPanelEstudiante() {
        panelCentral.removeAll();
        panelCentral.setLayout(new GridLayout(2, 1, 10, 10));

        JPanel panelAgregar = new JPanel(new GridLayout(5, 2, 5, 5));
        panelAgregar.setBorder(BorderFactory.createTitledBorder("Agregar Estudiante"));
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblEdad = new JLabel("Edad:");
        JTextField txtEdad = new JTextField();
        JLabel lblMateria = new JLabel("Materia:");
        JTextField txtMateria = new JTextField();
        JLabel lblHoras = new JLabel("Horas:");
        String[] opcionesHoras = {"1 hora", "2 horas", "3 horas"};
        JComboBox<String> cmbHoras = new JComboBox<>(opcionesHoras);
        JButton btnGuardar = new JButton("Guardar");
        JLabel lblResultado = new JLabel();

        btnGuardar.addActionListener(e -> {
            try {
                Estudiante est = new Estudiante(
                        txtNombre.getText(),
                        Integer.parseInt(txtEdad.getText()),
                        txtMateria.getText(),
                        (String) cmbHoras.getSelectedItem()
                );
                sistema.agregarSolicitud(est);
                lblResultado.setText("Estudiante agregado (código: " + est.getCodigo() + ")");
                txtNombre.setText(""); txtEdad.setText(""); txtMateria.setText(""); cmbHoras.setSelectedIndex(0);
            } catch (Exception ex) {
                lblResultado.setText("Datos inválidos. Intenta de nuevo.");
            }
        });

        panelAgregar.add(lblNombre);   panelAgregar.add(txtNombre);
        panelAgregar.add(lblEdad);     panelAgregar.add(txtEdad);
        panelAgregar.add(lblMateria);  panelAgregar.add(txtMateria);
        panelAgregar.add(lblHoras);    panelAgregar.add(cmbHoras);
        panelAgregar.add(btnGuardar);  panelAgregar.add(lblResultado);

        JPanel panelUltima = new JPanel(new GridLayout(2, 2, 5, 5));
        panelUltima.setBorder(BorderFactory.createTitledBorder("Ver Última Tutoría Tomada"));

        JLabel lblCodigoEst = new JLabel("Código Estudiante:");
        JTextField txtCodigoEst = new JTextField();
        JButton btnBuscar = new JButton("Buscar");
        JTextArea areaUltima = new JTextArea(3, 30);
        areaUltima.setEditable(false);

        btnBuscar.addActionListener(e -> {
            String codigo = txtCodigoEst.getText().trim();
            Tutoria ultima = null;
            for (int i = sistema.getHistorialTutorias().size() - 1; i >= 0; i--) {
                Tutoria t = sistema.getHistorialTutorias().get(i);
                if (t.getEstudiante().getCodigo().equals(codigo)) {
                    ultima = t;
                    break;
                }
            }
            if (ultima != null) {
                areaUltima.setText("Estudiante: " + ultima.getEstudiante().getNombre() + " (" + ultima.getEstudiante().getCodigo() + ")\n" +
                        "Profesor: " + ultima.getProfesor().getNombre() + " (" + ultima.getProfesor().getCodigo() + ")\n" +
                        "Materia: " + ultima.getEstudiante().getMateria() + "\n" +
                        "Horas: " + ultima.getEstudiante().getHoras());
            } else {
                areaUltima.setText("No se encontró tutoría para ese código.");
            }
        });

        panelUltima.add(lblCodigoEst); panelUltima.add(txtCodigoEst);
        panelUltima.add(btnBuscar);    panelUltima.add(new JScrollPane(areaUltima));

        panelCentral.add(panelAgregar);
        panelCentral.add(panelUltima);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // PANEL: Profesor
    private void mostrarPanelProfesor() {
        panelCentral.removeAll();
        panelCentral.setLayout(new GridLayout(2, 1, 10, 10));

        JPanel panelAgregar = new JPanel(new GridLayout(5, 2, 5, 5));
        panelAgregar.setBorder(BorderFactory.createTitledBorder("Agregar Profesor"));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblExperiencia = new JLabel("Experiencia:");
        JTextField txtExperiencia = new JTextField();
        JLabel lblMateria = new JLabel("Materia a dar:");
        JTextField txtMateria = new JTextField();
        JLabel lblHoras = new JLabel("Horas:");
        String[] opcionesHoras = {"1 hora", "2 horas", "3 horas"};
        JComboBox<String> cmbHoras = new JComboBox<>(opcionesHoras);
        JButton btnGuardar = new JButton("Guardar");
        JLabel lblResultado = new JLabel();

        btnGuardar.addActionListener(e -> {
            Profesor prof = new Profesor(
                    txtNombre.getText(),
                    txtExperiencia.getText(),
                    txtMateria.getText(),
                    (String) cmbHoras.getSelectedItem()
            );
            sistema.agregarProfesor(prof);
            lblResultado.setText("Profesor registrado (código: " + prof.getCodigo() + ")");
            txtNombre.setText(""); txtExperiencia.setText(""); txtMateria.setText(""); cmbHoras.setSelectedIndex(0);
        });

        panelAgregar.add(lblNombre);       panelAgregar.add(txtNombre);
        panelAgregar.add(lblExperiencia);  panelAgregar.add(txtExperiencia);
        panelAgregar.add(lblMateria);      panelAgregar.add(txtMateria);
        panelAgregar.add(lblHoras);        panelAgregar.add(cmbHoras);
        panelAgregar.add(btnGuardar);      panelAgregar.add(lblResultado);

        JPanel panelHistorial = new JPanel(new GridLayout(2, 2, 5, 5));
        panelHistorial.setBorder(BorderFactory.createTitledBorder("Ver Historial Tutorías (por Código)"));

        JLabel lblCodigoProf = new JLabel("Código Profesor:");
        JTextField txtCodigoProf = new JTextField();
        JButton btnBuscar = new JButton("Buscar");
        JTextArea areaHistorial = new JTextArea(3, 30);
        areaHistorial.setEditable(false);

        btnBuscar.addActionListener(e -> {
            String codigo = txtCodigoProf.getText().trim();
            StringBuilder sb = new StringBuilder();
            for (Tutoria t : sistema.getHistorialTutorias()) {
                if (t.getProfesor().getCodigo().equals(codigo)) {
                    sb.append("Estudiante: ").append(t.getEstudiante().getNombre())
                            .append(" | Materia: ").append(t.getEstudiante().getMateria())
                            .append(" | Horas: ").append(t.getEstudiante().getHoras()).append("\n");
                }
            }
            if (sb.length() == 0) sb.append("No se encontró historial para ese código.");
            areaHistorial.setText(sb.toString());
        });

        panelHistorial.add(lblCodigoProf); panelHistorial.add(txtCodigoProf);
        panelHistorial.add(btnBuscar);     panelHistorial.add(new JScrollPane(areaHistorial));

        panelCentral.add(panelAgregar);
        panelCentral.add(panelHistorial);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // PANEL: Asignar tutoría
    private void mostrarPanelAsignarTutoria() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BorderLayout());
        Tutoria t = sistema.asignarTutor();
        JTextArea area = new JTextArea();
        area.setEditable(false);
        if (t != null) {
            area.setText("Tutoría asignada:\nEstudiante: " + t.getEstudiante().getNombre() +
                    " (" + t.getEstudiante().getCodigo() + ")\nProfesor: " + t.getProfesor().getNombre() +
                    " (" + t.getProfesor().getCodigo() + ")\nMateria: " + t.getEstudiante().getMateria() +
                    "\nHoras: " + t.getEstudiante().getHoras());
        } else {
            area.setText("No hay estudiantes o profesores disponibles para asignar.");
        }
        panelCentral.add(new JScrollPane(area), BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // PANEL: Solicitudes de estudiantes
    private void mostrarPanelSolicitudes() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BorderLayout());
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
        panelCentral.add(new JScrollPane(area), BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // PANEL: Profesores registrados
    private void mostrarPanelProfesores() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BorderLayout());
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
        panelCentral.add(new JScrollPane(area), BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // PANEL: Estudiantes registrados (únicos)
    private void mostrarPanelEstudiantesRegistrados() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BorderLayout());

        HashSet<String> codigosMostrados = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (Estudiante e : sistema.getColaSolicitudes()) {
            if (codigosMostrados.add(e.getCodigo())) {
                sb.append(e).append("\n");
            }
        }
        for (Tutoria t : sistema.getHistorialTutorias()) {
            Estudiante e = t.getEstudiante();
            if (codigosMostrados.add(e.getCodigo())) {
                sb.append(e).append("\n");
            }
        }

        if (sb.length() == 0) sb.append("No hay estudiantes registrados.");
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        panelCentral.add(new JScrollPane(area), BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // PANEL: Historial de tutorías
    private void mostrarPanelHistorialTutorias() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BorderLayout());
        StringBuilder sb = new StringBuilder();
        ArrayList<Tutoria> historial = sistema.getHistorialTutorias();
        if (historial.isEmpty()) {
            sb.append("No hay tutorías asignadas aún.");
        } else {
            int cont = 1;
            for (Tutoria t : historial) {
                sb.append("Tutoría #").append(cont++).append(":\n")
                        .append("Estudiante: ").append(t.getEstudiante().getNombre()).append(" (").append(t.getEstudiante().getCodigo()).append(")\n")
                        .append("Profesor: ").append(t.getProfesor().getNombre()).append(" (").append(t.getProfesor().getCodigo()).append(")\n")
                        .append("Materia: ").append(t.getEstudiante().getMateria()).append("\n")
                        .append("Horas: ").append(t.getEstudiante().getHoras()).append("\n")
                        .append("-----------------------------------------------------\n");
            }
        }
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        panelCentral.add(new JScrollPane(area), BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // PANEL: Eliminar Estudiante por código
    private void mostrarPanelEliminarEstudiante() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BorderLayout());

        JPanel form = new JPanel(new FlowLayout());
        JLabel lbl = new JLabel("Código del estudiante a eliminar:");
        JTextField txt = new JTextField(10);
        JButton btn = new JButton("Eliminar");
        JLabel resultado = new JLabel();

        btn.addActionListener(e -> {
            String codigo = txt.getText().trim();
            if (!codigo.isEmpty()) {
                boolean eliminado = sistema.eliminarEstudiantePorCodigo(codigo);
                resultado.setText(eliminado ? "Estudiante eliminado correctamente." : "No se encontró ese código o ya fue eliminado.");
            } else {
                resultado.setText("Ingrese un código válido.");
            }
        });

        form.add(lbl);
        form.add(txt);
        form.add(btn);

        panelCentral.add(form, BorderLayout.NORTH);
        panelCentral.add(resultado, BorderLayout.CENTER);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // PANEL: Eliminar Profesor por código
    private void mostrarPanelEliminarProfesor() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BorderLayout());

        JPanel form = new JPanel(new FlowLayout());
        JLabel lbl = new JLabel("Código del profesor a eliminar:");
        JTextField txt = new JTextField(10);
        JButton btn = new JButton("Eliminar");
        JLabel resultado = new JLabel();

        btn.addActionListener(e -> {
            String codigo = txt.getText().trim();
            if (!codigo.isEmpty()) {
                boolean eliminado = sistema.eliminarProfesorPorCodigo(codigo);
                resultado.setText(eliminado ? "Profesor eliminado correctamente." : "No se encontró ese código o ya fue eliminado.");
            } else {
                resultado.setText("Ingrese un código válido.");
            }
        });

        form.add(lbl);
        form.add(txt);
        form.add(btn);

        panelCentral.add(form, BorderLayout.NORTH);
        panelCentral.add(resultado, BorderLayout.CENTER);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
