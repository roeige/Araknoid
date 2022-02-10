package geometricshapes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class geometric.shapes.Rectangle is a class which responsible to
 * creat a rectangle's object which contains all the needed
 * details of rectangle like upper point, sides of the rectangles.
 * main purpose of rectangle class is to be used as blocks or paddle in the future
 *
 * @author Roei Gehassi
 */
public class Rectangle {
    //Fields
    private double width;
    private double height;
    private Point upperLeftPoint;
    private Color color;
    private Line upperSide;
    private Line lowerSide;
    private Line rightSide;
    private Line leftSide;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft is the upperLeft point
     * @param width     the width of the rect
     * @param height    rect's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeftPoint = upperLeft;
        this.width = width;
        this.height = height;
        setRectangleSides();
    }

    /**
     * @return the color of the rectangle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * define rectangle's sides by calculating them from upper left point.
     */
    public void setRectangleSides() {
        double x1, y1, x2, y2;
        //int i=0;
        x1 = this.upperLeftPoint.getX() + width;
        y1 = this.upperLeftPoint.getY();
        x2 = this.upperLeftPoint.getX();
        y2 = this.upperLeftPoint.getY() + height;
        Point upperRightP = new Point(x1, y1);
        Point lowerLeft = new Point(x2, y2);
        Point lowerRight = new Point(x1, y2);
        this.upperSide = new Line(this.upperLeftPoint, upperRightP);
        this.lowerSide = new Line(lowerLeft, lowerRight);
        this.leftSide = new Line(this.upperLeftPoint, lowerLeft);
        this.rightSide = new Line(upperRightP, lowerRight);
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line is the line we compare to
     * @return List of points which contains the intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        if (this.leftSide.isIntersecting(line)) {
            list.add(this.leftSide.intersectionWith(line));
        }
        if (this.rightSide.isIntersecting(line)) {
            list.add(this.rightSide.intersectionWith(line));
        }
        if (this.upperSide.isIntersecting(line)) {
            list.add(this.upperSide.intersectionWith(line));
        }
        if (this.lowerSide.isIntersecting(line)) {
            list.add(this.lowerSide.intersectionWith(line));
        }
        return list;
    }

    /**
     * @return Return the width of rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return Returns the upper-left point of the rectangle.
     */
    public Point getUpperLeftPoint() {
        return this.upperLeftPoint;
    }

    /**
     * @return upper side of the rectangle
     */
    public Line getUpperSide() {
        return this.upperSide;
    }

    /**
     * @return lower side of rectangle
     */
    public Line getLowerSide() {
        return this.lowerSide;
    }

    /**
     * @return right side of rectangle
     */
    public Line getRightSide() {
        return this.rightSide;
    }

    /**
     * @return left side of rectangle
     */
    public Line getLeftSide() {
        return this.leftSide;
    }
}

