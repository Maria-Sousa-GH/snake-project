

public class Main {
    public static void main(String[] args) throws InterruptedException {


        Sound sound = new Sound("sound.wav");
        sound.play();



        Game game = new Game(49,30, 150);

        Background background = new Background();


        game.init();
        game.start();

    }
}



