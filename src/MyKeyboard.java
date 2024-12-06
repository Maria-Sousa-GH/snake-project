import com.codeforall.online.simplegraphics.keyboard.Keyboard;
import com.codeforall.online.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.online.simplegraphics.keyboard.KeyboardEventType;
import com.codeforall.online.simplegraphics.keyboard.KeyboardHandler;
import java.util.ArrayList;
public class MyKeyboard implements KeyboardHandler {

    private Keyboard keyboard;
    private ArrayList<SnakeParts> snakelist;
    private boolean disableKey;

    public MyKeyboard(ArrayList<SnakeParts> snakelist) {
        this.keyboard = new Keyboard(this);
        this.snakelist = snakelist;
        disableKey = false;
        keyInit();
    }
    public void keyInit() {

        KeyboardEvent goRight = new KeyboardEvent();
        goRight.setKey(KeyboardEvent.KEY_RIGHT);
        goRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(goRight);

        KeyboardEvent goLeft = new KeyboardEvent();
        goLeft.setKey(KeyboardEvent.KEY_LEFT);
        goLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(goLeft);

        KeyboardEvent goUp = new KeyboardEvent();
        goUp.setKey(KeyboardEvent.KEY_UP);
        goUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(goUp);

        KeyboardEvent goDown = new KeyboardEvent();
        goDown.setKey(KeyboardEvent.KEY_DOWN);
        goDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(goDown);
    }
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (!disableKey) {

            if ((keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) && (!snakelist.get(0).getDirection().isOpposite(Direction.RIGHT))) {
                snakelist.get(0).setDirection(Direction.RIGHT);
                disableKey = true;

            } else if ((keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) && (!snakelist.get(0).getDirection().isOpposite(Direction.LEFT))) {

                snakelist.get(0).setDirection(Direction.LEFT);
                disableKey = true;

            } else if ((keyboardEvent.getKey() == KeyboardEvent.KEY_UP) && (!snakelist.get(0).getDirection().isOpposite(Direction.UP))) {

                snakelist.get(0).setDirection(Direction.UP);
                disableKey = true;

            } else if ((keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) && (!snakelist.get(0).getDirection().isOpposite(Direction.DOWN))) {

                snakelist.get(0).setDirection(Direction.DOWN);
                disableKey = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {}

    public void setDisableKey(boolean state){ this.disableKey = state;}

    public boolean getDisablekey(){return this.disableKey;}
}
