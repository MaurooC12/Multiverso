# Combate (`combate/`)

Esta carpeta contiene la **lógica de combate y el cálculo de estadísticas** del juego.

---

## ¿Qué hace cada archivo?

| Archivo | Descripción |
|---------|-------------|
| `Batalla.java` | Simula el combate por turnos entre el jugador y una ciudad. Calcula daño, aplica pérdidas de unidades y determina victoria o derrota. |
| `Estadisticas.java` | Calcula el ataque total, defensa total y los beneficios activos del jugador basados en sus unidades y mejoras. |

---

## 
Detalles de implementación

### `Batalla.java`
- **Combate por turnos**: El jugador ataca primero, luego la ciudad contraataca.
- **Pérdidas de unidades**:
  - Victoria: 10% - 30% de unidades perdidas.
  - Derrota: 60% - 80% de unidades perdidas.
- La ciudad se refuerza (+5 de defensa) si el jugador pierde.
- **Resultado**: Retorna un objeto `Resultado` con victoria, oro ganado, unidades perdidas y maravilla obtenida.

### `Estadisticas.java`
- **Ataque total**: Suma del ataque de todas las unidades + mejoras + bonificaciones.
- **Defensa total**: Suma de la defensa de todas las unidades + mejoras + bonificaciones.
- **Beneficios activos**:
  - Se activan al tener **8 o más unidades** de una misma civilización.
  - Cada bloque de 8 unidades otorga +25% de ataque o defensa (según el tipo de la civilización).
  - Máximo 3 beneficios activos.