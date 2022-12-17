package models;

import java.util.concurrent.Callable;

public class Trabajador implements Runnable, Callable<Integer> {

    private final Capsula c;

    public Trabajador(Capsula c) {
        this.c = c;
    }

    @Override
    public void run() {
        System.out.println("Trabajador: " + Thread.currentThread().getName() + " con la capsula: " + c.getId() + " con "
                + c.getPasajeros() + " pasajeros \nTiempo de lanzamiento: " + c.getTiempoLanzamiento()
                + "\n-----------------------------------------------------------------");
        try {
            Thread.sleep(c.getTiempoLanzamiento());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\tTrabajador: " + Thread.currentThread().getName() + " termino de preparar la capsula " + c.getId());
    }

    @Override
    public Integer call() {
        int pasajerosSalvados = 0;

        System.out.println("Trabajador: " + Thread.currentThread().getName() + " con la capsula: " + c.getId() + " con "
                + c.getPasajeros() + " pasajeros \nTiempo de lanzamiento: " + c.getTiempoLanzamiento()
                + "\n-----------------------------------------------------------------");

        pasajerosSalvados += c.getPasajeros();

        try {
            Thread.sleep(c.getTiempoLanzamiento());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\tTrabajador: " + Thread.currentThread().getName() + " termino de preparar la capsula " + c.getId());

        return pasajerosSalvados;
    }
}
