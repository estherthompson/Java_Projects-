/**
 * The Circle class represents a circle object with a given x, y coordinate, and radius
 * @author estherthompson
 */

public class Circle extends Shape{
    /** the radius of the circle */
    private double rVal;
    /**Constructs a Circle objects with given x, y coordinates and radius
     * @param xVal the x coordinate
     * @param yVal the y coordinate
     * @param rVal the radius of the circle */
    public Circle(double xVal, double yVal, double rVal ){
        super(xVal,yVal);
        this.rVal = rVal;
    }
    /** Constructs a Circle object the intializes the radius to 0.0*/
    public Circle (double rVal){
        super();

        this.rVal = 0.0;
    }
    /** Set instance Method for the radius of the Circle
     * @param rVal sets the radius of the circle */
    public void setRadius(double rVal) {

        this.rVal = rVal;
    }
    /**Get instance Method for the radius of the Circle
     * @return returns the radius of the circle
     * */
    public double getRadius(){
        return rVal;

    }
    /**Calculates the area of the Circle
     * @return the area of the Circle
     * */
    public double area(){
        return Math.PI * (rVal*rVal);
    }
    /**Calculates the circumference of the Circle
     * @return the circumference of the Circle
     * */
    public double circumference(){
        return 2 * Math.PI * rVal;
    }

    /** returns the Circle x,y coordinate and radius as a string
     * @return return the string of the location of the Circle, as well as the radius
     * */

    @Override
    public String toString() {
        return "The location of the Circle is: " + super.getOrigin().toString() + "with a Radius of" +
                rVal;
    }
}
