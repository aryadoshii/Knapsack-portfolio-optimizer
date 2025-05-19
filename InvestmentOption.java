// InvestmentOption.java in com.portfolio.model
package com.portfolio.model;

public class InvestmentOption {
    private String name;
    private double expectedReturn; // value
    private double riskFactor;     // weight
    private String sector;         // for diversification extension

    // Constructor
    public InvestmentOption(String name, double expectedReturn, double riskFactor, String sector) {
        this.name = name;
        this.expectedReturn = expectedReturn;
        this.riskFactor = riskFactor;
        this.sector = sector;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public double getExpectedReturn() {
        return expectedReturn;
    }

    public double getRiskFactor() {
        return riskFactor;
    }

    public String getSector() {
        return sector;
    }

    @Override
    public String toString() {
        return "InvestmentOption{" +
                "name='" + name + '\'' +
                ", expectedReturn=" + expectedReturn +
                ", riskFactor=" + riskFactor +
                ", sector='" + sector + '\'' +
                '}';
    }
}
