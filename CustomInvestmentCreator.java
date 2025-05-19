// CustomInvestmentCreator.java in com.portfolio.util
package com.portfolio.util;

import com.portfolio.model.InvestmentOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomInvestmentCreator {

    /**
     * Allows the user to create custom investment options interactively
     *
     * @return List of user-created investment options
     */
    public static List<InvestmentOption> createCustomInvestments() {
        List<InvestmentOption> investments = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== CUSTOM INVESTMENT CREATION ===");
        System.out.println("Enter investment details (or leave blank to finish):");

        boolean addMore = true;
        while (addMore) {
            System.out.print("\nInvestment Name (or blank to finish): ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                addMore = false;
                continue;
            }

            double expectedReturn = getDoubleInput(scanner, "Expected Return ($): ");
            double riskFactor = getDoubleInput(scanner, "Risk Factor: ");

            System.out.print("Sector: ");
            String sector = scanner.nextLine().trim();

            investments.add(new InvestmentOption(name, expectedReturn, riskFactor, sector));

            System.out.println("Investment added successfully!");
        }

        return investments;
    }

    private static double getDoubleInput(Scanner scanner, String prompt) {
        double value = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                value = Double.parseDouble(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }

        return value;
    }
}
