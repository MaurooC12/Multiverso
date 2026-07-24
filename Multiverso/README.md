# Código Fuente

Código fuente del **Multiverso de Civilizaciones**.

Esta carpeta contiene el proyecto completo, organizado de la siguiente manera:

---

## Contenido de cada carpeta

| Carpeta | Descripción |
|----------|-------------|
| `.vscode/` | Contiene `tasks.json` con la configuración para compilar el proyecto en VS Code. |
| `docs/` | Almacena la imagen del grafo (`grafo.png`) que se usa en la documentación. |
| `src/` | Todo el código fuente del juego, organizado por capas (modelo, vista, controlador, combate, grafo). |
| `out/` | Carpeta generada automáticamente al compilar. Contiene los archivos `.class`. |

---

## Estructuras de Datos Utilizadas

Este proyecto aplica los siguientes conceptos de estructuras de datos en su implementación:

### Implementadas en el código

| Estructura | Uso en el proyecto |
|------------|---------------------|
| `ArrayList` | Almacenamiento de unidades, maravillas y listas de conexiones. |
| `LinkedList` / `Queue` | Cola para el recorrido BFS en el grafo. |
| `HashMap` | Nodos, adyacencias y agrupación de unidades por civilización. |
| `HashSet` | Control de nodos visitados en BFS. |
| **Grafo Dirigido** | 36 nodos (civilizaciones) con conexiones unidireccionales. |
| **Lista de Adyacencia** | Modelado eficiente de conexiones (máximo 6 por nodo). |
| **BFS** | Verificación de conexidad del grafo. |
| `Arrays` | Banco de 30 preguntas y costos de mejoras. |
| `StringBuilder` | Construcción de cadenas en consola. |
| `Scanner` / `Random` | Entrada de usuario y generación de valores aleatorios. |

