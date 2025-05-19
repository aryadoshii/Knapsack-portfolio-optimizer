// Portfolio.java in com.portfolio.model
package com.portfolio.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {
    private List<InvestmentOption> selectedInvestments;
    private double totalRisk;
    private double totalReturn;
    private Map<String, Double> sectorAllocation;

    public Portfolio() {
        this.selectedInvestments = new ArrayList<>();
        this.totalRisk = 0.0;
        this.totalReturn = 0.0;
        this.sectorAllocation = new HashMap<>();
    }

    public void addInvestment(InvestmentOption investment) {
        selectedInvestments.add(investment);
        totalRisk += investment.getRiskFactor();
        totalReturn += investment.getExpectedReturn();

        // Update sector allocation
        String sector = investment.getSector();
        sectorAllocation.put(sector, sectorAllocation.getOrDefault(sector, 0.0) + investment.getRiskFactor());
    }

    // Getters
    public List<InvestmentOption> getSelectedInvestments() {
        return selectedInvestments;
    }

    public double getTotalRisk() {
        return totalRisk;
    }

    public double getTotalReturn() {
        return totalReturn;
    }

    public Map<String, Double> getSectorAllocation() {
        return sectorAllocation;
    }

    public double getRiskReturnRatio() {
        return totalRisk > 0 ? totalReturn / totalRisk : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Portfolio Summary:\n");
        sb.append("Total Expected Return: ").append(String.format("%.2f", totalReturn)).append("\n");
        sb.append("Total Risk: ").append(String.format("%.2f", totalRisk)).append("\n");
        sb.append("Risk-Return Ratio: ").append(String.format("%.4f", getRiskReturnRatio())).append("\n\n");
        sb.append("Selected Investments:\n");

        for (InvestmentOption investment : selectedInvestments) {
            sb.append("- ").append(investment.getName())
                    .append(" (Return: ").append(String.format("%.2f", investment.getExpectedReturn()))
                    .append(", Risk: ").append(String.format("%.2f", investment.getRiskFactor()))
                    .append(", Sector: ").append(investment.getSector())
                    .append(")\n");
        }

        sb.append("\nSector Allocation:\n");
        for (Map.Entry<String, Double> entry : sectorAllocation.entrySet()) {
            double percentage = (entry.getValue() / totalRisk) * 100;
            sb.append("- ").append(entry.getKey()).append(": ")
                    .append(String.format("%.2f%%", percentage)).append("\n");
        }

        return sb.toString();
    }
}
