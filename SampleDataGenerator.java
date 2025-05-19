// SampleDataGenerator.java in com.portfolio.util
package com.portfolio.util;

import com.portfolio.model.InvestmentOption;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleDataGenerator {

    /**
     * Generates a sample CSV file with investment options
     *
     * @param filePath Output file path
     * @param count Number of investments to generate
     */
    public static void generateSampleCSV(String filePath, int count) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write header
            writer.println("Name,ExpectedReturn,RiskFactor,Sector");

            // Generate random investments
            Random random = new Random();
            String[] sectors = {"Technology", "Finance", "Healthcare", "Energy", "Consumer",
                    "Real Estate", "Utilities", "Telecom", "Materials", "Industrial"};

            String[] typesPrefixes = {"Stock", "Bond", "ETF", "Fund", "REIT"};
            String[] typesModifiers = {"Growth", "Value", "Income", "Dividend", "Index"};

            for (int i = 0; i < count; i++) {
                String sector = sectors[random.nextInt(sectors.length)];
                String typePrefix = typesPrefixes[random.nextInt(typesPrefixes.length)];
                String typeModifier = typesModifiers[random.nextInt(typesModifiers.length)];

                String name = sector + " " + typePrefix + " " + (char)('A' + i % 26);

                // Realistic return range based on type
                double baseReturn;
                if (typePrefix.equals("Bond")) {
                    baseReturn = 2.0 + random.nextDouble() * 4.0; // 2-6%
                } else if (typePrefix.equals("Stock")) {
                    baseReturn = 5.0 + random.nextDouble() * 15.0; // 5-20%
                } else {
                    baseReturn = 4.0 + random.nextDouble() * 10.0; // 4-14%
                }

                // Risk factor generally correlates with return
                double riskFactor = (baseReturn / 20.0) * 10.0 + random.nextDouble() * 3.0 - 1.5;
                // Ensure risk is between 1-10
                riskFactor = Math.max(1.0, Math.min(10.0, riskFactor));

                writer.printf("%s,%s %.2f,%.2f,%s\n",
                        name, typeModifier, baseReturn, riskFactor, sector);
            }

            System.out.println("Generated " + count + " sample investments to " + filePath);

        } catch (IOException e) {
            System.err.println("Error generating sample data: " + e.getMessage());
        }
    }
}
