package cinema;

import java.util.Scanner;

public class Cinema {

    private static int[][] cinemaArray;
    private static int cinemaRows;
    private static int seatsInRow;
    private static final Scanner SC = new Scanner(System.in);
    private static int noOfTickets = 0;
    private static int totalPurchased = 0;


    public static void main(String[] args) {


        System.out.println("Enter the number of rows:");
        cinemaRows = SC.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsInRow = SC.nextInt();
        // create cinema array
        cinemaArray = new int[cinemaRows][seatsInRow];

        //Show Menu
        showMenu();

    }

    private static void executeMenu(int input) {
        switch (input) {
            case 1: {
                showCinema();
                break;
            }
            case 2: {
                buySeat();
                break;
            }
            case 3: {
                showStatistics();
                break;
            }
            default: {
                SC.close();
                System.exit(0);
            }
            ;
        }
    }

    private static void showStatistics() {
        int capacity = cinemaRows*seatsInRow;
        double percentage = (double)noOfTickets/capacity*100;
        int totalIncome = calculateIncome();
        System.out.printf("Number of purchased tickets: %d %n", noOfTickets);
        System.out.printf("Percentage: %.2f%  %n", percentage);
        System.out.printf("Current income: %d %n", totalPurchased);
        System.out.printf("Total income: %d %n", totalIncome);
        showMenu();
    }

    private static int calculateIncome() {
        int capacity = cinemaRows * seatsInRow;
        int total = 0;
        if (capacity < 60) {
            return total = capacity * 10;
        } else {
            total = 10 * capacity / 2 + 8 * capacity / 2;
            return total;
        }
    }

    private static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        executeMenu(SC.nextInt());
    }

    private static boolean checkPremium(int row) {
        return row <= cinemaRows / 2;
    }

    private static void buySeat() {
        boolean succes = false; // if purchased successfully
        do {
            System.out.println("Enter a row number:");
            int row = SC.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = SC.nextInt();
            try {
                if (cinemaArray[row - 1][seat - 1] == 1) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    cinemaArray[row - 1][seat - 1] = 1;
                    succes = true;
                }
                noOfTickets++;
                int capacity = cinemaRows * seatsInRow;
                if (capacity < 60) {
                    System.out.println("Ticket price: $10");
                    totalPurchased += 10;
                } else if (checkPremium(row)) {
                    System.out.println("Ticket price: $10");
                    totalPurchased += 10;
                } else {
                    System.out.println("Ticket price: $8");
                    totalPurchased += 8;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
            }
        } while (succes==false);
        showMenu();
    }

    private static void showCinema() {
        System.out.println();
        System.out.println("Cinema: ");
        System.out.println();
        for (int i = 0; i <= cinemaRows; i++) {
            for (int j = 0; j <= seatsInRow; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(j + " ");
                } else if (j == 0) {
                    System.out.print(i + " ");
                } else {
                    System.out.print(cinemaArray[i - 1][j - 1] != 0 ? "B " : "S ");
                }
            }
            System.out.println();
        }
        showMenu();
    }
}






