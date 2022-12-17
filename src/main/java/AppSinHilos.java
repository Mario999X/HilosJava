import models.Capsula;

import java.util.ArrayList;

public class AppSinHilos {
    private static final int NUM_CAPSULAS = 5;

    public static void main(String[] args) {
        long tiempoEjecucionStart = System.currentTimeMillis();
        initMision();
        long tiempoEjecucionEnd = System.currentTimeMillis();

        System.out.println("Mision completada en " + (tiempoEjecucionEnd - tiempoEjecucionStart) + "ms");
    }

    private static void initMision() {
        System.out.println("Preparando zonas de lanzamiento | Numero de Capsulas: " + NUM_CAPSULAS);
        ArrayList<Capsula> capsulas = produceCapsulas();

        capsulas.forEach(capsula -> {
            System.out.println("Capsula: " + capsula.getId() + " | Pasajeros: " + capsula.getPasajeros());
            System.out.println("Lanzando capsula, tiempo estimado: " + capsula.getTiempoLanzamiento() + "ms");
            try {
                Thread.sleep(capsula.getTiempoLanzamiento());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private static ArrayList<Capsula> produceCapsulas() {
        int contadorId = 0;

        ArrayList<Capsula> listadoCapsulas = new ArrayList<>();

        for (int i = 0; i < NUM_CAPSULAS; i++) {
            contadorId++;
            listadoCapsulas.add(new Capsula(contadorId));
        }
        return listadoCapsulas;
    }

}
