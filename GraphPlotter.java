// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP112 - 2019T1, Assignment 4
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;

public class GraphPlotter {

    // Constants for plotting the graph
    public static final double GRAPH_LEFT = 50;
    public static final double GRAPH_RIGHT = 550;
    public static final double GRAPH_BASE = 400;
    /**
     * Plot a graph of a sequence of numbers read from a file using +'s for each point.
     * The origin of the graph should be at (GRAPH_LEFT, GRAPH_BASE)
     * The method should ask the user for the name of a file
     *  then read all the numbers from the file into an ArrayList.
     * It should then plot the numbers:
     *  - Draw two axes
     *  - Plot each number as a small +.
     *  - The x value of the first point should be at GRAPH_LEFT, and
     *    the last point should be at GRAPH_RIGHT.
     *  - The points should be separated by (GRAPH_RIGHT - GRAPH_LEFT)/(number of points - 1)
     * Hints:
     *   use the readAllNumbers method from the lab exercise
     *   look at the model answers for the Temperature Analyser problem from assignment 3.
     */
   public void plotGraph() {
        String fileName = UI.askString("What is the name of your file?");
        //draws the x and y axis
        UI.drawLine(GRAPH_LEFT, 0, GRAPH_LEFT, GRAPH_BASE);
        UI.drawLine(GRAPH_LEFT, GRAPH_BASE, GRAPH_RIGHT, GRAPH_BASE);
        ArrayList<Double> numbers = this.readAllNumbers(fileName);
        double pointX = GRAPH_LEFT;
        //draws the points
        for (int i = 0; i < numbers.size(); i++) {
            double pointY = GRAPH_BASE-numbers.get(i);
            UI.drawString("+", pointX, pointY);
            pointX = pointX + ((GRAPH_RIGHT - GRAPH_LEFT)/(numbers.size()-1));
        }                                            
    }
   public ArrayList<Double> readAllNumbers(String fileName) {
        // scans the file the user put in 
        ArrayList<Double> points = new ArrayList<Double>();
        try {
            Scanner scan = new Scanner(new File(fileName));
            while (scan.hasNext()) {
                if (scan.hasNextDouble()) {
                    points.add(scan.nextDouble());
                }
                else {
                    scan.next();
                }
            }
            scan.close();
        }
        catch(IOException e) {
            UI.println("File reading failed");
        }
        return points;
    }
}

