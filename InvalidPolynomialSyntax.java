/*Jaden Russell
  Project 2
  4/9/23
  This program creates a custom, unchecked exception for invalid polynomial syntax.
*/
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_jaden_russell;

/**
 *
 * @author jaden
 */
import javax.swing.*;

public class InvalidPolynomialSyntax extends RuntimeException {

    public InvalidPolynomialSyntax(String msg) {
        super();
        JOptionPane.showMessageDialog(null, msg, "Error", 0);
    }

}
