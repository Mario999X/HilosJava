package models;

public class Capsula {
    private int id;
    private int pasajeros;
    private int tiempoLanzamiento;

    // Constructor
    public Capsula(int id) {
        this.id = id;
        pasajeros = (int) (15 + Math.random() * 50);
        tiempoLanzamiento = (int) ((5 + Math.random() * 10) * 1000);
    }

    // GET & SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    public int getTiempoLanzamiento() {
        return tiempoLanzamiento;
    }

    public void setTiempoLanzamiento(int tiempoLanzamiento) {
        this.tiempoLanzamiento = tiempoLanzamiento;
    }
}
