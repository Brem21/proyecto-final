import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private GestionApoyos sistema;
    private JPanel panelCentral;

    private JTextField txtNombre, txtExp, txtMateria;
    private JComboBox<String> cbHoras;
    private JComboBox<Region> cbRegion;

    public MainFrame(GestionApoyos sistema) {
        this.sistema = sistema;
        setTitle("Gestión de Tutorías - Proyecto Final");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Panel Menú ---
        JPanel panelMenu = new JPanel(new GridLayout(11, 1, 10, 10)); // Ahora 11 filas

        JButton btnEstudiante = new JButton("Estudiante");
        JButton btnAgregarProfesor = new JButton("Agregar Profesor");
        JButton btnCrearTutoria = new JButton("Crear Tutoría");
        JButton btnSolicitudes = new JButton("Solicitudes");
        JButton btnProfesoresReg = new JButton("Profesores Registrados");
        JButton btnHistorialTutorias = new JButton("Historial de Tutorías");
        JButton btnMostrarRecursos = new JButton("Mostrar Recursos");
        JButton btnAgregarRecurso = new JButton("Agregar Recurso Académico");
        JButton btnEliminarEstudiante = new JButton("Eliminar Estudiante");
        JButton btnEliminarProfesor = new JButton("Eliminar Profesor");

        panelMenu.add(btnEstudiante);
        panelMenu.add(btnAgregarProfesor);
        panelMenu.add(btnCrearTutoria);
        panelMenu.add(btnSolicitudes);
        panelMenu.add(btnProfesoresReg);
        panelMenu.add(btnHistorialTutorias);
        panelMenu.add(btnMostrarRecursos); // <-- Nuevo botón
        panelMenu.add(btnAgregarRecurso);
        panelMenu.add(btnEliminarEstudiante);
        panelMenu.add(btnEliminarProfesor);

        add(panelMenu, BorderLayout.WEST);

        panelCentral = new JPanel();
        panelCentral.add(new JLabel("Selecciona una opción del menú."));
        add(panelCentral, BorderLayout.CENTER);

        // --- Listeners ---
        btnEstudiante.addActionListener(e -> mostrarPanelAgregarEstudiante());
        btnAgregarProfesor.addActionListener(e -> mostrarPanelAgregarProfesor());
        btnCrearTutoria.addActionListener(e -> mostrarPanelCrearTutoria());
        btnSolicitudes.addActionListener(e -> mostrarSolicitudes());
        btnProfesoresReg.addActionListener(e -> mostrarProfesoresRegistrados());
        btnHistorialTutorias.addActionListener(e -> mostrarHistorialTutorias());
        btnMostrarRecursos.addActionListener(e -> mostrarPanelMostrarRecursos()); // Nuevo
        btnAgregarRecurso.addActionListener(e -> mostrarPanelAgregarRecurso());
        btnEliminarEstudiante.addActionListener(e -> mostrarPanelEliminarEstudiante());
        btnEliminarProfesor.addActionListener(e -> mostrarPanelEliminarProfesor());
    }

    private void mostrarPanelAgregarEstudiante() {
        panelCentral.removeAll();
        panelCentral.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(15);

        JLabel lblEdad = new JLabel("Edad:");
        txtExp = new JTextField(5);

        JLabel lblMateria = new JLabel("Materia:");
        txtMateria = new JTextField(10);

        JLabel lblHoras = new JLabel("Horas:");
        cbHoras = new JComboBox<>(new String[] {"1 hora", "2 horas", "3 horas"});

        JLabel lblRegion = new JLabel("Región:");
        cbRegion = new JComboBox<>(Region.REGIONES);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        gbc.gridx = 0; gbc.gridy = 0; panelCentral.add(lblNombre, gbc);
        gbc.gridx = 1; panelCentral.add(txtNombre, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(lblEdad, gbc);
        gbc.gridx = 1; panelCentral.add(txtExp, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(lblMateria, gbc);
        gbc.gridx = 1; panelCentral.add(txtMateria, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(lblHoras, gbc);
        gbc.gridx = 1; panelCentral.add(cbHoras, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(lblRegion, gbc);
        gbc.gridx = 1; panelCentral.add(cbRegion, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(btnGuardar, gbc);
        gbc.gridx = 1; panelCentral.add(btnCancelar, gbc);

        btnGuardar.addActionListener(a -> {
            try {
                String nombre = txtNombre.getText().trim();
                int edad = Integer.parseInt(txtExp.getText().trim());
                String materia = txtMateria.getText().trim();
                String horas = (String) cbHoras.getSelectedItem();
                String region = ((Region) cbRegion.getSelectedItem()).getNombre();

                Estudiante est = new Estudiante(nombre, edad, materia, horas, region);
                sistema.agregarEstudiante(est);

                JOptionPane.showMessageDialog(this, "Estudiante agregado correctamente.");
                panelCentral.removeAll();
                panelCentral.add(new JLabel("Estudiante registrado."));
                panelCentral.revalidate();
                panelCentral.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos: " + ex.getMessage());
            }
        });

        btnCancelar.addActionListener(a -> {
            panelCentral.removeAll();
            panelCentral.add(new JLabel("Operación cancelada."));
            panelCentral.revalidate();
            panelCentral.repaint();
        });

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void mostrarPanelAgregarProfesor() {
        panelCentral.removeAll();
        panelCentral.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(15);

        JLabel lblExp = new JLabel("Experiencia:");
        txtExp = new JTextField(10);

        JLabel lblMateria = new JLabel("Materia:");
        txtMateria = new JTextField(10);

        JLabel lblHoras = new JLabel("Horas:");
        cbHoras = new JComboBox<>(new String[] {"1 hora", "2 horas", "3 horas"});

        JLabel lblRegion = new JLabel("Región:");
        cbRegion = new JComboBox<>(Region.REGIONES);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        gbc.gridx = 0; gbc.gridy = 0; panelCentral.add(lblNombre, gbc);
        gbc.gridx = 1; panelCentral.add(txtNombre, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(lblExp, gbc);
        gbc.gridx = 1; panelCentral.add(txtExp, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(lblMateria, gbc);
        gbc.gridx = 1; panelCentral.add(txtMateria, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(lblHoras, gbc);
        gbc.gridx = 1; panelCentral.add(cbHoras, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(lblRegion, gbc);
        gbc.gridx = 1; panelCentral.add(cbRegion, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(btnGuardar, gbc);
        gbc.gridx = 1; panelCentral.add(btnCancelar, gbc);

        btnGuardar.addActionListener(a -> {
            try {
                String nombre = txtNombre.getText().trim();
                String experiencia = txtExp.getText().trim();
                String materia = txtMateria.getText().trim();
                String horas = (String) cbHoras.getSelectedItem();
                String region = ((Region) cbRegion.getSelectedItem()).getNombre();

                Profesor prof = new Profesor(nombre, experiencia, materia, horas, region);
                sistema.agregarProfesor(prof);

                JOptionPane.showMessageDialog(this, "Profesor agregado correctamente.");
                panelCentral.removeAll();
                panelCentral.add(new JLabel("Profesor registrado."));
                panelCentral.revalidate();
                panelCentral.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos: " + ex.getMessage());
            }
        });

        btnCancelar.addActionListener(a -> {
            panelCentral.removeAll();
            panelCentral.add(new JLabel("Operación cancelada."));
            panelCentral.revalidate();
            panelCentral.repaint();
        });

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void mostrarPanelCrearTutoria() {
        panelCentral.removeAll();
        panelCentral.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblEstudiante = new JLabel("Código Estudiante:");
        JTextField txtCodEst = new JTextField(10);

        JLabel lblProfesor = new JLabel("Código Profesor:");
        JTextField txtCodProf = new JTextField(10);

        JButton btnAsignar = new JButton("Asignar Tutoría");
        JButton btnCancelar = new JButton("Cancelar");

        gbc.gridx = 0; gbc.gridy = 0; panelCentral.add(lblEstudiante, gbc);
        gbc.gridx = 1; panelCentral.add(txtCodEst, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(lblProfesor, gbc);
        gbc.gridx = 1; panelCentral.add(txtCodProf, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(btnAsignar, gbc);
        gbc.gridx = 1; panelCentral.add(btnCancelar, gbc);

        btnAsignar.addActionListener(e -> {
            String codEst = txtCodEst.getText().trim();
            String codProf = txtCodProf.getText().trim();

            String resultado = sistema.crearTutoria(codEst, codProf);
            JOptionPane.showMessageDialog(this, resultado);

            if (resultado.equals("Tutoría asignada exitosamente.")) {
                txtCodEst.setText("");
                txtCodProf.setText("");
            }
        });

        btnCancelar.addActionListener(a -> {
            panelCentral.removeAll();
            panelCentral.add(new JLabel("Operación cancelada."));
            panelCentral.revalidate();
            panelCentral.repaint();
        });

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void mostrarSolicitudes() {
        panelCentral.removeAll();
        JTextArea area = new JTextArea(sistema.listarSolicitudes());
        area.setEditable(false);
        panelCentral.setLayout(new BorderLayout());
        panelCentral.add(new JScrollPane(area), BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void mostrarProfesoresRegistrados() {
        panelCentral.removeAll();
        JTextArea area = new JTextArea(sistema.listarProfesoresRegistrados());
        area.setEditable(false);
        panelCentral.setLayout(new BorderLayout());
        panelCentral.add(new JScrollPane(area), BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void mostrarHistorialTutorias() {
        panelCentral.removeAll();
        JTextArea area = new JTextArea(sistema.listarHistorialTutorias());
        area.setEditable(false);
        panelCentral.setLayout(new BorderLayout());
        panelCentral.add(new JScrollPane(area), BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // --- NUEVO MÉTODO ---
    private void mostrarPanelMostrarRecursos() {
        panelCentral.removeAll();
        JTextArea area = new JTextArea(sistema.listarRecursosAcademicos());
        area.setEditable(false);
        panelCentral.setLayout(new BorderLayout());
        panelCentral.add(new JScrollPane(area), BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void mostrarPanelAgregarRecurso() {
        panelCentral.removeAll();
        panelCentral.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField(15);

        JLabel lblDescripcion = new JLabel("Descripción:");
        JTextField txtDescripcion = new JTextField(20);

        JLabel lblEnlace = new JLabel("Enlace:");
        JTextField txtEnlace = new JTextField(20);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        gbc.gridx = 0; gbc.gridy = 0; panelCentral.add(lblTitulo, gbc);
        gbc.gridx = 1; panelCentral.add(txtTitulo, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(lblDescripcion, gbc);
        gbc.gridx = 1; panelCentral.add(txtDescripcion, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(lblEnlace, gbc);
        gbc.gridx = 1; panelCentral.add(txtEnlace, gbc);
        gbc.gridx = 0; gbc.gridy++; panelCentral.add(btnGuardar, gbc);
        gbc.gridx = 1; panelCentral.add(btnCancelar, gbc);

        btnGuardar.addActionListener(a -> {
            try {
                String titulo = txtTitulo.getText().trim();
                String descripcion = txtDescripcion.getText().trim();
                String enlace = txtEnlace.getText().trim();

                RecursoAcademico recurso = new RecursoAcademico(titulo, descripcion, enlace);
                sistema.agregarRecursoAcademico(recurso);

                JOptionPane.showMessageDialog(this, "Recurso agregado correctamente.");
                panelCentral.removeAll();
                panelCentral.add(new JLabel("Recurso agregado."));
                panelCentral.revalidate();
                panelCentral.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos: " + ex.getMessage());
            }
        });

        btnCancelar.addActionListener(a -> {
            panelCentral.removeAll();
            panelCentral.add(new JLabel("Operación cancelada."));
            panelCentral.revalidate();
            panelCentral.repaint();
        });

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void mostrarPanelEliminarEstudiante() {
        panelCentral.removeAll();
        panelCentral.setLayout(new FlowLayout());
        JTextField txtCodigo = new JTextField(10);
        JButton btnEliminar = new JButton("Eliminar");
        panelCentral.add(new JLabel("Código de estudiante:"));
        panelCentral.add(txtCodigo);
        panelCentral.add(btnEliminar);

        btnEliminar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            boolean ok = sistema.eliminarEstudiantePorCodigo(codigo);
            String msg = ok ? "Estudiante eliminado." : "Estudiante no encontrado.";
            JOptionPane.showMessageDialog(this, msg);
        });

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void mostrarPanelEliminarProfesor() {
        panelCentral.removeAll();
        panelCentral.setLayout(new FlowLayout());
        JTextField txtCodigo = new JTextField(10);
        JButton btnEliminar = new JButton("Eliminar");
        panelCentral.add(new JLabel("Código de profesor:"));
        panelCentral.add(txtCodigo);
        panelCentral.add(btnEliminar);

        btnEliminar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            boolean ok = sistema.eliminarProfesorPorCodigo(codigo);
            String msg = ok ? "Profesor eliminado." : "Profesor no encontrado.";
            JOptionPane.showMessageDialog(this, msg);
        });

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    public static void main(String[] args) {
        GestionApoyos sistema = new GestionApoyos();
        SwingUtilities.invokeLater(() -> new MainFrame(sistema).setVisible(true));
    }
}
