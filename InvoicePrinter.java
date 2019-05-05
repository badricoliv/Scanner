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
 * InvoicePrinter
 * Reads an order from a file and prints an invoice
 */

public class InvoicePrinter{

    /**
     * Asks the user for the name of a file containing the details of an order
     *  and then prints out an invoice for the order.
     * Each line of an order file contains an item, a count, and a unit-price.
     * eg: an order line for 3 packets of butter at $4.20 per packet would be
     *    butter      3  4.20
     *  
     * For each line in the order, the invoice must have a line with the
     *  the count, the item, the unit price, and the total price. eg
     *    3 x butter @ $4.20       $12:60
     * 
     * At the end of the invoice, it must print out
     *   the total cost of the order
     *   the GST component of the order
     *   the ex-GST cost.
     * Hints:
     *   This assignment builds directly on the lab exercise!
     *   Use the readAllLines method from the lab exercise and a Scanner for each line
     */
    public void printInvoice() {
        String fileName = UI.askString("What is the name of the file: "); 
        UI.println("=======================================");
        UI.println("Invoice:");
        UI.println("=======================================");
        ArrayList<String> order = this.readAllLines(fileName);
    }
  public ArrayList<String> readAllLines (String fileName) {
        //scanning the file
        ArrayList<String> lines = new ArrayList<String>(); 
        double totalPrice = 0;
        double GST = 0;
        double priceWithGST = 0;
        try {
            File orderFile = new File(fileName);
            Scanner scan = new Scanner(orderFile);
            while (scan.hasNext()) {
                lines.add(scan.nextLine());                
            }
            scan.close();
        } catch (IOException e) {
            UI.println("File failure: "+e);
        }
        // scans the tokens in each line, done for every line
        for (String line : lines) {
            Scanner lineSC = new Scanner(line);
            String item = lineSC.next();
            int quantity = lineSC.nextInt();
            double pricePerItem = lineSC.nextDouble();
            double totalItemPrice = pricePerItem*quantity;
            UI.printf(" " + quantity + " x " + item + " @ $" + "%5.2f%n",totalItemPrice);
            totalPrice = totalPrice + totalItemPrice;
            GST = (totalPrice*3)/23;
            priceWithGST = totalPrice-GST;
        }
        UI.println("=======================================");
        UI.printf("Total cost  $"+"%5.2f%n",totalPrice);
        UI.printf("GST         $"+"%4.2f%n",GST);
        UI.printf("ex-GST cost $"+"%5.2f%n",priceWithGST);
        UI.println("=======================================");
        return lines; 
    }



}
