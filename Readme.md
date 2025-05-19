# Knapsack-Based Investment Portfolio Optimization

This Java application optimizes investment portfolios using the 0/1 Knapsack algorithm to maximize returns while staying within a specified risk tolerance.

## Overview

The project applies the classic 0/1 Knapsack algorithm to the investment domain, where:
* Each investment option has a risk factor (weight) and an expected return (value)
* The total risk tolerance represents the knapsack capacity
* The goal is to select the optimal combination of investments to maximize returns while staying within risk tolerance

## Features

* **Multiple Optimization Algorithms**:
   * Dynamic Programming (optimal solution)
   * Greedy approach by highest returns
   * Greedy approach by best return-to-risk ratio
   * Diversified portfolio with sector constraints
* **Investment Management**:
   * Load investment options from CSV files
   * Create custom investments interactively
   * Save investment portfolios
* **Analysis Tools**:
   * Compare performance across different algorithms
   * Analyze risk-return trade-offs
   * Evaluate sector allocation and diversification

## Project Structure

* `com.portfolio.algorithm`: Algorithm implementations
   * `KnapsackSolver.java`: Dynamic programming implementation of knapsack
   * `GreedySolver.java`: Greedy approaches for comparison
   * `DiversifiedKnapsackSolver.java`: Solutions with sector constraints
* `com.portfolio.model`: Data models
   * `InvestmentOption.java`: Represents an individual investment
   * `Portfolio.java`: Collection of selected investments
* `com.portfolio.util`: Utility classes
   * `PerformanceAnalyzer.java`: Analysis of portfolio performance
   * `InvestmentDataLoader.java`: File I/O for investments
   * `CustomInvestmentCreator.java`: Interactive investment creation
   * `TimeComplexityAnalysis.java`: Algorithm performance analysis
* `com.portfolio.main`: Application entry points
   * `KnapsackPortfolioApp.java`: Main menu-driven application
   * `PortfolioOptimizer.java`: Simple implementation example
   * `EnhancedPortfolioOptimizer.java`: Advanced implementation

## How to Run

1. Open the project in IntelliJ IDEA
2. Run `KnapsackPortfolioApp` main class
3. Follow the menu options to:
   * Set your risk tolerance
   * View and manage investment options
   * Run portfolio optimization algorithms
   * Compare algorithm performance

## Algorithms

### Dynamic Programming (Knapsack)
* Generates the mathematically optimal solution
* Time complexity: O(n*W) where n is the number of investments and W is the risk capacity
* Space complexity: O(n*W)

### Greedy Approaches
* Faster but potentially suboptimal solutions
* Time complexity: O(n log n) due to sorting
* Two variations implemented:
   1. Highest return first
   2. Best return-to-risk ratio first

### Diversified Portfolio
* Adds sector constraints to prevent overexposure
* Uses a brute force approach with exponential complexity
* Limited to smaller portfolios due to computational constraints

## Extensions

The project includes several extensions beyond the basic knapsack problem:
1. **Sector Diversification**: Limits allocation to any single sector
2. **Interactive Portfolio Creation**: Build custom investment portfolios
3. **Performance Analysis**: Compare different optimization strategies
4. **File I/O**: Import and export investment data

## Sample Output

```
=== PORTFOLIO PERFORMANCE COMPARISON ===
Strategy             Return          Risk            Return/Risk     % of Optimal    
---------------------------------------------------------------------
Optimal (DP)         $63.50          49.00           1.2959          100.00%
Greedy (Return)      $61.00          47.50           1.2842          96.06%
Greedy (Ratio)       $59.50          45.00           1.3222          93.70%
Diversified (30%)    $57.00          48.50           1.1753          89.76%
Diversified (40%)    $60.50          48.50           1.2474          95.28%
```

## CSV File Format

To import your own investment options, create a CSV file in the following format:

```csv
Name,ExpectedReturn,RiskFactor,Sector
"Tech Stock A",15.0,8.0,"Technology"
"Finance Stock A",10.0,5.0,"Finance"
"Healthcare Stock B",11.0,6.0,"Healthcare"
"Energy Stock A",8.0,3.0,"Energy"
```


## Key Concepts

### The Knapsack Problem

The 0/1 Knapsack problem is a classic optimization problem where:
- You have a set of items, each with a weight and a value
- You need to select items to maximize total value while staying within a weight capacity
- Each item can only be selected once (0/1)

### Application to Portfolio Optimization

In our financial context:
- Investments are the items
- Risk factors are the weights
- Expected returns are the values
- Risk tolerance is the knapsack capacity

This approach allows us to find the combination of investments that maximizes returns while staying within an acceptable risk level.

## Algorithm Implementation Details

### Dynamic Programming Solution

```java
// Pseudocode for the dynamic programming approach
for (int i = 0; i <= n; i++) {
    for (int w = 0; w <= W; w++) {
        if (i == 0 || w == 0)
            dp[i][w] = 0;
        else if (weight[i-1] <= w)
            dp[i][w] = Math.max(value[i-1] + dp[i-1][w-weight[i-1]], dp[i-1][w]);
        else
            dp[i][w] = dp[i-1][w];
    }
}
```

### Sector Diversification Constraints

The diversification approach adds sector constraints to ensure no single sector exceeds a specified percentage of the portfolio. This is implemented by tracking sector allocations and enforcing maximum thresholds during the optimization process.

## Future Enhancements

- Add support for fractional investments (Fractional Knapsack)
- Implement modern portfolio theory with covariance matrices
- Add visualization tools for portfolio performance
- Support for multi-period investment optimization
- Incorporate Monte Carlo simulations for risk assessment


## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- The Knapsack problem is a fundamental concept in computer science and operations research
- This project demonstrates how classic algorithms can be applied to financial decision-making
- Special thanks to all contributors and testers who helped improve this application
