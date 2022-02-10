package game.gameenvironment;

import animation.AnimationRunner;
import animation.Animation;
import animation.EndGame;
import animation.KeyPressStoppableAnimation;
import animation.CountdownAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.Indicators.NameLevelIndicate;
import game.gameobjects.Ball;
import game.gameobjects.Block;
import game.gameobjects.Sprite;
import game.gameobjects.SpriteCollection;
import game.gameobjects.Collidable;
import game.gameobjects.Paddle;
import game.Indicators.ScoreIndicator;
import game.listeners.BallRemover;
import game.listeners.HitListener;
import game.listeners.BlockRemover;
import game.listeners.Counter;
import game.listeners.ScoreTrackingListener;
import geometricshapes.Point;
import geometricshapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * gameObjects.Game's class is responsible to be the environment of the game, contains all the objects of game ,
 * and union it all to one Unit.
 * Here we initialize the game and its parts and also run it.
 *
 * @author Roei Gehassi
 */
public class GameLevel implements Animation {
    private LevelInformation levelInformation;
    private AnimationRunner runner;
    private BallRemover ballRemover;
    private boolean running;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private BlockRemover blockRemover;
    private Counter score;
    private ScoreIndicator scoreIndicator;
    private ScoreTrackingListener scoreTracker;
    private static final int RADIUS = 5, PADDLE_HEIGHT = 20;
    private KeyboardSensor keyboard;
    private Paddle paddle;
    private List<Ball> ballLst;
    private List<HitListener> hitListeners;
    private Sleeper sleeper;
    private Animation pause;
    private Animation endGame;
    private Animation pauseKeyPress;
    private Animation endKeyPress;


    /**
     * GameLevel is responsible to activate each level and run it properly.
     *
     * @param levelInfo       the level information
     * @param keyboardSensor  keyboard sensor
     * @param animationRunner the runner of animation
     * @param score           current score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter score) {
        this.levelInformation = levelInfo;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.sprites = new SpriteCollection();
        this.sleeper = new Sleeper();
        this.environment = new GameEnvironment();
        this.ballsCounter = new Counter();
        this.blocksCounter = new Counter();
        this.score = score;
        this.scoreTracker = new ScoreTrackingListener(this.score);
        this.blockRemover = new BlockRemover(this, this.blocksCounter);
        this.hitListeners = new ArrayList<>();
        this.ballLst = new ArrayList<>();
        this.ballRemover = new BallRemover(this, this.ballsCounter);
        this.pause = new PauseScreen(this.keyboard);
        this.endGame = new EndGame(this.score, this.keyboard);
        this.pauseKeyPress = new KeyPressStoppableAnimation(this.keyboard,

                KeyboardSensor.SPACE_KEY, pause, this.runner);
        this.endKeyPress = new KeyPressStoppableAnimation(this.keyboard,
                KeyboardSensor.SPACE_KEY, endGame, this.runner);

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * adds the object to the environment.
     *
     * @param c is the gameObjects.Collidable's object
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite's object to the game.
     *
     * @param s is a sprite's object
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.levelInformation.drawBackground(d);
        int blockFlag = 0, ballFlag = 0;
        scoreIndicator.update(scoreTracker.getScore());
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(pauseKeyPress);
        }
        if (ballsCounter.getValue() == 0) {
            ballFlag = 1;
        }
        if (ballFlag == 1) {
            this.runner.run(endKeyPress);
            this.running = false;
        }
        if (this.blocksCounter.getValue() == 0) {
            this.score.increase(100);
            blockFlag = 1;
        }
        if (blockFlag == 1) {
            this.running = false;
        }
//        if (this.keyboard.isPressed("p")) {
//            this.runner.run(new PauseScreen(this.keyboard));
//        }
        this.blockRemover.setCounter(this.blocksCounter);
    }

    /**
     * @return int
     */
    public int getRemainingBlocks() {
        return this.blocksCounter.getValue();
    }

    /**
     * @return int
     */
    public int getRemainingBalls() {
        return this.ballsCounter.getValue();
    }


    /**
     * Initialize a new game: create the Blocks and gameObjects.Ball (and gameObjects.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        initializeDeathRegion();
        initializeBounds();
        initializeBlocks();
        initializePaddle();
        initializeIndicators();
        initializeBalls();
    }

    /**
     * Adding score indicator.
     */
    public void addScoreIndicator() {
        this.scoreIndicator = new ScoreIndicator(new Point(350, 18), this.score);
        this.addSprite(scoreIndicator);
    }

    /**
     * Assistance method to initialize the game.
     */

    public void initializePaddle() {
        this.paddle = new Paddle(this.keyboard, levelInformation.paddleSpeed(),
                this.levelInformation.paddleWidth(), PADDLE_HEIGHT, this.levelInformation.paddleColor());
        paddle.setBall(this.ballLst);
        paddle.addToGame(this);
    }

    /**
     * Initializing balls for game.
     */
    public void initializeBalls() {
        double ballX = this.paddle.getX() + (double) (this.levelInformation.paddleWidth() / 2);
        double ballY = this.paddle.getY() - 10.0;
        this.ballsCounter.increase(this.levelInformation.numberOfBalls());
        this.ballRemover = new BallRemover(this, this.ballsCounter);
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(ballX, ballY, RADIUS, new Color(245, 245, 245), this.environment);
            this.ballLst.add(ball);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
    }

    /**
     * initialize blocks per game.
     */
    public void initializeBlocks() {
        List<Block> blockList = this.levelInformation.blocks();
        this.blocksCounter.increase(this.levelInformation.numberOfBlocksToRemove());
        for (Block block : blockList) {
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTracker);
            block.addToGame(this);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        // this.runner.run(pauseKeyPress);
        this.runner.run(new CountdownAnimation(3.0, 3, this.sprites, this.levelInformation));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Sets the game bounds - so the ball wont leave the game environment.
     */
    public void initializeBounds() {

        Rectangle rectangle1 = new Rectangle(new Point(0, 20), 800, 20);
        Rectangle rectangle2 = new Rectangle(new Point(0, 0), 25, 600);
        Rectangle rectangle3 = new Rectangle(new Point(775, 0), 25, 600);
        Block block1 = new Block(rectangle1, Color.gray, Color.gray);
        Block block2 = new Block(rectangle2, Color.gray, Color.gray);
        Block block3 = new Block(rectangle3, Color.gray, Color.gray);
        block1.addToGame(this);
        block2.addToGame(this);
        block3.addToGame(this);
    }

    /**
     * initialize indicators per game.
     */
    public void initializeIndicators() {
        Rectangle indicatorsRect = new Rectangle((new Point(0, 0)), 800, 20);
        Block indicatorsBlock = new Block(indicatorsRect, Color.LIGHT_GRAY, Color.lightGray);
        indicatorsBlock.addToGame(this);
        addScoreIndicator();
        NameLevelIndicate nameLevel = new NameLevelIndicate(new Point(600, 18), levelInformation.levelName());
        this.addSprite(nameLevel);
        this.scoreIndicator.update(this.scoreTracker.getScore());

    }

    /**
     * initialize blocks per game.
     */
    public void initializeDeathRegion() {
        Rectangle rectangle4 = new Rectangle(new Point(0, 590), 800, 10);
        //"death-region"
        Block deathRegion = new Block(rectangle4, Color.gray, Color.gray);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(this.ballRemover);
//        this.addSprite(this.levelInformation.getBackground());
    }

    /**
     * Remove collidable method.
     *
     * @param c the object
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite method.
     *
     * @param s sprite object
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}
