package game.gameenvironment;


import biuoop.DrawSurface;
import game.gameobjects.Block;
import game.gameobjects.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * Interface to represent level information.
 */
public interface LevelInformation {
    /**
     * return number of balls in the level.
     *
     * @return int
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * return paddle speed definition.
     *
     * @return integer -- paddle's speed
     */
    int paddleSpeed();

    /**
     * return paddle width definition.
     *
     * @return int
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return String
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite
     */
    Sprite getBackground();

    /**
     * returns paddle's color.
     *
     * @return Color
     */
    Color paddleColor();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return List of blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return int
     */
    int numberOfBlocksToRemove();


    /**
     * draw current level's background.
     *
     * @param d the surface
     */
    void drawBackground(DrawSurface d);
}
