// TimeComplexityAnalysis.java in com.portfolio.util
package com.portfolio.util;

import com.portfolio.algorithm.DiversifiedKnapsackSolver;
import com.portfolio.algorithm.GreedySolver;
import com.portfolio.algorithm.KnapsackSolver;
import com.portfolio.model.InvestmentOption;
import com.portfolio.model.Portfolio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimeComplexityAnalysis {

    /**
     * Analyzes and compares the execution time of different algorithms
     */
    public static void analyzeAlgorithms() {
        System.out.println("\n=== TIME COMPLEXITY ANALYSIS ===");
        System.out.println("Testing algorithms with different input sizes...");

        int[] sizes = {10, 20, 50, 100, 200};
        double riskTolerance = 100.0;

        System.out.printf("%-10s %-20s %-20s %-20s %-20s\n",
                "Size", "DP (ms)", "Greedy Return (ms)", "Greedy Ratio (ms)", "Diversified (ms)");
        System.out.println("-----------------------------------------------------------------------------------");

        for (int size : sizes) {
            List<InvestmentOption> testData = generateRandomInvestments(size);

            // Measure execution time for each algorithm
            long startTime, endTime;

            // Dynamic Programming
            startTime = System.currentTimeMillis();
            KnapsackSolver.solveWithDP(testData, riskTolerance);
            endTime = System.currentTimeMillis();
            long dpTime = endTime - startTime;

            // Greedy (Highest Return)
            startTime = System.currentTimeMillis();
            GreedySolver.solveHighestReturnFirst(testData, riskTolerance);
            endTime = System.currentTimeMillis();
            long greedyReturnTime = endTime - startTime;

            // Greedy (Best Ratio)
            startTime = System.currentTimeMillis();
            GreedySolver.solveBestRatioFirst(testData, riskTolerance);
            endTime = System.currentTimeMillis();
            long greedyRatioTime = endTime - startTime;

            // Diversified (limited to smaller datasets due to exponential complexity)
            long diversifiedTime = 0;
            if (size <= 20) {  // Only test for smaller datasets
                startTime = System.currentTimeMillis();
                DiversifiedKnapsackSolver.solveWithSectorConstraints(testData, riskTolerance, 0.4);
                endTime = System.currentTimeMillis();
                diversifiedTime = endTime - startTime;
            } else {
                diversifiedTime = -1; // Too large for brute force
            }

            System.out.printf("%-10d %-20d %-20d %-20d %-20s\n",
                    size, dpTime, greedyReturnTime, greedyRatioTime,
                    diversifiedTime >= 0 ? Long.toString(diversifiedTime) : "N/A");
        }

        // Print theoretical time complexities
        System.out.println("\nTheoretical Time Complexities:");
        System.out.println("- Dynamic Programming (Knapsack): O(n*W) where n is the number of items and W is the capacity");
        System.out.println("- Greedy Algorithms: O(n log n) due to sorting");
        System.out.println("- Diversified Solver (Brute Force): O(2^n) - exponential complexity");
    }

    /**
     * Generates random investment options for testing
     *
     * @param size Number of investment options to generate
     * @return List of random investment options
     */
    private static List<InvestmentOption> generateRandomInvestments(int size) {
        List<InvestmentOption> investments = new ArrayList<>();
        Random random = new Random(42); // Fixed seed for reproducibility
        String[] sectors = {"Tech", "Finance", "Energy", "Healthcare", "Consumer", "Utilities"};

        for (int i = 0; i < size; i++) {
            String name = "Investment " + (i + 1);
            double expectedReturn = 5.0 + random.nextDouble() * 15.0; // Returns between 5-20
            double riskFactor = 1.0 + random.nextDouble() * 9.0;      // Risk between 1-10
            String sector = sectors[random.nextInt(sectors.length)];

            investments.add(new InvestmentOption(name, expectedReturn, riskFactor, sector));
        }

        return investments;
    }
}
