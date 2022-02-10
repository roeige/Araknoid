package animation;


import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /**
     * runs one frame.
     *
     * @param d the surface to draw into
     */
    void doOneFrame(DrawSurface d);

    /**
     * Boolean method to check if the method should do one more frame.
     *
     * @return boolean value
     */
    boolean shouldStop();
}
