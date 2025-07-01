public class Estudiante {
    private String nombre;
    private String cedula;
    private String region;
    private int edad;
    private String anio; // Año de estudio
    private String materia;
    private String horas;
    private String codigo;

    public Estudiante(String nombre, int edad, String materia, String horas, String region, String cedula, String anio) {
        this.nombre = nombre;
        this.edad = edad;
        this.materia = materia;
        this.horas = horas;
        this.region = region;
        this.cedula = cedula;
        this.anio = anio;
        this.codigo = generarCodigo();
    }

    private String generarCodigo() {
        return "EST" + ((int)(Math.random()*9000)+1000);
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getMateria() { return materia; }
    public String getHoras() { return horas; }
    public String getCodigo() { return codigo; }
    public String getRegion() { return region; }
    public String getCedula() { return cedula; }
    public String getAnio() { return anio; }
}
