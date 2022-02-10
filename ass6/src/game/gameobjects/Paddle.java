package game.gameobjects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.gameenvironment.GameLevel;
import game.gameenvironment.Velocity;
import geometricshapes.Point;
import geometricshapes.Rectangle;

import java.awt.Color;
import java.util.List;


/**
 * gameObjects.Paddle's class is responsible for creating
 * a paddle tool to move and collide the ball back to the blocks.
 */
public class Paddle implements Sprite, Collidable {
    private static final int WIDTH = 800, HEIGHT = 600,
            SCREEN_LIMIT = 25, DEATH_AERIA_HEIGHT = 20;
    private biuoop.KeyboardSensor keyboard;
    private Color color;
    private Rectangle rectangle;
    private Point point;
    private Velocity leftVelocity, rightVelocity;
    private List<Ball> ballLst;
    private double width;
    private double height;
    private int speed;
    private double speed2;

    /**
     * Constructor for paddle's class.
     *
     * @param keyboard the keyboard sensor -- move the paddle
     * @param speed    paddle speed
     * @param width    paddle width
     * @param height   paddle height
     * @param color    paddle's color
     */
    public Paddle(KeyboardSensor keyboard, int speed, double width, double height, Color color) {
        this.speed = speed;
        this.keyboard = keyboard;
        this.width = width;
        this.height = height;
        this.point = new Point((WIDTH / 2.0) - (this.width / 2), HEIGHT - (this.height + DEATH_AERIA_HEIGHT));
        generatePaddle();
        this.leftVelocity = new Velocity(-this.speed, 0);
        this.rightVelocity = new Velocity(this.speed, 0);
        this.color = color;
    }

    /**
     * Sets two ball's objects for future conditions.
     *
     * @param ballList is ball 1
     */
    public void setBall(List<Ball> ballList) {
        this.ballLst = ballList;
    }

    /**
     * Checks if the next move of the rectangle will force the ball to be in its boundaries.
     *
     * @param nextRect the future's possible rectangle's position
     * @return boolean true or false if the ball will end in paddle's boundaries
     */
    public boolean isInPaddleBlock(Rectangle nextRect) {
        for (Ball ball : this.ballLst) {
            if (ball != null) {
                if (ball.getCenter().isBetweenPaddle(nextRect.getUpperSide(), nextRect.getRightSide())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Creates the paddle.
     */
    public void generatePaddle() {
        this.rectangle = new Rectangle(this.point, this.width, this.height);
    }

    /**
     * Order the programme to move the paddle to the left.
     */
    public void moveLeft() {
        Point newPossiblePoint = leftVelocity.applyToPoint(this.getCollisionRectangle().getUpperLeftPoint());
        Rectangle nextRectangle = new Rectangle(newPossiblePoint,
                this.rectangle.getWidth(), this.rectangle.getHeight());
        if ((this.rectangle.getUpperLeftPoint().getX() - this.speed >= SCREEN_LIMIT)
                && !(isInPaddleBlock(nextRectangle))) {
            this.rectangle = nextRectangle;
        }
    }

    /**
     * Order the programme to move the paddle to the right.
     */
    public void moveRight() {
        Point newPossiblePoint = rightVelocity.applyToPoint(this.getCollisionRectangle().getUpperLeftPoint());
        Rectangle nextRectangle = new Rectangle(newPossiblePoint,
                this.rectangle.getWidth(), this.rectangle.getHeight());
        if ((this.rectangle.getUpperLeftPoint().getX() + (this.rectangle.getWidth() + this.speed))
                <= (WIDTH - SCREEN_LIMIT)
                && !(isInPaddleBlock(nextRectangle))) {
            this.rectangle = nextRectangle;
        }
    }

    /**
     * activate the work of gameObjects.Paddle (move it right or left as wished).
     */
    @Override
    public void timePassed() {
        isPressed();
    }

    /**
     * Draws the paddle to the game' surface.
     *
     * @param surface is the surface to draw on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeftPoint().getX(),
                (int) this.rectangle.getUpperLeftPoint().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rectangle.getUpperLeftPoint().getX(),
                (int) this.rectangle.getUpperLeftPoint().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * @return this paddle's rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Checks possible hit with the other object(ball).
     *
     * @param hitter          the hitter
     * @param collisionPoint  the possible collision point
     * @param currentVelocity current velocity of the ball
     * @return a new velocity accordingly
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Rectangle tempRect = this.rectangle;
        int angleForReg1 = 300, angleForReg2 = 330, angleForReg4 = 30, angleForReg5 = 60;
        this.speed2 = computeSpeed(currentVelocity.getDx(), currentVelocity.getDy());
        //in case hit is on region 3
        if (collisionPoint.isOnLine(tempRect.getUpperSide())) {
            return new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
        }
        //in case hit is on region 1
        if (collisionPoint.isOnLine(tempRect.getLeftSide())) {
            return Velocity.fromAngleAndSpeed(angleForReg1, speed);
        }
        //in case hit is on region 2
        if (collisionPoint.equals(tempRect.getUpperLeftPoint())) {
            return Velocity.fromAngleAndSpeed(angleForReg2, speed);
        }
        //in case hit is on region 4
        if (collisionPoint.equals(tempRect.getUpperSide().getEndPoint())) {
            return Velocity.fromAngleAndSpeed(angleForReg4, speed);
        }
        //in case hit is on region 5
        if (collisionPoint.isOnLine(tempRect.getRightSide())) {
            return Velocity.fromAngleAndSpeed(angleForReg5, speed);
        }
        //else (didn't hit)
        return currentVelocity;
    }

    /**
     * Method checks if one of the buttons is pressed, and execute respectively.
     */
    public void isPressed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g is the game's object adding to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * compute the speed of the ball and use it for hit possibility.
     *
     * @param dx velocity of ball
     * @param dy velocity of ball
     * @return the speed of the ball
     */
    public double computeSpeed(double dx, double dy) {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * retyrn x point.
     *
     * @return double
     */
    public double getX() {
        return this.point.getX();
    }

    /**
     * return y point.
     *
     * @return double
     */
    public double getY() {
        return this.point.getY();
    }
}
