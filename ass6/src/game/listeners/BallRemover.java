package game.listeners;

import game.gameenvironment.GameLevel;
import game.gameobjects.Ball;
import game.gameobjects.Block;

/**
 * Class used as listener to ball removal.
 */
public class BallRemover implements HitListener {
    //Fields
    private Counter ballCounter;
    private GameLevel game;


    /**
     * Constructor.
     *
     * @param game        the game object
     * @param ballCounter ball's counter
     */
    public BallRemover(GameLevel game, Counter ballCounter) {
        this.game = game;
        this.ballCounter = ballCounter;

    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.ballCounter.decrease(1);
    }

    /**
     * Method to update ball's counter.
     *
     * @param counter updated counter
     */
    public void setCounter(Counter counter) {
        this.ballCounter = counter;
    }
}
