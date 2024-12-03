package mechanics;

import com.codeforall.online.simplegraphics.keyboard.Keyboard;
import com.codeforall.online.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.online.simplegraphics.keyboard.KeyboardEventType;
import com.codeforall.online.simplegraphics.keyboard.KeyboardHandler;
import grid.snakePosition;

public class MyKeyboard implements KeyboardHandler {

    private Keyboard keyboard;
    private snakePosition snake;
    private Direction currentDirection;

    public void setSnake(snakePosition snake) {
        this.snake = snake;
    }

    public void init() {
        keyboard = new Keyboard(this);

        createKeyEvent(KeyboardEvent.KEY_RIGHT, Direction.RIGHT);
        createKeyEvent(KeyboardEvent.KEY_LEFT, Direction.LEFT);
        createKeyEvent(KeyboardEvent.KEY_UP, Direction.UP);
        createKeyEvent(KeyboardEvent.KEY_DOWN, Direction.DOWN);

        //startMovementLoop();
    }

    private void createKeyEvent(int key, Direction direction) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(key);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(event);
    }

    //a thread para continuar em movimento após um click
   /* private void startMovementLoop() {
        new Thread(() -> {
            while (true) {
                if (snake != null && currentDirection != null) {
                    switch (currentDirection) {
                        case RIGHT:
                            snake.move();
                            break;
                        case LEFT:
                            snake.getPosition().moveLeft();
                            break;
                        case UP:
                            snake.getPosition().moveUp();
                            break;
                        case DOWN:
                            snake.getPosition().moveDown();
                            break;
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    */

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                currentDirection = Direction.RIGHT;
                break;
            case KeyboardEvent.KEY_LEFT:
                currentDirection = Direction.LEFT;
                break;
            case KeyboardEvent.KEY_UP:
                currentDirection = Direction.UP;
                break;
            case KeyboardEvent.KEY_DOWN:
                currentDirection = Direction.DOWN;
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }
}

