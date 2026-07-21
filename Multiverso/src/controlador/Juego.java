package controlador;

import combate.Batalla;
import combate.Estadisticas;
import grafo.Grafo;
import modelo.*;
import vista.Consola;

import java.util.List;
import java.util.Random;

/**
 * Clase orquestadora principal del juego.
 */
public class Juego {
    private Grafo grafo;
    private Jugador jugador;
    private Consola consola;
    private String civilizacionActual;
    private String dificultad;
    private boolean juegoTerminado;
    private final int[] costosMejoraAtaque = {30, 40, 55, 70, 90};
    private final int[] costosMejoraDefensa = {30, 40, 55, 70, 90};
    private int nivelMejoraAtaque;
    private int nivelMejoraDefensa;

    public Juego() {
        this.consola = new Consola();
        this.juegoTerminado = false;
        this.nivelMejoraAtaque = 0;
        this.nivelMejoraDefensa = 0;
    }

    public void iniciar() {
        consola.mostrarBienvenida();
        consola.mostrarInfo("Cargando el Multiverso...");
        grafo = DatosCivilizaciones.cargarGrafo();
        
        if (grafo.esConexo()) {
            consola.mostrarExito("El Multiverso está conectado. ¡Puedes viajar a cualquier civilización!");
        } else {
            consola.mostrarError("El Multiverso no está conectado. Revisa el grafo.");
            return;
        }
        
        jugador = new Jugador();
        elegirDificultad();
        elegirCivilizacionInicial();
        
        while (!juegoTerminado) {
            consola.limpiarConsola();
            consola.mostrarBienvenida();
            consola.mostrarEstadoJugador(jugador, civilizacionActual, grafo);
            consola.mostrarMenu();
            int opcion = consola.leerOpcion();
            procesarOpcion(opcion);
            verificarCondiciones();
            if (!juegoTerminado) {
                consola.pausa();
            }
        }
    }

    private void elegirDificultad() {
        consola.mostrarInfo("Elige la dificultad:");
        System.out.println(" [1] Fácil");
        System.out.println(" [2] Difícil");
        System.out.print("Opción: ");
        int opcion = consola.leerOpcion();
        if (opcion == 1) {
            dificultad = "Fácil";
            jugador.setOro(jugador.getOro() + 50);
            for (Civilizacion c : grafo.getCivilizaciones()) {
                c.setDefensaCiudad(c.getDefensaCiudad() - 10);
            }
            consola.mostrarExito("Dificultad Fácil seleccionada. +50 de oro inicial y ciudades más débiles.");
        } else {
            dificultad = "Difícil";
            jugador.setOro(jugador.getOro() - 20);
            for (Civilizacion c : grafo.getCivilizaciones()) {
                c.setDefensaCiudad(c.getDefensaCiudad() + 10);
            }
            consola.mostrarExito("Dificultad Difícil seleccionada. -20 de oro inicial y ciudades más fuertes.");
        }
    }

    private void elegirCivilizacionInicial() {
        List<String> nombres = grafo.getNombresCivilizaciones().stream().toList();
        Random rand = new Random();
        civilizacionActual = nombres.get(rand.nextInt(nombres.size()));
        consola.mostrarExito("Comienzas tu viaje en: " + civilizacionActual);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> viajar();
            case 2 -> reclutar();
            case 3 -> atacar();
            case 4 -> comerciar();
            case 5 -> consola.mostrarEstadisticasDetalladas(jugador);
            case 6 -> consola.mostrarMapa(grafo);
            case 7 -> salir();
            default -> consola.mostrarError("Opción inválida. Intenta de nuevo.");
        }
    }

    private void viajar() {
        List<Conexion> conexiones = grafo.getConexiones(civilizacionActual);
        if (conexiones.isEmpty()) {
            consola.mostrarError("No hay conexiones desde esta civilización.");
            return;
        }
        if (jugador.getTickets() <= 0) {
            consola.mostrarError("No tienes tickets de viaje. Compra más en el comercio.");
            return;
        }
        consola.mostrarDestinos(civilizacionActual, grafo);
        System.out.print("Elige un destino (0 para cancelar): ");
        int opcion = consola.leerOpcion();
        if (opcion == 0) return;
        if (opcion < 1 || opcion > conexiones.size()) {
            consola.mostrarError("Opción inválida.");
            return;
        }
        String destino = conexiones.get(opcion - 1).getDestino();
        jugador.setTickets(jugador.getTickets() - 1);
        consola.mostrarViaje(civilizacionActual, destino);
        civilizacionActual = destino;
    }

    private void reclutar() {
        Civilizacion actual = grafo.getCivilizacion(civilizacionActual);
        if (jugador.getPoblacion() >= jugador.getMaxPoblacion()) {
            consola.mostrarError("Has alcanzado el límite de población (25). No puedes reclutar más.");
            return;
        }
        if (jugador.getOro() < 20) {
            consola.mostrarError("No tienes suficiente oro. Necesitas 20 de oro para reclutar.");
            return;
        }
        String tipo = actual.getTipo();
        Unidad nueva = new Unidad(tipo, actual.getNombre());
        jugador.recolectarUnidad(nueva);
        consola.mostrarReclutamiento(tipo, actual.getNombre(), 20);
    }

    private void atacar() {
        Civilizacion ciudad = grafo.getCivilizacion(civilizacionActual);
        if (jugador.getUnidades().isEmpty()) {
            consola.mostrarError("No tienes unidades para atacar. ¡Recluta algunas!");
            return;
        }
        consola.mostrarInfo("Preparando ataque a " + ciudad.getNombre() + "...");
        consola.mostrarInfo("Tu ataque: " + Estadisticas.getAtaqueTotal(jugador));
        consola.mostrarInfo("Defensa de la ciudad: " + ciudad.getDefensaCiudad());
        System.out.print("\n¿Deseas continuar con el ataque? (s/n): ");
        String confirm = consola.leerTexto();
        if (!confirm.equalsIgnoreCase("s")) {
            consola.mostrarInfo("Ataque cancelado.");
            return;
        }
        Batalla.Resultado resultado = Batalla.pelear(jugador, ciudad);
        consola.mostrarResultadoBatalla(resultado, ciudad.getNombre());
        if (resultado.isVictoria()) {
            consola.mostrarExito("Has conquistado " + ciudad.getNombre() + " y obtenido su maravilla: " + ciudad.getMaravilla());
        }
    }

    private void comerciar() {
        consola.mostrarMenuComercio(
            obtenerCostoMejoraAtaque(),
            obtenerCostoMejoraDefensa(),
            obtenerMejorasRestantesAtaque(),
            obtenerMejorasRestantesDefensa(),
            puedeMejorarAtaque(),
            puedeMejorarDefensa()
        );
        int opcion = consola.leerOpcion();
        switch (opcion) {
            case 1 -> comprarTickets();
            case 2 -> realizarMision();
            case 3 -> mejorarAtaque();
            case 4 -> mejorarDefensa();
            case 5 -> consola.mostrarInfo("Volviendo al menú principal.");
            default -> consola.mostrarError("Opción inválida.");
        }
    }

    private void comprarTickets() {
        if (jugador.getOro() < 15) {
            consola.mostrarError("No tienes suficiente oro. Necesitas 15 de oro.");
            return;
        }
        if (jugador.getTickets() >= 30) {
            consola.mostrarError("Ya tienes el máximo de tickets (30).");
            return;
        }
        jugador.setOro(jugador.getOro() - 15);
        int nuevosTickets = Math.min(jugador.getTickets() + 3, 30);
        jugador.setTickets(nuevosTickets);
        consola.mostrarExito("Has comprado 3 tickets. Tickets totales: " + jugador.getTickets());
    }

    private void realizarMision() {
        Mision mision = Mision.generarAleatoria();
        consola.mostrarMision(mision);
        int respuesta = consola.leerRespuestaMision();
        if (respuesta == -1) {
            consola.mostrarError("Respuesta inválida.");
            return;
        }
        boolean acerto = (respuesta == mision.getRespuestaCorrecta());
        int oroGanado = 0;
        if (acerto) {
            oroGanado = (int)(Math.random() * (mision.getRecompensaMax() - mision.getRecompensaMin() + 1)) 
                        + mision.getRecompensaMin();
            jugador.setOro(jugador.getOro() + oroGanado);
        }
        consola.mostrarResultadoMision(acerto, oroGanado);
    }

    private void mejorarAtaque() {
        if (!puedeMejorarAtaque()) {
            consola.mostrarError("Ya alcanzaste el máximo de mejoras de ataque.");
            return;
        }
        int costo = obtenerCostoMejoraAtaque();
        if (jugador.getOro() < costo) {
            consola.mostrarError("No tienes suficiente oro. Necesitas " + costo + " de oro.");
            return;
        }
        jugador.setOro(jugador.getOro() - costo);
        jugador.setAtaqueExtra(jugador.getAtaqueExtra() + 1);
        nivelMejoraAtaque++;
        consola.mostrarExito("Ataque mejorado permanentemente en +1. Ataque total extra: " + jugador.getAtaqueExtra());
    }

    private void mejorarDefensa() {
        if (!puedeMejorarDefensa()) {
            consola.mostrarError("Ya alcanzaste el máximo de mejoras de defensa.");
            return;
        }
        int costo = obtenerCostoMejoraDefensa();
        if (jugador.getOro() < costo) {
            consola.mostrarError("No tienes suficiente oro. Necesitas " + costo + " de oro.");
            return;
        }
        jugador.setOro(jugador.getOro() - costo);
        jugador.setDefensaExtra(jugador.getDefensaExtra() + 1);
        nivelMejoraDefensa++;
        consola.mostrarExito("Defensa mejorada permanentemente en +1. Defensa total extra: " + jugador.getDefensaExtra());
    }

    private int obtenerCostoMejoraAtaque() {
        return costosMejoraAtaque[Math.min(nivelMejoraAtaque, costosMejoraAtaque.length - 1)];
    }

    private int obtenerCostoMejoraDefensa() {
        return costosMejoraDefensa[Math.min(nivelMejoraDefensa, costosMejoraDefensa.length - 1)];
    }

    private boolean puedeMejorarAtaque() {
        return nivelMejoraAtaque < costosMejoraAtaque.length;
    }

    private boolean puedeMejorarDefensa() {
        return nivelMejoraDefensa < costosMejoraDefensa.length;
    }

    private int obtenerMejorasRestantesAtaque() {
        return costosMejoraAtaque.length - nivelMejoraAtaque;
    }

    private int obtenerMejorasRestantesDefensa() {
        return costosMejoraDefensa.length - nivelMejoraDefensa;
    }

    private void verificarCondiciones() {
        if (jugador.getMaravillasCount() >= 36) {
            consola.mostrarVictoria();
            juegoTerminado = true;
            return;
        }
        if (jugador.getUnidades().isEmpty() && jugador.getOro() < 20) {
            consola.mostrarDerrota();
            juegoTerminado = true;
            return;
        }
        if (jugador.getUnidades().isEmpty() && jugador.getOro() >= 20) {
            consola.mostrarInfo("No tienes unidades, pero tienes oro para reclutar. ¡Aprovecha!");
        }
    }

    private void salir() {
        System.out.print("¿Estás seguro de que quieres salir? (s/n): ");
        String confirm = consola.leerTexto();
        if (confirm.equalsIgnoreCase("s")) {
            consola.mostrarDespedida();
            juegoTerminado = true;
        } else {
            consola.mostrarInfo("Continuando el viaje...");
        }
    }
}