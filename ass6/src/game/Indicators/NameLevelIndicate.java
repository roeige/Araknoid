package game.Indicators;

import biuoop.DrawSurface;
import game.gameobjects.Sprite;
import geometricshapes.Point;

import java.awt.Color;

/**
 * name level class.
 */
public class NameLevelIndicate implements Sprite {
    private static final int SIZE_TEXT = 16;
    private Point point;
    private String levelName;

    /**
     * Constructor.
     *
     * @param point     points where text will be positioned
     * @param levelName current level's name
     */
    public NameLevelIndicate(Point point, String levelName) {
        this.levelName = levelName;
        this.point = point;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.gray);
        d.drawText((int) point.getX(), (int) point.getY(), this.levelName, SIZE_TEXT);

    }

    @Override
    public void timePassed() {
        return;

    }
}
