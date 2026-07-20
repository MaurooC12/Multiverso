package modelo;

/**
 * Clase que representa una arista (conexión) en el grafo dirigido.
 */
public class Conexion {
    private String origen;
    private String destino;

    public Conexion(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }

    @Override
    public String toString() {
        return origen + " → " + destino;
    }
}