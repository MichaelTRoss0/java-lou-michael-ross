/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.contactjdbcexercise.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author mike
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal result = BigDecimal.ZERO;
        boolean tryAgain = true;

        do {
            try {
                print(prompt);
                result = scanner.nextBigDecimal();
                scanner.nextLine();
                tryAgain = false;
            } catch (InputMismatchException e) {
                print("Input must be a BigDecimal.");
                scanner.nextLine();
            }
        } while (tryAgain);

        return result;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal result = BigDecimal.ZERO;
        boolean goodInput = false;

        do {
            result = readBigDecimal(prompt);
            if ((result.compareTo(min) >= 0) && (result.compareTo(max) <= 0)) {
                goodInput = true;
            } else {
                print("Input must be a BigDecimal between " + min + " and " + max + ".");
            }
        } while (!goodInput);

        return result;
    }

    @Override
    public double readDouble(String prompt) {
        double result = 0.0;
        boolean tryAgain = true;

        do {
            try {
                print(prompt);
                result = scanner.nextDouble();
                scanner.nextLine();
                tryAgain = false;
            } catch (InputMismatchException e) {
                print("Input must be a double.");
                scanner.nextLine();
            }
        } while (tryAgain);

        return result;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double result = 0.0;
        boolean goodInput = false;

        do {
            result = readDouble(prompt);
            if (min <= result && result <= max) {
                goodInput = true;
            } else {
                print("Input must be a double between " + min + " and " + max + ".");
            }
        } while (!goodInput);

        return result;
    }

    @Override
    public float readFloat(String prompt) {
        float result = 0;
        boolean tryAgain = true;

        do {
            try {
                print(prompt);
                result = scanner.nextFloat();
                scanner.nextLine();
                tryAgain = false;
            } catch (InputMismatchException e) {
                print("Input must be a float.");
                scanner.nextLine();
            }
        } while (tryAgain);

        return result;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float result = 0;
        boolean goodInput = false;

        do {
            result = readFloat(prompt);
            if (min <= result && result <= max) {
                goodInput = true;
            } else {
                print("Input must be an flaot between " + min + " and " + max + ".");
            }
        } while (!goodInput);

        return result;
    }

    @Override
    public int readInt(String prompt) {
        int result = 0;
        boolean tryAgain = true;

        do {
            try {
                print(prompt);
                result = scanner.nextInt();
                scanner.nextLine();
                tryAgain = false;
            } catch (InputMismatchException e) {
                print("Input must be an integer.");
                scanner.nextLine();
            }
        } while (tryAgain);

        return result;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int result = 0;
        boolean goodInput = false;

        do {
            result = readInt(prompt);
            if (min <= result && result <= max) {
                goodInput = true;
            } else {
                print("Input must be an integer between " + min + " and " + max + ".");
            }
        } while (!goodInput);

        return result;
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        LocalDate result = LocalDate.MIN;
        boolean tryAgain = true;

        do {
            try {
                String dateAsText = this.readString(prompt);
                result = LocalDate.parse(dateAsText);
                tryAgain = false;
            } catch (DateTimeParseException e) {
                print("Input must be a date in the format YYYY-MM-DD.");
            }
        } while (tryAgain);

        return result;
    }

    @Override
    public LocalDate readLocalDate(String prompt, LocalDate min, LocalDate max) {
        LocalDate result = LocalDate.MIN;
        boolean goodInput = false;

        do {
            result = readLocalDate(prompt);
            if ((result.compareTo(min) >= 0) && (result.compareTo(max) <= 0)) {
                goodInput = true;
            } else {
                print("Input must be a LocalDate between " + min + " and " + max + ".");
            }
        } while (!goodInput);

        return result;
    }

    @Override
    public long readLong(String prompt) {
        long result = 0;
        boolean tryAgain = true;

        do {
            try {
                print(prompt);
                result = scanner.nextLong();
                scanner.nextLine();
                tryAgain = false;
            } catch (InputMismatchException e) {
                print("Input must be a long.");
                scanner.nextLine();
            }
        } while (tryAgain);

        return result;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long result = 0;
        boolean goodInput = false;

        do {
            result = readLong(prompt);
            if (min <= result && result <= max) {
                goodInput = true;
            } else {
                print("Input must be an long between " + min + " and " + max + ".");
            }
        } while (!goodInput);

        return result;
    }

    @Override
    public String readString(String prompt) {
        String result;

        print(prompt);
        result = scanner.nextLine();

        return result;
    }

}
