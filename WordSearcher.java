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

public class WordSearcher {

    /**
     * Asks the user for a pattern and then finds and prints out (one per line) all
     *  the words in a dictionary that contain that pattern.
     * At the end, it prints out how many words contained the pattern.
     * It stops printing out words after it has found 100 of them, but it still
     *  prints the total number of matches at the end.
     * The dictionary is in the file dictionary.txt. 
     * Hints:
     *   Use the readAllTokens method from the lab exercise
     */
    public void search() {
        String fileName = "dictionary.txt";
        String pattern = UI.askString("Pattern: ");
        ArrayList<String> words = this.readAllTokens(fileName);
        int amountOfLines = 0;
        // checks if the word contains the pattern, if there are less than 100 printed, will be printed
        for (int i = 0; i < words.size(); i++) {
            String wordInFile = words.get(i);
            if (wordInFile.contains(pattern)) {
                if (amountOfLines <= 100) {
                    UI.println(wordInFile);
                }
                amountOfLines++;
            }
        }
        UI.println("Total matching words = "+amountOfLines);
    }
    public ArrayList<String> readAllTokens(String fileName) {
        //scans the file
        ArrayList<String> ans = new ArrayList<String>();
        try {
            Scanner scan = new Scanner(new File(fileName));
            while (scan.hasNext()) {
                if (scan.hasNext()) {
                    ans.add(scan.next());
                } else {
                    scan.next();
                }
            }
            scan.close();
        }
        catch(IOException e) {
            UI.println ("File reading failed");
        }
        return ans;
    }                                     
}


