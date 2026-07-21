package modelo;

import java.util.Random;

public class Mision {
    private String pregunta;
    private String[] opciones;
    private int respuestaCorrecta;
    private int recompensaMin;
    private int recompensaMax;

    public Mision(String pregunta, String[] opciones, int respuestaCorrecta, 
                  int recompensaMin, int recompensaMax) {
        this.pregunta = pregunta;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.recompensaMin = recompensaMin;
        this.recompensaMax = recompensaMax;
    }

    public String getPregunta() { return pregunta; }
    public String[] getOpciones() { return opciones; }
    public int getRespuestaCorrecta() { return respuestaCorrecta; }
    public int getRecompensaMin() { return recompensaMin; }
    public int getRecompensaMax() { return recompensaMax; }

    public static Mision generarAleatoria() {
        Mision[] misiones = {
            // ============================================================
            // 1. ESTRUCTURAS DE DATOS (12 preguntas)
            // ============================================================
            new Mision(
                "¿Qué estructura de datos usa el principio LIFO?",
                new String[]{"Cola", "Pila", "Lista", "Árbol"},
                1, 10, 20
            ),
            new Mision(
                "¿Qué estructura de datos usa el principio FIFO?",
                new String[]{"Pila", "Cola", "Lista", "Conjunto"},
                1, 10, 20
            ),
            new Mision(
                "¿Cuál es la complejidad promedio de búsqueda en un HashMap?",
                new String[]{"O(1)", "O(n)", "O(log n)", "O(n log n)"},
                0, 12, 22
            ),
            new Mision(
                "¿Qué recorrido de un BST imprime los elementos en orden ascendente?",
                new String[]{"Preorden", "Inorden", "Postorden", "Por niveles"},
                1, 10, 18
            ),
            new Mision(
                "¿Qué estructura permite eliminar desde ambos extremos?",
                new String[]{"Pila", "Cola", "Deque", "Árbol"},
                2, 14, 24
            ),
            new Mision(
                "¿Qué estructura de datos es ideal para una búsqueda por niveles (BFS)?",
                new String[]{"Pila", "Cola", "Lista", "Mapa"},
                1, 10, 18
            ),
            new Mision(
                "¿Qué complejidad tiene un acceso promedio en un ArrayList?",
                new String[]{"O(1)", "O(n)", "O(log n)", "O(n^2)"},
                0, 10, 20
            ),
            new Mision(
                "¿Qué recorrido de árbol visita primero el nodo izquierdo?",
                new String[]{"Preorden", "Inorden", "Postorden", "Por niveles"},
                1, 12, 20
            ),
            new Mision(
                "¿Qué estructura asocia claves con valores?",
                new String[]{"Lista", "Pila", "HashMap", "Cola"},
                2, 12, 22
            ),
            new Mision(
                "¿Qué algoritmo se usa para encontrar el camino más corto en un grafo sin pesos?",
                new String[]{"DFS", "BFS", "QuickSort", "HeapSort"},
                1, 14, 24
            ),
            new Mision(
                "¿Qué estructura mantiene los elementos ordenados por prioridad?",
                new String[]{"PriorityQueue", "ArrayList", "Stack", "LinkedList"},
                0, 14, 24
            ),
            new Mision(
                "¿Cuál es el peor caso de inserción en un arreglo dinámico cuando debe expandirse?",
                new String[]{"O(1)", "O(log n)", "O(n)", "O(n log n)"},
                2, 12, 20
            ),

            // ============================================================
            // 2. AGE OF EMPIRES II (12 preguntas)
            // ============================================================
            new Mision(
                "¿Qué civilización tiene la unidad única 'Longbowman'?",
                new String[]{"Francos", "Britanos", "Mongoles", "Vikingos"},
                1, 10, 20
            ),
            new Mision(
                "¿Cuál es la maravilla de los Bizantinos?",
                new String[]{"Santa Sofía", "Angkor Wat", "Templo del Cielo", "Coliseo"},
                0, 10, 20
            ),
            new Mision(
                "¿Qué civilización es famosa por sus elefantes de guerra?",
                new String[]{"Persas", "Mongoles", "Celtas", "Japoneses"},
                0, 10, 20
            ),
            new Mision(
                "¿Qué unidad única tienen los Godos?",
                new String[]{"Samurai", "Huskarl", "Mangudai", "Berserker"},
                1, 10, 20
            ),
            new Mision(
                "¿Qué civilización tiene la unidad 'Mameluke'?",
                new String[]{"Sarracenos", "Turcos", "Egipcios", "Bereberes"},
                0, 10, 20
            ),
            new Mision(
                "¿Qué civilización tiene la unidad 'Samurai'?",
                new String[]{"Chinos", "Japoneses", "Vikingos", "Coreanos"},
                1, 10, 20
            ),
            new Mision(
                "¿Qué civilización tiene la unidad 'Cataphract'?",
                new String[]{"Bizantinos", "Francos", "Teutones", "Persas"},
                0, 10, 20
            ),
            new Mision(
                "¿Qué civilización tiene la unidad 'Jaguar Warrior'?",
                new String[]{"Mayas", "Aztecas", "Incas", "Mapuche"},
                1, 10, 20
            ),
            new Mision(
                "¿Qué civilización tiene la unidad 'Berserker'?",
                new String[]{"Vikingos", "Godos", "Celtas", "Eslavos"},
                0, 10, 20
            ),
            new Mision(
                "¿Qué civilización tiene la unidad 'Conquistador'?",
                new String[]{"Españoles", "Portugueses", "Turcos", "Italianos"},
                0, 10, 20
            ),
            new Mision(
                "¿Qué civilización tiene la unidad 'Throwing Axeman'?",
                new String[]{"Francos", "Britanos", "Godos", "Vikingos"},
                0, 10, 20
            ),
            new Mision(
                "¿Qué civilización construyó Angkor Wat?",
                new String[]{"Indostaníes", "Jemer", "Bengalíes", "Gurjaras"},
                1, 10, 20
            ),

            // ============================================================
            // 3. HISTORIA Y GEOGRAFÍA (6 preguntas)
            // ============================================================
            new Mision(
                "¿Qué imperio construyó el Coliseo?",
                new String[]{"Griego", "Romano", "Egipcio", "Persa"},
                1, 10, 20
            ),
            new Mision(
                "¿Qué país construyó la Gran Muralla?",
                new String[]{"Japón", "Corea", "China", "Mongolia"},
                2, 10, 20
            ),
            new Mision(
                "¿Quién fue el líder de los Mongoles?",
                new String[]{"Atila", "Gengis Kan", "Saladino", "Carlomagno"},
                1, 10, 20
            ),
            new Mision(
                "¿En qué continente está Egipto?",
                new String[]{"Asia", "Europa", "África", "América"},
                2, 10, 20
            ),
            new Mision(
                "¿Dónde está Angkor Wat?",
                new String[]{"India", "Tailandia", "Camboya", "Vietnam"},
                2, 10, 20
            ),
            new Mision(
                "¿Qué país es famoso por los samuráis?",
                new String[]{"China", "Corea", "Japón", "Mongolia"},
                2, 10, 20
            )
        };

        return misiones[new Random().nextInt(misiones.length)];
    }

    @Override
    public String toString() {
        return pregunta;
    }
}