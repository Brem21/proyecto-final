import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Optional;

public class MainFrame extends JFrame {
    private GestionApoyos sistema;
    private JPanel panelCentral;
    private java.util.List<RecursoAcademico> recursosAcademicos = new ArrayList<>();
    private static final Map<String, String> PREFIJOS_REGION;
    static {
        PREFIJOS_REGION = new HashMap<>();
        PREFIJOS_REGION.put("Costa", "09");
        PREFIJOS_REGION.put("Sierra", "01");
        PREFIJOS_REGION.put("Amazonía", "14");
        PREFIJOS_REGION.put("Galápagos", "20");
    }

    public MainFrame() {
        sistema = new GestionApoyos();
        setTitle("📚 Tutorías App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 770);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(new Color(230, 240, 255));

        mostrarBienvenida();

        add(panelCentral, BorderLayout.CENTER);
        add(crearMenuBotones(), BorderLayout.WEST);

        setVisible(true);
    }

    private void mostrarBienvenida() {
        panelCentral.removeAll();
        JLabel lbl = new JLabel(
                "<html><center><h1 style='color:#004A99;'>Bienvenido a TutoriasAPP ✨</h1></center></html>",
                SwingConstants.CENTER);
        panelCentral.add(lbl, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private JPanel crearMenuBotones() {
        Color azul = new Color(0, 123, 255);
        JPanel menu = new JPanel(new GridLayout(9, 1, 8, 8));
        menu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menu.setBackground(new Color(200, 220, 255));

        String[] labels = { "Estudiante", "Profesor", "Asignar", "Ver", "Otros", "Buscar Estudiante",
                "Buscar Profesor", "Recursos", "Ver Recursos" };
        JButton[] botones = new JButton[labels.length];

        for (int i = 0; i < labels.length; i++) {
            botones[i] = new JButton(labels[i]);
            botones[i].setBackground(azul);
            botones[i].setForeground(Color.WHITE);
            botones[i].setFont(new Font("Segoe UI", Font.BOLD, 13));
            botones[i].setFocusable(false);
            botones[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            menu.add(botones[i]);
        }

        botones[0].addActionListener(e -> panelEstudiante());
        botones[1].addActionListener(e -> panelProfesor());
        botones[2].addActionListener(e -> panelAsignarTutoria());
        botones[3].addActionListener(e -> panelVerOpciones());
        botones[4].addActionListener(e -> panelOtros());
        botones[5].addActionListener(e -> buscarUltimaTutoriaEstudiante());
        botones[6].addActionListener(e -> buscarUltimaTutoriaProfesor());
        botones[7].addActionListener(e -> panelAgregarRecurso());
        botones[8].addActionListener(e -> panelVerRecursos());

        return menu;
    }

    private JButton crearBotonVolver() {
        JButton btn = new JButton("🔙 Volver");
        btn.setBackground(new Color(180, 180, 180));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setFocusable(false);
        btn.addActionListener(e -> mostrarBienvenida());
        return btn;
    }

    private void panelEstudiante() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(9, 2, 6, 6));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 123, 255)),
                "Registro de Estudiante", 0, 0, new Font("Segoe UI", Font.BOLD, 14), Color.BLUE));

        JTextField n = new JTextField(), e = new JTextField(), m = new JTextField(), anio = new JTextField(), edadCampo = new JTextField();
        JComboBox<String> regionBox = new JComboBox<>(new String[] { "Costa", "Sierra", "Amazonía", "Galápagos" });
        JComboBox<String> h = new JComboBox<>(new String[] { "1 hora", "2 horas", "3 horas" });
        JLabel res = new JLabel();

        regionBox.addActionListener(a -> {
            String region = (String) regionBox.getSelectedItem();
            String prefijo = PREFIJOS_REGION.get(region);
            e.setText(prefijo + generarCedulaAleatoria(8));
        });

        JButton g = new JButton("Guardar");
        g.setBackground(new Color(0, 150, 100));
        g.setForeground(Color.WHITE);
        g.addActionListener(a -> {
            try {
                int edad = Integer.parseInt(edadCampo.getText());
                Estudiante est = new Estudiante(n.getText(), edad, m.getText(),
                        (String) h.getSelectedItem(), (String) regionBox.getSelectedItem(), e.getText(), anio.getText());
                sistema.agregarSolicitud(est);
                res.setText("✅ Agregado con código: " + est.getCodigo());
                n.setText("");
                e.setText("");
                m.setText("");
                h.setSelectedIndex(0);
                anio.setText("");
                edadCampo.setText("");
            } catch (Exception ex) {
                res.setText("❌ Error en los datos");
            }
        });

        form.add(new JLabel("Nombre:"));
        form.add(n);
        form.add(new JLabel("Cédula:"));
        form.add(e);
        form.add(new JLabel("Región:"));
        form.add(regionBox);
        form.add(new JLabel("Año:"));
        form.add(anio);
        form.add(new JLabel("Edad:"));
        form.add(edadCampo);
        form.add(new JLabel("Materia:"));
        form.add(m);
        form.add(new JLabel("Horas:"));
        form.add(h);
        form.add(g);
        form.add(res);

        panelCentral.add(form, BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void panelProfesor() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(7, 2, 6, 6));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(100, 0, 130)),
                "Registro de Profesor", 0, 0, new Font("Segoe UI", Font.BOLD, 14), new Color(100, 0, 130)));

        JTextField n = new JTextField(), ex = new JTextField(), ma = new JTextField(), ced = new JTextField();
        JComboBox<String> regionBox = new JComboBox<>(new String[] { "Costa", "Sierra", "Amazonía", "Galápagos" });
        JComboBox<String> h = new JComboBox<>(new String[] { "1 hora", "2 horas", "3 horas" });
        JLabel res = new JLabel();

        regionBox.addActionListener(a -> {
            String region = (String) regionBox.getSelectedItem();
            String prefijo = PREFIJOS_REGION.get(region);
            ced.setText(prefijo + generarCedulaAleatoria(8));
        });

        JButton g = new JButton("Guardar");
        g.setBackground(new Color(75, 0, 130));
        g.setForeground(Color.WHITE);
        g.addActionListener(a -> {
            Profesor p = new Profesor(n.getText(), ex.getText(), ma.getText(), (String) h.getSelectedItem(),
                    ced.getText(), (String) regionBox.getSelectedItem());
            sistema.agregarProfesor(p);
            res.setText("✅ Agregado con código: " + p.getCodigo());
            n.setText("");
            ex.setText("");
            ma.setText("");
            ced.setText("");
            h.setSelectedIndex(0);
        });

        form.add(new JLabel("Nombre:"));
        form.add(n);
        form.add(new JLabel("Cédula:"));
        form.add(ced);
        form.add(new JLabel("Región:"));
        form.add(regionBox);
        form.add(new JLabel("Experiencia:"));
        form.add(ex);
        form.add(new JLabel("Materia:"));
        form.add(ma);
        form.add(new JLabel("Horas:"));
        form.add(h);
        form.add(g);
        form.add(res);

        panelCentral.add(form, BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void panelAsignarTutoria() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4, 2, 6, 6));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder("Asignar Tutoría"));

        JComboBox<Estudiante> ce = new JComboBox<>();
        sistema.getColaSolicitudes().forEach(ce::addItem);

        JComboBox<Profesor> cp = new JComboBox<>();
        sistema.getProfesoresDisponibles().forEach(cp::addItem);

        JLabel res = new JLabel();

        JButton g = new JButton("Asignar");
        g.setBackground(new Color(255, 140, 0));
        g.setForeground(Color.WHITE);
        g.addActionListener(a -> {
            Estudiante est = (Estudiante) ce.getSelectedItem();
            Profesor prof = (Profesor) cp.getSelectedItem();
            if (est != null && prof != null) {
                if (est.getMateria().equalsIgnoreCase(prof.getMateria())) {
                    sistema.asignarTutor(est, prof);
                    res.setText("✅ Asignado " + est.getNombre() + " con " + prof.getNombre());
                } else {
                    res.setText("Error: materias no coinciden");
                }
            } else {
                res.setText("Selecciona ambos campos");
            }
        });

        form.add(new JLabel("Estudiante:"));
        form.add(ce);
        form.add(new JLabel("Profesor:"));
        form.add(cp);
        form.add(g);
        form.add(res);

        panelCentral.add(form, BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // PANEL DE SOLICITUDES (mejorado: sí se muestran las solicitudes actuales)
    private void panelSolicitudes() {
        panelCentral.removeAll();

        String[] columnas = { "Código", "Nombre", "Cédula", "Región", "Edad", "Año", "Materia", "Horas" };
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sistema.getColaSolicitudes().forEach(e -> model.addRow(
                new Object[] { e.getCodigo(), e.getNombre(), e.getCedula(), e.getRegion(), e.getEdad(), e.getAnio(), e.getMateria(), e.getHoras() }));

        JTable tabla = new JTable(model);

        panelCentral.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void panelVerOpciones() {
        String[] opciones = { "Solicitudes", "Profesores", "Estudiantes", "Historial" };
        String seleccion = (String) JOptionPane.showInputDialog(this, "Elige una sección:", "Visualizar",
                JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        if (seleccion != null) {
            switch (seleccion) {
                case "Solicitudes":
                    panelSolicitudes();
                    break;
                case "Profesores":
                    panelProfesores();
                    break;
                case "Estudiantes":
                    panelEstudiantesRegistrados();
                    break;
                case "Historial":
                    panelHistorial();
                    break;
            }
        }
    }

    private void panelProfesores() {
        panelCentral.removeAll();

        String[] columnas = { "Código", "Nombre", "Cédula", "Región", "Experiencia", "Materia", "Horas" };
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sistema.getProfesoresDisponibles().forEach(p -> model.addRow(
                new Object[] { p.getCodigo(), p.getNombre(), p.getCedula(), p.getRegion(), p.getExperiencia(), p.getMateria(), p.getHoras() }));

        JTable tabla = new JTable(model);

        panelCentral.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void panelEstudiantesRegistrados() {
        panelCentral.removeAll();

        String[] columnas = { "Código", "Nombre", "Cédula", "Región", "Edad", "Año", "Materia", "Horas" };
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Set<String> codigos = new HashSet<>();

        sistema.getColaSolicitudes().forEach(e -> {
            if (codigos.add(e.getCodigo())) {
                model.addRow(new Object[] { e.getCodigo(), e.getNombre(), e.getCedula(), e.getRegion(), e.getEdad(), e.getAnio(), e.getMateria(), e.getHoras() });
            }
        });

        sistema.getHistorialTutorias().forEach(t -> {
            var e = t.getEstudiante();
            if (codigos.add(e.getCodigo())) {
                model.addRow(new Object[] { e.getCodigo(), e.getNombre(), e.getCedula(), e.getRegion(), e.getEdad(), e.getAnio(), e.getMateria(), e.getHoras() });
            }
        });

        JTable tabla = new JTable(model);

        panelCentral.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void panelHistorial() {
        panelCentral.removeAll();

        DefaultListModel<String> modelo = new DefaultListModel<>();
        List<Tutoria> historial = sistema.getHistorialTutorias();

        if (historial.isEmpty()) {
            modelo.addElement("No hay tutorías registradas.");
        } else {
            for (Tutoria t : historial) {
                var e = t.getEstudiante();
                var p = t.getProfesor();
                modelo.addElement(String.format("Estudiante: %s (Cód: %s), Profesor: %s (Cód: %s), Materia: %s, Horas: %s",
                        e.getNombre(), e.getCodigo(), p.getNombre(), p.getCodigo(), e.getMateria(), e.getHoras()));
            }
        }

        JList<String> lista = new JList<>(modelo);
        lista.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(lista);

        panelCentral.add(new JLabel("Historial de Tutorías", SwingConstants.CENTER), BorderLayout.NORTH);
        panelCentral.add(scroll, BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void panelOtros() {
        String[] opciones = { "Eliminar Estudiante", "Eliminar Profesor" };
        String seleccion = (String) JOptionPane.showInputDialog(this, "¿Qué deseas hacer?", "Otras acciones",
                JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        if (seleccion != null) {
            if (seleccion.equals(opciones[0]))
                panelEliminarEstudiante();
            else
                panelEliminarProfesor();
        }
    }

    private void panelEliminarEstudiante() {
        panelCentral.removeAll();

        JPanel p = new JPanel(new FlowLayout());
        JTextField c = new JTextField(8);
        JButton b = new JButton("Eliminar");
        JLabel res = new JLabel();

        b.addActionListener(a -> res.setText(sistema.eliminarEstudiantePorCodigo(c.getText()) ? "✅ Eliminado"
                : "❌ No encontrado"));

        p.add(new JLabel("Código Est.:"));
        p.add(c);
        p.add(b);
        p.add(res);

        panelCentral.add(p, BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void panelEliminarProfesor() {
        panelCentral.removeAll();

        JPanel p = new JPanel(new FlowLayout());
        JTextField c = new JTextField(8);
        JButton b = new JButton("Eliminar");
        JLabel res = new JLabel();

        b.addActionListener(a -> res.setText(sistema.eliminarProfesorPorCodigo(c.getText()) ? "✅ Eliminado"
                : "❌ No encontrado"));

        p.add(new JLabel("Código Prof.:"));
        p.add(c);
        p.add(b);
        p.add(res);

        panelCentral.add(p, BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void buscarUltimaTutoriaEstudiante() {
        panelCentral.removeAll();

        JPanel panel = new JPanel(new FlowLayout());
        JTextField campo = new JTextField(10);
        JButton buscar = new JButton("Buscar");
        JLabel res = new JLabel();

        buscar.addActionListener(e -> {
            String cod = campo.getText();
            Optional<Tutoria> tutoria = sistema.getUltimaTutoriaEstudiante(cod);
            if (tutoria.isPresent()) {
                Tutoria t = tutoria.get();
                res.setText("Última tutoría: Estudiante " + t.getEstudiante().getNombre() + " con Profesor "
                        + t.getProfesor().getNombre() + " en materia " + t.getEstudiante().getMateria() + " (" + t.getEstudiante().getHoras() + ")");
            } else {
                res.setText("❌ No se encontró historial");
            }
        });

        panel.add(new JLabel("Código Estudiante:"));
        panel.add(campo);
        panel.add(buscar);
        panel.add(res);

        panelCentral.add(panel, BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void buscarUltimaTutoriaProfesor() {
        panelCentral.removeAll();

        JPanel panel = new JPanel(new FlowLayout());
        JTextField campo = new JTextField(10);
        JButton buscar = new JButton("Buscar");
        JLabel res = new JLabel();

        buscar.addActionListener(e -> {
            String cod = campo.getText();
            Optional<Tutoria> tutoria = sistema.getUltimaTutoriaProfesor(cod);
            if (tutoria.isPresent()) {
                Tutoria t = tutoria.get();
                res.setText("Última tutoría: Profesor " + t.getProfesor().getNombre() + " con Estudiante "
                        + t.getEstudiante().getNombre() + " en materia " + t.getEstudiante().getMateria() + " (" + t.getEstudiante().getHoras() + ")");
            } else {
                res.setText("❌ No se encontró historial");
            }
        });

        panel.add(new JLabel("Código Profesor:"));
        panel.add(campo);
        panel.add(buscar);
        panel.add(res);

        panelCentral.add(panel, BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // ==== PANEL PARA AGREGAR RECURSOS ACADÉMICOS ====
    private void panelAgregarRecurso() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(5, 2, 6, 6));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder("Agregar Recurso Académico"));

        JTextField nombre = new JTextField();
        JTextField link = new JTextField();
        JTextField materia = new JTextField();
        JLabel res = new JLabel();

        JButton guardar = new JButton("Agregar");
        guardar.setBackground(new Color(30, 135, 250));
        guardar.setForeground(Color.WHITE);
        guardar.addActionListener(a -> {
            if (!nombre.getText().isEmpty() && !link.getText().isEmpty() && !materia.getText().isEmpty()) {
                recursosAcademicos.add(new RecursoAcademico(nombre.getText(), link.getText(), materia.getText()));
                res.setText("✅ Recurso agregado");
                nombre.setText("");
                link.setText("");
                materia.setText("");
            } else {
                res.setText("❌ Llene todos los campos");
            }
        });

        form.add(new JLabel("Nombre:"));
        form.add(nombre);
        form.add(new JLabel("Link:"));
        form.add(link);
        form.add(new JLabel("Materia:"));
        form.add(materia);
        form.add(guardar);
        form.add(res);

        panelCentral.add(form, BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // ==== PANEL PARA VER RECURSOS ACADÉMICOS ====
    private void panelVerRecursos() {
        panelCentral.removeAll();

        String[] columnas = { "Nombre", "Link", "Materia" };
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        recursosAcademicos.forEach(r -> model.addRow(new Object[] { r.getNombre(), r.getLink(), r.getMateria() }));

        JTable tabla = new JTable(model);

        panelCentral.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panelCentral.add(crearBotonVolver(), BorderLayout.SOUTH);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private String generarCedulaAleatoria(int digitos) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digitos; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
