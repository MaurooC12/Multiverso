package Multiverso.src.grafo;

import Multiverso.src.modelo.Civilizacion;
import Multiverso.src.modelo.Conexion;
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
     * Agrega una conexion dirigida (origen a destino).
     * Valida que el origen no supere 6 conexiones de salida y evita
     * conexiones reciprocas para mantener el grafo como dirigido.
     */
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

        // Evitar duplicados
        for (Conexion c : conexionesOrigen) {
            if (c.getDestino().equals(destino)) {
                return; // Ya existe esta conexión
            }
        }

        if (tieneConexion(destino, origen)) {
            return;
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
     * Verifica si el grafo es fuertemente conexo.
     * Debe existir un camino dirigido desde cualquier nodo hacia cualquier otro.
     */
    public boolean esConexo() {
        if (nodos.isEmpty()) return false;

        String inicio = nodos.keySet().iterator().next();
        Set<String> alcanzablesDesdeInicio = bfsConexiones(inicio, adyacencias);
        if (alcanzablesDesdeInicio.size() != nodos.size()) {
            System.out.println("Nodos no alcanzables desde " + inicio + ":");
            for (String nombre : nodos.keySet()) {
                if (!alcanzablesDesdeInicio.contains(nombre)) {
                    System.out.println("   - " + nombre);
                }
            }
            return false;
        }

        Map<String, List<String>> adyacenciasInvertidas = construirAdyacenciasInvertidas();
        Set<String> alcanzablesHaciaInicio = bfs(inicio, adyacenciasInvertidas);
        if (alcanzablesHaciaInicio.size() != nodos.size()) {
            System.out.println("Nodos que no pueden retornar hacia " + inicio + ":");
            for (String nombre : nodos.keySet()) {
                if (!alcanzablesHaciaInicio.contains(nombre)) {
                    System.out.println("   - " + nombre);
                }
            }
            return false;
        }

        return true;
    }

    private Set<String> bfsConexiones(String inicio, Map<String, List<Conexion>> mapaAdyacencia) {
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            String actual = cola.poll();
            List<Conexion> vecinos = mapaAdyacencia.getOrDefault(actual, new ArrayList<>());
            for (Conexion vecino : vecinos) {
                String destino = vecino.getDestino();
                if (!visitados.contains(destino)) {
                    visitados.add(destino);
                    cola.add(destino);
                }
            }
        }

        return visitados;
    }

    private boolean tieneConexion(String origen, String destino) {
        List<Conexion> conexiones = adyacencias.get(origen);
        if (conexiones == null) {
            return false;
        }
        for (Conexion conexion : conexiones) {
            if (conexion.getDestino().equals(destino)) {
                return true;
            }
        }
        return false;
    }

    private Set<String> bfs(String inicio, Map<String, List<String>> mapaAdyacencia) {
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            String actual = cola.poll();
            List<String> vecinos = mapaAdyacencia.getOrDefault(actual, new ArrayList<>());
            for (String vecino : vecinos) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }

        return visitados;
    }

    private Map<String, List<String>> construirAdyacenciasInvertidas() {
        Map<String, List<String>> invertidas = new HashMap<>();
        for (String nombre : nodos.keySet()) {
            invertidas.put(nombre, new ArrayList<>());
        }

        for (Map.Entry<String, List<Conexion>> entry : adyacencias.entrySet()) {
            String origen = entry.getKey();
            for (Conexion conexion : entry.getValue()) {
                invertidas.get(conexion.getDestino()).add(origen);
            }
        }

        return invertidas;
    }

    /**
     * Imprime el grafo en consola (lista de adyacencia).
     */
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