public class Profesor {
    private String nombre;
    private String experiencia;
    private String materia;
    private String horas;
    private String codigo;
    private String cedula;
    private String region;

    public Profesor(String nombre, String experiencia, String materia, String horas, String cedula, String region) {
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.materia = materia;
        this.horas = horas;
        this.cedula = cedula;
        this.region = region;
        this.codigo = generarCodigo();
    }

    private String generarCodigo() {
        return "PRO" + ((int)(Math.random()*9000)+1000);
    }

    public String getNombre() { return nombre; }
    public String getExperiencia() { return experiencia; }
    public String getMateria() { return materia; }
    public String getHoras() { return horas; }
    public String getCodigo() { return codigo; }
    public String getRegion() { return region; }
    public String getCedula() { return cedula; }
}
