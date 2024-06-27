/*Jaden Russell
  Project 2
  4/9/23
  This program lets the user choose a file with polynomials, creates a list of polynomials
  with the inputs from the file, and checks if they are sorted according to the weak and/or strong order.
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project2_jaden_russell;

/**
 *
 * @author jaden
 */
import javax.swing.JFileChooser;
import java.io.File;
import java.util.*;
import java.io.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        ArrayList<Polynomial> polyList = new ArrayList<>();

        JFileChooser c = new JFileChooser("C:\\Users\\jaden\\OneDrive\\Documents\\NetBeansProjects\\Project2_Jaden_Russell\\src\\project2_jaden_russell");
        int result = c.showOpenDialog(c);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("The file could not be opened.");
            System.exit(0);
        }

        File selectedFile = c.getSelectedFile();
        BufferedReader read = new BufferedReader(new FileReader(selectedFile));
        String fileLine = read.readLine();

        while (fileLine != null) {
            Polynomial tempPoly = new Polynomial(fileLine);
            polyList.add(tempPoly);
            System.out.println("Added the polynomial: " + tempPoly.toString());
            fileLine = read.readLine();
        }

        boolean strongSort = true;

        for (int i = 0; i < polyList.size(); i++) {
            for (int j = i + 1; j < polyList.size(); j++) {
                if (polyList.get(i).compareTo(polyList.get(j)) == -1) {
                    strongSort = false;
                }
            }
        }

        boolean weakSort = true;
        Comparator<Polynomial> polyComparator = (p1, p2) -> p1.iterator().next().expo - p2.iterator().next().expo;

        for (int i = 0; i < polyList.size(); i++) {
            for (int j = i + 1; j < polyList.size(); j++) {
                if (polyComparator.compare(polyList.get(i), polyList.get(j)) == -1) {
                    weakSort = false;
                }
            }
        }

        if (weakSort && strongSort) {
            System.out.println("The list is sorted by the strong and the weak order.");
        } else if (strongSort) {
            System.out.println("The list is sorted by the strong order.");
        } else if (weakSort) {
            System.out.println("The list is sorted by the weak order.");
        } else {
            System.out.println("The list is not sorted by the strong or the weak order.");
        }
    }
}
