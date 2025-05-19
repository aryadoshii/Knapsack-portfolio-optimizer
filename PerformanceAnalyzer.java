// PerformanceAnalyzer.java in com.portfolio.util
package com.portfolio.util;

import com.portfolio.model.Portfolio;

import java.util.HashMap;
import java.util.Map;

public class PerformanceAnalyzer {

    /**
     * Calculates performance metrics for various portfolios
     *
     * @param portfolios Map of portfolio name to portfolio
     * @return Map of portfolio name to performance metrics
     */
    public static Map<String, Map<String, Double>> analyzePerformance(Map<String, Portfolio> portfolios) {
        Map<String, Map<String, Double>> results = new HashMap<>();

        // Find optimal return as benchmark
        double optimalReturn = 0;
        for (Portfolio portfolio : portfolios.values()) {
            optimalReturn = Math.max(optimalReturn, portfolio.getTotalReturn());
        }

        for (Map.Entry<String, Portfolio> entry : portfolios.entrySet()) {
            Map<String, Double> metrics = new HashMap<>();
            Portfolio portfolio = entry.getValue();

            // Calculate metrics
            metrics.put("totalReturn", portfolio.getTotalReturn());
            metrics.put("totalRisk", portfolio.getTotalRisk());
            metrics.put("riskReturnRatio", portfolio.getRiskReturnRatio());
            metrics.put("percentOfOptimal", (portfolio.getTotalReturn() / optimalReturn) * 100);

            results.put(entry.getKey(), metrics);
        }

        return results;
    }

    /**
     * Prints a formatted comparison table of portfolio performances
     *
     * @param portfolios Map of portfolio name to portfolio
     */
    public static void printComparisonTable(Map<String, Portfolio> portfolios) {
        Map<String, Map<String, Double>> analysis = analyzePerformance(portfolios);

        // Print header
        System.out.printf("%-20s %-15s %-15s %-15s %-15s\n",
                "Strategy", "Return", "Risk", "Return/Risk", "% of Optimal");
        System.out.println("---------------------------------------------------------------------");

        // Print each row
        for (Map.Entry<String, Map<String, Double>> entry : analysis.entrySet()) {
            Map<String, Double> metrics = entry.getValue();
            System.out.printf("%-20s $%-14.2f %-15.2f %-15.4f %-15.2f%%\n",
                    entry.getKey(),
                    metrics.get("totalReturn"),
                    metrics.get("totalRisk"),
                    metrics.get("riskReturnRatio"),
                    metrics.get("percentOfOptimal"));
        }
    }
}
