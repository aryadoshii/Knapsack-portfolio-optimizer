// PortfolioOptimizerTest.java in test directory
package com.portfolio.test;

import com.portfolio.algorithm.GreedySolver;
import com.portfolio.algorithm.KnapsackSolver;
import com.portfolio.model.InvestmentOption;
import com.portfolio.model.Portfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PortfolioOptimizerTest {

    private List<InvestmentOption> testInvestments;
    private double riskTolerance;

    @BeforeEach
    void setUp() {
        testInvestments = new ArrayList<>();
        // Simple test data with predictable optimal solution
        testInvestments.add(new InvestmentOption("Investment A", 10.0, 5.0, "Sector1"));
        testInvestments.add(new InvestmentOption("Investment B", 15.0, 8.0, "Sector2"));
        testInvestments.add(new InvestmentOption("Investment C", 20.0, 12.0, "Sector3"));
        testInvestments.add(new InvestmentOption("Investment D", 25.0, 15.0, "Sector1"));

        riskTolerance = 20.0;
    }

    @Test
    void testKnapsackSolver() {
        Portfolio portfolio = KnapsackSolver.solveWithDP(testInvestments, riskTolerance);
        assertEquals(35.0, portfolio.getTotalReturn(), 0.01);
        assertEquals(20.0, portfolio.getTotalRisk(), 0.01);
        assertEquals(2, portfolio.getSelectedInvestments().size());
    }

    @Test
    void testGreedySolverHighestReturn() {
        Portfolio portfolio = GreedySolver.solveHighestReturnFirst(testInvestments, riskTolerance);
        // The greedy solution might not be optimal
        assertTrue(portfolio.getTotalRisk() <= riskTolerance);
    }

    @Test
    void testGreedySolverBestRatio() {
        Portfolio portfolio = GreedySolver.solveBestRatioFirst(testInvestments, riskTolerance);
        assertTrue(portfolio.getTotalRisk() <= riskTolerance);
    }

    @Test
    void compareAlgorithms() {
        Portfolio optimalPortfolio = KnapsackSolver.solveWithDP(testInvestments, riskTolerance);
        Portfolio greedyPortfolio = GreedySolver.solveHighestReturnFirst(testInvestments, riskTolerance);

        assertTrue(optimalPortfolio.getTotalReturn() >= greedyPortfolio.getTotalReturn());
    }
}
