// PortfolioOptimizer.java in com.portfolio.main
package com.portfolio.main;

import com.portfolio.algorithm.DiversifiedKnapsackSolver;
import com.portfolio.algorithm.GreedySolver;
import com.portfolio.algorithm.KnapsackSolver;
import com.portfolio.model.InvestmentOption;
import com.portfolio.model.Portfolio;

import java.util.ArrayList;
import java.util.List;

public class PortfolioOptimizer {

    public static void main(String[] args) {
        // Create sample investment options
        List<InvestmentOption> investments = createSampleInvestments();

        // Set risk tolerance
        double riskTolerance = 50.0;

        System.out.println("=== INVESTMENT PORTFOLIO OPTIMIZATION ===\n");
        System.out.println("Risk Tolerance: " + riskTolerance);
        System.out.println("Available Investment Options: " + investments.size());

        // Solve using 0/1 Knapsack (Dynamic Programming)
        System.out.println("\n=== OPTIMAL SOLUTION (DYNAMIC PROGRAMMING) ===");
        Portfolio optimalPortfolio = KnapsackSolver.solveWithDP(investments, riskTolerance);
        System.out.println(optimalPortfolio);

        // Solve using Greedy (Highest Return First)
        System.out.println("\n=== GREEDY SOLUTION (HIGHEST RETURN FIRST) ===");
        Portfolio greedyPortfolio1 = GreedySolver.solveHighestReturnFirst(investments, riskTolerance);
        System.out.println(greedyPortfolio1);

        // Solve using Greedy (Best Ratio First)
        System.out.println("\n=== GREEDY SOLUTION (BEST RATIO FIRST) ===");
        Portfolio greedyPortfolio2 = GreedySolver.solveBestRatioFirst(investments, riskTolerance);
        System.out.println(greedyPortfolio2);

        // Solve with diversification constraints
        System.out.println("\n=== DIVERSIFIED SOLUTION (MAX 40% PER SECTOR) ===");
        Portfolio diversifiedPortfolio = DiversifiedKnapsackSolver.solveWithSectorConstraints(
                investments, riskTolerance, 0.4); // Max 40% allocation per sector
        System.out.println(diversifiedPortfolio);

        // Perform analysis
        System.out.println("\n=== COMPARATIVE ANALYSIS ===");
        System.out.printf("Optimal Solution Return: %.2f (100.00%%)\n",
                optimalPortfolio.getTotalReturn());
        System.out.printf("Greedy (Highest Return) Return: %.2f (%.2f%%)\n",
                greedyPortfolio1.getTotalReturn(),
                (greedyPortfolio1.getTotalReturn() / optimalPortfolio.getTotalReturn()) * 100);
        System.out.printf("Greedy (Best Ratio) Return: %.2f (%.2f%%)\n",
                greedyPortfolio2.getTotalReturn(),
                (greedyPortfolio2.getTotalReturn() / optimalPortfolio.getTotalReturn()) * 100);
        System.out.printf("Diversified Solution Return: %.2f (%.2f%%)\n",
                diversifiedPortfolio.getTotalReturn(),
                (diversifiedPortfolio.getTotalReturn() / optimalPortfolio.getTotalReturn()) * 100);
    }

    private static List<InvestmentOption> createSampleInvestments() {
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
        investments.add(new InvestmentOption("Bond Fund", 4.0, 1.0, "Bond"));

        return investments;
    }
}
