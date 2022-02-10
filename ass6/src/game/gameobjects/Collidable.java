package game.gameobjects;

import game.gameenvironment.Velocity;
import geometricshapes.Point;
import geometricshapes.Rectangle;

/**
 * Interface gameObjects Collidable.
 */
public interface Collidable {
    /**
     * @return Return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter
     * @param collisionPoint  the collision possible point between the gameObjects.Collidable and another object
     * @param currentVelocity current veloicty of the other object
     * @param hitter          the ball object
     * @return a new velocity accordingly
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
