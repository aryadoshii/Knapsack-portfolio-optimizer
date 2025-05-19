// KnapsackSolver.java in com.portfolio.algorithm
package com.portfolio.algorithm;

import com.portfolio.model.InvestmentOption;
import com.portfolio.model.Portfolio;

import java.util.List;

public class KnapsackSolver {

    /**
     * Solves the 0/1 Knapsack problem using dynamic programming to optimize investment portfolio
     *
     * @param investments List of available investment options
     * @param riskTolerance Maximum risk tolerance (knapsack capacity)
     * @return Optimized portfolio
     */
    public static Portfolio solveWithDP(List<InvestmentOption> investments, double riskTolerance) {
        int n = investments.size();

        // Scale risk values to work with DP table
        int scaleFactor = 100;
        int scaledRiskTolerance = (int) (riskTolerance * scaleFactor);

        // Create DP table
        double[][] dp = new double[n + 1][scaledRiskTolerance + 1];

        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= scaledRiskTolerance; w++) {
                // Get current investment
                InvestmentOption investment = investments.get(i - 1);
                int scaledRisk = (int) (investment.getRiskFactor() * scaleFactor);

                // If current investment can't fit, take previous value
                if (scaledRisk > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // Otherwise, take maximum of:
                    // 1. Previous value (not taking this investment)
                    // 2. Value of this investment + best value for remaining capacity
                    dp[i][w] = Math.max(dp[i - 1][w],
                            investment.getExpectedReturn() + dp[i - 1][w - scaledRisk]);
                }
            }
        }

        // Reconstruct the solution
        Portfolio portfolio = new Portfolio();
        int w = scaledRiskTolerance;

        for (int i = n; i > 0; i--) {
            // Check if including the current item gives the optimal value
            if (dp[i][w] != dp[i - 1][w]) {
                InvestmentOption investment = investments.get(i - 1);
                portfolio.addInvestment(investment);

                // Reduce the remaining capacity
                int scaledRisk = (int) (investment.getRiskFactor() * scaleFactor);
                w -= scaledRisk;
            }
        }

        return portfolio;
    }
}
