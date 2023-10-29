// Esther Thompson
// UCID - 30176925
// TUTORIAL T03
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Test class that allows the user to create, delete shape objects
 */
public class Test {
    /**
     * The main method for the test class
     * @param args command line argument*/
    public static void main(String[] args) {

        int maxShapes = 10;
        Scanner keyboard = new Scanner(System.in);
        //creating an array list for the shapes
        ArrayList<Shape> shapes = new ArrayList<>();
        // putting the text menu and the rest of the code into a do-while loop
        do {
            System.out.println("1.\tCreate Rectangle");
            System.out.println("2.\tCreate Circle");
            System.out.println("3.\tSet the origin point for Shape");
            System.out.println("4.\tSet the dimensions for Shape ");
            System.out.println("5.\tDisplay the current state of Shape ");
            System.out.println("6.\tMove  Shape according to delta x and delta y");
            System.out.println("7.\tCompute distance between two objects");
            System.out.println("8.\tDestroy any object ");
            System.out.println("9.\tList the current state for all objects in the array.");
            System.out.println("10.\tQuit the program");
            System.out.println();
            System.out.println("Please enter a number from 1-9 in the text menu: " + "\n");
        // depending on the user input the code will run through these if - else-if statements
            int number = keyboard.nextInt();
        // Creating the Rectangle
            if (number == 1) {
                //error checking for max objects
                if (shapes.size() >= maxShapes){
                    System.out.println("Maximum number of rectangles has be created!");
                    break;
                }
                else {
                //taking user input for length and width
                System.out.println("Please enter length: ");
                double length = keyboard.nextDouble();
                System.out.println("Please enter width: ");
                double width = keyboard.nextDouble();
                //creating the rectangle object with users input, and adding it to the Array list
                Rectangle rectangle = new Rectangle(0,0, length, width);
                shapes.add(rectangle);
                System.out.println("Rectangle index: " + shapes.indexOf(rectangle));}

        // Creating the Circle, same steps at rectangle accept we are only asking for the radius.
            } else if (number == 2) {
                if (shapes.size() >= maxShapes){
                    System.out.println("Maximum number of circle has be created!");
                    break;
                }
                else{
                System.out.println("Please enter radius: ");
                double rVal = keyboard.nextDouble();
                Circle circle = new Circle(rVal);
                shapes.add(circle);
                System.out.println("Circle index: " + shapes.indexOf(circle));}
        //setting the origin for the shape
            } else if (number == 3) {
                System.out.println("Please enter index of the shape (Shape 1 is at index 0): ");
                int shapeIndex1 = keyboard.nextInt();
                if (shapeIndex1 < 0 || (shapeIndex1 >= shapes.size())) {
                    System.out.println("Invalid Shape index");
                    break;
                }
                else {
                    System.out.println("Please enter origin for x-coordinate: ");
                    double xOrigin = keyboard.nextDouble();
                    System.out.println("Please enter origin for y-coordinate: ");
                    double yOrigin = keyboard.nextDouble();
                    // creating origin point based on user input for the Array list
                    Shape shape = shapes.get(shapeIndex1);
                    shape.setOrigin(new Point(xOrigin, yOrigin));
                }
                //setting the dimensions of the shape
            } else if (number == 4) {
                System.out.println("Please enter index of the shape (Shape 1 is at index 0):   ");
                int shapeIndex2 = keyboard.nextInt();
                if (shapeIndex2 < 0 || (shapeIndex2 >= shapes.size())) {
                    System.out.println("Invalid Shape index");
                    break;
                }
                Shape shape = shapes.get(shapeIndex2);
                //setting the dimension of the shape if user shape is a rectangle
                if (shape instanceof Rectangle) {
                        System.out.println("Enter Length: ");
                        double length = keyboard.nextDouble();
                        System.out.println("Enter Width: ");
                        double width = keyboard.nextDouble();
                        ((Rectangle) shape).setBoth(length, width);
                //setting the dimension of the shape if user shape is a circle

                } else if (shape instanceof Circle) {
                        System.out.println("Enter the radius: ");
                        double radius = keyboard.nextDouble();
                        ((Circle) shape).setRadius(radius);
                    }
                }
            //displaying the index of the shape
             else if (number == 5) {

                System.out.println("Please enter the index of the Shape (Shape 1 is at index 0)");
                int shapeIndex3 = keyboard.nextInt();
                if (shapeIndex3 < 0 || (shapeIndex3 >= shapes.size())) {
                    System.out.println("Invalid Shape index");
                    break;
                }
            // displaying the shape at the index
                else {
                    Shape shape = shapes.get(shapeIndex3);
                    System.out.println("the index of the shape is: " + shape.toString());
                }
            // moving the shape
            } else if (number == 6) {
                System.out.println("Enter the shape's index (Shape 1 is at index 0):");
                int shapeIndex4 = keyboard.nextInt();
                if (shapeIndex4 < 0 || (shapeIndex4 >= shapes.size())) {
                    System.out.println("Invalid Shape index");
                    break;
                }
            //asking user how many points they want to move
                else {
                    Shape shape = shapes.get(shapeIndex4);
                System.out.println("Enter number of points do you want move the shape - x-coordinate: ");
                double deltaX = keyboard.nextDouble();
                System.out.println("Enter number of points do you want move the shape - y-coordinate: ");
                double deltaY = keyboard.nextDouble();
                shape.move(deltaX, deltaY);}
            //Displaying the distance of two shapes
            } else if (number == 7) {
                System.out.println("Enter the index of Shape 1 (Shape 1 is at index 0): ");
                int shapeIndex5 = keyboard.nextInt();
                Shape shape1 = shapes.get(shapeIndex5);
                System.out.println("Enter the index of Shape 2 (Shape 1 is at index 0): ");
                int shapeIndex6 = keyboard.nextInt();
                if ((shapeIndex5 < 0 || (shapeIndex5 >= shapes.size())) &&
                        (shapeIndex6 < 0 || (shapeIndex6 >= shapes.size()))) {
                    System.out.println("Invalid Shape index");
                    break;
                }
                else {
                    Shape shape2 = shapes.get(shapeIndex6);
                    Point origin1 = shape1.getOrigin();
                    Point origin2 = shape2.getOrigin();
                    double distance = origin1.pointDistance(origin2);
                    System.out.println("The distance of between two objects is:  " + distance);
                }

            // destroying objects the user doesn't want anymore

            } else if (number == 8) {
                System.out.println("Enter the index of the shape you want to destroy (Shape 1 is at index 0): ");
                int enteredIndex = keyboard.nextInt();
                if (enteredIndex < 0 || enteredIndex >= shapes.size()){
                    System.out.println("Invalid Shape index");
                    break;
                }
                // setting the shape at the entered index to null
                else{
                shapes.set(enteredIndex, null);
                System.out.println("Shape " + enteredIndex + " is not destroyed");
                }
            //listing the current state for all the objects in the Array list

            } else if (number == 9) {
                System.out.println("The state of the the shape is ");
                for (Shape shape : shapes) {
                    if (shape != null) {
                        System.out.println(shapes.toString());
                    }
                }
                break;
            }
         // allowing the user to quit the program
            else if (number == 10) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
            // ending the do-while loop
            } while (true);
        }
    }
