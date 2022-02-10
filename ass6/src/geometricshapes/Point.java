package geometricshapes;

/**
 * Class point is a class which creat geometric.shapes.Point and its methods.
 * such as distance, equal checks and more
 *
 * @author Roei Gehassi
 */
public class Point {
    //Fields.
    private static final double EPSILON = Math.pow(10, -12);
    private double x;
    private double y;

    /**
     * Constructor for geometric.shapes.Point.
     *
     * @param x is the x's coordinate
     * @param y is the y's coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    /**
     * This method receive point and return the distance between the current object and the point
     * it received.
     *
     * @param other Other point to compare with
     * @return returns the distance between two points
     */
    public double distance(Point other) {
        /*
         *Creats two variables, calculate the power between x1-x2 and y1-y2.
         * In the end, the program returns the sqrt of the addition between parmX and parmY.
         */
        final int pow2 = 2;
        double parmX, parmY;
        parmX = Math.pow((this.x - other.x), pow2);
        parmY = Math.pow((this.y - other.y), pow2);
        return Math.sqrt((parmX + parmY));
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other Other point to compare with
     * @return boolean result true or false if two points are equal or not
     */
    public boolean equals(Point other) {
        /*
         *How to we check if the points are equal?
         * We have to check equalization between x1 and x2 and in addition, equalization
         * between y1 and y2. If both are equal, then the points are equal as well.
         */

        return Math.abs(this.getX() - other.getX()) < EPSILON && Math.abs(this.getY() - other.getY()) < EPSILON;
    }
    // Return the x and y values of this point

    /**
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y's value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Checks if the point is on another line.
     *
     * @param other the other line to compare with
     * @return boolean true or false
     */
    public boolean isOnLine(Line other) {
        double distanceA, distanceB;
        distanceA = this.distance(other.getStartPoint());
        distanceB = this.distance(other.getEndPoint());
        /*
         * condition checks if the difference between the two distances is smalled then epsilon
         * if so, then the distances are equals
         */
        return (Math.abs(distanceA + distanceB - other.length()) < EPSILON);
    }

    /**
     * Checks if the a point is between two lines-- main purpose is to check if the ball's center
     * is about to be inside the paddle (a rectangle).
     *
     * @param horizontalSide is the upper/down side of the paddle
     * @param verticalSide   is the left or right side of rectangle
     * @return boolean true or false
     */
    public boolean isBetweenPaddle(Line horizontalSide, Line verticalSide) {
        return (this.getX() <= Math.max(horizontalSide.getStartPoint().getX(), horizontalSide.getEndPoint().getX())
                && this.getX() >= Math.min(horizontalSide.getStartPoint().getX(), horizontalSide.getEndPoint().getX())
                && this.getY() <= Math.max(verticalSide.getStartPoint().getY(), verticalSide.getEndPoint().getY())
                && this.getY() >= Math.min(verticalSide.getStartPoint().getY(), verticalSide.getEndPoint().getY()));
    }

    /**
     * over riding string to represent the point as we want.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "( " + x + " , " + y + " )";
    }

}
