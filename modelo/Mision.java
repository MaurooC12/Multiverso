package modelo;

import java.util.Random;

/**
 * Clase que representa una misión (pregunta) que el jugador puede resolver.
 */
public class Mision {
    private String pregunta;
    private String[] opciones; // 4 opciones: A, B, C, D
    private int respuestaCorrecta; // 0 = A, 1 = B, 2 = C, 3 = D
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

    // Getters
    public String getPregunta() { return pregunta; }
    public String[] getOpciones() { return opciones; }
    public int getRespuestaCorrecta() { return respuestaCorrecta; }
    public int getRecompensaMin() { return recompensaMin; }
    public int getRecompensaMax() { return recompensaMax; }

    public static Mision generarAleatoria() {
        Mision[] misiones = {
            new Mision(
                "¿Qué estructura de datos usa el principio LIFO?",
                new String[] {"Cola", "Pila", "Lista", "Árbol"},
                1,
                10,
                20
            ),
            new Mision(
                "¿Cuál es la complejidad promedio de búsqueda en un HashMap?",
                new String[] {"O(1)", "O(n)", "O(log n)", "O(n log n)"},
                0,
                12,
                22
            ),
            new Mision(
                "¿Qué recorrido visita primero la raíz de un árbol?",
                new String[] {"Inorden", "Postorden", "Preorden", "Por niveles"},
                2,
                10,
                18
            ),
            new Mision(
                "¿Qué estructura permite eliminar desde ambos extremos?",
                new String[] {"Pila", "Cola", "Deque", "Árbol"},
                2,
                14,
                24
            )
        };

        return misiones[new Random().nextInt(misiones.length)];
    }

    @Override
    public String toString() {
        return pregunta;
    }
}