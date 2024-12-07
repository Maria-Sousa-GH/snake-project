import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Game game = new Game(20, 20, 200);

        Background background = new Background();

        game.init();
        game.start();

    }
}



