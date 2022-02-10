package game.gameobjects;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Class's responsible for all the sprite collections
 * contains add and set methods.
 *
 * @author Roei Gehassi
 */
public class SpriteCollection {
    private List<Sprite> lst;

    /**
     * gameObjects.Sprite collection is the constructor for gameObjects.Sprite's object.
     * the constructor initialize the game
     */
    public SpriteCollection() {
        this.lst = new ArrayList<>();
    }

    /**
     * Adding a sprite to the sprite collection list.
     *
     * @param s the object we wants to add
     */
    public void addSprite(Sprite s) {
        this.lst.add(s);

    }

    /**
     * Call timePassed on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> tempLst = new ArrayList<>(this.lst);
        for (Sprite s : tempLst) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d draw to D surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : lst) {
            s.drawOn(d);
        }
    }

    /**
     * Method to remove sprite.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.lst.remove(s);
    }
}
