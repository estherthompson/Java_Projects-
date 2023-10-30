/**
 * The Point class represents a Cartesian Plane
 * @author estherthompson
 */
public class Point {
    /** The x coordinate of the point. */
    private double x;
    /** The y coordinate of the point. */
    private double y;
    /** A static variable that keeps track of the number of active instances of the Point class  */
    private static int activeInstances = 0;
    /** A unique identifier for each Point object. */
    private static double pointID = 0;

    /**
     * Constructs a Point object with given x and y coordinates.
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point
     */

    public Point(double x, double y) {

        this.x = x;
        this.y = y;
        this.setCoordinates(x,y);
        activeInstances++;
        pointID++;

    }

    /**
     * Constructs a Point object with default x and default y coordinates (0.0, 0.0).
     */

    public Point() {
        this(0.0, 0.0);
    }

    /**
     * Set instance method for the x and y coordinates of the point
     * @param x The x coordinate that is set
     * @param y The y coordinate that is set
     */

    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the x coordinate
     * @param x Represents the x coordinate that is set
     */

    public void setCoordinates2(double x) {
        this.x = x;
    }
    /**
     * Sets the value of y coordinate.
     *
     * @param y The new y coordinate value to set.
     */

    public void setCoordinates3(double y) {
        this.y = y;
    }
    /**
     * Get method for the value of x coordinate.
     *
     * @return The current x coordinate value.
     */

    public double getX() {
        return x;
    }
    /**
     * Get method for the value of y coordinate.
     *
     * @return The current y coordinate value.
     */

    public double getY() {
        return y;
    }
    /**
     * Adds deltaX to the x coordinate and deltaY to the y coordinate of the point.
     *
     * @param deltaX The value to add to the x coordinate.
     * @param deltaY The value to add to the y coordinate.
     */
    public void move(double deltaX, double deltaY) {
        this.setCoordinates2(x + deltaX);
        this.setCoordinates3(y + deltaY);
    }
    /**
     * Returns the distance between the current point and the point n.
     *
     * @param n The point to calculate the distance to.
     * @return The distance between the two points.
     */

    public double pointDistance(Point n) {

        double deltaX = x - n.getX();
        double deltaY = y - n.getY();

        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

    }
    /**
     * Returns the ID of this point.
     *
     * @return The ID of this point.
     */
    public double getPointID() {
        return pointID; }

   /**
    * Returns the number of active instances of Point objects.
     *
    * @return The number of active instances of Point objects.
    */

    public static int activeInstances(){

        return activeInstances;
    }
    /**
     * Calculates the distance between two points.
     *
     * @param m The first point to calculate the distance from.
     * @param n The second point to calculate the distance to.
     * @return The distance between the two points.
     */

    public static double pointDistance(Point m, Point n ){
        return m.pointDistance(n);}
    /**
     * Decrements the count of active instances of Point objects when this object is finalized.
     */

    protected void finalize() throws Throwable {

        super.finalize();

        activeInstances--;

    }



}








