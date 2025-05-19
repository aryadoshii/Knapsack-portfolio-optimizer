// KnapsackPortfolioApp.java in com.portfolio.main
package com.portfolio.main;

import com.portfolio.algorithm.DiversifiedKnapsackSolver;
import com.portfolio.algorithm.GreedySolver;
import com.portfolio.algorithm.KnapsackSolver;
import com.portfolio.model.InvestmentOption;
import com.portfolio.model.Portfolio;
import com.portfolio.util.CustomInvestmentCreator;
import com.portfolio.util.InvestmentDataLoader;
import com.portfolio.util.PerformanceAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class KnapsackPortfolioApp {

    private static List<InvestmentOption> investments;
    private static double riskTolerance = 50.0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===================================");
        System.out.println("KNAPSACK PORTFOLIO OPTIMIZER");
        System.out.println("===================================");

        // Default to sample investments
        investments = createSampleInvestments();

        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    displayInvestments();
                    break;
                case 2:
                    setRiskTolerance();
                    break;
                case 3:
                    optimizePortfolio();
                    break;
                case 4:
                    loadInvestmentsFromFile();
                    break;
                case 5:
                    addCustomInvestments();
                    break;
                case 6:
                    saveInvestmentsToFile();
                    break;
                case 7:
                    runAlgorithmComparison();
                    break;
                case 8:
                    resetToSampleData();
                    break;
                case 9:
                    exit = true;
                    System.out.println("Thank you for using the Knapsack Portfolio Optimizer!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("1. Display Investment Options");
        System.out.println("2. Set Risk Tolerance (Current: " + riskTolerance + ")");
        System.out.println("3. Optimize Portfolio");
        System.out.println("4. Load Investments from File");
        System.out.println("5. Add Custom Investments");
        System.out.println("6. Save Investments to File");
        System.out.println("7. Run Algorithm Comparison");
        System.out.println("8. Reset to Sample Data");
        System.out.println("9. Exit");
    }

    private static void displayInvestments() {
        System.out.println("\n=== AVAILABLE INVESTMENT OPTIONS ===");
        System.out.printf("%-20s %-15s %-15s %-15s\n", "Name", "Expected Return", "Risk Factor", "Sector");
        System.out.println("------------------------------------------------------------------");

        for (InvestmentOption inv : investments) {
            System.out.printf("%-20s $%-14.2f %-15.2f %-15s\n",
                    inv.getName(), inv.getExpectedReturn(),
                    inv.getRiskFactor(), inv.getSector());
        }
    }

    private static void setRiskTolerance() {
        System.out.println("\n=== SET RISK TOLERANCE ===");
        riskTolerance = getDoubleInput("Enter new risk tolerance: ");
        System.out.println("Risk tolerance set to: " + riskTolerance);
    }

    private static void optimizePortfolio() {
        System.out.println("\n=== PORTFOLIO OPTIMIZATION ===");
        System.out.println("Select optimization method:");
        System.out.println("1. Dynamic Programming (Optimal)");
        System.out.println("2. Greedy (Highest Return First)");
        System.out.println("3. Greedy (Best Return/Risk Ratio)");
        System.out.println("4. Diversified (with sector constraints)");

        int choice = getIntInput("Enter your choice: ");
        Portfolio portfolio = null;

        switch (choice) {
            case 1:
                portfolio = KnapsackSolver.solveWithDP(investments, riskTolerance);
                System.out.println("\n=== OPTIMAL PORTFOLIO (DYNAMIC PROGRAMMING) ===");
                break;
            case 2:
                portfolio = GreedySolver.solveHighestReturnFirst(investments, riskTolerance);
                System.out.println("\n=== GREEDY PORTFOLIO (HIGHEST RETURN FIRST) ===");
                break;
            case 3:
                portfolio = GreedySolver.solveBestRatioFirst(investments, riskTolerance);
                System.out.println("\n=== GREEDY PORTFOLIO (BEST RETURN/RISK RATIO) ===");
                break;
            case 4:
                double maxSectorAllocation = getDoubleInput("Enter maximum sector allocation (0.0-1.0): ");
                portfolio = DiversifiedKnapsackSolver.solveWithSectorConstraints(
                        investments, riskTolerance, maxSectorAllocation);
                System.out.println("\n=== DIVERSIFIED PORTFOLIO (MAX " +
                        (maxSectorAllocation * 100) + "% PER SECTOR) ===");
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                return;
        }

        if (portfolio != null) {
            System.out.println(portfolio);
        }
    }

    private static void loadInvestmentsFromFile() {
        System.out.println("\n=== LOAD INVESTMENTS FROM FILE ===");
        System.out.print("Enter CSV file path: ");
        String filePath = scanner.nextLine().trim();

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return;
        }

        List<InvestmentOption> loadedInvestments = InvestmentDataLoader.loadFromCSV(filePath);

        if (loadedInvestments.isEmpty()) {
            System.out.println("No valid investments found in the file or file format is incorrect.");
            return;
        }

        investments = loadedInvestments;
        System.out.println("Successfully loaded " + investments.size() + " investments from file.");
    }

    private static void addCustomInvestments() {
        List<InvestmentOption> customInvestments = CustomInvestmentCreator.createCustomInvestments();

        if (!customInvestments.isEmpty()) {
            investments.addAll(customInvestments);
            System.out.println("Added " + customInvestments.size() + " custom investments.");
        }
    }

    private static void saveInvestmentsToFile() {
        System.out.println("\n=== SAVE INVESTMENTS TO FILE ===");
        System.out.print("Enter output CSV file path: ");
        String filePath = scanner.nextLine().trim();

        InvestmentDataLoader.saveToCSV(investments, filePath);
        System.out.println("Investments saved to: " + filePath);
    }

    private static void runAlgorithmComparison() {
        System.out.println("\n=== ALGORITHM COMPARISON ===");

        Map<String, Portfolio> portfolios = new HashMap<>();

        // Optimal solution (DP)
        portfolios.put("Optimal (DP)", KnapsackSolver.solveWithDP(investments, riskTolerance));

        // Greedy solutions
        portfolios.put("Greedy (Return)", GreedySolver.solveHighestReturnFirst(investments, riskTolerance));
        portfolios.put("Greedy (Return/Risk)", GreedySolver.solveBestRatioFirst(investments, riskTolerance));

        // Diversified solutions with different constraints
        portfolios.put("Diversified (30%)",
                DiversifiedKnapsackSolver.solveWithSectorConstraints(investments, riskTolerance, 0.3));
        portfolios.put("Diversified (40%)",
                DiversifiedKnapsackSolver.solveWithSectorConstraints(investments, riskTolerance, 0.4));

        // Print comparison table
        PerformanceAnalyzer.printComparisonTable(portfolios);

        // Ask if user wants to see detailed portfolios
        System.out.print("\nDo you want to see detailed portfolio allocations? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("y") || response.equals("yes")) {
            for (Map.Entry<String, Portfolio> entry : portfolios.entrySet()) {
                System.out.println("\n=== " + entry.getKey().toUpperCase() + " PORTFOLIO ===");
                System.out.println(entry.getValue());
            }
        }
    }

    private static void resetToSampleData() {
        investments = createSampleInvestments();
        System.out.println("Reset to sample investment data.");
    }

    private static int getIntInput(String prompt) {
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                value = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }

        return value;
    }

    private static double getDoubleInput(String prompt) {
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

    private static List<InvestmentOption> createSampleInvestments() {
        // Same as previous implementation
        List<InvestmentOption> sampleInvestments = new ArrayList<>();

        sampleInvestments.add(new InvestmentOption("Tech Stock A", 15.0, 8.0, "Technology"));
        sampleInvestments.add(new InvestmentOption("Tech Stock B", 12.0, 7.0, "Technology"));
        sampleInvestments.add(new InvestmentOption("Finance Stock A", 10.0, 5.0, "Finance"));
        sampleInvestments.add(new InvestmentOption("Finance Stock B", 9.0, 4.0, "Finance"));
        sampleInvestments.add(new InvestmentOption("Healthcare Stock A", 14.0, 9.0, "Healthcare"));
        sampleInvestments.add(new InvestmentOption("Healthcare Stock B", 11.0, 6.0, "Healthcare"));
        sampleInvestments.add(new InvestmentOption("Energy Stock A", 8.0, 3.0, "Energy"));
        sampleInvestments.add(new InvestmentOption("Energy Stock B", 7.0, 2.0, "Energy"));
        sampleInvestments.add(new InvestmentOption("Consumer Stock A", 13.0, 7.5, "Consumer"));
        sampleInvestments.add(new InvestmentOption("Consumer Stock B", 9.5, 4.5, "Consumer"));
        sampleInvestments.add(new InvestmentOption("Real Estate REIT A", 6.0, 2.5, "Real Estate"));
        sampleInvestments.add(new InvestmentOption("Real Estate REIT B", 7.5, 3.5, "Real Estate"));
        sampleInvestments.add(new InvestmentOption("Utility Stock A", 5.0, 1.5, "Utilities"));
        sampleInvestments.add(new InvestmentOption("Utility Stock B", 4.5, 1.0, "Utilities"));
        sampleInvestments.add(new InvestmentOption("Telecom Stock A", 8.5, 4.0, "Telecom"));
        sampleInvestments.add(new InvestmentOption("Materials Stock A", 7.0, 3.0, "Materials"));
        sampleInvestments.add(new InvestmentOption("Industrial Stock A", 9.0, 5.0, "Industrial"));
        sampleInvestments.add(new InvestmentOption("Small Cap Growth Fund", 16.0, 10.0, "Fund"));
        sampleInvestments.add(new InvestmentOption("Mid Cap Value Fund", 11.0, 6.0, "Fund"));
        sampleInvestments.add(new InvestmentOption("Bond Fund", 4.0, 1.0, "Bond"));

        return sampleInvestments;
    }
}
