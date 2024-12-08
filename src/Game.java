import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;
import com.codeforall.online.simplegraphics.graphics.Text;

import java.util.ArrayList;

public class Game {

    private Grid grid;
    private int delay;
    private ArrayList<SnakeParts> snakelist;
    private MyKeyboard keyboard;
    private FoodPosition foodPosition;
    private Direction firstDir;
    private Direction currDir;
    private int score;
    private int[] highScores;
    private FileManagement file;
    private boolean borderless;


    public Game(int cols, int rows, int delay) {

        grid = new Grid(cols, rows);
        this.delay = delay;
        this.snakelist = new ArrayList<>();
        keyboard = new MyKeyboard(snakelist);
        highScores = new int[10];
        file = new FileManagement();

        this.borderless = false;
    }

    public void snakeInit() {

        Position snakeHeadPos = new Position(grid);
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
        snakeInit();
        foodInit();
        highScores = file.getScores();

        Text title = new Text(Grid.PADDING + 250,grid.rowToY(grid.getRows())+5," S N A K E_4 A L L ");
        title.grow(15,15);
        title.translate(title.getX()+(Grid.PADDING-title.getX()),10);
        title.setColor(Color.WHITE);
        title.draw();



    }

    public void start() throws InterruptedException {

        while (true) {
            // Pause for a while
            Thread.sleep(delay);

            collisionDetector();
            foodCollision();
            moveSnake();
            keyboard.setDisableKey(false);
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

    public void foodCollision() {
//   a = food position    /  B=  snake head

        if ((grid.columnToX(snakelist.get(0).getPos().getCol()) >= grid.columnToX(foodPosition.getCols())) &&                                                            // from left
                (grid.columnToX(snakelist.get(0).getPos().getCol()) + grid.getCellSize()) <= (grid.columnToX(foodPosition.getCols()) + grid.getCellSize()) &&              //from right
                grid.rowToY(snakelist.get(0).getPos().getRow()) >= grid.rowToY(foodPosition.getRows()) &&                                                          // from top
                ((grid.rowToY(snakelist.get(0).getPos().getRow()) + grid.getCellSize()) <= (grid.rowToY(foodPosition.getRows()) + grid.getCellSize()))) {             //   from buttom

            eat();
            this.score+=10;
            System.out.println("Game score: "+score);
            foodPosition.createFood();
        }
    }

    public void collisionDetector() {
        //collision with snake body
        for (SnakeParts c : snakelist) {
            if (!c.equals(snakelist.get(0))){

                if ((grid.columnToX(snakelist.get(0).getPos().getCol()) >= grid.columnToX(c.getPos().getCol())) &&                                                              // from left
                        (grid.columnToX(snakelist.get(0).getPos().getCol()) + grid.getCellSize()) <= (grid.columnToX(c.getPos().getCol()) + grid.getCellSize()) &&              //from right
                        grid.rowToY(snakelist.get(0).getPos().getRow()) >= grid.rowToY(c.getPos().getRow()) &&                                                                  // from top
                        ((grid.rowToY(snakelist.get(0).getPos().getRow()) + grid.getCellSize()) <= (grid.rowToY(c.getPos().getRow()) + grid.getCellSize()))) {//from bottom

                    if (!snakelist.get(0).isCrashed()) {
                        gameOver();
                    }
                    for (SnakeParts n: snakelist){
                        n.setCrashed();
                        n.getPos().setRecColor(Color.RED);
                    }
                }
            }
        }
        // collision with borders if not borderless
        if (!borderless){
            if ((grid.columnToX(snakelist.get(0).getPos().getCol()) <= 10  && snakelist.get(0).getDirection() == Direction.LEFT )||                                                          // collide with left limit wall
                    ((grid.columnToX(snakelist.get(0).getPos().getCol())+15) >= grid.columnToX(grid.getCols()) && snakelist.get(0).getDirection() == Direction.RIGHT)||                     // collide with right limit wall
                    (grid.rowToY(snakelist.get(0).getPos().getRow()) <= 10 && snakelist.get(0).getDirection() == Direction.UP)||                                                         // collide with top limit wall
                    (((grid.rowToY(snakelist.get(0).getPos().getRow()) + grid.getCellSize()) >= grid.rowToY(grid.getRows()))&& snakelist.get(0).getDirection() == Direction.DOWN)) {       // collide with bottom limit wall

                if (!snakelist.get(0).isCrashed()) {
                    gameOver();
                }
                for (SnakeParts n: snakelist){
                    n.setCrashed();
                    n.getPos().setRecColor(Color.RED);
                }
            }
        }else{
            // pass by left limit wall
            if (grid.columnToX(snakelist.get(0).getPos().getCol()) <= 10  && snakelist.get(0).getDirection() == Direction.LEFT ){

            }

        }


    }

    public void gameOver(){
// insert new score if belongs to the high scores. sort it and write to file
        boolean scorePresent = false;

        for (int ii= 0; ii< highScores.length;ii++){
            if (score == highScores[ii]){
                scorePresent = true;
            }
        }
        if (!scorePresent){
            for (int i=0; i<highScores.length;i++){
                int savedScore;
                int secSavedScore;
                if (score > highScores[i]){
                    savedScore = highScores[i];
                    highScores[i] = score;
                    for (int j= i+1;j<highScores.length; j++){
                        secSavedScore = highScores[j];
                        highScores[j]=savedScore;
                        savedScore = secSavedScore;
                        secSavedScore =0;
                    }
                    break;
                }
            }
        }
        System.out.println("Final Game Score: "+score+"\nHighscores: ");
        for (int k=0; k<highScores.length;k++){
            System.out.println(highScores[k]);
        }

        file.saveScores(highScores);
    }


}
