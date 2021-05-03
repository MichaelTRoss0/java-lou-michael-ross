/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temperature;

import java.util.Scanner;

/**
 *
 * @author mike
 */
public class Temperature {
    public static void main(String[] args) {
        // create & initialize variables
        Scanner sc = new Scanner(System.in);
        double temp;
        String scale;
        double conv;
        boolean cont;
        
        do {
            // clear the variables
            temp = 0.0;
            scale = "";
            conv = 0.0;
            
            // Ask user for temp
            System.out.print("What is the temperature? ");
            temp = sc.nextDouble();

            // Ask user for C or F
            System.out.print("Is this measured in F or C? ");

            // C or F
            scale = sc.next();

            // if C, compute Fahrenheit
            // if F, compute Celsius
            if (null == scale) {
                System.out.println("Sorry, I don't know what to do.");
            } else 
            switch (scale) {
                case "C":
                    conv = temp * 9.0 / 5.0 + 32.0;
                    // Report back conversion
                    System.out.println(temp + " degrees Celsius is " + conv + " degrees Fahrenheit.");
                    break;
                case "F":
                    conv = (temp - 32.0) * 5.0 / 9.0;
                    // Report back conversion
                    System.out.println(temp + " degrees Fahrenheit is " + conv + " degrees Celsius.");
                    break;
                default:
                    System.out.println("Sorry, I don't know what to do.");
                    break;
            }
            
            // Ask the user if they want to continue
            System.out.print("Would you like to do continue (y/n)? ");
            cont = "y".equals(sc.next());
        } while (cont == true);
        
        sc.close();
    }
    
    
}
