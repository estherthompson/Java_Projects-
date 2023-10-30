package lab4;
import java.awt.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The MortgageCalculatorView class provides the user interface for the Mortgage Calculator.
 * It extends JFrame and contains text fields for user input and labels for displaying results.
 * It also contains a button for calculating the mortgage.
 * */

public class MortgageCalculatorView extends JFrame {
    // Text fields for user input
    private final JTextField principalLoaned, monthlyPayments, interestRate, years;
    // Button for calculating the mortgage
    final JButton calculateMortgage;
    // Labels for displaying results
    private final JLabel monthlyBlendedAmount, totalMortgageInterest, totalInterestPrincipal, totalRatio,
            averageAnnualInterest, averageMonthlyInterest, amortizationYearly;
    /**
     * Constructor for the MortgageCalculatorView class.
     * Initializes and sets up the GUI components.
     */
    public MortgageCalculatorView() {
        super("Mortgage Calculator");
        // using a grid layout for the user input text panel
        JPanel textPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textPanel.add(new JLabel("Enter Principal Amount Loaned: "));
        principalLoaned = new JTextField(10);
        textPanel.add(principalLoaned);
        textPanel.add(new JLabel("Enter Number Monthly Payments (Amortization): "));
        monthlyPayments = new JTextField(10);
        textPanel.add(monthlyPayments);
        textPanel.add(new JLabel("Enter Annual Interest rate: "));
        interestRate = new JTextField(10);
        textPanel.add(interestRate);
        textPanel.add(new JLabel("Enter number of years: "));
        years = new JTextField(10);
        textPanel.add(years);

        //labeling the results
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(6,2,10,10));
        monthlyBlendedAmount = new JLabel("Monthly Blended Amount: ", SwingConstants.CENTER);
        resultPanel.add(monthlyBlendedAmount);
        totalMortgageInterest = new JLabel("Total Mortgage Interest: ", SwingConstants.CENTER);
        resultPanel.add(totalMortgageInterest);
        totalInterestPrincipal = new JLabel("Total Interest/Principal: ", SwingConstants.CENTER);
        resultPanel.add(totalInterestPrincipal);
        totalRatio = new JLabel("Total Ratio: ", SwingConstants.CENTER);
        resultPanel.add(totalRatio);
        averageAnnualInterest = new JLabel("Average Annual Interest: ", SwingConstants.CENTER);
        resultPanel.add(averageAnnualInterest);
        averageMonthlyInterest = new JLabel("Average Monthly Interest: ", SwingConstants.CENTER);
        resultPanel.add(averageMonthlyInterest);
        amortizationYearly = new JLabel("Yearly Amortization: ", SwingConstants.CENTER);
        resultPanel.add(amortizationYearly);

        // Create Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        calculateMortgage = new JButton("Calculate");
        calculateMortgage.setFont(new Font("Arial", Font.BOLD, 14));
        calculateMortgage.setBackground(new Color(0,0,0));
        calculateMortgage.setForeground(Color.BLACK);
        calculateMortgage.setBorder(BorderFactory.createMatteBorder(5,10,5,10, (new Color(156,175,136))));
        buttonPanel.add(calculateMortgage);
        // Making the full panel
        JPanel fullPanel = new JPanel(new GridBagLayout());
        fullPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        fullPanel.setBackground(new Color(156,175,136));

        //adding everything to the Grid Bag Layout

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        fullPanel.add(textPanel, gbc);
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        fullPanel.add(resultPanel, gbc);
        gbc.gridy = 2;
        gbc.weighty = 0.0;
        fullPanel.add(buttonPanel, gbc);
        add(fullPanel);


        calculateMortgage.addActionListener(new ActionListener() {
            /**
             * Adds an ActionListener to the calculateMortgage button that calculates and displays the monthly payment,
             * total mortgage interest, total interest principal, total ratio, average annual interest, average monthly interest,
             * and yearly amortization using user input values of monthly payments, principal loaned, annual interest rate,
             * and years of loans.
             * @param e The ActionEvent object
             * */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Parse user input values of monthly payments, principal loaned, annual interest rate, and years of loans
                double paymentMonthly = Double.parseDouble(monthlyPayments.getText());
                double principal = Double.parseDouble(principalLoaned.getText());
                double annualInterestRate = Double.parseDouble(interestRate.getText());
                int yearsOfLoans = Integer.parseInt(years.getText());
                // Create a new MortgageCalculatorModel object with the parsed user input values
                MortgageCalculatorModel model = new MortgageCalculatorModel(principal, paymentMonthly,
                        annualInterestRate, yearsOfLoans);
                // Calculate the mortgage values using the MortgageCalculatorModel object
                model.calculate();
                // Set the text of the monthly blended amount, total mortgage interest, total interest principal, total ratio,
                // average annual interest, average monthly interest, and yearly amortization labels to the calculated values
                monthlyBlendedAmount.setText("Monthly Blended Amount:  " + model.computeMonthlyBlendedAmount());
                totalMortgageInterest.setText("Total Mortgage Interest: " + model.computeTotalMortgageInterest());
                totalInterestPrincipal.setText("Total Interest Principal: " + model.computeTotalInterestPrincipal());
                totalRatio.setText("The Total Ratio: " + model.computeRatioInterestPrincipal());
                averageAnnualInterest.setText("Average Annual Interest: " + model.computeAverageAnnualInterest());
                averageMonthlyInterest.setText("Average Monthly Interest: " + model.computeAverageMonthlyInterest());
                amortizationYearly.setText("Yearly Amortization: " + model.computeAmortizationYearly());
            }
        });
        // Set the default close operation of the JFrame to exit the application
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Pack the JFrame to fit the preferred size of its components
        pack();
        // Center the JFrame on the screen
        setLocationRelativeTo(null);
        // Make the JFrame visible to the user
        setVisible(true);
    }
    public void addCalculateListener(ActionListener listenForCalcButton) {
        calculateMortgage.addActionListener(listenForCalcButton);
    }

    /**
     * Returns the value entered by the user in the principalLoaned text field as a String.
     * @return the value entered by the user in the principalLoaned text field as a String
     * */
    public String getPrincipal() {
        return String.valueOf(Double.parseDouble(principalLoaned.getText()));
    }
    /**
     * Returns the value entered by the user in the monthlyPayments text field as a String.
     * @return the value entered by the user in the monthlyPayments text field as a String
     * */
    public String getMonthlyPayments() {
        return String.valueOf(Double.parseDouble(monthlyPayments.getText()));
    }
    /**
     * Returns the value entered by the user in the interestRate text field as a String.
     * @return the value entered by the user in the interestRate text field as a String
     * */
    public String getInterestRate() {
        return String.valueOf(Double.parseDouble(interestRate.getText()));
    }
    /**
     * Returns the value entered by the user in the years text field as a String.
     * @return the value entered by the user in the years text field as a String
     * */
    public String getYears() {
        return String.valueOf(Integer.parseInt(years.getText()));
    }
    /**
     * Sets the text of the monthlyBlendedAmount JLabel to the specified String value.
     * @param monthlyBlendedAmount the String value to set as the text of the monthlyBlendedAmount JLabel
     * */
    public void setMonthlyBlendedAmount(String monthlyBlendedAmount) {
        this.monthlyBlendedAmount.setText("Monthly Blended Amount: " + monthlyBlendedAmount);
    }
    /**
     * Sets the text of the totalMortgageInterest JLabel to the specified String value.
     * @param totalMortgageInterest the String value to set as the text of the totalMortgageInterest JLabel
     * */
    public void setTotalMortgageInterest(String totalMortgageInterest) {
        this.totalMortgageInterest.setText("Total Mortgage Interest: " + totalMortgageInterest);
    }
    /**
     * Sets the text of the totalInterestPrincipal JLabel to the specified String value.
     * @param totalInterestPrincipal the String value to set as the text of the totalInterestPrincipal JLabel
     * */
    public void setTotalInterestPrincipal(String totalInterestPrincipal) {
        this.totalInterestPrincipal.setText("Total Interest Principal: " + totalInterestPrincipal);
    }
    /**
     * Sets the text of the totalRatio JLabel to the specified String value.
     * @param totalRatio the String value to set as the text of the totalRatio JLabel
     * */
    public void setTotalRatio(String totalRatio) {
        this.totalRatio.setText("The Total Ratio: " + totalRatio);
    }
    /**
     * Sets the text of the averageAnnualInterest JLabel to the specified String value.
     * @param averageAnnualInterest the String value to set as the text of the averageAnnualInterest JLabel
     * */
    public void setAverageAnnualInterest(String averageAnnualInterest) {
        this.averageAnnualInterest.setText("Average Annual Interest: " + averageAnnualInterest);
    }
    public void setAverageMonthlyInterest(String averageMonthlyInterest) {
        this.averageMonthlyInterest.setText("Average Monthly Interest: " + averageMonthlyInterest);
    }
    public void setAmortizationYearly(String amortizationYearly) {
        this.amortizationYearly.setText("Yearly Amortization: " + amortizationYearly);
    }
    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
