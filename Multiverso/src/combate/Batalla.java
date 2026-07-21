package Multiverso.src.combate;

import modelo.Jugador;
import modelo.Civilizacion;

/**
 * Clase que simula el combate por turnos entre el jugador y una ciudad.
 */
public class Batalla {

    /**
     * Resultado de una batalla.
     */
    public static class Resultado {
        private boolean victoria;
        private int oroGanado;
        private int unidadesPerdidas;
        private String maravillaObtenida;

        public Resultado(boolean victoria, int oroGanado, int unidadesPerdidas, String maravillaObtenida) {
            this.victoria = victoria;
            this.oroGanado = oroGanado;
            this.unidadesPerdidas = unidadesPerdidas;
            this.maravillaObtenida = maravillaObtenida;
        }

        public boolean isVictoria() { return victoria; }
        public int getOroGanado() { return oroGanado; }
        public int getUnidadesPerdidas() { return unidadesPerdidas; }
        public String getMaravillaObtenida() { return maravillaObtenida; }

        @Override
        public String toString() {
            if (victoria) {
                return "VICTORIA! Obtienes " + oroGanado + " de oro y la maravilla '" + 
                       maravillaObtenida + "'. Pierdes " + unidadesPerdidas + " unidades.";
            } else {
                return "DERROTA! Pierdes " + unidadesPerdidas + " unidades. La ciudad se refuerza.";
            }
        }
    }

    /**
     * Simula una batalla entre el jugador y una ciudad.
     * @param jugador El jugador actual
     * @param ciudad La civilización a atacar
     * @return Resultado de la batalla
     */
    public static Resultado pelear(Jugador jugador, Civilizacion ciudad) {
        int ataqueJugador = Estadisticas.getAtaqueTotal(jugador);
        int defensaJugador = Estadisticas.getDefensaTotal(jugador);
        int ataqueCiudad = ciudad.getAtaqueCiudad();
        int defensaCiudad = ciudad.getDefensaCiudad();

        // ============================================
        // TURNO 1: JUGADOR ATACA
        // ============================================
        int dañoInfligido = (int)(Math.random() * ataqueJugador) + 1;
        defensaCiudad -= dañoInfligido;

        // Si la ciudad muere en el primer turno, victoria
        if (defensaCiudad <= 0) {
            int unidadesPerdidas = calcularPerdidas(jugador, 10, 30);
            jugador.perderUnidades(unidadesPerdidas);
            jugador.agregarMaravilla(ciudad.getMaravilla());
            jugador.setOro(jugador.getOro() + 20);
            return new Resultado(true, 20, unidadesPerdidas, ciudad.getMaravilla());
        }

        // ============================================
        // TURNO 2: CIUDAD CONTRAATACA
        // ============================================
        int dañoRecibido = (int)(Math.random() * ataqueCiudad) + 1;
        defensaJugador -= dañoRecibido;

        // Si el jugador muere, derrota
        if (defensaJugador <= 0) {
            int unidadesPerdidas = calcularPerdidas(jugador, 60, 80);
            jugador.perderUnidades(unidadesPerdidas);
            ciudad.setDefensaCiudad(ciudad.getDefensaCiudad() + 5); // ciudad se refuerza
            return new Resultado(false, 0, unidadesPerdidas, null);
        }

        // ============================================
        // VICTORIA CON DAÑO RECIBIDO
        // ============================================
        int unidadesPerdidas = calcularPerdidas(jugador, 10, 30);
        jugador.perderUnidades(unidadesPerdidas);
        jugador.agregarMaravilla(ciudad.getMaravilla());
        jugador.setOro(jugador.getOro() + 20);
        return new Resultado(true, 20, unidadesPerdidas, ciudad.getMaravilla());
    }

    /**
     * Calcula el número de unidades perdidas en una batalla.
     * @param jugador El jugador actual
     * @param min Porcentaje mínimo de pérdida
     * @param max Porcentaje máximo de pérdida
     * @return Número de unidades a perder
     */
    private static int calcularPerdidas(Jugador jugador, int min, int max) {
        int totalUnidades = jugador.getUnidades().size();
        if (totalUnidades == 0) return 0;
        
        int porcentaje = (int)(Math.random() * (max - min + 1)) + min;
        int perdidas = (int)Math.round(totalUnidades * porcentaje / 100.0);
        
        // Asegurar que no se pierdan más unidades de las que hay
        return Math.min(perdidas, totalUnidades);
    }
}