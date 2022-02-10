package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Animation Runner's class-- runs each animation.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private static final int FRAMES = 60;
    private Sleeper sleeper;

    /**
     * Constructor.
     *
     * @param gui the gui to run with
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = FRAMES;
        this.sleeper = new Sleeper();
    }

    /**
     * Runs current animation.
     *
     * @param animation the animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
