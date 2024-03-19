// Jane Sobeck
// 3/5/2024
// CS 145 Lab 5
// The purpose of this java class is to help me learn how to utilize recursion in methods properly

import java.math.BigInteger;
import java.util.Scanner;

//Generates a Pascal's Triangle with a user defined iteration count, and prints it to the console
public class PascalsTriangle {

    /*
     * Main runs the intro/menu for the program, prompts the user for the amount of
     * iterations,
     * creates the array to be used as the triangle(aligned to the left/lower end of
     * the indexes),
     * then runs the generateValues and printTriangle methods.
     */
    public static void main(String[] args) throws Exception {
        Scanner userInput = new Scanner(System.in);
        String inputString = "";
        System.out.println("Welcome to the Pascal's Triangle Program!");
        while (inputString != "q") {
            System.out.println("Please enter how many iterations you would like to generate:");
            System.out.println("(Enter \"q\" if you would like to quit the program)");
            inputString = userInput.nextLine();
            int n = 0;
            if (inputString.toLowerCase().contains("q")) {
                break;
            } else if (Integer.parseInt(inputString) > 0) {
                n = Integer.parseInt(inputString);
            } else {
                System.out.println("Please enter a num greater than 0!");
                continue;
            }
            int[][] triangleArray = new int[n][n];
            triangleArray = generateValues(n, triangleArray);
            printTriangle(n, triangleArray);
        }
        System.out.println("Goodbye!");
        userInput.close();
    }

    /*
     * Using the nCr formula, calculates the binomial coeficcient for given row and
     * column in the
     * triangle(aligned to the left), and populates necessary indexes of an array
     * representing the
     * triangle.
     */
    public static int[][] generateValues(int n, int[][] triangleArray) {
        // for (int r = n, r > 0; r--) {
        // System.out.println(" ");
        // }
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (c > r) {
                    break;
                }
                /*
                 * Had to use BigInteger rather than int or long because the factorial products
                 * kept getting too big for both int and long
                 */
                BigInteger cFctrl = factorial(BigInteger.valueOf(c));
                BigInteger rFctrl = factorial(BigInteger.valueOf(r));
                BigInteger diffFctrl = factorial(BigInteger.valueOf(r - c));
                triangleArray[r][c] = (rFctrl.divide(cFctrl.multiply(diffFctrl))).intValue();
            }
        }
        return triangleArray;
    }

    /*
     * Recursive method with a base case of 1, returns the factorial value(BigInt)
     * of the passed
     * BigInteger value. Used BigInteger, as values kept getting too large for int
     * and
     * long variable types after iteration 13 and 21 respectively.
     */
    public static BigInteger factorial(BigInteger i) {
        if (i.compareTo(BigInteger.ONE) <= 0) {
            return BigInteger.ONE;
        } else {
            return i.multiply(factorial(i.subtract(BigInteger.ONE)));
        }
    }

    // Prints the values of the indexes for the triangle array to the console
    public static void printTriangle(int n, int[][] triangleArray) {
        for (int r = 0; r < n; r++) {

            for (int i = n - 1; i > r; i--) {
                System.out.print("   ");
            }

            for (int c = 0; c < n; c++) {
                int currentInt = triangleArray[r][c];
                int length = (int) (Math.log10(currentInt) + 1);
                if (currentInt == 0) {
                    continue;
                }
                for (int i = (3 - length); i > 0; i--) {
                    System.out.print(" ");
                }

                System.out.print(currentInt + "   ");
            }
            System.out.println();
        }
    }
}
