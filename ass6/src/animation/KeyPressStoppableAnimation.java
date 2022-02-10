package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Key press class-- represent to stop frame when pressing specific key.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param sensor    sensor keyboard
     * @param key       the key which should be pressed
     * @param animation animation to run
     * @param runner    animation runner
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation, AnimationRunner runner) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.runner = runner;
        this.isAlreadyPressed = true;
        this.stop = false;


    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        this.isAlreadyPressed = false;
    }

    @Override
    public boolean shouldStop() {
        if (this.keyboard.isPressed(key) && !this.isAlreadyPressed) {
            return true;
        }
        return false;
    }
}
