package game.gameobjects;

import biuoop.DrawSurface;

/**
 * is the interface gameObjects.Sprite contains its method, such as ball,paddle and blocks.
 *
 * @author Roei Gehassi
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
