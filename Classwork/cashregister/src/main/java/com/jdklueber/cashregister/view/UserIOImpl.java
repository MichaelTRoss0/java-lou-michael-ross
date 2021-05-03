package com.jdklueber.cashregister.view;

import java.util.Scanner;


public class UserIOImpl implements UserIO {
    Scanner scanner = new Scanner(System.in);
    
    public void print(String message) {
        System.out.println(message);
    }

    public double readDouble(String prompt) {
        print(prompt);
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    }

    public double readDouble(String prompt, double min, double max) {
        boolean tryAgain = true;
        double result = 0;
        do {
            result = readDouble(prompt);
            if (result < min || result > max) {
                print("Please input a value between " + min + " and " + max);
            } else {
                tryAgain = false;
            }
            
        } while(tryAgain); 
        return result;
    }

    public float readFloat(String prompt) {
        print(prompt);
        float result = scanner.nextFloat();
        scanner.nextLine();
        return result;
    }

    public float readFloat(String prompt, float min, float max) {
        boolean tryAgain = true;
        float result = 0;
        do {
            result = readFloat(prompt);
            
            if (result < min || result > max) {
                print("Please input a value between " + min + " and " + max);
            } else {
                tryAgain = false;
            }
            
        } while(tryAgain); 
        return result;
    }

    public int readInt(String prompt) {
        print(prompt);
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    public int readInt(String prompt, int min, int max) {
        boolean tryAgain = true;
        int result = 0;
        do {
            result = readInt(prompt);
            
            if (result < min || result > max) {
                print("Please input a value between " + min + " and " + max);
            } else {
                tryAgain = false;
            }
            
        } while(tryAgain); 
        return result;
    }

    public long readLong(String prompt) {
        print(prompt);
        long result = scanner.nextLong();
        scanner.nextLine();
        return result;
    }

    public long readLong(String prompt, long min, long max) {
        boolean tryAgain = true;
        long result = 0;
        do {
            result = readLong(prompt);
            
            if (result < min || result > max) {
                print("Please input a value between " + min + " and " + max);
            } else {
                tryAgain = false;
            }
            
        } while(tryAgain); 
        return result;

    }

    public String readString(String prompt) {
        print(prompt);
        return scanner.nextLine();
    }    
}
