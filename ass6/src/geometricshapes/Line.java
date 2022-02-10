package geometricshapes;

import java.util.List;

/**
 * This class responsible of creating geometric.shapes.Line which contains
 * Start geometric.shapes.Point and end geometric.shapes.Point.
 * the class has a few methods which belongs to geometric.shapes.Line operations such as middle, distance and more.
 *
 * @author Roei Gehassi
 */
public class Line {
    private static final double EPSILON = Math.pow(10, -15);
    //Fields
    private Point start, end;

    /**
     * return start point.
     *
     * @return start Point
     */
    public Point getStartPoint() {
        return this.start;
    }

    /**
     * return end point.
     *
     * @return end point
     */
    public Point getEndPoint() {
        return this.end;
    }


    /**
     * constructor for geometric.shapes.Line by two points.
     *
     * @param start first point
     * @param end   end last point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

    }

    /**
     * constructor by receiving two coordinates for points and set new line.
     *
     * @param x1 first coordinate of a point
     * @param y1 y's coordinate of the first point
     * @param x2 x's coordinate of the second point
     * @param y2 y's coordinate of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }


    /**
     * @return the middle point of the geometric.shapes.Line
     */
    public Point middle() {
        double x, y;
        x = (this.start.getX() + this.end.getX()) / 2;
        y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }


    /**
     * @return the start point of the geometric.shapes.Line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Method calculate the slope of the line's equation.
     *
     * @return the slope of the geometric.shapes.Line's equation
     */
    public double lineSlop() {

        double parmX = this.end.getX() - this.start.getX();
        double parmY = this.end.getY() - this.start.getY();
        if (parmX == 0) {
            return 0;
        }
        return parmY / parmX;
    }

    /**
     * This method receive slope and line and return the y's coordinate intersection.
     *
     * @param slope the slope of geometric.shapes.Line's equation
     * @param line  any line to execute on
     * @return y cordinate's intersection.
     */
    public double yCordinateIntersect(double slope, Line line) {

        return slope * (-1 * (line.start.getX())) + line.start.getY();
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Method check if two lines are intersected.
     *
     * @param other other line to compare with
     * @return boolean result if there is an intersection or there is not
     */
    public boolean isIntersecting(Line other) {
        //The potential intersected point.
        Point intersected = this.intersectionWith(other);
        //first we have to check if the lines are equals and if the intersectionPoint is not exist.
        return !this.equals(other) && (intersected != null);
    }

    /**
     * Method return the intersection point between two geometric.shapes.Point's objects, if there is, if there is not
     * returns null.
     *
     * @param other is a line to compare with
     * @return the intersection point if the lines intersect, and null otherwise
     */
    public Point intersectionWith(Line other) {
        double b1, b2, m1, m2, x, y;
        if (this.equals(other)) {
            return null;
        }
        //condition means this current line object is a point
        if (lineIsPoint(this)) {
            if (this.start.isOnLine(other)) {
                return this.start;
            }
        } else if (lineIsPoint(other)) {
            if (other.start.isOnLine(this)) {
                return other.start;
            }
        }
        //checks if the lines are overlapping at some point
        if (this.lineSlop() == other.lineSlop()) {
            if (this.isOverLap(other)) {
                return null;
            }
        }
        //in cases both parallel to Y-pivot
        if (this.isParallelX() && other.isParallelX()) {
            if (this.start.getX() != other.start.getX()) {
                return null;

                //if x's coordinates are equals we can assume lines are not overlapping
            } else if (this.start.getX() == other.start.getX()) {
                if (intersectOnlyAtOnePoint(other)
                        && (this.start.equals(other.start) || this.start.equals(other.end))) {
                    return this.start;
                }
                if (intersectOnlyAtOnePoint(other)
                        && (this.end.equals(other.start) || this.end.equals(other.end))) {
                    return this.end;
                }
            }
            return null;
        }
        //cases both are vertical to X's axis
        if (this.isVertical() && other.isVertical()) {
            //if y's coordinates are equals we can assume lines are not overlapping
            if (this.start.getY() == other.start.getY()) {
                if (intersectOnlyAtOnePoint(other)
                        && (this.start.equals(other.start) || this.start.equals(other.end))) {
                    return this.start;
                }
                if (intersectOnlyAtOnePoint(other)
                        && (this.end.equals(other.start) || this.end.equals(other.end))) {
                    return this.end;
                }
            }
            return null;
        }

        /*
         *These conditions cover the case start/end point is the same.
         * we can assume there is no over lapp
         */
        if ((this.start.equals(other.start) && !(this.end.equals(other.end))
                || this.start.equals(other.end) && !(this.end.equals(other.start)))) {
            return this.start;
        }
        if (this.end.equals(other.start) && !(this.start.equals(other.end))
                || this.end.equals(other.end) && !(this.start.equals(other.start))) {
            return this.end;
        }

        /*
         *in case we have the form "+" which means, there is a cross intersection
         */
        if (this.isVertical() && other.isParallelX()) {
            if (other.isBetweenX(this) && other.isYOnLine(this.start.getY())) {
                return new Point(other.start.getX(), this.start.getY());
            }
            if (this.isBetweenX(other) && this.isYOnLine(other.start.getY())) {
                return new Point(this.start.getX(), other.start.getY());
            }
            return null;
        }
        if (this.isParallelX() && other.isVertical()) {
            if (other.isBetweenX(this) && other.isYOnLine(this.start.getY())) {
                return new Point(other.start.getX(), this.start.getY());
            }
            if (this.isBetweenX(other) && this.isYOnLine(other.start.getY())) {
                return new Point(this.start.getX(), other.start.getY());
            }
            return null;
        }

        /*
         *cover cases which one is vertical but the other is not parallel to on of the axis
         */
        if (this.isVertical() && !(other.isVertical() || other.isParallelX())) {
            m2 = other.lineSlop();
            b2 = yCordinateIntersect(m2, other);
            if (isBetweenX(other)) {
                double yToCheck = ((this.start.getX()) * m2) + b2;
                if (other.isYOnLine(yToCheck) && this.isYOnLine(yToCheck)) {
                    return new Point(this.start.getX(), yToCheck);

                }
            }
            return null;
        }
        if (other.isVertical() && !(this.isVertical() || this.isParallelX())) {
            m1 = this.lineSlop();
            b1 = yCordinateIntersect(m1, this);
            if (isBetweenX(other)) {
                double yToCheck = ((other.start.getX()) * m1) + b1;
                if (this.isYOnLine(yToCheck) && this.isYOnLine(yToCheck)) {
                    return new Point(other.start.getX(), yToCheck);
                }
            }
            return null;
        }
        Point edgePoint1 = edgeCases(this, other);
        Point edgePoint2 = edgeCases(other, this);
        if (edgePoint1 != null) {
            return edgePoint1;
        } else if (edgePoint2 != null) {
            return edgePoint2;
        }
        m1 = this.lineSlop();
        m2 = other.lineSlop();
        b1 = yCordinateIntersect(m1, this);
        b2 = yCordinateIntersect(m2, other);
        if (m1 - m2 != 0) {
            x = (b2 - b1) / (m1 - m2);
            y = m1 * ((b2 - b1) / (m1 - m2)) + b1;
            Point intersected = new Point(x, y);
            //two booleans variables which will check if the intersection point is on the line.
            if (this.isBetweenLines(intersected, other) && intersected.isOnLine(this)) {
                return intersected;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * Checks if the line is parallel to x's axis or not.
     *
     * @return true or false
     */
    public boolean isParallelX() {
        return (this.lineSlop() == 0 && this.start.getY() == this.end.getY());
    }

    /**
     * checks if two lines intersect at only one point or not.
     *
     * @param other other line to compare with
     * @return true or false - boolean
     */
    public boolean intersectOnlyAtOnePoint(Line other) {
        double halfDistA = (this.length()) / 2, halfDistB = (other.length()) / 2;
        double equalTo = this.middle().distance(other.middle());
        return (Math.abs((halfDistA + halfDistB) - equalTo) < EPSILON);

    }

    /**
     * This method checks if the current line is a point.
     *
     * @param line is the object we want to check on
     * @return true or false
     */
    public boolean lineIsPoint(Line line) {
        return line.start().getX() == line.end.getX() && line.start.getY() == line.end.getY();
    }

    /**
     * This method receives a line which is actually a point, and checks if the point is on
     * the current line. we assume that the other line is a point
     *
     * @param other is the other line(if its point) we want to check if its on the current geometric.shapes.Line
     * @return true or false whether the point is on the geometric.shapes.Line or not
     */
    public boolean isOnLine(Line other) {
        double distanceA, distanceB;
        distanceA = other.start.distance(this.start);
        distanceB = other.start.distance(this.end);
        /*
         * condition checks if the difference between the two distances is smalled then epsilon
         * if so, then the distances are equals
         */
        return (Math.abs(distanceA + distanceB - this.length()) < EPSILON);

    }

    /**
     * This method receives other geometric.shapes.Line and checks if the two lines are overlapping at some point
     * if they do, we return true, otherwise returns false.
     *
     * @param other is the other line we compare with
     * @return true or false
     */
    public boolean isOverLap(Line other) {
        /*
         *if we arrived here we can assume that:
         * both lines are parallel and not a point(there is an actual line for both)
         * now we have to check if they're actually overlapping
         */
        if (this.start().equals(other.start()) && isOnSegment(this.start(), other.end(), this.end())) {
            return true;
        }
        if (this.start().equals(other.start()) && isOnSegment(other.start(), this.end(), other.end())) {
            return true;
        }
        if (this.end().equals(other.end()) && isOnSegment(this.start(), other.end(), this.end())) {
            return true;
        }
        if (this.end().equals(other.end()) && isOnSegment(other.start(), this.end(), other.end())) {
            return true;
        }
        if (isOnSegment(this.start(), other.start(), this.end())
                && isOnSegment(this.start(), other.end(), this.end())) {
            return true;
        }
        if (isOnSegment(other.start(), this.start(), other.end())
                && isOnSegment(other.start(), this.end(), other.end())) {
            return true;
        }

        if (isOnSegment(this.start(), other.start(), this.end())
                && isOnSegment(other.start(), this.end(), other.end())
                && !(other.start().equals(this.end))) {
            return true;
        }
        return isOnSegment(this.start(), other.end(), this.end())
                && isOnSegment(other.start(), this.start(), other.end())
                && !(this.start().equals(other.end));

    }

    /**
     * Checks if a current point is between two points (middle point between left and right parameter's points).
     *
     * @param first  is the left point to compare with
     * @param second is the point we check about
     * @param third  the left point to compare with
     * @return true or false, if the point is on segment
     */
    public static boolean isOnSegment(Point first, Point second, Point third) {
        return second.getX() < Math.max(first.getX(), third.getX())
                && second.getX() > Math.min(first.getX(), third.getX())
                && second.getY() < Math.max(first.getY(), third.getY())
                && second.getY() > Math.min(first.getY(), third.getY());
    }

    /**
     * This method receive other geometric.shapes.Line and checks if the slopes of both lines are equals.
     *
     * @param other is other geometric.shapes.Line to compare with
     * @return true or false Boolean result
     */
    public boolean isSlopeEquals(Line other) {
        return this.lineSlop() == other.lineSlop();
    }

    /**
     * This method checks if this geometric.shapes.Line is vertical.
     *
     * @return Boolean value
     */
    public boolean isVertical() {
        return (this.start.getX() == this.end.getX());
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     *
     * @param other is a line to compare with
     * @return boolean result if both lines are equal or not
     */
    public boolean equals(Line other) {
        //checks the slopes and the start/end points.
        return this.lineSlop() == other.lineSlop() && (this.start.equals(other.start)
                || this.start.equals(other.end)) && (this.end.equals(other.end) || this.end.equals(other.start));

    }

    /**
     * Method checks if the X is between the two lines.
     *
     * @param other the other line to compare with
     * @return true of false
     */
    public boolean isBetweenX(Line other) {
        return ((this.start.getX() <= Math.max(other.start.getX(), other.end.getX()))
                && this.start.getX() >= Math.min(other.start.getX(), other.end.getX()));

    }

    /**
     * This method assume that the line is vertical
     * Therefor- the method checks if the y intersection is on the vertical geometric.shapes.Line or not.
     *
     * @param y is the Y we want to check on the line
     * @return boolean true of false
     */
    public boolean isYOnLine(double y) {
        return ((y <= Math.max(this.start.getY(), this.end.getY()))
                && y >= Math.min(this.start.getY(), this.end.getY()));
    }

    /**
     * Method checks if the intersected point is between the two lines.
     *
     * @param intersected is the intersection point
     * @param other       is the other line to compare with
     * @return true or false
     */
    public boolean isBetweenLines(Point intersected, Line other) {
        boolean isBetweenLine1, isBetweenLine2;

        /*
         *Condtion checks if the intersection point is between Start's x and end's x
         * and if y is between start's y and end's y.
         * And also for the other geometric.shapes.Line respectively.
         */
        isBetweenLine1 = (((intersected.getX() >= Math.min(this.start.getX(), this.end.getX())
                && intersected.getX() <= Math.max(this.start.getX(), this.end.getX()))
                && intersected.getY() >= Math.min(this.start.getY(), this.end.getY())
                && intersected.getY() <= Math.max(this.start.getY(), this.end.getY())));
        isBetweenLine2 = (((intersected.getX() >= Math.min(other.start.getX(), other.end.getX())
                && intersected.getX() <= Math.max(other.start.getX(), other.end.getX()))
                && intersected.getY() >= Math.min(other.start.getY(), other.end.getY())
                && intersected.getY() <= Math.max(other.start.getY(), other.end.getY())));
        return isBetweenLine1 && isBetweenLine2;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect is the rectangle we compare with
     * @return intersected point in case there is an intersection, or null if there is not
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        int counter = 0;
        List<Point> lst = rect.intersectionPoints(this);
        Point point1 = null;
        for (Point intersection : lst) {
            if (counter == 0) {
                point1 = intersection;
                counter++;
            } else if (intersection.distance(this.start) < point1.distance(this.start)) {
                point1 = intersection;
            }
        }
        return point1;
    }

    /**
     * Checks edge cases in intersection possible situations.
     *
     * @param line1 to compare with line 2
     * @param line2 to compare with line 1
     * @return the point of intersection
     */
    public Point edgeCases(Line line1, Line line2) {
        Point point1 = new Point((line1.start.getY() - yCordinateIntersect(line2.lineSlop(),
                line2)) / line2.lineSlop(),
                line1.start.getY());
        Point point2 = new Point(line1.start.getX(),
                line2.lineSlop() * line1.start.getX() + yCordinateIntersect(line2.lineSlop(), line2));
        if (line1.lineSlop() == 0 && line2.lineSlop() != 0 && !line2.isVertical() && point1.isOnLine(line1)
                && point1.isOnLine(line2)) {
            return point1;
        }
        if (line1.isVertical() && !line2.isVertical() && line2.lineSlop() != 0
                && point2.isOnLine(line1) && point2.isOnLine(line2)) {
            return point2;
        }
        return null;
    }
}




