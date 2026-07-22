# Grafo (`grafo/`)

Esta carpeta contiene la **implementación del grafo dirigido** que representa el multiverso.

---

## ¿Qué hace cada archivo?

| Archivo | Descripción |
|---------|-------------|
| `Grafo.java` | Implementa un grafo dirigido con 36 nodos (civilizaciones) y máximo 6 conexiones por nodo. Incluye verificación de conexidad con BFS. |

---

## Detalles de implementación

### `Grafo.java`
- **Nodos**: 36 civilizaciones.
- **Conexiones**: Máximo 6 aristas de salida por nodo.
- **Tipo**: Grafo dirigido (unidireccional).
- **Conexidad**: Verifica que todos los nodos sean alcanzables desde un nodo inicial usando BFS.
- **Representación**: `Map<String, Civilizacion>` para nodos y `Map<String, List<Conexion>>` para adyacencias.

### Métodos principales:
| Método | Descripción |
|--------|-------------|
| `agregarNodo(Civilizacion)` | Añade una civilización al grafo. |
| `agregarConexion(String origen, String destino)` | Añade una arista dirigida (origen → destino). |
| `getConexiones(String nombre)` | Retorna las conexiones de salida de un nodo. |
| `esConexo()` | Verifica que el grafo sea conexo usando BFS. |
| `imprimirGrafo()` | Muestra la lista de adyacencia en consola. |