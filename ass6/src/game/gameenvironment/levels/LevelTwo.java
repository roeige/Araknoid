package game.gameenvironment.levels;

import biuoop.DrawSurface;
import game.gameenvironment.LevelInformation;
import game.gameenvironment.Velocity;
import game.gameobjects.Block;
import game.gameobjects.Sprite;
import geometricshapes.Point;
import geometricshapes.Rectangle;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Class which represent level two in the game.
 */
public class LevelTwo implements LevelInformation {
    private static final double SPEED = 10;

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new LinkedList<>();
        int count = 10;
        double angle = 60.0;
        while (count > 0) {
            velocityList.add(Velocity.fromAngleAndSpeed(angle, SPEED));
            angle -= 10;
            count--;
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rectBackGround = new Rectangle(new Point(0, 0), 800, 600);
        Sprite backGround = new Block(rectBackGround, Color.white, Color.black);
        return backGround;
    }

    @Override
    public Color paddleColor() {
        return Color.orange;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList();
        int y = 250;
        int blockHeight = 25;
        blocks.add(new Block(25, y, 50, blockHeight, Color.RED, Color.BLACK));
        blocks.add(new Block(75, y, 50, blockHeight, Color.RED, Color.BLACK));
        blocks.add(new Block(125, y, 50, blockHeight, Color.ORANGE, Color.BLACK));
        blocks.add(new Block(175, y, 50, blockHeight, Color.ORANGE, Color.BLACK));
        blocks.add(new Block(225, y, 50, blockHeight, Color.YELLOW, Color.BLACK));
        blocks.add(new Block(275, y, 50, blockHeight, Color.YELLOW, Color.BLACK));
        blocks.add(new Block(325, y, 50, blockHeight, Color.GREEN, Color.BLACK));
        blocks.add(new Block(375, y, 50, blockHeight, Color.GREEN, Color.BLACK));
        blocks.add(new Block(425, y, 50, blockHeight, Color.GREEN, Color.BLACK));
        blocks.add(new Block(475, y, 50, blockHeight, Color.BLUE, Color.BLACK));
        blocks.add(new Block(525, y, 50, blockHeight, Color.BLUE, Color.BLACK));
        blocks.add(new Block(575, y, 50, blockHeight, Color.PINK, Color.BLACK));
        blocks.add(new Block(625, y, 50, blockHeight, Color.PINK, Color.BLACK));
        blocks.add(new Block(675, y, 50, blockHeight, Color.CYAN, Color.BLACK));
        blocks.add(new Block(725, y, 50, blockHeight, Color.CYAN, Color.BLACK));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public void drawBackground(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(25, 25, d.getWidth(), d.getHeight());
        d.setColor(new Color(239, 231, 176));
        d.fillCircle(150, 150, 60);
        int numLines = 100;
        int startX = 25;
        int endX = 775;

        for (int i = numLines; i > 0; --i) {
            d.drawLine(150, 150, (endX - startX) / numLines * i, 250);
        }

        d.setColor(new Color(236, 215, 73));
        d.fillCircle(150, 150, 50);
        d.setColor(Color.decode("#f95821"));
        d.fillCircle(150, 150, 40);
        d.setColor(Color.decode("#f95821"));
        d.fillCircle(150, 150, 30);
    }
}
