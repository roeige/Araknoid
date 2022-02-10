package game.listeners;

import game.gameobjects.Ball;
import game.gameobjects.Block;

/**
 * Hit listener interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit Block object
     * @param hitter   ball object
     */
    void hitEvent(Block beingHit, Ball hitter);
}
