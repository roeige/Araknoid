package game.gameobjects;

import biuoop.DrawSurface;
import game.gameenvironment.GameLevel;
import game.gameenvironment.GameEnvironment;
import game.gameenvironment.Velocity;
import geometricshapes.Line;
import geometricshapes.Point;

import java.awt.Color;

/**
 * gameObjects.Ball's a class which represent a ball object with its methods.
 * such as: creating a new ball, drawing a new ball to the board and more.
 *
 * @author Roei Gehassi
 */
public class Ball implements Sprite {
    //Fields
    private Point point;
    private int r;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Line trajectory;

    //default values for velocity
    private static final double DEFAULT_DX = 5;
    private static final double DEFAULT_DY = 5;

    /**
     * This method is the constructor of ball object by point ,radius and color.
     *
     * @param center point of the ball
     * @param r      radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.point = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(DEFAULT_DX, DEFAULT_DY);
    }

    /**
     * This method is the constructor of ball object by coordinates for point,
     * radius and color.
     *
     * @param x               is the ball's x coordinate
     * @param y               is the ball's y coordinate
     * @param r               radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment is the game environment object
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment gameEnvironment) {
        this.r = r;
        this.color = color;
        this.point = new Point(x, y);
        this.gameEnvironment = gameEnvironment;
        this.velocity = new Velocity(DEFAULT_DX, DEFAULT_DY);
    }


    /**
     * This method gain access to point's x.
     *
     * @return the x of the point
     */
    public int getX() {
        return (int) point.getX();
    }

    /**
     * @return return the y of the point
     */
    public int getY() {
        return (int) point.getY();
    }

    /**
     * @return return the size
     */
    public int getSize() {
        return r;
    }

    /**
     * @return the color of the ball
     */
    public Color getColor() {
        return color;
    }

    /**
     * The method draws the ball on the given DrawSurface.
     *
     * @param surface the surface which we want to draw on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        surface.fillCircle(this.getX(), this.getY(), this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * Method timePassed activates ball movement.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Method adds ball's object to the game List's sprites.
     *
     * @param g gameObjects.Game object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * This method is a constructor for the velocity, gets an object and sets
     * current object to be that argument.
     *
     * @param v the velocity's object.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * This method receive a point and return new velocity's object.
     *
     * @param dx the x's point of geometric.shapes.Point
     * @param dy y's point of geometric.shapes.Point
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return velocity's current object
     */
    public Velocity getVelocity() {
        return this.velocity;

    }

    /**
     * The method move the ball by steps.
     */
    public void moveOneStep() {
        computeTrajectory();
        Point returnPoint;
        CollisionInfo collInfo = this.gameEnvironment.getClosestCollision(this.trajectory);
        if (collInfo != null) {
            Point collideP = collInfo.collisionPoint();
            Collidable colidObj = collInfo.collisionObject();
            Velocity v1 = colidObj.hit(this, collideP, this.velocity);
            setVelocity(v1);
        } else {
            returnPoint = this.getVelocity().applyToPoint(this.point);
            this.point = returnPoint;
        }
    }

    /**
     * Method compute the trajectory after one step if no obstacle will be on its way.
     */
    public void computeTrajectory() {
        Point endPoint = this.getVelocity().applyToPoint(this.point);
        this.trajectory = new Line(this.point, endPoint);
    }

    /**
     * Method sets the environment.
     *
     * @param environment is the gameObjects.GameEnvironment's object
     */
    public void setEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * Method returns the center of the ball.
     *
     * @return current ball's center point
     */
    public Point getCenter() {
        return new Point(this.getX(), this.getY());
    }

    /**
     * Method to remove a sprite from game.
     *
     * @param g is a game object
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}

