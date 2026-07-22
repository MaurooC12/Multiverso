# Controlador (`controlador/`)

Esta carpeta contiene la clase que **orquesta el flujo completo del juego**.

---

## ¿Qué hace cada archivo?

| Archivo | Descripción |
|---------|-------------|
| `Juego.java` | Controlador principal del juego. Gestiona el bucle principal, las acciones del jugador y las condiciones de victoria/derrota. |

---

## Detalles de implementación

### `Juego.java`
- **Inicialización**: Carga el grafo, crea el jugador, elige dificultad y civilización inicial.
- **Bucle principal**: Muestra el estado del jugador, el menú y procesa las acciones.
- **Acciones del jugador**:
  - `viajar()`: Verifica tickets, muestra destinos y actualiza la civilización actual.
  - `reclutar()`: Verifica oro y población, crea una nueva unidad.
  - `atacar()`: Calcula estadísticas, simula la batalla y aplica pérdidas.
  - `comerciar()`: Muestra el menú de comercio y ejecuta la opción elegida.
- **Verificación de condiciones**: Después de cada acción, verifica si el jugador ha ganado (36 maravillas) o perdido (sin unidades y sin oro).
- **Sistema de mejoras**: Costos progresivos `[30, 40, 55, 70, 90]` para mejorar ataque y defensa.