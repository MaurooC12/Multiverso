#  Age of Empires II

## Descripción General

**Multiverso de Civilizaciones** es un juego de exploración y conquista desarrollado como proyecto final para la asignatura de **Estructuras de Datos**. El jugador asume el rol de un **Viajero Eterno** que navega a través de 36 civilizaciones del universo de *Age of Empires II*, con el objetivo de recolectar las 36 maravillas que representan la grandeza de cada cultura.

---

## Planteamiento Inicial

La idea surgió considerando una temática diferente y que se alineara con los parámetros establecidos de un multiverso donde deben existir mínimo 36 universos y cada universo debe tener máximo 6 conexiones. Se eligió el universo de *Age of Empires II* por su riqueza de civilizaciones y la variedad de unidades únicas, lo que permitía diseñar interacciones diferenciadas para cada nodo del grafo, utilizando la teoría de grafos, para crear un grafo conexo, con 36 nodos donde cada nodo tiene máximo 6 aristas incidentes.

### Decisiones de diseño:

- **36 civilizaciones** → Nodos del grafo.
- **Máximo 6 conexiones por nodo** → Cada civilización tiene entre 4 y 6 rutas de viaje.
- **Grafo conexo** → Se puede llegar de cualquier nodo a cualquier otro.
- **Conexiones unidireccionales** → Representan rutas comerciales o conquistas históricas donde no se puede devolver a la civilización ya seleccionada.
- **Dos tipos de unidad** → Ofensiva (10 ataque, 3 defensa) y Defensiva (3 ataque, 10 defensa).
- **Sistema de combate por turnos** → El jugador ataca, luego la ciudad contraataca.
- **Beneficios acumulables** → Cada 8 unidades de una misma civilización otorgan +25% de ataque o defensa.

---

## Grafo del Multiverso

![Grafo del Multiverso](docs/grafo.png)

*Cada nodo es una civilización. Las flechas indican las rutas de viaje disponibles.*

---

## Créditos

- **Desarrollo**: Proyecto académico para la asignatura de **Estructuras de Datos**.
- **Estudiantes**: 
Andrés Mauricio Cepeda Villanueva
Lucas García Álvarez
Cristian David Prada Merchan

---