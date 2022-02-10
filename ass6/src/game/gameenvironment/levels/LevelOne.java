package game.gameenvironment.levels;

import biuoop.DrawSurface;
import game.gameenvironment.LevelInformation;
import game.gameenvironment.Velocity;
import game.gameobjects.Block;
import game.gameobjects.Sprite;
import game.listeners.HitListener;
import geometricshapes.Point;
import geometricshapes.Rectangle;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which represent level one in the game.
 */
public class LevelOne implements LevelInformation {
    public static final int NUM_OF_BALLS = 1, PADDLE_SPEED = 10, PADDLE_WIDTH = 90,
            BLOCK_WIDTH = 30, BLOCK_HEIGHT = 30;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }


    @Override
    public void drawBackground(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 25, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        d.setColor(Color.red);
        d.drawCircle(400, 162, 60);
        d.drawCircle(400, 162, 90);
        d.drawCircle(400, 162, 120);
        d.setColor(Color.green);
        d.drawLine(400, 182, 400, 302);
        d.drawLine(420, 162, 540, 162);
        d.drawLine(380, 162, 260, 162);
        d.drawLine(400, 142, 400, 22);
    }


    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lstVelocity = new ArrayList<>();
        Velocity velocity = new Velocity(0, 5);
        lstVelocity.add(velocity);
        return lstVelocity;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public Color paddleColor() {
        return Color.orange;
    }

    @Override
    public List<Block> blocks() {
        List<HitListener> bHitListeners = new ArrayList<>();
        Rectangle rectangle = new Rectangle(new Point(385, 150), BLOCK_WIDTH, BLOCK_HEIGHT);
        Block block = new Block(rectangle, Color.blue, Color.black);
        List<Block> lstBlock = new ArrayList<>();
        lstBlock.add(block);
        return lstBlock;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
