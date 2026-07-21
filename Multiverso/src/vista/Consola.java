package Multiverso.src.vista;

import Multiverso.src.modelo.*;
import Multiverso.src.grafo.Grafo;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que maneja toda la interacción con el usuario en consola.
 */
public class Consola {
    private Scanner scanner;

    public Consola() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Muestra el mensaje de bienvenida.
     */
    public void mostrarBienvenida() {
        System.out.println("\n==========================================================================================================");
        System.out.println("                                      MULTIVERSO DE CIVILIZACIONES");
        System.out.println("                            Basado en Age of Empires II - Estructuras de Datos");
        System.out.println("==========================================================================================================\n");
    }

    /**
     * Muestra el menú principal.
     */
    public void mostrarMenu() {
        System.out.println("\n=======================================================");
        System.out.println("                    MENU PRINCIPAL                      ");
        System.out.println("=======================================================");
        System.out.println(" [1] Viajar a otra civilización");
        System.out.println(" [2] Reclutar unidad");
        System.out.println(" [3] Atacar civilización actual");
        System.out.println(" [4] Comerciar");
        System.out.println(" [5] Ver estadísticas detalladas");
        System.out.println(" [6] Ver mapa de conexiones");
        System.out.println(" [7] Salir del juego");
        System.out.println("=======================================================");
        System.out.print("Elige una opción (1-7): ");
    }

    /**
     * Muestra el menú de comercio.
     */
    public void mostrarMenuComercio(int costoAtaque, int costoDefensa, int mejorasRestantesAtaque, int mejorasRestantesDefensa, boolean puedeMejorarAtaque, boolean puedeMejorarDefensa) {
        System.out.println("\n=======================================================");
        System.out.println("                    COMERCIO                           ");
        System.out.println("=======================================================");
        System.out.println(" [1] Comprar tickets (15 de oro a 3 tickets)");
        System.out.println(" [2] Misión (pregunta) - Gana oro si aciertas");
        System.out.println(" [3] Mejorar ataque (" + costoAtaque + " de oro a +1 ataque permanente, quedan " + mejorasRestantesAtaque + " mejora(s)" + (puedeMejorarAtaque ? "" : " - maximo alcanzado") + ")");
        System.out.println(" [4] Mejorar defensa (" + costoDefensa + " de oro a +1 defensa permanente, quedan " + mejorasRestantesDefensa + " mejora(s)" + (puedeMejorarDefensa ? "" : " - maximo alcanzado") + ")");
        System.out.println(" [5] Volver al menú principal");
        System.out.println("=======================================================");
        System.out.print("Elige una opción (1-5): ");
    }

    /**
     * Muestra el estado actual del jugador.
     */
    public void mostrarEstadoJugador(Jugador jugador, String civilizacionActual, Grafo grafo) {
        System.out.println("\n======================================================");
        System.out.println("                    ESTADO DEL JUGADOR                  ");
        System.out.println("======================================================");
        System.out.println("Civilizacion actual: " + civilizacionActual);
        System.out.println("Oro: " + jugador.getOro() + "/200");
        System.out.println("Tickets: " + jugador.getTickets() + "/30");
        System.out.println("Poblacion: " + jugador.getPoblacion() + "/" + jugador.getMaxPoblacion());
        System.out.println("Maravillas recolectadas: " + jugador.getMaravillasCount() + "/36");
        
        // Mostrar beneficios activos
        System.out.println("\nBeneficios activos:");
        double bonifAtaque = Multiverso.src.combate.Estadisticas.getBonificacionAtaque(jugador);
        double bonifDefensa = Multiverso.src.combate.Estadisticas.getBonificacionDefensa(jugador);
        if (bonifAtaque > 0) System.out.println("   Ataque +" + (int)bonifAtaque + "%");
        if (bonifDefensa > 0) System.out.println("   Defensa +" + (int)bonifDefensa + "%");
        if (bonifAtaque == 0 && bonifDefensa == 0) System.out.println("   Ningun beneficio activo");
        
        System.out.println("\nUnidades: " + jugador.getUnidades().size());
        if (jugador.getUnidades().size() > 0) {
            System.out.println("   (Detalles en 'Ver estadísticas')");
        }
        System.out.println("=======================================================\n");
    }

    /**
     * Muestra las estadísticas detalladas del jugador.
     */
    public void mostrarEstadisticasDetalladas(Jugador jugador) {
        System.out.println("\n======================================================");
        System.out.println("                ESTADISTICAS DETALLADAS               ");
        System.out.println("======================================================");
        
        System.out.println("Oro: " + jugador.getOro() + "/200");
        System.out.println("Tickets: " + jugador.getTickets() + "/30");
        System.out.println("Poblacion: " + jugador.getPoblacion() + "/" + jugador.getMaxPoblacion());
        System.out.println("Maravillas: " + jugador.getMaravillasCount() + "/36");
        
        System.out.println("\nAtaque total: " + Multiverso.src.combate.Estadisticas.getAtaqueTotal(jugador));
        System.out.println("Defensa total: " + Multiverso.src.combate.Estadisticas.getDefensaTotal(jugador));
        
        System.out.println("\nUnidades (" + jugador.getUnidades().size() + "):");
        if (jugador.getUnidades().isEmpty()) {
            System.out.println("   No tienes unidades. Recluta algunas!");
        } else {
            for (Unidad u : jugador.getUnidades()) {
                System.out.println("   - " + u);
            }
        }
        
        System.out.println("\nMaravillas recolectadas:");
        if (jugador.getMaravillasRecolectadas().isEmpty()) {
            System.out.println("   No tienes maravillas. Conquista civilizaciones!");
        } else {
            for (String m : jugador.getMaravillasRecolectadas()) {
                System.out.println("   - " + m);
            }
        }
        System.out.println("=======================================================\n");
    }

    /**
     * Muestra el mapa de conexiones del grafo.
     */
    public void mostrarMapa(Grafo grafo) {
        System.out.println("\n======================================================");
        System.out.println("                    MAPA DEL MULTIVERSO                ");
        System.out.println("======================================================");
        grafo.imprimirGrafo();
    }

    /**
     * Muestra los destinos disponibles desde una civilización.
     */
    public void mostrarDestinos(String civilizacion, Grafo grafo) {
        List<Conexion> conexiones = grafo.getConexiones(civilizacion);
        System.out.println("\nDesde " + civilizacion + " puedes viajar a:");
        if (conexiones.isEmpty()) {
            System.out.println("   No hay conexiones disponibles.");
        } else {
            for (int i = 0; i < conexiones.size(); i++) {
                System.out.println("   [" + (i+1) + "] " + conexiones.get(i).getDestino());
            }
        }
        System.out.println("   [0] Volver");
    }

    /**
     * Muestra una misión (pregunta) al jugador.
     */
    public void mostrarMision(Mision mision) {
        System.out.println("\n======================================================");
        System.out.println("                    MISION                           ");
        System.out.println("======================================================");
        System.out.println(mision.getPregunta());
        String[] opciones = mision.getOpciones();
        char letra = 'A';
        for (String opcion : opciones) {
            System.out.println("   " + letra + ") " + opcion);
            letra++;
        }
        System.out.print("\nElige tu respuesta (A/B/C/D): ");
    }

    /**
     * Muestra el resultado de una misión.
     */
    public void mostrarResultadoMision(boolean acertó, int oroGanado) {
        if (acertó) {
            System.out.println("Correcto. Ganas " + oroGanado + " de oro.");
        } else {
            System.out.println("Incorrecto. No obtienes oro.");
        }
    }

    /**
     * Muestra el resultado de una batalla.
     */
    public void mostrarResultadoBatalla(Multiverso.src.combate.Batalla.Resultado resultado, String ciudadNombre) {
        System.out.println("\n======================================================");
        System.out.println("                    BATALLA                           ");
        System.out.println("======================================================");
        System.out.println("Ciudad: " + ciudadNombre);
        System.out.println(resultado);
        System.out.println("=======================================================\n");
    }

    /**
     * Muestra mensaje de viaje.
     */
    public void mostrarViaje(String origen, String destino) {
        System.out.println("\nViajando de " + origen + " a " + destino + "...");
        System.out.println("Tiempo estimado: " + (int)(Math.random() * 3 + 1) + " segundos");
        try {
            Thread.sleep(1500); // Simula el tiempo de viaje
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Has llegado a " + destino + "!\n");
    }

    /**
     * Muestra mensaje de reclutamiento.
     */
    public void mostrarReclutamiento(String unidad, String civilizacion, int costo) {
        System.out.println("\nReclutando " + unidad + " de " + civilizacion + "...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Unidad reclutada. Costo: " + costo + " de oro.\n");
    }

    /**
     * Muestra mensaje de error.
     */
    public void mostrarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }

    /**
     * Muestra mensaje de éxito.
     */
    public void mostrarExito(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra mensaje de información.
     */
    public void mostrarInfo(String mensaje) {
        System.out.println("Info: " + mensaje);
    }

    /**
     * Lee una opción del usuario (entero).
     */
    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Lee una opción del usuario (cadena).
     */
    public String leerTexto() {
        return scanner.nextLine().trim();
    }

    /**
     * Lee una respuesta de misión (A/B/C/D).
     */
    public int leerRespuestaMision() {
        String input = scanner.nextLine().trim().toUpperCase();
        if (input.length() == 1) {
            char c = input.charAt(0);
            if (c >= 'A' && c <= 'D') {
                return c - 'A';
            }
        }
        return -1;
    }

    /**
     * Pausa la ejecución hasta que el usuario presione Enter.
     */
    public void pausa() {
        System.out.print("\nPresiona Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Limpia la consola (simulado).
     */
    public void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Muestra mensaje de victoria.
     */
    public void mostrarVictoria() {
        System.out.println("\n======================================================");
        System.out.println("HAS RECOLECTADO LAS 36 MARAVILLAS");
        System.out.println("HAS GANADO EL JUEGO");
        System.out.println("El Multiverso te reconoce como el Viajero Eterno");
        System.out.println("======================================================\n");
    }

    /**
     * Muestra mensaje de derrota.
     */
    public void mostrarDerrota() {
        System.out.println("\n======================================================");
        System.out.println("HAS PERDIDO TODAS TUS UNIDADES");
        System.out.println("EL JUEGO HA TERMINADO");
        System.out.println("Vuelve a intentarlo y conquista el Multiverso");
        System.out.println("======================================================\n");
    }

    /**
     * Muestra el mensaje de despedida.
     */
    public void mostrarDespedida() {
        System.out.println("\n¡Gracias por jugar al Multiverso de Civilizaciones!");
        System.out.println("Que la historia te guie en tu proxima aventura.\n");
    }
}