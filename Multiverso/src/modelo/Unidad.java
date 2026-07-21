package modelo;

public class Unidad {
    private String tipo;
    private int ataque;
    private int defensa;
    private String civilizacionOrigen;

    public Unidad(String tipo, String civilizacionOrigen) {
        this.tipo = tipo;
        this.civilizacionOrigen = civilizacionOrigen;
        if (tipo.equals("Ofensiva")) {
            this.ataque = 10;
            this.defensa = 3;
        } else {
            this.ataque = 3;
            this.defensa = 10;
        }
    }

    public String getTipo() { return tipo; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public String getCivilizacionOrigen() { return civilizacionOrigen; }

    @Override
    public String toString() {
        return tipo + " (" + civilizacionOrigen + ") [A:" + ataque + " D:" + defensa + "]";
    }
}