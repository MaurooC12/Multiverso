package Multiverso.src.combate;

import Multiverso.src.modelo.Jugador;
import Multiverso.src.modelo.Unidad;
import java.util.*;

/**
 * Clase utilitaria para calcular estadísticas del jugador.
 * 
 * Métodos principales:
 * - getAtaqueTotal(): suma el ataque de todas las unidades + mejoras
 * - getDefensaTotal(): suma la defensa de todas las unidades + mejoras
 * - getBonificacionAtaque(): calcula beneficios de ataque por bloques de 8 unidades ofensivas
 * - getBonificacionDefensa(): calcula beneficios defensivos por bloques de 8 unidades defensivas
 * - getUnidadesPorCivilizacion(): agrupa unidades por civilización para calcular beneficios
 */
public class Estadisticas {

    /**
     * Calcula el ataque total del jugador.
     * @param jugador El jugador actual
     * @return Ataque total (unidades + mejoras + beneficios)
     */
    public static int getAtaqueTotal(Jugador jugador) {
        int ataqueBase = 0;
        for (Unidad u : jugador.getUnidades()) {
            ataqueBase += u.getAtaque();
        }
        ataqueBase += jugador.getAtaqueExtra();
        
        // Aplicar bonificación de ataque
        double bonificacion = getBonificacionAtaque(jugador);
        return (int)(ataqueBase * (1 + bonificacion / 100.0));
    }

    /**
     * Calcula la defensa total del jugador.
     * @param jugador El jugador actual
     * @return Defensa total (unidades + mejoras + beneficios)
     */
    public static int getDefensaTotal(Jugador jugador) {
        int defensaBase = 0;
        for (Unidad u : jugador.getUnidades()) {
            defensaBase += u.getDefensa();
        }
        defensaBase += jugador.getDefensaExtra();
        
        // Aplicar bonificación de defensa
        double bonificacion = getBonificacionDefensa(jugador);
        return (int)(defensaBase * (1 + bonificacion / 100.0));
    }

    /**
     * Calcula la bonificación de ataque activa.
     * @param jugador El jugador actual
     * @return Porcentaje de bonificación (ej. 25 para 25%)
     */
    public static double getBonificacionAtaque(Jugador jugador) {
        Map<String, Integer> unidadesPorCivilizacion = getUnidadesPorCivilizacion(jugador);
        double bonificacion = 0;
        
        for (Map.Entry<String, Integer> entry : unidadesPorCivilizacion.entrySet()) {
            int cantidad = entry.getValue();
            String civilizacion = entry.getKey();
            String tipoCivilizacion = obtenerTipoCivilizacion(jugador, civilizacion);

            if ("Ofensiva".equals(tipoCivilizacion)) {
                int niveles = Math.min(cantidad / 8, 3);
                bonificacion += niveles * 25;
            }
        }
        
        return bonificacion;
    }

    /**
     * Calcula la bonificación de defensa activa.
     * @param jugador El jugador actual
     * @return Porcentaje de bonificación (ej. 25 para 25%)
     */
    public static double getBonificacionDefensa(Jugador jugador) {
        Map<String, Integer> unidadesPorCivilizacion = getUnidadesPorCivilizacion(jugador);
        double bonificacion = 0;
        
        for (Map.Entry<String, Integer> entry : unidadesPorCivilizacion.entrySet()) {
            int cantidad = entry.getValue();
            String civilizacion = entry.getKey();
            String tipoCivilizacion = obtenerTipoCivilizacion(jugador, civilizacion);

            if ("Defensiva".equals(tipoCivilizacion)) {
                int niveles = Math.min(cantidad / 8, 3);
                bonificacion += niveles * 25;
            }
        }
        
        return bonificacion;
    }

    /**
     * Agrupa las unidades del jugador por civilización de origen.
     * @param jugador El jugador actual
    * @return Mapa con nombre de civilizacion a cantidad de unidades
     */
    public static Map<String, Integer> getUnidadesPorCivilizacion(Jugador jugador) {
        Map<String, Integer> mapa = new HashMap<>();
        for (Unidad u : jugador.getUnidades()) {
            String origen = u.getCivilizacionOrigen();
            mapa.put(origen, mapa.getOrDefault(origen, 0) + 1);
        }
        return mapa;
    }

    /**
     * Obtiene el tipo de una civilización a partir de una de sus unidades.
     * Todas las unidades de una misma civilización comparten el mismo tipo.
     */
    private static String obtenerTipoCivilizacion(Jugador jugador, String civilizacion) {
        for (Unidad unidad : jugador.getUnidades()) {
            if (civilizacion.equals(unidad.getCivilizacionOrigen())) {
                return unidad.getTipo();
            }
        }
        return null;
    }

    /**
     * Muestra las unidades agrupadas por civilización (útil para depuración).
     */
    public static void mostrarResumenUnidades(Jugador jugador) {
        Map<String, Integer> mapa = getUnidadesPorCivilizacion(jugador);
        System.out.println("\n=== RESUMEN DE UNIDADES ===");
        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
            String civilizacion = entry.getKey();
            int cantidad = entry.getValue();
            String tipoCivilizacion = obtenerTipoCivilizacion(jugador, civilizacion);
            int niveles = Math.min(cantidad / 8, 3);

            System.out.println(civilizacion + ": " + cantidad + " unidades");
            if (niveles > 0 && tipoCivilizacion != null) {
                String estadistica = "Ofensiva".equals(tipoCivilizacion) ? "ataque" : "defensa";
                System.out.println("" + niveles + " bono(s) activo(s) de " + estadistica + " (+" + (niveles * 25) + "%)");
            }
        }
        System.out.println("=============================\n");
    }
}