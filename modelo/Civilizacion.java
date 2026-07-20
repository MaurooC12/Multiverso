package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una civilización dentro del multiverso.
 * Almacena toda la información estática de cada civilización.
 */
public class Civilizacion {
    private String nombre;
    private String tipo; // "Ofensiva" o "Defensiva"
    private String dificultad; // "Fácil", "Media", "Difícil"
    private int ataqueCiudad;
    private int defensaCiudad;
    private String maravilla;
    private List<Conexion> conexiones;

    public Civilizacion(String nombre, String tipo, String dificultad, 
                        int ataqueCiudad, int defensaCiudad, String maravilla) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.dificultad = dificultad;
        this.ataqueCiudad = ataqueCiudad;
        this.defensaCiudad = defensaCiudad;
        this.maravilla = maravilla;
        this.conexiones = new ArrayList<>();
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getDificultad() { return dificultad; }
    public int getAtaqueCiudad() { return ataqueCiudad; }
    public int getDefensaCiudad() { return defensaCiudad; }
    public String getMaravilla() { return maravilla; }
    public List<Conexion> getConexiones() { return conexiones; }

    public void setDefensaCiudad(int defensaCiudad) {
        this.defensaCiudad = Math.max(0, defensaCiudad);
    }

    public void agregarConexion(Conexion c) {
        if (conexiones.size() < 6) {
            conexiones.add(c);
        }
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ", " + dificultad + ")";
    }
}