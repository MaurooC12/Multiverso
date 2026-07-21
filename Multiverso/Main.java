package Multiverso;
import Multiverso.controlador.Juego;

/**
 * Punto de entrada del programa.
 */
public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }
}