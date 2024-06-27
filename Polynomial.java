/*Jaden Russell
  Project 2
  4/9/23
  This program creates a class to create polynomials with a 
  list of terms each with a coefficient and exponent.
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_jaden_russell;

import java.util.*;

/**
 *
 * @author jaden
 */
public class Polynomial implements Iterable, Comparable<Polynomial> {

    private TermList terms = new TermList();

    public Polynomial(String polyTerms) throws InvalidPolynomialSyntax {

        String[] termNum = polyTerms.split(" ");

        try {
            if (termNum.length % 2 != 0) {
                throw new InvalidPolynomialSyntax("Each polynomial term needs a coefficient and exponent.");
            }

            for (int i = 0; i < termNum.length; i++) {
                if (i % 2 != 0) {
                    if (Integer.parseInt(termNum[i]) <= -1 || termNum[i].contains(".")) {
                        throw new InvalidPolynomialSyntax("Exponents should be whole numbers greater than or equal to 0.");
                    } else if (i > 1) {
                        if (Integer.parseInt(termNum[i]) > Integer.parseInt(termNum[1])) {
                            throw new InvalidPolynomialSyntax("The polynomial should be written from the highest to lowest exponents.");
                        } else if (Integer.parseInt(termNum[i]) == Integer.parseInt(termNum[1])) {
                            if (Integer.parseInt(termNum[i - 1]) > Integer.parseInt(termNum[0])) {
                                throw new InvalidPolynomialSyntax("The polynomial should be written from the highest to lowest coefficients.");
                            }
                        }
                    }
                    terms.insertTerm(Double.parseDouble(termNum[i - 1]), Integer.parseInt(termNum[i]));
                }
            }
        } catch (NumberFormatException e) {
            throw new InvalidPolynomialSyntax("Your polynomial terms need to contain numbers only.");
        }
    }

    @Override
    public int compareTo(Polynomial comp) {

        Iterator<Link> p1 = this.iterator();
        Iterator<Link> p2 = comp.iterator();
        Link t1, t2;

        do {
            t1 = p1.next();
            t2 = p2.next();
            if (t1.expo > t2.expo) {
                return 1;
            } else if (t1.expo < t2.expo) {
                return -1;
            } else if (t1.coEff > t2.coEff) {
                return 1;
            } else if (t1.coEff < t2.coEff) {
                return -1;
            }
        } while (p1.hasNext() && p2.hasNext());
        return 0;
    }

    @Override
    public Iterator<Link> iterator() {
        return new Iterator<Link>() {

            Link termLink = terms.first;

            @Override
            public Link next() {
                if (hasNext()) {
                    Link current = termLink;
                    termLink = termLink.next;
                    return current;
                }
                return null;
            }

            @Override
            public boolean hasNext() {
                return termLink != null;
            }
        };
    }

    public String toString() {
        Iterator<Link> movePoly = this.iterator();
        String poly = "";
        Link term;
        while (movePoly.hasNext()) {
            if (!"".equals(poly)) {
                poly = poly + " + ";
            }
            term = movePoly.next();
            switch (term.expo) {
                case 0:
                    poly = poly + String.valueOf(term.coEff);
                    break;
                case 1:
                    poly = poly + String.valueOf(term.coEff + "x");
                    break;
                default:
                    poly = poly + String.valueOf(term.coEff) + "x^" + String.valueOf(term.expo);
                    break;
            }
        }

        return poly;
    }

    static class TermList {

        private Link first;

        public TermList() {

            first = null;
        }

        public void insertTerm(double c, int e) {

            if (first == null) {
                first = new Link(c, e);
            } else {
                Link temp = first;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = new Link(c, e);
                temp.next.next = null;
            }
        }
    }

    static class Link {

        public double coEff;
        public int expo;
        public Link next;

        public Link(double c, int e) {
            coEff = c;
            expo = e;
            next = null;
        }
    }
}
