package modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private int oro;
    private int tickets;
    private int poblacion;
    private int maxPoblacion = 25;
    private List<Unidad> unidades;
    private List<String> maravillasRecolectadas;
    private int ataqueExtra;
    private int defensaExtra;

    public Jugador() {
        this.oro = 100;
        this.tickets = 20;
        this.poblacion = 0;
        this.unidades = new ArrayList<>();
        this.maravillasRecolectadas = new ArrayList<>();
        this.ataqueExtra = 0;
        this.defensaExtra = 0;
    }

    public int getOro() { return oro; }
    public void setOro(int oro) { this.oro = Math.min(oro, 200); }
    public int getTickets() { return tickets; }
    public void setTickets(int tickets) { this.tickets = Math.min(tickets, 30); }
    public int getPoblacion() { return poblacion; }
    public int getMaxPoblacion() { return maxPoblacion; }
    public List<Unidad> getUnidades() { return unidades; }
    public List<String> getMaravillasRecolectadas() { return maravillasRecolectadas; }
    public int getAtaqueExtra() { return ataqueExtra; }
    public void setAtaqueExtra(int ataqueExtra) { this.ataqueExtra = ataqueExtra; }
    public int getDefensaExtra() { return defensaExtra; }
    public void setDefensaExtra(int defensaExtra) { this.defensaExtra = defensaExtra; }

    public void agregarMaravilla(String maravilla) {
        maravillasRecolectadas.add(maravilla);
    }

    public int getMaravillasCount() {
        return maravillasRecolectadas.size();
    }

    public boolean recolectarUnidad(Unidad u) {
        if (poblacion < maxPoblacion && oro >= 20) {
            unidades.add(u);
            poblacion++;
            oro -= 20;
            return true;
        }
        return false;
    }

    public void perderUnidades(int cantidad) {
        if (cantidad >= unidades.size()) {
            unidades.clear();
            poblacion = 0;
        } else {
            for (int i = 0; i < cantidad; i++) {
                unidades.remove(unidades.size() - 1);
            }
            poblacion = unidades.size();
        }
    }

    @Override
    public String toString() {
        return "Oro: " + oro + " | Tickets: " + tickets + " | Población: " + 
               poblacion + "/" + maxPoblacion + " | Maravillas: " + 
               getMaravillasCount() + "/36";
    }
}