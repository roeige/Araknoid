package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.listeners.Counter;

import java.awt.Color;

/**
 * End game class-- represent end game screen.
 */
public class EndGame implements Animation {
    private Counter score;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param score current score
     * @param k     keyboard sensor
     */
    public EndGame(Counter score, KeyboardSensor k) {
        this.keyboard = k;
        this.score = score;
        this.stop = false;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(132, 149, 235));
        d.fillRectangle(0, 18, d.getWidth(), d.getHeight());
        d.setColor(Color.black);

        d.setColor(new Color(139, 17, 17));
        String outPut = "Game Over. Your score is: " + this.score.getValue();
        d.drawText(199, 500, outPut, 32);
        d.setColor(Color.decode("#1788d0"));
        d.drawText(200, 501, outPut, 32);
        d.setColor(Color.BLACK);
        d.drawText(202, 502, outPut, 32);
//       if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
//           this.stop = true;
//       }
    }


    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
