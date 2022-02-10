package game.gameenvironment;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.WinGame;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.listeners.Counter;

import java.util.List;

/**
 * Game flow to run all the levels.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private int gameWidth;
    private int gameHeight;
    private GUI gui;
    private Counter scoreCounter;
    private boolean flag;

    /**
     * Constructor.
     *
     * @param ar         annimation runner
     * @param ks         keyboard sensor
     * @param gui        gui for surface
     * @param gameWidth  game width size
     * @param gameHeight game height size
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, int gameWidth, int gameHeight) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.gui = gui;
        this.flag = false;
    }

    /**
     * Method to run all the levels.
     *
     * @param levels list of game levels
     */
    public void runLevels(List<LevelInformation> levels) {
        this.scoreCounter = new Counter();
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.scoreCounter);
            level.initialize();
            while (level.getRemainingBalls() > 0 && level.getRemainingBlocks() > 0) {
                level.run();
            }
            if (level.getRemainingBalls() == 0) {
                this.flag = true;
                break;
            }
        }
        //present the end game screen
        if (!this.flag) {
            WinGame winGame = new WinGame(this.scoreCounter, keyboardSensor);
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY, winGame, this.animationRunner));
        }
        this.gui.close();
    }
}
