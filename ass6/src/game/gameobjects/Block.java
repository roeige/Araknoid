package game.gameobjects;

import biuoop.DrawSurface;
import game.gameenvironment.GameLevel;
import game.gameenvironment.Velocity;
import game.listeners.HitListener;
import game.listeners.HitNotifier;
import geometricshapes.Point;
import geometricshapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * gameObjects.Block class is responsible to create rectangles blocks for the game,
 * the class implements two interfaces- gameObjects.Collidable and gameObjects.Sprite.
 *
 * @author Roei Gehassi
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //Field
    private Rectangle rect;
    private Color color, secondColor;
    private List<HitListener> hitListeners;

    /**
     * Constructor for gameObjects.Block.
     *
     * @param rectangle the rectangle we create the block with
     * @param color     is the color parameter
     * @param color2    second color to be the frame of the object
     */
    public Block(Rectangle rectangle, Color color, Color color2) {
        this.rect = rectangle;
        this.color = color;
        this.secondColor = color2;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * Another constructor with two parameters.
     *
     * @param x      the x for point
     * @param y      for point
     * @param width  length of rect
     * @param height height of rect
     * @param color1 color of rect
     * @param color2 color of rect
     */
    public Block(int x, int y, int width, int height, Color color1, Color color2) {
        this.rect = new Rectangle(new Point(x, y), (double) width, (double) height);
        this.color = color1;
        this.secondColor = color2;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * @return rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Checks if there is a possible hit between one object and the current block
     * if so, it returns a new velocity respectively.
     *
     * @param collisionPoint  possible collision point with another object
     * @param currentVelocity the velocity of the other object
     * @param hitter          the hitball
     * @return the new velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        /*
         *checks if the hit came either from left or right or either from up/down
         */
        if (collisionPoint.isOnLine(this.rect.getLeftSide()) || collisionPoint.isOnLine(this.rect.getRightSide())) {
            this.notifyHit(hitter);
            return new Velocity(-1 * (currentVelocity.getDx()), (currentVelocity.getDy()));
        }
        if (collisionPoint.isOnLine(this.rect.getUpperSide()) || collisionPoint.isOnLine(this.rect.getLowerSide())) {
            this.notifyHit(hitter);
            return new Velocity((currentVelocity.getDx()), -1 * (currentVelocity.getDy()));
        }
        //if none of the conditions is true, don't do anything.
        return currentVelocity;
    }

    /**
     * Draws the block on the surface.
     *
     * @param surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rect.getUpperLeftPoint().getX(),
                (int) this.rect.getUpperLeftPoint().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rect.getUpperLeftPoint().getX(),
                (int) this.rect.getUpperLeftPoint().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());

    }

    /**
     *
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * Adds the block to the game object.
     * adds it as a sprite and also as a collidable
     *
     * @param g gameObjects.Game object
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove from game method to remove sprite and collidable from the game.
     *
     * @param game the game object
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Make a copy of the hitListeners before iterating over them.
     *
     * @param hitter the ball about to hit
     */
    private void notifyHit(Ball hitter) {
        if (this.hitListeners != null) {
            List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
            // Notify all listeners about a hit event:
            for (HitListener hl : listeners) {
                hl.hitEvent(this, hitter);
            }
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
