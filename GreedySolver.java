// GreedySolver.java in com.portfolio.algorithm
package com.portfolio.algorithm;

import com.portfolio.model.InvestmentOption;
import com.portfolio.model.Portfolio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GreedySolver{

    /**
     * Solves the portfolio optimization using a greedy approach - highest return first
     *
     * @param investments List of available investment options
     * @param riskTolerance Maximum risk tolerance
     * @return Greedy portfolio solution
     */
    public static Portfolio solveHighestReturnFirst(List<InvestmentOption> investments, double riskTolerance) {
        Portfolio portfolio = new Portfolio();

        // Create a copy and sort by expected return (descending)
        List<InvestmentOption> sortedInvestments = new ArrayList<>(investments);
        sortedInvestments.sort(Comparator.comparing(InvestmentOption::getExpectedReturn).reversed());

        double remainingRiskCapacity = riskTolerance;

        for (InvestmentOption investment : sortedInvestments) {
            if (investment.getRiskFactor() <= remainingRiskCapacity) {
                portfolio.addInvestment(investment);
                remainingRiskCapacity -= investment.getRiskFactor();
            }
        }

        return portfolio;
    }

    /**
     * Solves the portfolio optimization using a greedy approach - best return/risk ratio first
     *
     * @param investments List of available investment options
     * @param riskTolerance Maximum risk tolerance
     * @return Greedy portfolio solution
     */
    public static Portfolio solveBestRatioFirst(List<InvestmentOption> investments, double riskTolerance) {
        Portfolio portfolio = new Portfolio();

        // Create a copy and sort by return/risk ratio (descending)
        List<InvestmentOption> sortedInvestments = new ArrayList<>(investments);
        sortedInvestments.sort(Comparator.comparing(
                (InvestmentOption inv) -> inv.getExpectedReturn() / inv.getRiskFactor()
        ).reversed());

        double remainingRiskCapacity = riskTolerance;

        for (InvestmentOption investment : sortedInvestments) {
            if (investment.getRiskFactor() <= remainingRiskCapacity) {
                portfolio.addInvestment(investment);
                remainingRiskCapacity -= investment.getRiskFactor();
            }
        }

        return portfolio;
    }
}
