package game.gameenvironment;

import game.gameobjects.Collidable;
import game.gameobjects.CollisionInfo;
import geometricshapes.Line;
import geometricshapes.Point;

import java.util.ArrayList;

/**
 * Class game environment responsible to gather all the collidables of the game
 * in addition the class knows to return collision information with other objects.
 *
 * @author Roei Gehassi
 */
public class GameEnvironment {
    //fields
    private java.util.List<Collidable> list;

    /**
     * Constructor - initialize List.
     */
    public GameEnvironment() {
        list = new ArrayList<>();
    }

    /**
     * Adding a new object to the list of colilidables.
     *
     * @param c is the new object we want to add
     */
    public void addCollidable(Collidable c) {
        list.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory line of the ball
     * @return gameObjects.CollisionInfo object
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point temp;
        CollisionInfo collisionInfo = null;
        int flag = 0;
        for (Collidable collidable : list) {
            if (trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle()) != null) {
                temp = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
                if (flag == 0) {
                    collisionInfo = new CollisionInfo(collidable, temp);
                    flag = 1;
                } else if (trajectory.getStartPoint().distance(temp)
                        <= trajectory.getStartPoint().distance(collisionInfo.collisionPoint())) {
                    collisionInfo = new CollisionInfo(collidable, temp);
                }
            }
        }
        return collisionInfo;
    }

    /**
     * Method to remove collidable obbject.
     *
     * @param c the object
     */
    public void removeCollidable(Collidable c) {
        this.list.remove(c);
    }
}
