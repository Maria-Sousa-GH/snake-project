

public class Main {
    public static void main(String[] args) throws InterruptedException {


        Sound sound = new Sound("sound.wav");
//      sound.play();

        Game game = new Game(33,27, 150);

        Background background = new Background();

        game.init();
        game.start();

    }
}



