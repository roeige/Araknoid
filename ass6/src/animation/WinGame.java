package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.listeners.Counter;

import java.awt.Color;

/**
 * Class to represent win in the game.
 */
public class WinGame implements Animation {
    private Counter score;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param score current score the player gained
     * @param k     the keyboard sensor
     */
    public WinGame(Counter score, KeyboardSensor k) {
        this.keyboard = k;
        this.score = score;
        this.stop = false;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(132, 149, 235));
        d.fillRectangle(0, 18, d.getWidth(), d.getHeight());
        d.setColor(new Color(139, 17, 17));
        String outPut = "You Win! Your score is:" + this.score.getValue();
       d.drawText(199, 500, outPut, 32);
        d.setColor(Color.decode("#1788d0"));
        d.drawText(200, 501, outPut, 32);
       d.setColor(Color.BLACK);
        d.drawText(202, 502, outPut, 32);

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
