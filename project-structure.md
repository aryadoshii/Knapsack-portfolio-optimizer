# Project Structure

This document outlines the structure of the Knapsack Portfolio Optimizer project to help navigate the codebase.

```
knapsack-portfolio-optimizer/
├── src/
│   └── com/
│       └── portfolio/
│           ├── algorithm/
│           │   ├── DiversifiedKnapsackSolver.java
│           │   ├── GreedySolver.java
│           │   └── KnapsackSolver.java
│           ├── model/
│           │   ├── InvestmentOption.java
│           │   └── Portfolio.java
│           ├── util/
│           │   ├── CustomInvestmentCreator.java
│           │   ├── InvestmentDataLoader.java
│           │   └── PerformanceAnalyzer.java
│           └── main/
│               └── KnapsackPortfolioApp.java
├── bin/     (generated compiled files)
├── data/    (optional directory for sample data files)
│   └── sample_investments.csv
├── .gitignore
├── LICENSE
├── README.md
└── GITHUB_SETUP.md
```

## Key Components

### Algorithm Package
Contains the core optimization algorithms:

- **KnapsackSolver.java**: Implements the dynamic programming approach for optimal solutions
- **GreedySolver.java**: Implements two greedy approaches (by return and by return/risk ratio)
- **DiversifiedKnapsackSolver.java**: Extends the knapsack algorithm with sector diversification constraints

### Model Package
Contains the data structures:

- **InvestmentOption.java**: Represents individual investment options with attributes like name, expected return, risk factor, and sector
- **Portfolio.java**: Represents a collection of investments with methods to calculate performance metrics

### Utility Package
Contains helper classes:

- **CustomInvestmentCreator.java**: Provides functionality to create custom investment options
- **InvestmentDataLoader.java**: Handles CSV file import/export operations
- **PerformanceAnalyzer.java**: Provides performance comparison tools

### Main Package
Contains the application entry point:

- **KnapsackPortfolioApp.java**: Main class with the command-line interface and application logic

## Data Flow

1. Investment options are loaded (from sample data, user input, or CSV file)
2. User selects optimization method and parameters
3. Selected algorithm creates a portfolio based on investment options and constraints
4. Results are displayed to the user
5. User can save results or compare different approaches