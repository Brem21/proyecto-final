public class Region {
    public static final Region[] REGIONES = {
            new Region("Costa"),
            new Region("Sierra"),
            new Region("Amazonía"),
            new Region("Galápagos")
    };

    private String nombre;

    public Region(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static Region buscarRegionPorNombre(String nombre) {
        for (Region r : REGIONES) {
            if (r.getNombre().equalsIgnoreCase(nombre)) {
                return r;
            }
        }
        return null;
    }
}

