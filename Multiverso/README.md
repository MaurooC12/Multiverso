# Multiverso de Civilizaciones — Código Fuente

Código fuente del **Multiverso de Civilizaciones**. 
Aquí encontrarás toda la implementación del juego: desde la lógica de combate hasta la estructura del grafo que conecta las 36 civilizaciones.

---


## ¿Qué hace cada archivo?

| Archivo | Descripción |
|---------|-------------|
| `Main.java` | Arranca el juego. |
| **modelo/** | |
| `Civilizacion.java` | Define cada civilización (nombre, tipo, dificultad, ataque, defensa y maravilla). |
| `Conexion.java` | Representa una ruta entre dos civilizaciones. |
| `DatosCivilizaciones.java` | Carga las 36 civilizaciones y todas sus conexiones. |
| `Jugador.java` | Gestiona el progreso del jugador (oro, tickets, unidades, maravillas). |
| `Mision.java` | Preguntas de opción múltiple para ganar oro en el comercio. |
| `Unidad.java` | Tropas del jugador (ofensivas o defensivas). |
| **vista/** | |
| `Consola.java` | Maneja la interacción con el usuario (menús, entrada/salida). |
| **controlador/** | |
| `Juego.java` | Controla el flujo del juego: turnos, acciones y condiciones de victoria/derrota. |
| **combate/** | |
| `Batalla.java` | Simula el combate por turnos entre el jugador y una ciudad. |
| `Estadisticas.java` | Calcula ataque total, defensa total y beneficios activos. |
| **grafo/** | |
| `Grafo.java` | Implementa el grafo dirigido de 36 nodos con verificación de conexidad (BFS). |

---