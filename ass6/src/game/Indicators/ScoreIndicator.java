package game.Indicators;

import biuoop.DrawSurface;
import game.gameobjects.Sprite;
import game.listeners.Counter;
import geometricshapes.Point;

import java.awt.Color;

/**
 * Score indicator class-- to count the current score.
 */
public class ScoreIndicator implements Sprite {
    private static final int SIZE_TEXT = 20;
    private Counter score;
    private Point point;

    /**
     * Constructor.
     *
     * @param point is the frame block's point to hold the score title
     * @param cScore is the score we initialize from the beginning
     */
    public ScoreIndicator(Point point, Counter cScore) {
        this.score = cScore;
        this.point = point;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.gray);
        d.drawText((int) point.getX(), (int) point.getY(), this.score.toString(), SIZE_TEXT);

    }

    /**
     * Method updating score.
     *
     * @param updatedScore is the updated score
     */
    public void update(Counter updatedScore) {
        this.score = updatedScore;
    }

    @Override
    public void timePassed() {
        return;
    }
}
