package game.listeners;

import game.gameenvironment.GameLevel;
import game.gameobjects.Ball;
import game.gameobjects.Block;


/**
 * BlockRemover Class which is responsible to be a listener to every block needed to be removed.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game          game's Object
     * @param removedBlocks number of blockes already removed
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }

    /**
     * Method to update counter.
     *
     * @param counter is counter object
     */
    public void setCounter(Counter counter) {
        this.remainingBlocks = counter;
    }
}
