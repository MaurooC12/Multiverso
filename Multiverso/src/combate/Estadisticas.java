package combate;

import modelo.Jugador;
import modelo.Unidad;
import java.util.*;

/**
 * Clase utilitaria para calcular estadísticas del jugador.
 */
public class Estadisticas {

    public static int getAtaqueTotal(Jugador jugador) {
        int ataqueBase = 0;
        for (Unidad u : jugador.getUnidades()) {
            ataqueBase += u.getAtaque();
        }
        ataqueBase += jugador.getAtaqueExtra();
        double bonificacion = getBonificacionAtaque(jugador);
        return (int)(ataqueBase * (1 + bonificacion / 100.0));
    }

    public static int getDefensaTotal(Jugador jugador) {
        int defensaBase = 0;
        for (Unidad u : jugador.getUnidades()) {
            defensaBase += u.getDefensa();
        }
        defensaBase += jugador.getDefensaExtra();
        double bonificacion = getBonificacionDefensa(jugador);
        return (int)(defensaBase * (1 + bonificacion / 100.0));
    }

    public static double getBonificacionAtaque(Jugador jugador) {
        Map<String, Integer> unidadesPorCivilizacion = getUnidadesPorCivilizacion(jugador);
        double bonificacion = 0;
        for (Map.Entry<String, Integer> entry : unidadesPorCivilizacion.entrySet()) {
            int cantidad = entry.getValue();
            String civilizacion = entry.getKey();
            String tipoCivilizacion = obtenerTipoCivilizacion(jugador, civilizacion);
            if (tipoCivilizacion == null) continue;
            if ("Ofensiva".equals(tipoCivilizacion)) {
                int niveles = Math.min(cantidad / 8, 3);
                bonificacion += niveles * 25;
            }
        }
        return bonificacion;
    }

    public static double getBonificacionDefensa(Jugador jugador) {
        Map<String, Integer> unidadesPorCivilizacion = getUnidadesPorCivilizacion(jugador);
        double bonificacion = 0;
        for (Map.Entry<String, Integer> entry : unidadesPorCivilizacion.entrySet()) {
            int cantidad = entry.getValue();
            String civilizacion = entry.getKey();
            String tipoCivilizacion = obtenerTipoCivilizacion(jugador, civilizacion);
            if (tipoCivilizacion == null) continue;
            if ("Defensiva".equals(tipoCivilizacion)) {
                int niveles = Math.min(cantidad / 8, 3);
                bonificacion += niveles * 25;
            }
        }
        return bonificacion;
    }

    public static Map<String, Integer> getUnidadesPorCivilizacion(Jugador jugador) {
        Map<String, Integer> mapa = new HashMap<>();
        for (Unidad u : jugador.getUnidades()) {
            String origen = u.getCivilizacionOrigen();
            mapa.put(origen, mapa.getOrDefault(origen, 0) + 1);
        }
        return mapa;
    }

    private static String obtenerTipoCivilizacion(Jugador jugador, String civilizacion) {
        for (Unidad unidad : jugador.getUnidades()) {
            if (civilizacion.equals(unidad.getCivilizacionOrigen())) {
                return unidad.getTipo();
            }
        }
        return null;
    }

    public static void mostrarResumenUnidades(Jugador jugador) {
        Map<String, Integer> mapa = getUnidadesPorCivilizacion(jugador);
        System.out.println("\n=== RESUMEN DE UNIDADES ===");
        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
            String civilizacion = entry.getKey();
            int cantidad = entry.getValue();
            String tipoCivilizacion = obtenerTipoCivilizacion(jugador, civilizacion);
            if (tipoCivilizacion == null) continue;
            int niveles = Math.min(cantidad / 8, 3);
            System.out.println(civilizacion + ": " + cantidad + " unidades");
            if (niveles > 0) {
                String estadistica = "Ofensiva".equals(tipoCivilizacion) ? "ataque" : "defensa";
                System.out.println("   " + niveles + " bono(s) activo(s) de " + estadistica + " (+" + (niveles * 25) + "%)");
            }
        }
        System.out.println("=============================\n");
    }
}