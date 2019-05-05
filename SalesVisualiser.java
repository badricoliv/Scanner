// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP112 - 2019T1, Assignment 4
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/**
 * SalesVisualiser
 * Reads an order from a file and produces a bar graph of the data
 */

public class SalesVisualiser{

    // Constants for plotting the graph
    public static final double GRAPH_LEFT = 50;
    public static final double GRAPH_RIGHT = 650;
    public static final double GRAPH_BASE = 400;
    public static final double MONTH_WIDTH = (GRAPH_RIGHT-GRAPH_LEFT)/12;  // the width for each set of three bars
    public static final double BAR_WIDTH = 12;    // the width of each bar


    /**
     * Asks the user for the name of a file containing the details of sales
     *  reported by dealers over the last three years, and then produces a
     *  bar graph of the data, showing the sales for each month, with different
     *  color bars for each year.
     * Each line of a sales data file contains
     *    a year, a month, and a series of sales from the dealers
     *    There may be a different number of sales on each line.
     * For example:
     *   2016 01 21 15 32 12 2 7
     *   2016 02 5 18 12
     *   :
     *   2017 01 16 3 2 4 1 8 4 13
     *   2017 Feb 41 3
     *   :
     * There is no guarantee that the lines are in order of date
     * The total sales in any month will never be over 200.
     * 
     * The method should draw a bar graph with 12 sets of bars, one set for each month
     * Each set should have
     *  a red bar for the 2016 data,
     *  a green bar for the 2017 data, and
     *  a blue bar for the 2018 data
     * The height of the bar should be the total number of sales in that month
     * Hints:
     *   Use the readAllLines method from the lab exercise and a Scanner for each line
     *   After getting the year and month from the Scanner, you will need a loop to add
     *       up all the sales on each line.
     *   Look carefully at the example file and the example output.
     */
    public void graphSales() {
        String fileName = UI.askString("Enter the file: ");        
        UI.drawLine(GRAPH_LEFT, GRAPH_BASE, GRAPH_RIGHT, GRAPH_BASE);
        UI.drawString("Month:", GRAPH_LEFT-50, GRAPH_BASE+12);
        double numberLineX = GRAPH_LEFT+0;
        //draws the lines that indicate where the each month is, and number for each month
        for (int i = 0; i < 12; i++) {
            UI.drawLine(numberLineX,GRAPH_BASE+5,numberLineX,GRAPH_BASE-5);
            double numberX = numberLineX +MONTH_WIDTH/2;
            numberLineX = numberLineX + MONTH_WIDTH;
            
            int number = i+1;
            UI.drawString(""+number, numberX, GRAPH_BASE+12);
        }
        ArrayList<String> sales = this.readAllLines(fileName);
    }
    public ArrayList<String> readAllLines (String fileName) {
        
        ArrayList<String> items = new ArrayList<String>();
        //scans the file
        try {
            File salesFile = new File(fileName);
            Scanner scan = new Scanner(salesFile);
            while (scan.hasNext()) {
                items.add(scan.nextLine());
            }
            
            scan.close();
        } catch (IOException e) {
            UI.println("File failure " +e);
        }
        //scans every token in every line
        for (String line : items) {
            Scanner lineSC = new Scanner(line);
            int year = lineSC.nextInt();
            int month = lineSC.nextInt();
            ArrayList<Double> sales = new ArrayList<Double>();
            double singleSale = 0;
            int counter = 0;
            double salesTotal = 0;
            while(lineSC.hasNext()) {
                
                singleSale = lineSC.nextInt();
                sales.add(singleSale);
                salesTotal = salesTotal+sales.get(counter);
                counter++;
            }            
            double checkYear = 2016;
            double amountOfYears = 3;
            double bar_left = GRAPH_LEFT;
            int r = 255;
            int g = 0;
            int b = 0;
            // draws the bars, each year in different colour
            for (int i = 0; i < amountOfYears; i++) {
                Color barColor = new Color(r,g,b);
                if (year == checkYear) {
                    
                    UI.setColor(barColor);
                    if (month == 1) {
                        UI.fillRect(bar_left, GRAPH_BASE-salesTotal, BAR_WIDTH, salesTotal);
                    } else if (month == 2) {
                        UI.fillRect(bar_left+MONTH_WIDTH,GRAPH_BASE-salesTotal, BAR_WIDTH, salesTotal);
                    } else if (month == 3) {
                        UI.fillRect(bar_left+MONTH_WIDTH*2,GRAPH_BASE-salesTotal, BAR_WIDTH, salesTotal);
                    } else if (month == 4) {
                        UI.fillRect(bar_left+MONTH_WIDTH*3,GRAPH_BASE-salesTotal, BAR_WIDTH, salesTotal);
                    } else if (month == 5) {
                        UI.fillRect(bar_left+MONTH_WIDTH*4, GRAPH_BASE-salesTotal, BAR_WIDTH, salesTotal);
                    } else if (month == 6) {
                        UI.fillRect(bar_left+MONTH_WIDTH*5, GRAPH_BASE-salesTotal, BAR_WIDTH, salesTotal);
                    } else if (month == 7) {
                        UI.fillRect(bar_left+MONTH_WIDTH*6, GRAPH_BASE-salesTotal, BAR_WIDTH, salesTotal);
                    } else if (month == 8) {
                        UI.fillRect(bar_left+MONTH_WIDTH*7, GRAPH_BASE-salesTotal, BAR_WIDTH, salesTotal);
                    } else if (month == 9) {
                        UI.fillRect(bar_left+MONTH_WIDTH*8, GRAPH_BASE-salesTotal, BAR_WIDTH, salesTotal);
                    } else if (month == 10) {
                        UI.fillRect(bar_left+MONTH_WIDTH*9, GRAPH_BASE-salesTotal, BAR_WIDTH, salesTotal);
                    } else if (month == 11) {
                        UI.fillRect(bar_left+MONTH_WIDTH*10, GRAPH_BASE-salesTotal, BAR_WIDTH, salesTotal);
                    } else if (month == 12) {
                        UI.fillRect(bar_left+MONTH_WIDTH*11, GRAPH_BASE-salesTotal, BAR_WIDTH, salesTotal);
                    }                
                }
                bar_left = bar_left+BAR_WIDTH+1;
                checkYear = checkYear+1;
                if (year == 2017) {
                    g = 255;
                    r = 0;
                } else if (year == 2018) {
                    b = 255;
                    r = 0;
                }
            }
        }        
        return items;
    }



}
