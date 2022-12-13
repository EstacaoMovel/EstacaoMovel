package pt.isec.gps.tp.utils;

public enum CategoriaFiltro {
    TODOS("TODOS"),
    TRANSDEV("Transdev"),
    SMTUC("SMTUC");


    public final String label;
    private CategoriaFiltro(String label) {
        this.label = label;
    }
}
