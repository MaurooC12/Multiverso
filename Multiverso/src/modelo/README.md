# Modelo (`modelo/`)

Esta carpeta contiene las clases que representan los **datos y la lógica de negocio** del juego.

---

## ¿Qué hace cada archivo?

| Archivo | Descripción |
|---------|-------------|
| `Civilizacion.java` | Define cada civilización (nombre, tipo, dificultad, ataque, defensa y maravilla). |
| `Conexion.java` | Representa una ruta (arista dirigida) entre dos civilizaciones. |
| `DatosCivilizaciones.java` | Carga las 36 civilizaciones y todas sus conexiones (grafo unidireccional). |
| `Jugador.java` | Gestiona el progreso del jugador (oro, tickets, unidades, maravillas). |
| `Mision.java` | Banco de 30 preguntas de opción múltiple para ganar oro en el comercio. |
| `Unidad.java` | Representa una tropa del jugador (ofensiva o defensiva). |

---

## Detalles de implementación

### `Civilizacion.java`
- Cada civilización tiene un **tipo** (`Ofensiva` o `Defensiva`) que define su unidad única.
- La **defensa de la ciudad** se modifica según la dificultad elegida por el jugador.
- Las **conexiones** se limitan a un máximo de 6 por civilización.

### `Jugador.java`
- El jugador comienza con **100 de oro** y **20 tickets**.
- La **población máxima** es de **25 unidades**.
- Las **mejoras de ataque y defensa** son permanentes y acumulables.
- Las **maravillas** se almacenan en una lista para verificar la victoria.

### `Mision.java`
- **30 preguntas** distribuidas en 3 categorías:
  - Estructuras de Datos (12 preguntas).
  - Age of Empires II (12 preguntas).
  - Historia y Geografía (6 preguntas).
- Cada misión tiene una pregunta, 4 opciones, una respuesta correcta y una recompensa en oro (10-20).
- Si el jugador acierta, gana oro; si falla, no obtiene nada.

### `DatosCivilizaciones.java`
- Carga estática de las 36 civilizaciones con sus atributos y conexiones.
- Las conexiones están diseñadas para que el grafo sea **unidireccional** y **conexo**.
- Ningún nodo supera las 6 conexiones de salida.