import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor fixedThreadPool =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<SearchTask> tasks = new ArrayList<>();
        List<List<Integer>> matriz = new ArrayList<>();
        List<Integer> row;
        int numeroBuscado, element;
        numeroBuscado= ThreadLocalRandom.current().nextInt(20)+1;

        System.out.printf("Se va a buscar el número %d\n",numeroBuscado);

        for (int i = 0; i < 5; i++) {
            row = new ArrayList<>();
            for(int h=0; h<5;h++){
                element = ThreadLocalRandom.current().nextInt(10)+1;
                System.out.printf("%d ",element); //quiero ver la matriz
                row.add(element);
            }
            System.out.println();
            matriz.add(row);
            tasks.add(new SearchTask(matriz.get(i),numeroBuscado,i+1));
        }


        try {
            Position resultado =
                    fixedThreadPool.invokeAny(tasks);
            System.out.printf("Resultado encontrado en fila %d columna: %d\n",
                    resultado.y,resultado.x);
        } catch (InterruptedException ignored) {
        } catch (ExecutionException e) {
            System.out.print("Resultado no encontrado\n");
        } finally {
            fixedThreadPool.shutdown();
        }






    }
}
