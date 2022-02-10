package game.gameenvironment;

import geometricshapes.Point;

/**
 * gameObjects.Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author Roei Gehassi
 */
public class Velocity {
    //Fields
    //const to convert angle to radian
    private static final double TO_RADIAN = (Math.PI) / 180;
    private double dx;
    private double dy;

    /**
     * constructor for gameObjects.Velocity by receiving dx and dy.
     *
     * @param dx is the x's coordinate
     * @param dy is the y's coordinate
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor for gameObjects.Velocity by receiving speed and angle.
     *
     * @param angle the angle of the ball
     * @param speed the speed of the ball
     * @return returns a new velocity by calculating a new x,y
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx, dy;
        double radianAngle = angle * TO_RADIAN;
        dx = speed * Math.sin(radianAngle);
        dy = -(speed * Math.cos(radianAngle));
        return new Velocity(dx, dy);
    }

    /**
     * method returns dx coordinate.
     *
     * @return dx coordinates
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Methos returns dy's coordinate of a geometric.shapes.Point.
     *
     * @return dy coordinate
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     *
     * @param p The point we want to apply of.
     * @return return new point
     */
    public Point applyToPoint(Point p) {
        return new Point(this.dx + p.getX(), this.dy + p.getY());
    }
}
