// EnhancedPortfolioOptimizer.java in com.portfolio.main
package com.portfolio.main;

import com.portfolio.algorithm.DiversifiedKnapsackSolver;
import com.portfolio.algorithm.GreedySolver;
import com.portfolio.algorithm.KnapsackSolver;
import com.portfolio.model.InvestmentOption;
import com.portfolio.model.Portfolio;
import com.portfolio.util.PerformanceAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EnhancedPortfolioOptimizer {

    public static void main(String[] args) {
        // Allow user to enter risk tolerance or use default
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your risk tolerance (or press Enter for default 50.0): ");
        String input = scanner.nextLine();

        double riskTolerance = input.isEmpty() ? 50.0 : Double.parseDouble(input);

        // Create sample investment options
        List<InvestmentOption> investments = createSampleInvestments();

        System.out.println("\n=== INVESTMENT PORTFOLIO OPTIMIZATION ===\n");
        System.out.println("Risk Tolerance: " + riskTolerance);
        System.out.println("Available Investment Options: " + investments.size() + "\n");

        // Print all available investment options
        System.out.println("Available Investment Options:");
        System.out.printf("%-20s %-15s %-15s %-15s\n", "Name", "Expected Return", "Risk Factor", "Sector");
        System.out.println("------------------------------------------------------------------");
        for (InvestmentOption inv : investments) {
            System.out.printf("%-20s $%-14.2f %-15.2f %-15s\n",
                    inv.getName(), inv.getExpectedReturn(),
                    inv.getRiskFactor(), inv.getSector());
        }

        // Run all optimization algorithms
        Map<String, Portfolio> portfolios = new HashMap<>();

        // Solve using the different algorithms
        portfolios.put("Optimal (DP)", KnapsackSolver.solveWithDP(investments, riskTolerance));
        portfolios.put("Greedy (Return)", GreedySolver.solveHighestReturnFirst(investments, riskTolerance));
        portfolios.put("Greedy (Return/Risk)", GreedySolver.solveBestRatioFirst(investments, riskTolerance));
        portfolios.put("Diversified (40%)", DiversifiedKnapsackSolver.solveWithSectorConstraints(
                investments, riskTolerance, 0.4));

        // Print comparison table
        System.out.println("\n=== PORTFOLIO PERFORMANCE COMPARISON ===");
        PerformanceAnalyzer.printComparisonTable(portfolios);

        // Print detailed results for the optimal portfolio
        System.out.println("\n=== DETAILED OPTIMAL PORTFOLIO ===");
        System.out.println(portfolios.get("Optimal (DP)"));

        // Ask if user wants to see details of other portfolios
        System.out.println("\nDo you want to see details of other portfolios? (y/n)");
        input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")) {
            for (Map.Entry<String, Portfolio> entry : portfolios.entrySet()) {
                if (!entry.getKey().equals("Optimal (DP)")) {
                    System.out.println("\n=== DETAILED " + entry.getKey().toUpperCase() + " PORTFOLIO ===");
                    System.out.println(entry.getValue());
                }
            }
        }

        scanner.close();
    }

    private static List<InvestmentOption> createSampleInvestments() {
        // Same as before
        List<InvestmentOption> investments = new ArrayList<>();

        // Format: name, expectedReturn, riskFactor, sector
        investments.add(new InvestmentOption("Tech Stock A", 15.0, 8.0, "Technology"));
        investments.add(new InvestmentOption("Tech Stock B", 12.0, 7.0, "Technology"));
        investments.add(new InvestmentOption("Finance Stock A", 10.0, 5.0, "Finance"));
        investments.add(new InvestmentOption("Finance Stock B", 9.0, 4.0, "Finance"));
        investments.add(new InvestmentOption("Healthcare Stock A", 14.0, 9.0, "Healthcare"));
        investments.add(new InvestmentOption("Healthcare Stock B", 11.0, 6.0, "Healthcare"));
        investments.add(new InvestmentOption("Energy Stock A", 8.0, 3.0, "Energy"));
        investments.add(new InvestmentOption("Energy Stock B", 7.0, 2.0, "Energy"));
        investments.add(new InvestmentOption("Consumer Stock A", 13.0, 7.5, "Consumer"));
        investments.add(new InvestmentOption("Consumer Stock B", 9.5, 4.5, "Consumer"));
        investments.add(new InvestmentOption("Real Estate REIT A", 6.0, 2.5, "Real Estate"));
        investments.add(new InvestmentOption("Real Estate REIT B", 7.5, 3.5, "Real Estate"));
        investments.add(new InvestmentOption("Utility Stock A", 5.0, 1.5, "Utilities"));
        investments.add(new InvestmentOption("Utility Stock B", 4.5, 1.0, "Utilities"));
        investments.add(new InvestmentOption("Telecom Stock A", 8.5, 4.0, "Telecom"));
        investments.add(new InvestmentOption("Materials Stock A", 7.0, 3.0, "Materials"));
        investments.add(new InvestmentOption("Industrial Stock A", 9.0, 5.0, "Industrial"));
        investments.add(new InvestmentOption("Small Cap Growth Fund", 16.0, 10.0, "Fund"));
        investments.add(new InvestmentOption("Mid Cap Value Fund", 11.0, 6.0, "Fund"));
        investments.add(new InvestmentOption("Mid Cap Value Fund", 11.0, 6.0, "Fund"));
        investments.add(new InvestmentOption("Bond Fund", 4.0, 1.0, "Bond"));

        return investments;
    }
}
