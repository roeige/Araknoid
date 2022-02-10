package game.gameobjects;

import geometricshapes.Point;

/**
 * Class Collisision point responsible for the collision point between the ball and an object
 * Class contains the collision object and also the collision geometric.shapes.Point , as fields in its object.
 *
 * @author Roei Gehassi
 */
public class CollisionInfo {
    //Fields
    private Collidable collisionObject;
    private Point collisionPoint;

    /**
     * Constructor.
     *
     * @param collidableObject the collidable object
     * @param collisionPoint   collisionPoint object
     */
    public CollisionInfo(Collidable collidableObject, Point collisionPoint) {
        this.collisionObject = collidableObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

}
