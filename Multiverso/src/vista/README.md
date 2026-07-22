# Vista (`vista/`)

Esta carpeta contiene la clase que maneja la **interfaz de usuario en consola**.

---

## ¿Qué hace cada archivo?

| Archivo | Descripción |
|---------|-------------|
| `Consola.java` | Maneja toda la interacción con el usuario: menús, entrada de datos, visualización de información y mensajes del juego. |

---

## Detalles de implementación

### `Consola.java`
- Todas las interacciones con el usuario están centralizadas aquí.
- **Métodos de entrada**: `leerOpcion()`, `leerTexto()`, `leerRespuestaMision()`.
- **Métodos de salida**: `mostrarMenu()`, `mostrarEstadoJugador()`, `mostrarMapa()`, `mostrarBatalla()`, `mostrarMision()`.
- **Mensajes del juego**: Viajes, reclutamiento, combates, comercio, victoria y derrota.

### Métodos destacados:
| Método | Descripción |
|--------|-------------|
| `mostrarMenu()` | Muestra el menú principal con las opciones del jugador. |
| `mostrarEstadoJugador()` | Muestra el estado actual del jugador (oro, tickets, población, maravillas). |
| `mostrarMapa()` | Muestra el mapa de conexiones del grafo. |
| `mostrarMision()` | Muestra una pregunta con sus opciones y espera la respuesta del usuario. |
| `mostrarResultadoBatalla()` | Muestra el resultado de una batalla. |
| `limpiarConsola()` | Simula la limpieza de la consola. |