package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.gameenvironment.LevelInformation;
import game.gameobjects.SpriteCollection;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private LevelInformation levelInfo;
    private boolean firstRun;
    private boolean stop;
    private static final int THREE_SECOND_TIMER = 500;

    /**
     * Constructor.
     *
     * @param numOfSeconds number of seconds
     * @param countFrom    from where to start the count
     * @param gameScreen   game screen
     * @param levelInfo    specific level info
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
                              LevelInformation levelInfo) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.levelInfo = levelInfo;
        this.firstRun = true;
        this.stop = false;
    }

    /**
     * Here we will do the logic of adding a text of countdown.
     *
     * @param d the surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        if (this.numOfSeconds >= 0) {
//            if (this.firstRun) {
//                this.startTime = System.currentTimeMillis();
//                this.firstRun = false;
//                long currentTime = System.currentTimeMillis();
            this.levelInfo.drawBackground(d);
            this.gameScreen.drawAllOn(d);
            d.setColor(new Color(0, 128, 128));
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, (int) this.numOfSeconds + "", 50);
            sleeper.sleepFor(THREE_SECOND_TIMER);
            this.numOfSeconds--;
        } else {
            //only after the countdown is over
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
