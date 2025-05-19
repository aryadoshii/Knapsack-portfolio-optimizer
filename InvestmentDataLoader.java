// InvestmentDataLoader.java in com.portfolio.util
package com.portfolio.util;

import com.portfolio.model.InvestmentOption;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvestmentDataLoader {

    /**
     * Loads investment options from a CSV file
     * Format: name,expectedReturn,riskFactor,sector
     *
     * @param filePath Path to the CSV file
     * @return List of investment options
     */
    public static List<InvestmentOption> loadFromCSV(String filePath) {
        List<InvestmentOption> investments = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                // Skip header line
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    double expectedReturn = Double.parseDouble(parts[1].trim());
                    double riskFactor = Double.parseDouble(parts[2].trim());
                    String sector = parts[3].trim();

                    investments.add(new InvestmentOption(name, expectedReturn, riskFactor, sector));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading investment data: " + e.getMessage());
        }

        return investments;
    }

    /**
     * Saves a list of investments to a CSV file
     *
     * @param investments List of investment options
     * @param filePath Path to the output file
     */
    public static void saveToCSV(List<InvestmentOption> investments, String filePath) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(filePath)) {
            // Write header
            writer.println("Name,ExpectedReturn,RiskFactor,Sector");

            // Write data
            for (InvestmentOption investment : investments) {
                writer.printf("%s,%.2f,%.2f,%s\n",
                        investment.getName(),
                        investment.getExpectedReturn(),
                        investment.getRiskFactor(),
                        investment.getSector());
            }
        } catch (IOException e) {
            System.err.println("Error writing investment data: " + e.getMessage());
        }
    }
}
