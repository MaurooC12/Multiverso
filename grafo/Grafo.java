package grafo;

import modelo.Civilizacion;
import modelo.Conexion;
import java.util.*;

/**
 * Clase que implementa un grafo dirigido para representar el multiverso.
 * 
 * Características:
 * - 36 nodos (civilizaciones)
 * - Grado máximo: 6 conexiones de salida por nodo
 * - Grafo conexo: se puede llegar de cualquier nodo a cualquier otro
 */
public class Grafo {
    private Map<String, Civilizacion> nodos;
    private Map<String, List<Conexion>> adyacencias;

    public Grafo() {
        this.nodos = new HashMap<>();
        this.adyacencias = new HashMap<>();
    }

    /**
     * Agrega una civilización al grafo.
     */
    public void agregarNodo(Civilizacion civilizacion) {
        String nombre = civilizacion.getNombre();
        nodos.put(nombre, civilizacion);
        adyacencias.putIfAbsent(nombre, new ArrayList<>());
    }

    /**
     * Agrega una conexión dirigida (origen → destino).
     * Valida que el origen no supere 6 conexiones de salida.
     */
    public void agregarConexion(String origen, String destino) {
        if (!nodos.containsKey(origen) || !nodos.containsKey(destino)) {
            System.out.println("⚠️ Error: " + origen + " o " + destino + " no existe en el grafo.");
            return;
        }

        List<Conexion> conexionesOrigen = adyacencias.get(origen);
        if (conexionesOrigen.size() >= 6) {
            System.out.println("⚠️ " + origen + " ya tiene 6 conexiones. No se puede agregar más.");
            return;
        }

        // Evitar duplicados
        for (Conexion c : conexionesOrigen) {
            if (c.getDestino().equals(destino)) {
                return; // Ya existe esta conexión
            }
        }

        Conexion conexion = new Conexion(origen, destino);
        conexionesOrigen.add(conexion);
        nodos.get(origen).agregarConexion(conexion);
    }

    /**
     * Obtiene las conexiones de salida de un nodo.
     */
    public List<Conexion> getConexiones(String nombre) {
        return adyacencias.getOrDefault(nombre, new ArrayList<>());
    }

    /**
     * Obtiene una civilización por su nombre.
     */
    public Civilizacion getCivilizacion(String nombre) {
        return nodos.get(nombre);
    }

    /**
     * Obtiene todos los nombres de las civilizaciones.
     */
    public Set<String> getNombresCivilizaciones() {
        return nodos.keySet();
    }

    /**
     * Obtiene todas las civilizaciones.
     */
    public Collection<Civilizacion> getCivilizaciones() {
        return nodos.values();
    }

    /**
     * Verifica si el grafo es conexo usando BFS.
     */
    public boolean esConexo() {
        if (nodos.isEmpty()) return false;

        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();
        
        String inicio = nodos.keySet().iterator().next();
        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            String actual = cola.poll();
            for (Conexion c : adyacencias.get(actual)) {
                if (!visitados.contains(c.getDestino())) {
                    visitados.add(c.getDestino());
                    cola.add(c.getDestino());
                }
            }
        }

        boolean conexo = visitados.size() == nodos.size();
        if (!conexo) {
            System.out.println("⚠️ Nodos no alcanzables desde " + inicio + ":");
            for (String nombre : nodos.keySet()) {
                if (!visitados.contains(nombre)) {
                    System.out.println("   - " + nombre);
                }
            }
        }
        return conexo;
    }

    /**
     * Imprime el grafo en consola (lista de adyacencia).
     */
    public void imprimirGrafo() {
        System.out.println("\n=== MAPA DEL MULTIVERSO (CONEXIONES) ===");
        for (String origen : adyacencias.keySet()) {
            System.out.print(origen + " → ");
            List<Conexion> conexiones = adyacencias.get(origen);
            if (conexiones.isEmpty()) {
                System.out.println("(sin conexiones)");
            } else {
                for (int i = 0; i < conexiones.size(); i++) {
                    System.out.print(conexiones.get(i).getDestino());
                    if (i < conexiones.size() - 1) System.out.print(", ");
                }
                System.out.println();
            }
        }
        System.out.println("=========================================\n");
    }

    public int getCantidadNodos() {
        return nodos.size();
    }
}