package game.listeners;

import game.gameobjects.Ball;
import game.gameobjects.Block;


/**
 * Listener class to score tracking.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter is counter object
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }

    /**
     * get score.
     *
     * @return current counter
     */
    public Counter getScore() {
        return this.currentScore;
    }
}
