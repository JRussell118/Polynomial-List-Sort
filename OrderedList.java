/*Jaden Russell
  Project 2
  4/9/23
  This program creates a class that defines two checkSorted methods to
  check if a list that extends Comparable is in ascending order using a Comparator.
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
import java.util.List;
import java.util.Comparator;

public class OrderedList {

    public static <T extends Comparable<? super T>> boolean checkSorted(List<T> l) {
        return (!checkSorted(l, new Comparator<T>() {
            @Override
            public int compare(T e1, T e2) {
                return e1.compareTo(e2);
            }
        }));
    }

    public static <T extends Comparable<? super T>> boolean checkSorted(List<T> l, Comparator<? super T> c) {
        for (int i = 0; i < l.size(); i++) {
            for (int j = 1; j < l.size(); j++) {
                if (c.compare(l.get(i), l.get(j)) < 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
