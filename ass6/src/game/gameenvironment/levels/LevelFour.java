package game.gameenvironment.levels;

import biuoop.DrawSurface;
import game.gameenvironment.LevelInformation;
import game.gameenvironment.Velocity;
import game.gameobjects.Block;
import game.gameobjects.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class which represent level four in the game.
 */
public class LevelFour implements LevelInformation {
    private List<Block> blockList;

    /**
     * Constructor.
     */
    public LevelFour() {
        this.blockList = new LinkedList<>();
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new LinkedList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(0, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(20, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(-20, 5));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public Color paddleColor() {
        return new Color(55, 101, 173);
    }

    @Override
    public List<Block> blocks() {
        List<Color> colorList = new ArrayList<>();
        colorList.add(Color.gray);
        colorList.add(Color.red);
        colorList.add(Color.yellow);
        colorList.add(Color.green);
        colorList.add(Color.white);
        colorList.add(Color.pink);
        colorList.add(Color.cyan);
        int y = 100;
        int blockWidth = 25;
        for (int i = 0; i < colorList.size(); ++i) {
            for (int j = 0; j < 15; ++j) {
                this.blockList.add(new Block(j * 50 + 25, y, 50, blockWidth, colorList.get(i), Color.BLACK));
            }
            y += blockWidth;
        }
        return this.blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blockList.size();
    }

    @Override
    public void drawBackground(DrawSurface d) {
        d.setColor(Color.decode("#D0E7F5"));
        d.fillRectangle(0, 25, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);

        int i;
        for (i = 0; i < 10; ++i) {
            d.drawLine(105 + i * 10, 400, 80 + i * 10, 600);
        }

        d.setColor(Color.decode("#D8D8D8"));
        d.fillCircle(120, 400, 23);
        d.fillCircle(140, 420, 27);
        d.setColor(Color.decode("#bbbbbb"));
        d.fillCircle(160, 390, 29);
        d.setColor(Color.decode("#aaaaaa"));
        d.fillCircle(180, 420, 22);
        d.fillCircle(200, 400, 32);
        d.setColor(Color.WHITE);

        for (i = 0; i < 10; ++i) {
            d.drawLine(625 + i * 10, 520, 580 + i * 10, 600);
        }

        d.setColor(Color.decode("#D8D8D8"));
        d.fillCircle(600, 500, 23);
        d.fillCircle(620, 540, 27);
        d.setColor(Color.decode("#bbbbbb"));
        d.fillCircle(640, 510, 29);
        d.setColor(Color.decode("#aaaaaa"));
        d.fillCircle(660, 530, 22);
        d.fillCircle(680, 520, 32);

    }
}
