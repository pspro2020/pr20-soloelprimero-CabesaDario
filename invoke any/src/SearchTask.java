import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SearchTask implements Callable<Position> {
    private final List<Integer> row;
    private final int number;
    private final int yPosition;
    private int xPosition;

    SearchTask(List<Integer> row, int number, int yPosition) {
        this.row = row;
        this.number = number;
        this.yPosition = yPosition;
    }

    @Override
    public Position call() throws InterruptedException {
        return searchRow(row);
    }

    private Position searchRow(List<Integer> row) throws InterruptedException {
        for (int i = 0; i < row.size(); i++) {
            if(number == row.get(i)){
                xPosition=i;
                return new Position(xPosition+1, yPosition);
            }

            try {
                TimeUnit.MILLISECONDS.sleep((long) (1000*(ThreadLocalRandom.current().nextDouble(0.5)+0.5)));
            } catch (InterruptedException e) {
                System.out.printf("Búsqueda en la fila %d cancelada.\n", yPosition);
                throw new InterruptedException();
            }

        }

        System.out.printf("El hilo de la fila %d no encontró el número\n",yPosition);
        throw new RuntimeException("No se encontró el valor");
    }
}
