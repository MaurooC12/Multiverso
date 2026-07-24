package grafo;

import modelo.Civilizacion;
import modelo.Conexion;
import java.util.*;

/**
 * Clase que implementa un grafo dirigido para representar el multiverso.
 */
public class Grafo {
    private Map<String, Civilizacion> nodos;
    private Map<String, List<Conexion>> adyacencias;

    public Grafo() {
        this.nodos = new HashMap<>();
        this.adyacencias = new HashMap<>();
    }

    public void agregarNodo(Civilizacion civilizacion) {
        String nombre = civilizacion.getNombre();
        nodos.put(nombre, civilizacion);
        adyacencias.putIfAbsent(nombre, new ArrayList<>());
    }

    public void agregarConexion(String origen, String destino) {
        if (!nodos.containsKey(origen) || !nodos.containsKey(destino)) {
            System.out.println("Error: " + origen + " o " + destino + " no existe en el grafo.");
            return;
        }
        List<Conexion> conexionesOrigen = adyacencias.get(origen);
        if (conexionesOrigen.size() >= 6) {
            System.out.println("Advertencia: " + origen + " ya tiene 6 conexiones. No se puede agregar mas.");
            return;
        }
        for (Conexion c : conexionesOrigen) {
            if (c.getDestino().equals(destino)) {
                return;
            }
        }
        conexionesOrigen.add(new Conexion(origen, destino));
    }

    public List<Conexion> getConexiones(String nombre) {
        return adyacencias.getOrDefault(nombre, new ArrayList<>());
    }

    public Civilizacion getCivilizacion(String nombre) {
        return nodos.get(nombre);
    }

    public Set<String> getNombresCivilizaciones() {
        return nodos.keySet();
    }

    public Collection<Civilizacion> getCivilizaciones() {
        return nodos.values();
    }

    public boolean esConexo() {
        if (nodos.isEmpty()) return false;
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();
        String inicio = nodos.keySet().iterator().next();
        cola.add(inicio);
        visitados.add(inicio);
        while (!cola.isEmpty()) {
            String actual = cola.poll();
            // Vecinos salientes
            for (Conexion c : adyacencias.getOrDefault(actual, new ArrayList<>())) {
                if (!visitados.contains(c.getDestino())) {
                    visitados.add(c.getDestino());
                    cola.add(c.getDestino());
                }
            }
            // Vecinos entrantes (conexiones de otros nodos hacia el actual)
            for (Map.Entry<String, List<Conexion>> entrada : adyacencias.entrySet()) {
                String nodoOrigen = entrada.getKey();
                for (Conexion c : entrada.getValue()) {
                    if (c.getDestino().equals(actual) && !visitados.contains(nodoOrigen)) {
                        visitados.add(nodoOrigen);
                        cola.add(nodoOrigen);
                    }
                }
            }
        }
        return visitados.size() == nodos.size();
    }

    public void imprimirGrafo() {
        System.out.println("\n=== MAPA DEL MULTIVERSO (CONEXIONES) ===");
        for (String origen : adyacencias.keySet()) {
            System.out.print(origen + " -> ");
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