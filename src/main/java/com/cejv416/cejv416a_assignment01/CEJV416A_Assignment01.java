package com.cejv416.cejv416a_assignment01;

import java.util.Scanner;

/**
 * Calculate one of three financial formula chosen from a menu
 *
 * @author Ken Fogel
 */
public class CEJV416A_Assignment01 {

    private final Scanner sc;
    private double loanAmount;
    private double savingsAmount;
    private double futureValue;
    private double interestRate;
    private double term;

    /**
     * Constructor to initialize the Scanner object
     */
    public CEJV416A_Assignment01() {
        sc = new Scanner(System.in);
    }

    /**
     * Show the menu
     */
    private void displayMenuText() {
        System.out.println("Please enter the letter for one of the following:");

        System.out.println("A. Loan Payment");
        System.out.println("B. Future Value");
        System.out.println("C. Savings Goal");
        System.out.println("D. Exit");
    }

    /**
     * Retrieve user menu choice, validating that it falls in the range,
     * inclusive of A,B,C,D
     *
     * @return user choice as a char
     */
    public char menu() {

        char choice;

        do {
            displayMenuText();
            // Validate with a regular expression for a range of characters, 
            // upper and lower case
            if (sc.hasNext("[a-dA-D]")) { // Accept only a single letter String
                // Retrieve the string from the keyboard buffer, convert it to 
                // upper case, and retrieve the first letter as a char
                choice = sc.next().toUpperCase().charAt(0);
            } else {
                // Did not match the regular expression. Inform user and set 
                // choice to the character z
                System.out.println("Invalid choice");
                choice = 'z'; // Indicates invalid input
            }
            // Clear out the keyboard buffer
            sc.nextLine();
        } while (choice == 'z'); // Keep asking until choice is not wrequal to z

        return choice;
    }

    /**
     * Display a prompt and accept a double that falls in the allowable range.
     * This approach is preferable to duplicating this code for each
     * calculation.
     *
     * @param upperRange The maximum value allowed
     * @param prompt The prompt for user input
     * @return the value in range
     */
    private double inputDouble(String prompt, double upperRange) {
        double value = 0.0; // User input
        boolean inCorrect = false; // If value is not a double or out of range
        do {
            System.out.println(prompt + upperRange + ": ");
            if (sc.hasNextDouble()) { // Check that there is a double in the keyboard buffer
                value = sc.nextDouble(); // 
                // Check if the number is in range
                if (value < 0.0 || value >= upperRange) {
                    System.out.printf("%.2f is out of range.", value);
                    inCorrect = true;
                }
            } else { // There was not a double in the keyboard buffer
                inCorrect = true;
                System.out.println("You have not entered a number");
            }
            sc.nextLine(); // Clean out the buffer
        } while (inCorrect);

        return value;
    }

    /**
     * Request values for loan payment calculation
     */
    private void doLoanPaymentInput() {
        loanAmount = inputDouble("Enter loan amount, maximum: ", 1_000_000.00);
        interestRate = inputDouble("Enter interest rate as a decimal (5% -> 0.05), maximum: ", 1.0) / 12;
        term = inputDouble("Enter the term in months: ", 120.0);
    }

    /**
     * Request values for future value calculation
     */
    private void doFutureValueInput() {
        futureValue = inputDouble("Enter monthly savings amount, maximum ", 1_000.00);
        interestRate = inputDouble("Enter interest rate as a decimal (5% -> 0.05), maximum: ", 1.0) / 12;
        term = inputDouble("Enter the term in months, maximum: ", 120.0);
    }

    /**
     * Request values for savings goal calculation
     */
    private void doSavingsGoalInput() {
        savingsAmount = inputDouble("Enter savings goal amount, maximum: ", 1_000_000.00);
        interestRate = inputDouble("Enter interest rate as a decimal (5% -> 0.05) maximum: ", 1.0) / 12;
        term = inputDouble("Enter the term in months, maximum: ", 120.0);
    }

    /**
     * Calculate the monthly loan payment
     *
     * @return the loan payment
     */
    private double doLoanPayment() {
        double result = loanAmount * ((interestRate) / (1 - Math.pow(1 + interestRate, -term)));
        return result;
    }

    /**
     * Calculate how much money to save each month to reach a goal
     *
     * @return the monthly amount to save
     */
    private double doFutureValue() {
        double result = savingsAmount * (interestRate / (1 - Math.pow(1 + interestRate, term)));
        return Math.abs(result);
    }

    /**
     * Calculate the amount you will have saved from making regular payments
     * each month
     *
     * @return the savings goal
     */
    private double doSavingsGoal() {
        double result = futureValue * (((1 - Math.pow(1 + interestRate, term)) / interestRate));
        return Math.abs(result);
    }

    /**
     * This is method that manages the application by taking the menu choice and
     * then calling upon the appropriate methods to carry out the work.
     */
    public void perform() {
        char choice;

        do {
            choice = menu();
            switch (choice) {
                case 'A' -> {
                    doLoanPaymentInput();
                    System.out.printf("Monthly payment will be %.2f.\n", doLoanPayment());
                }
                case 'B' -> {
                    doFutureValueInput();
                    System.out.printf("Savings goal %.2f.\n", doSavingsGoal());
                }
                case 'C' -> {
                    doSavingsGoalInput();
                    System.out.printf("Save %.2f each month.\n", doFutureValue());
                }
                case 'D' ->
                    System.out.println("Thank you for using the CEJV416A calculator");
                default -> {
                    System.out.println("Something has gone wrong. Call IT department.");
                    System.exit(1);
                }
            }

        } while (choice != 'd');
    }

    /**
     * Every Java program requires a main method. You want to do as little as
     * possible in this method. You should just instantiate the class that will
     * take control and then call upon a method in that class to start the work.
     *
     * @param args You can pass values when the program is run from the command
     * line.
     */
    public static void main(String[] args) {
        new CEJV416A_Assignment01().perform();
        // or
        // CEJV416A_Assignment01 calculator = new CEJV416A_Assignment01();
        // calculator.perform();
    }
}
