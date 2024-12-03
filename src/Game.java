import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;
import com.codeforall.online.simplegraphics.keyboard.Keyboard;
import com.codeforall.online.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.online.simplegraphics.keyboard.KeyboardEventType;
import com.codeforall.online.simplegraphics.keyboard.KeyboardHandler;

import java.util.ArrayList;

public class Game implements KeyboardHandler {

    private Grid grid;
    private int delay;
    private ArrayList<SnakeParts> snakelist;
    private Keyboard keyboard;
    private FoodPosition foodPosition;
    private Direction firstDir;
    private Direction currDir;
    private boolean disableKey;


    public Game(int cols, int rows, int delay) {

        grid = new Grid(cols, rows);
        this.delay = delay;
        this.snakelist = new ArrayList<>();
    }

    public void snakeInit() {

        Position snakeHeadPos = new Position(grid);
        int a = grid.getCols()/2;
        int b = grid.getRows()/2;
        snakeHeadPos.setRow(grid.getRows()/2);
        snakeHeadPos.setCol(grid.getCols()/2);


        Rectangle headRectangle = new Rectangle(grid.columnToX(snakeHeadPos.getCol()), grid.rowToY(snakeHeadPos.getRow()), grid.getCellSize(), grid.getCellSize());

        headRectangle.fill();
        snakeHeadPos.setRectangle(headRectangle);
        SnakeParts headSnake = new SnakeParts(snakeHeadPos);
        headSnake.setDirection(Direction.values()[(int) (Math.random() * Direction.values().length)]);

        snakelist.add(headSnake);
    }

    public void foodInit(){
        this.foodPosition = new FoodPosition(grid);
    }

    public void init() {

        grid.init();
        keyInit();
        snakeInit();
        foodInit();
    }

    public void keyInit() {

        keyboard = new Keyboard(this);

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
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void start() throws InterruptedException {

        while (true) {
            // Pause for a while
            Thread.sleep(delay);

            colisionDetector();
            foodColision();

            moveSnake();
            disableKey = false;

        }
    }

    public void moveSnake() {
        for (SnakeParts s : snakelist) {

            s.move();
        }
        passDirection();
    }

    public void passDirection() {


        for (SnakeParts r : snakelist) {

            if (r.equals(snakelist.get(0))) {

                firstDir = r.getDirection();
                currDir = firstDir;

            } else {

                currDir = r.getDirection();
                r.setDirection(firstDir);
                firstDir = currDir;
            }
        }


    }

    public void eat() {

        Position pos = new Position(grid);
        SnakeParts snake = new SnakeParts(pos);

        SnakeParts first = snakelist.get(0);

        switch (first.getDirection()) {

            case UP:
                snake.getPos().setCol(first.getPos().getCol());
                snake.getPos().setRow(first.getPos().getRow() - 1);
                snake.setDirection(Direction.UP);
                break;
            case DOWN:
                snake.getPos().setCol(first.getPos().getCol());
                snake.getPos().setRow(first.getPos().getRow() + 1);
                snake.setDirection(Direction.DOWN);
                break;
            case LEFT:
                snake.getPos().setCol(first.getPos().getCol() - 1);
                snake.getPos().setRow(first.getPos().getRow());
                snake.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                snake.getPos().setCol(first.getPos().getCol() + 1);
                snake.getPos().setRow(first.getPos().getRow());
                snake.setDirection(Direction.RIGHT);
                break;
            default:
                snake.getPos().setCol(first.getPos().getCol() - 1);
                snake.getPos().setRow(first.getPos().getRow());
                snake.setDirection(Direction.UP);
        }

        Rectangle rec = new Rectangle(grid.columnToX(snake.getPos().getCol()), grid.rowToY(snake.getPos().getRow()), grid.getCellSize(), grid.getCellSize());
        rec.fill();
        snake.getPos().setRectangle(rec);
        snakelist.add(0, snake);

    }

    public void foodColision() {
//   a = food position    /  B=  snake head

        if ((grid.columnToX(snakelist.get(0).getPos().getCol()) >= grid.columnToX(foodPosition.getCols())) &&                                                            // from left
                (grid.columnToX(snakelist.get(0).getPos().getCol()) + grid.getCellSize()) <= (grid.columnToX(foodPosition.getCols()) + grid.getCellSize()) &&              //from right
                grid.rowToY(snakelist.get(0).getPos().getRow()) >= grid.rowToY(foodPosition.getRows()) &&                                                          // from top
                ((grid.rowToY(snakelist.get(0).getPos().getRow()) + grid.getCellSize()) <= (grid.rowToY(foodPosition.getRows()) + grid.getCellSize()))) {             //   from buttom

            eat();
            foodPosition.createFood();
        }
    }

    public void colisionDetector() {

        for (SnakeParts c : snakelist) {
            if (!c.equals(snakelist.get(0))){

                if ((grid.columnToX(snakelist.get(0).getPos().getCol()) >= grid.columnToX(c.getPos().getCol())) &&                                                              // from left
                        (grid.columnToX(snakelist.get(0).getPos().getCol()) + grid.getCellSize()) <= (grid.columnToX(c.getPos().getCol()) + grid.getCellSize()) &&              //from right
                        grid.rowToY(snakelist.get(0).getPos().getRow()) >= grid.rowToY(c.getPos().getRow()) &&                                                                  // from top
                        ((grid.rowToY(snakelist.get(0).getPos().getRow()) + grid.getCellSize()) <= (grid.rowToY(c.getPos().getRow()) + grid.getCellSize()))) {                  //from bottom

                    for (SnakeParts n: snakelist){
                        n.setCrashed();
                        n.getPos().setRecColor(Color.RED);
                    }

                }
            }
        }

        if ((grid.columnToX(snakelist.get(0).getPos().getCol()) <= 10  && snakelist.get(0).getDirection() == Direction.LEFT )||                                                          // collide with left limit wall
                ((grid.columnToX(snakelist.get(0).getPos().getCol())+15) >= grid.columnToX(grid.getCols()) && snakelist.get(0).getDirection() == Direction.RIGHT)||                     // collide with right limit wall
                (grid.rowToY(snakelist.get(0).getPos().getRow()) <= 10 && snakelist.get(0).getDirection() == Direction.UP)||                                                         // collide with top limit wall
                (((grid.rowToY(snakelist.get(0).getPos().getRow()) + grid.getCellSize()) >= grid.rowToY(grid.getRows()))&& snakelist.get(0).getDirection() == Direction.DOWN)) {       // collide with bottom limit wall



            for (SnakeParts n: snakelist){
                n.setCrashed();
                n.getPos().setRecColor(Color.RED);
            }

        }

    }

}
