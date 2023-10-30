/**
 * The Rectangle class represents a rectangle  objects with a given x, y coordinate, length and width
 * @author estherthompson
 */
public class Rectangle extends Shape {
    /** The length and width of the Rectangle */
    private double length, width;
    /** Constructs a Rectangle objects with given x, y coordinates, length and width
     * @param xVal the x coordinate of the rectangle
     * @param yVal the y coordinate of the rectangle
     * @param length the length of the rectangle
     * @param width the width of the rectangle **/
    public Rectangle(double xVal, double yVal, double length, double width) {
        super(xVal, yVal);
        this.length = length;
        this.width = width;
    }
    /** Constructs a Rectangle object the initializes the  length and width to 0.  */
    public Rectangle(){
        super();
        this.length = 0.0;
        this.width = 0.0;
    }
    /**
     * Set instance method for the length of the Rectangle
     * @param length sets the length of the Rectangle
     * */
    public void setLength(double length){
        this.length = length;

    }
    /** Set instance method for the width of the Rectangle
     * @param width sets the width of the Rectangle
     * */
    public void setWidth(double width){

        this.width = width;
    }
    /** Set instance method for both the width and length of the Rectangle
     * @param length sets the width of the Rectangle
     * @param width sets the width of the Rectangle
     * */
    public void setBoth(double length, double width){
        this.length = length;
        this.width = width;

    }
    /** Get method for the length of the Rectangle.
     * @return the width of the Rectangle
     * */
    public double getLength(){
        return length;
    }
    /** Get method for the length of the Rectangle.
     * @return the width of the Rectangle
     * */
    public double getWidth(){
        return width;
    }
    /** Calculates the area of the Rectangle
     * @return the area of the Rectangle
     * */
    public double area(){
        return length * width;
    }
    /** Calculates the area of the Rectangle
     * @return the circumference in this case perimeter of the Rectangle
     * */
    public double circumference(){
        return 2 * (length + width);
    }
    /** returns the Rectangle, length and width as a string
     * @return return the string of the rectangle, as well as the length and width
     * */

    @Override
    public String toString() {
        return "The location of the Rectangle is: " + super.getOrigin().toString() + "with a length of" +
                length + "and width of " + width;
    }

}

