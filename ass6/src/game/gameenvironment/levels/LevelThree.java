package game.gameenvironment.levels;

import biuoop.DrawSurface;
import game.gameenvironment.LevelInformation;
import game.gameenvironment.Velocity;
import game.gameobjects.Block;
import game.gameobjects.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Class which represent level three in the game.
 */
public class LevelThree implements LevelInformation {
    private List<Block> blockList;

    /**
     * Constructor.
     */
    public LevelThree() {
        this.blockList = new LinkedList<>();
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new LinkedList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(50, 10));
        velocityList.add(Velocity.fromAngleAndSpeed(-50, 10));
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
        return "New York";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public Color paddleColor() {
        return Color.PINK;
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        int y = 150;
        int blockWidth = 25;

        for (int i = 0; i < colors.length; ++i) {
            for (int j = i + 5; j < 15; ++j) {
                this.blockList.add(new Block(j * 50 + 25, y, 50, blockWidth, colors[i], Color.BLACK));
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
        d.setColor(Color.decode("#221b51"));
        d.fillRectangle(0, 25, d.getWidth(), d.getHeight());
        d.setColor(new Color(239, 231, 176));
        d.fillCircle(150, 150, 60);
        d.setColor(new Color(164, 158, 192, 255));
        d.drawCircle(150, 150, 60);
        d.setColor(Color.decode("#4e4a49"));
        d.fillRectangle(110, 200, 10, 200);
        d.setColor(Color.decode("#d8ac66"));
        d.fillCircle(115, 200, 12);
        d.setColor(Color.decode("#f64d36"));
        d.fillCircle(115, 200, 8);
        d.setColor(Color.WHITE);
        d.fillCircle(115, 200, 3);
        d.setColor(Color.decode("#3e3a39"));
        d.fillRectangle(100, 400, 30, 200);
        d.setColor(Color.decode("#2e2a29"));
        d.fillRectangle(65, 450, 100, 200);
        int startX = 75;
        int startY = 460;
        d.setColor(Color.WHITE);

        for (int rows = 0; rows < 5; ++rows) {
            for (int columns = 0; columns < 5; ++columns) {
                d.fillRectangle(startX + columns * 18, startY + rows * 32, 10, 25);
            }
        }
        d.setColor(Color.decode("#4e4a49"));
        d.fillRectangle(250, 240, 10, 160);
        d.setColor(Color.decode("#d8ac66"));
        d.fillCircle(255, 240, 12);
        d.setColor(Color.decode("#f64d36"));
        d.fillCircle(255, 240, 8);
        d.setColor(Color.WHITE);
        d.fillCircle(255, 240, 3);
        d.setColor(Color.decode("#3e3a39"));
        d.fillRectangle(240, 400, 30, 200);
        d.setColor(Color.decode("#2e2a29"));
        d.fillRectangle(205, 450, 100, 200);
        d.setColor(Color.WHITE);
        int newStartX = 215;
        int newStartY = 460;

        for (int rows = 0; rows < 5; ++rows) {
            for (int columns = 0; columns < 5; ++columns) {
                d.fillRectangle(newStartX + columns * 18, newStartY + rows * 32, 10, 25);
            }
        }
    }
}
