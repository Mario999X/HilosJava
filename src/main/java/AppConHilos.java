import models.Capsula;
import models.Trabajador;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class AppConHilos {

    private static final int NUM_CAPSULAS = 5;
    private static final int NUM_HILOS = 2;

    public static void main(String[] args) throws InterruptedException {
        //initMisionRunnable();
        initMisionCallable();

    }

    private static void initMisionRunnable() {
        long tiempoEjecucionStart = System.currentTimeMillis();

        System.out.println("Preparando zonas de lanzamiento | Numero de Capsulas: " + NUM_CAPSULAS);
        System.out.println("Numero de trabajadores: " + NUM_HILOS);
        ArrayList<Capsula> capsulas = produceCapsulas();

        ExecutorService executor = Executors.newFixedThreadPool(NUM_HILOS);

        capsulas.forEach(capsula -> {
            executor.execute(new Trabajador(capsula));
        });
        executor.shutdown();

        long tiempoEjecucionEnd = System.currentTimeMillis();
        System.out.println("Mision completada en " + (tiempoEjecucionEnd - tiempoEjecucionStart) + "ms"); // Recuerdos de la version Kotlin
    }

    private static void initMisionCallable() throws InterruptedException {
        long tiempoEjecucionStart = System.currentTimeMillis();

        System.out.println("Preparando zonas de lanzamiento | Numero de Capsulas: " + NUM_CAPSULAS);
        System.out.println("Numero de trabajadores: " + NUM_HILOS);
        ArrayList<Capsula> capsulas = produceCapsulas();
        ExecutorService executor = Executors.newFixedThreadPool(NUM_HILOS);

        AtomicInteger pasajerosSalvados = new AtomicInteger();

        ArrayList<Trabajador> trabajadores = new ArrayList<>();

        capsulas.forEach(capsula -> {
            trabajadores.add(new Trabajador(capsula));
        });

        List<Future<Integer>> futures = executor.invokeAll(trabajadores);

        futures.forEach(future -> {
            try {
                pasajerosSalvados.addAndGet(future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        executor.shutdown();

        System.out.println("Pasajeros rescatados: " + pasajerosSalvados);
        long tiempoEjecucionEnd = System.currentTimeMillis();
        System.out.println("Mision completada en " + (tiempoEjecucionEnd - tiempoEjecucionStart) + "ms"); // Recuerdos de la version Kotlin x2
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
